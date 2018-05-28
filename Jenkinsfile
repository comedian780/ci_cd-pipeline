node {
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/comedian780/ci_cd-pipeline.git'

   }
     stage('Build') { // for display purposes
      // run gradle build -> happens while docker builds

      if (isUnix()) {
          /* build docker image */
          IMAGE_EXISTS = sh(
          script: "docker images -q parcel-api",
            returnStatus : true)
          /* Remove the previous build image */
          if(IMAGE_EXISTS!=""){
            sh 'docker rmi -f "193.174.205.28:443/parcel-api"'
          }
          sh 'docker build -t "193.174.205.28:443/parcel-api" .'
          sh 'docker image prune -f'

      } else {
          /* build docker image */

          bat 'docker rmi -f 193.174.205.28:443/parcel-api:latest'
          bat 'docker build -t 193.174.205.28:443/parcel-api .'
          bat 'docker image prune -f'
      }

   }
   stage('Deploy to registry'){
    if (isUnix()) {
      sh 'docker push "193.174.205.28:443/parcel-api"'
    } else {
      bat 'docker push "193.174.205.28:443/parcel-api"'
    }
   }
   stage('Deploy to test server'){
      // check if VM exists and is running
      VM_EXISTS = sh(
          script: "'docker-machine' ls -q | grep '^parcel-test\$'",
            returnStatus : true)
      //Remove VM if it exists
      if(VM_EXISTS!=""){
          VM_RUNNING = sh(
            script: "'docker-machine' status parcel-test | grep '^Running\$'",
            returnStatus : true)
        if(VM_RUNNING!=""){
          //sh 'docker-machine stop parcel-test'
        }
        sh 'docker-machine rm parcel-test -y'
      }
      // create production VM
      sh './initializeTestServer.sh'
      sh "docker-machine stop parcel-test"
   }

   stage('Integration'){
    if(isUnix()){
      sh "docker-machine start parcel-test"
      sh "python integration.py"
    }
  }
}
