pipeline {
    agent any
    tools {
        maven 'maven-3.6.0'
        jdk 'jdk11'
    }

    stages {
        stage ('Environment') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                    mvn --version
                '''
            }
        }
        stage ('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage ('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage ('ParallelTest') {
            parallel {
                stage('Parallel Test A') {
                    step {
                        sh 'mvn test -Dtest.groups="SetA"'
                    }
                }
                stage('Parallel Test B') {
                    step {
                        sh 'mvn test -Dtest.groups="SetB"'
                    }
                }
                stage('Parallel Test C') {
                    step {
                        sh 'mvn test -Dtest.groups="SetC"'
                    }
                }
            }
        }
     }
}