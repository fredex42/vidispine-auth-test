#!/bin/bash

### Starts up Keycloak on port 8090

docker volume inspect keycloak-data >/dev/null 2>&1

if [ "$?" != "0" ]; then
  echo Creating keycloak data volume
  docker volume create keycloak-data
fi

docker run -p 8090:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -v keycloak-data:/opt/jboss/keycloak/standalone/data quay.io/keycloak/keycloak:10.0.1
