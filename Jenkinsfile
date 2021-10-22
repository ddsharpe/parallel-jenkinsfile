// Documentation for Jenkinsfile https://jenkins.io/doc/book/pipeline/jenkinsfile/

pipeline {
    agent any
    environment {
        version_number="1.0.1"
    }

    tools {
        // Jenkins should install these tools into the VM (if not already there)
        maven 'maven-3.6.0'
        jdk 'jdk11'
    }

    stages {
         stage ('Release Version') {
            when {
                not {
                    tag 'release-*'
                }
            }
            steps {
                sh 'echo $version_number'
                script {
                    version_number = "$version_number"+"-beta-"
                }
                sh 'echo $version_number'
            }
         }

        stage ('check value') {
            // always runs this stage
            steps {
                sh '''
                    echo $version_number
                '''
            }
        }
    }
}