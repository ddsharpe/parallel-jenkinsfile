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
         stage ('stage 1') {
            when {
                not {
                    tag 'release-*'
                }
            }
            steps {
                echo "${version_number}"
                script {
                    env.version = version_number + "-beta-"
                    version_number = "alpha"
                }
                echo "${version_number}"
                echo "${version}"
            }
         }

        stage ('stage 2') {
            // always runs this stage
            steps {
                echo "${version_number}"
                echo "${version}"
                echo "${env.version_number}"
            }
        }
    }
}