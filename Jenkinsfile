pipeline {



agent any

stages {

stage('Preparation'){

git 'https://github.com/comedian780/ci_cd-pipeline.git'

}

stage('Build') {

steps {


def buildInfo = rtGradle.run rootDir: "ci_cd-pipeline/", buildFile: 'build.gradle', tasks: 'clean build'

}

}

}

post {

}

}
