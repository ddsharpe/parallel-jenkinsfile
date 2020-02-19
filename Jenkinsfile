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
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage ('ParallelTest') {
            failFast true
            parallel {
                stage('Parallel Test A') {
                    steps {
                        sh 'mvn test -Dtest.groups="SetA"'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }
                stage('Parallel Test B') {
                    steps {
                        sh 'mvn test -Dtest.groups="SetB"'
                    }
                }
                stage('Parallel Test C') {
                    steps {
                        sh 'mvn test -Dtest.groups="SetC"'
                    }
                }
            }
        }
        stage ('AfterParallel') {
            steps {
                sh 'echo "All parallel should be done"'
            }
        }
        stage ('MatrixParallel') {
            matrix {
                axes {
                    axis {
                        name 'TESTGROUP'
                        values 'SetA', 'SetB', 'SetC'
                    }
                }
                stages {
                    stage('Test${TESTGROUP}') {
                        steps {
                            sh 'mvn test -Dtest.groups="${TESTGROUP}"'
                        }
                    }
                }
            }
        }
        stage ('AfterMatrix') {
            steps {
                sh 'echo "All matrix should be done"'
            }
        }
    }
}