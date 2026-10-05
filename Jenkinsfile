pipeline {
    agent any

    // Tool configurations (must be configured in Jenkins Global Tool Configuration)
    tools {
        maven 'Maven-3.9'
        jdk 'JDK-11'
    }

    environment {
        APP_NAME    = 'java-project'
        APP_VERSION = '1.0.0'
        JAR_NAME    = "${APP_NAME}-${APP_VERSION}.jar"
    }

    stages {

        stage('Checkout') {
            steps {
                echo "========== STAGE: Checkout =========="
                echo "Checking out source code from SCM..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "========== STAGE: Build =========="
                dir('java-project') {
                    sh 'mvn clean compile -B'
                }
            }
        }

        stage('Test') {
            steps {
                echo "========== STAGE: Test =========="
                dir('java-project') {
                    sh 'mvn test -B'
                }
            }
            post {
                always {
                    // Publish JUnit test results
                    junit 'java-project/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                echo "========== STAGE: Package =========="
                dir('java-project') {
                    sh 'mvn package -DskipTests -B'
                }
                echo "JAR created: target/${JAR_NAME}"
            }
        }

        stage('Code Quality Check') {
            steps {
                echo "========== STAGE: Code Quality Check =========="
                dir('java-project') {
                    // Run Maven verify (includes checkstyle, spotbugs, etc. if configured)
                    sh 'mvn verify -DskipTests -B'
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                echo "========== STAGE: Archive Artifacts =========="
                dir('java-project') {
                    archiveArtifacts artifacts: "target/${JAR_NAME}", fingerprint: true
                }
            }
        }

        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo "========== STAGE: Deploy =========="
                echo "Deploying ${JAR_NAME} to the server..."
                // Add your deployment commands here, e.g.:
                // sh 'scp target/${JAR_NAME} user@server:/opt/app/'
                // sh 'ssh user@server "java -jar /opt/app/${JAR_NAME} &"'
                echo "Deployment complete!"
            }
        }
    }

    post {
        success {
            echo "=============================="
            echo "  BUILD SUCCESSFUL!"
            echo "  Project: ${APP_NAME} v${APP_VERSION}"
            echo "=============================="
        }
        failure {
            echo "=============================="
            echo "  BUILD FAILED!"
            echo "  Check the logs above."
            echo "=============================="
        }
        always {
            echo "Pipeline finished. Cleaning up workspace..."
            cleanWs()
        }
    }
}
