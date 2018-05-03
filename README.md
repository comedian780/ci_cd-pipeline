# ci_cd-pipeline
Repository für lustigen kleinen Microservice als Ausgangspunkt einer CI/CD-Pipeline

Buildbefehl:
```
docker build -t parcel-api:1.0 .
```

Startbefehl:
```
docker run -d --restart always --network host --name=parcel-webservice parcel-api ./start.sh
```
