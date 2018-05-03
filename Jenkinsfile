node {
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/comedian780/ci_cd-pipeline.git'

   }
     stage('Build') { // for display purposes
      // run gradle build

      if (isUnix()) {
          sh 'gradle clean build'
      } else {
          bat 'gradlew.bat clean build'
      }

   }
   stage('Dockerize'){// for display purposes

      if (isUnix()) {
          //build docker image
          sh 'docker build -t parcel-api .'
          // start docker container
          // sh 'docker run -d --restart always --network host --name=parcel-webserver parcel-api ./start.sh'
      } else {
          //build docker image
          bat 'docker build -t parcel-api .'
          // start docker container
          // bat 'docker run -d --restart always --network host --name=parcel-webserver parcel-api ./start.sh'
      }

   }

}
