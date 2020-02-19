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
        stage ('SingleThreadedTest') {
            steps {
                sh 'mvn test -Dtest.groups="SetA,SetB,SetC"'
            }
        }
        stage ('AfterTests') {
            steps {
                sh 'echo "All matrix should be done"'
                junit 'target/surefire-reports/*.xml'
            }
        }
    }
}