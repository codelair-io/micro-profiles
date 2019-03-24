#!/usr/bin/env bash

echo "Starting Keycloak OIDC server" 
docker container run -d -p 4040:8080 -p 4443:8443 --name keycloak_sso -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin jboss/keycloak 

echo "Keycloak server started, hit CTRL+C to exit"
trap ctrl_c INT

function ctrl_c() {
  echo "Stopping keycloak"
  docker rm -f keycloak_sso
  exit 0
}

# Wait forever
read -r -d '' _ </dev/tty

