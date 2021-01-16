#!/usr/bin/env sh
# Deploy For Heroku Container Registery
echo "Deploy For Heroku Container Registery Started"

#Get Image ID
echo "Get IMAGE_ID"
IMAGE_ID=$(docker images --filter=reference=erdiapps/uname-api --format "{{.ID}}")

heroku container:login

docker tag "$IMAGE_ID" registry.heroku.com/uname-api/web

docker push registry.heroku.com/uname-api/web

heroku container:release web -a uname-api

echo "DEPLOY DONE"
