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
            sh 'docker rmi -f "asset.allgaeu-parcel-service.com:443/parcel-api"'
          }
          sh 'docker build -t "asset.allgaeu-parcel-service.com:443/parcel-api" .'
          sh 'docker tag asset.allgaeu-parcel-service.com:443/parcel-api asset.allgaeu-parcel-service.com:443/parcel-api:$BUILD_NUMBER'
          sh 'docker build -t "asset.allgaeu-parcel-service.com:443/parcel-asset-size" -f ./js/Dockerfile ./js'
          sh 'docker tag asset.allgaeu-parcel-service.com:443/parcel-asset-size asset.allgaeu-parcel-service.com:443/parcel-asset-size:$BUILD_NUMBER'
          sh 'docker image prune -f'

      } else {
          /* build docker image */
          bat 'docker rmi -f asset.allgaeu-parcel-service.com:443/parcel-api:latest'
          bat 'docker build -t asset.allgaeu-parcel-service.com:443/parcel-api .'
          bat 'docker image prune -f'
      }

   }
   stage('Deploy to registry'){
    if (isUnix()) {
      sh 'docker push "asset.allgaeu-parcel-service.com:443/parcel-api"'
      sh 'docker push "asset.allgaeu-parcel-service.com:443/parcel-asset-size"'
    } else {
      bat 'docker push "asset.allgaeu-parcel-service.com:443/parcel-api"'
    }
   }
   stage('Deploy to test server'){
      // create production VM
      sh './scripts/initializeVM.sh parcel-test'

   }
   stage('Integration'){
    if(isUnix()){
      sh "./scripts/startVM.sh parcel-test"
      sh "python ./scripts/integration.py"
    }
  }
  stage('UAT'){
   if(isUnix()){
     sh "./scripts/startVM.sh parcel-test"
     //sh "./updateProxyIp.sh"
     sh "python ./scripts/uat.py"
   }
 }
 stage('Capacity'){
  if(isUnix()){
    sh "./scripts/startVM.sh parcel-test"
    sh "./scripts/capacity.sh"
    gatlingArchive()
  }
 }
 stage('Manual'){
 if(isUnix()){
   sh "./scripts/startVM.sh parcel-test"
   input 'Deploy to Production?'
   sh "docker-machine stop parcel-test"
  }
 }

}
