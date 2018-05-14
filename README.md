# ci_cd-pipeline
Repository für lustigen kleinen Microservice als Ausgangspunkt einer CI/CD-Pipeline

Buildbefehl:
```
docker build -t parcel-api .
```

Startbefehl:
```
docker run -d --restart always --network=parcelnetwork --name=parcel-webservice -p 8443:8443 parcel-api ./start.sh
```
