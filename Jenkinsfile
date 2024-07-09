pipeline {
  agent any
  tools {
    maven 'Maven 3.9.6'
  }

  stages {
    stage('Example') {
      steps {

        withMaven(maven: 'Maven 3.9.6') {

          bat 'mvn clean test'
        }
      }
    }
  }
}
