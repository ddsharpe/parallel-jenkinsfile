// Documentation for Jenkinsfile https://jenkins.io/doc/book/pipeline/jenkinsfile/
import groovy.json.JsonSlurper

pipeline {
    agent any

    triggers {
        // timer trigger for "nightly build" on master branch
        cron( env.BRANCH_NAME.equals('master') ? 'H H(0-3) * * 1-5' : '')
    }

    tools {
        // Jenkins should install these tools into the VM (if not already there)
        maven 'maven-3.6.0'
        jdk 'jdk11'
    }

    stages {
        stage ('Setup Environment') {
            // always runs this stage
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                    mvn --version
                '''
                printLatestChanges()
            }
        }
        stage ('Build') {
            // always runs this stage
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage ('Unit Tests') {
            // run this stage when the commit message does not have [skip-ci
            // for example, normal unit tests (unless the user is committing documentation only and there is no need)
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
        stage ('Matrix Parallel Steps') {
            // only run this stage when triggered by a cron timer and the commit does not have []skip-ci in the message
            // for example, only run integration tests during the timer triggered nightly build
            when {
                allOf {
                    triggeredBy 'TimerTrigger'
                    not {
                        changelog '\\[skip-ci\\]'
                    }
                }
            }
            matrix {
                // run test groups in parallel.  unit tests tagged as SetA, B, and C should run in parallel.
                axes {
                    // matrix supports more than one axis, for multi-dimensional parallelism.
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
            // after all sets are complete, the job will continue here.
        }
        stage ('After Matrix') {
            // only run this stage when triggered by a cron timer and the commit does not have []skip-ci in the message
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
        stage ('Deploy Release') {
            // this stage should be skipped unless the build was triggered by a new tag
            when {
                tag "release-*"
            }
            steps {
                sh 'echo Deploy artifact to a repo'
            }
        }
    }
}

def printLatestChanges() {
    def projectMap = [ 'WIT': 'https://api.github.com/repos/oracle/weblogic-image-tool/commits',
                       'WDT': 'https://api.github.com/repos/oracle/weblogic-deploy-tooling/commits']
    print "Changes in last 24 hours:"
    for ( project in projectMap ) {
        def projectCommitsResp = httpRequest project.value
        if(projectCommitsResp.getStatus() == 200) {
            def projectCommits = new JsonSlurper().parseText( projectCommitsResp.getContent() )
            projectCommits.each{
                print project.key + ' : ' + it.commit.message
            }
        } else {
            print 'Failed to get commits for ' + project.key
        }
    }
}