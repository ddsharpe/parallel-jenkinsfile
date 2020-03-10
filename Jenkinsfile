// Documentation for Jenkinsfile https://jenkins.io/doc/book/pipeline/jenkinsfile/

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
            when {
                not {
                    changelog '\\[skip-ci\\]'
                }
            }
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage ('MatrixParallel') {
            when {
                allOf {
                    triggeredBy 'TimerTrigger'
                    not {
                        changelog '\\[skip-ci\\]'
                    }
                }
            }
            matrix {
                axes {
                    axis {
                        name 'TESTGROUP'
                        values 'SetA', 'SetB', 'SetC'
                    }
                }
                stages {
                    stage('Test') {
                        steps {
                            sh 'mvn test -Dtest.groups="${TESTGROUP}"'
                        }
                    }
                }
            }
        }
        stage ('AfterMatrix') {
            when {
                allOf {
                    triggeredBy 'TimerTrigger'
                    not {
                        changelog '\\[skip-ci\\]'
                    }
                }
            }
            steps {
                sh 'echo "All matrix should be done"'
                junit 'target/surefire-reports/*.xml'
            }
        }
    }
}