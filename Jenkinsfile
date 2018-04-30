node {
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/comedian780/ci_cd-pipeline.git'

   }
     stage('Build') { // for display purposes
      // run gradle build
      sh 'gradle clean build'
      //def buildInfo = rtGradle.run rootDir: "ci_cd-pipeline/", buildFile: 'build.gradle', tasks: 'clean build'

   }

}
