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

  echo "Waiting for token response"
  until [ ! -z "$TKN" ] ; do : ; done

  echo "Create a KeyCloak Client named: app-client"
  curl -X POST 'http://localhost:4040/auth/admin/realms/master/clients' \
  -d '{"clientId":"app-client", "directAccessGrantsEnabled": "true", "redirectUris": ["*"], "secret":"app-client-secret", "enabled":"true"}' \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TKN"  -vvv | grep Location

  echo "Create a KeyCloak Group with name: USER"
  curl -X POST "http://localhost:4040/auth/admin/realms/master/roles" \
  -d '{"name": "USER"}' \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TKN" -vvv
  
  echo "Create a KeyCloak Role with name: ADMIN"
  curl -X POST "http://localhost:4040/auth/admin/realms/master/roles" \
  -d '{"name": "ADMIN"}' \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TKN" -vvv

  echo "Create a KeyCloak User with name: app-user-regular"
  curl -X POST 'http://localhost:4040/auth/admin/realms/master/users' \
  -d '{"username": "app-user-regular", "firstName":"John", "lastName": "Doe", "credentials": [{"type": "password", "value": "app-user-regular-pass", "temporary": "false"}], "enabled":"true"}' \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TKN"

  echo "Create a KeyCloak User with name: app-user-admin"
  curl -X POST 'http://localhost:4040/auth/admin/realms/master/users' \
  -d '{"username": "app-user-admin", "firstName":"Admin", "lastName": "Adminsson", "credentials": [{"type": "password", "value": "app-user-admin-pass", "temporary": "false"}], "enabled":"true"}' \
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

