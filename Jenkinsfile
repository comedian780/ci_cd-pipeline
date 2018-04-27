pipeline {



agent any

stages {

stage('Preparation'){

git 'https://github.com/comedian780/ci_cd-pipeline.git'

}

stage('Build') {

steps {


gradle clean build

}

}

}

post {

always {

cleanWs()

}

}

}
