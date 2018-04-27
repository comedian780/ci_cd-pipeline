pipeline {

environment {



}

agent any

stages {

stage('Build') {

steps {


sh gradle clean build

}

}

}

post {

always {

cleanWs()

}

}

}
