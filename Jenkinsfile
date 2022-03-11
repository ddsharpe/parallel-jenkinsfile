// Documentation for Jenkinsfile https://jenkins.io/doc/book/pipeline/jenkinsfile/
def isDocOnlyChanges() {
    for (changeLogSet in currentBuild.changeSets) {
        for (entry in changeLogSet.getItems()) { // for each commit in the detected changes
            echo "found commit ${entry.msg} by ${entry.author}"
            for (file in entry.getAffectedFiles()) {
                filename = file.getPath();
                echo "found file in entry: ${file.getPath()} type: ${file.editType.name}"
                // if the file is part of the documentation set, skip it
                if (!filename.startsWith('documentation/')) {
                    // if the file is a markdown file, skip it
                    if (!filename.endsWith(".md")) {
                        // since the two previous conditions were not met, this change includes more than documentation
                        println filename
                        return false
                    }
                }
            }
        }
    }

    return true
}

pipeline {
    agent any
    environment {
        version_number="1.0.1"
        my_path="${WORKSPACE}/bin:/usr/bin"
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
                echo "${my_path}"
            }
        }
        stage ('stage 3') {
            when {
                not {
                    expression { isDocOnlyChanges() }
                }
            }
            steps {
                echo "Execute integration tests here"
            }
        }
    }
}