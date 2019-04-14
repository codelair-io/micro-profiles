#!/usr/bin/env bash

export keycloakContainer=$(docker container ls -a | grep keycloak_sso)
if [ "$keycloakContainer" = "" ]; then
  echo "Starting Keycloak OIDC server" 
  docker container run -d -p 4040:8080 -p 4443:8443 --name keycloak_sso -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin jboss/keycloak 

  echo "Wait for container startup..."
  until (docker container logs keycloak_sso) | grep -m 1 "started in"; do : ; done

  echo "Setting up Keycloak"
  export TKN=$(curl -X POST 'http://localhost:4040/auth/realms/master/protocol/openid-connect/token' \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "username=admin" \
  -d 'password=admin' \
  -d 'grant_type=password' \
  -d 'client_id=admin-cli' | jq -r '.access_token')

  echo "Creating Admin Client"
  curl -X POST 'http://localhost:4040/auth/admin/realms/master/clients' \
  -d '{"clientId":"admin", "enabled":"true"}' \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TKN"
else
  echo "Keycloak server already exists, starting container...."
  docker container start keycloak_sso
fi

echo "Keycloak server started, hit CTRL+C to exit"
trap ctrl_c INT

function ctrl_c() {
  echo "Stopping keycloak"
  docker stop keycloak_sso
  exit 0
}

# Wait forever
read -r -d '' _ </dev/tty