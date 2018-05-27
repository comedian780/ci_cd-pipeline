node {
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/comedian780/ci_cd-pipeline.git'

   }
     stage('Build') { // for display purposes
      // run gradle build -> happens while docker builds

      /*if (isUnix()) {
          sh 'gradle clean build'
      } else {
          bat 'gradlew.bat clean build'
      }*/

      if (isUnix()) {
          //build docker image
          IMAGE_EXISTS = sh "-n ${docker images -q parcel-api}"
          //Remove the previous build image
          if(IMAGE_EXISTS){
            sh 'docker rmi "193.174.205.28:443/parcel-api"'
          }
          sh 'docker build -t "193.174.205.28:443/parcel-api" .'
          sh 'docker images purge -y'

          // start docker container
          //sh 'docker stop parcel-webservice'
          //sh 'docker rm parcel-webservice'
          // sh 'docker run -d --restart always --network host --name=parcel-webservice 193.174.205.28:443/parcel-api ./start.sh'
      } else {
          //build docker image

          bat 'docker rmi 193.174.205.28:443/parcel-api:latest'
          bat 'docker build -t 193.174.205.28:443/parcel-api .'
          bat 'docker images purge -y'
          // start docker container
          //bat 'docker stop parcel-webservice'
          //bat 'docker rm parcel-webservice'
          // bat 'docker run -d --restart always --network host --name=parcel-webservice 193.174.205.28:443/parcel-api ./start.sh'
      }

   }
   stage('Deploy to registry'){
    if (isUnix()){
      sh 'docker push "193.174.205.28:443/parcel-api"'
    }else{
      bat 'docker push "193.174.205.28:443/parcel-api"'
    }
   }
   stage('Deploy to test server'){
      // check if VM exists and is running
      VM_EXISTS = sh "-n ${docker-machine ls -q | grep '^parcel-test$'}
      VM_RUNNING = sh "-n ${docker-machine status parcel-test | grep '^Running$'}"
      //Remove VM if it exists
      if(VM_EXISTS){
        if(VM_RUNNING){
          sh 'docker-machine stop parcel-test'
        }
        sh 'docker-machine rm parcel-test -y'
      }
      // create production VM
      sh 'docker-machine create --driver virtualbox --engine-insecure-registry 193.174.205.28:44 parcel-test'
      // switch to VM docker environment
      sh "eval $(docker-machine env parcel-test)"
      sh 'docker network create --driver bridge parcelnetwork'
      sh 'docker run -d --restart always --network=parcelnetwork -p 3306:3306 --name=parcel-db 193.174.205.28:443/parcel-db'
      sh 'docker run -d --restart always --network=parcelnetwork -p 80:80 --name=parcel-frontend 193.174.205.28:443/parcel-frontend'
      sh 'docker run -d --network=parcelnetwork --name=parcel-webservice -p 8443:8443 193.174.205.28:443/parcel-api ./start.sh'
   }

}
