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

}
