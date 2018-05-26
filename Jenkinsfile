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
          IMAGE_EXISTS = sh(
          script: "docker images -q 193.174.205.28:443/parcel-api:latest",
          returnStatus : true) !=""
          //Remove the previous build image
          if(IMAGE_EXISTS){
            sh 'docker rmi 193.174.205.28:443/parcel-api'
          }
          sh 'docker build -t 193.174.205.28:443/parcel-api .'

          // start docker container
          //sh 'docker stop parcel-webservice'
          //sh 'docker rm parcel-webservice'
          // sh 'docker run -d --restart always --network host --name=parcel-webservice 193.174.205.28:443/parcel-api ./start.sh'
      } else {
          //build docker image

          bat 'docker rmi 193.174.205.28:443/parcel-api:latest'
          bat 'docker build -t 193.174.205.28:443/parcel-api .'

          // start docker container
          //bat 'docker stop parcel-webservice'
          //bat 'docker rm parcel-webservice'
          // bat 'docker run -d --restart always --network host --name=parcel-webservice 193.174.205.28:443/parcel-api ./start.sh'
      }

   }
   stage('Deploy to registry'){
    if (isUnix()){
      sh 'docker push 193.174.205.28:443/parcel-api'
    }else{
      bat 'docker push 193.174.205.28:443/parcel-api'
    }
   }
   //stage('Deploy to production'){
      // start production VM

   //}

}
