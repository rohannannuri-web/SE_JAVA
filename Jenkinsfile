pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
        jdk   'JAVA_HOME'
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
                bat 'mvn clean compile -B'
            }
        }

        stage('Test') {
            steps {
                echo "========== STAGE: Test =========="
                bat 'mvn test -B'
            }
            post {
                always {
                    junit allowEmptyResults: true,
                          testResults: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                echo "========== STAGE: Package =========="
                bat 'mvn package -DskipTests -B'
                echo "JAR created: target/${JAR_NAME}"
            }
        }

        stage('Archive Artifacts') {
            steps {
                echo "========== STAGE: Archive Artifacts =========="
                archiveArtifacts artifacts: "target/${JAR_NAME}",
                                 fingerprint: true
            }
        }

        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo "========== STAGE: Deploy =========="
                echo "Deploying ${JAR_NAME}..."
                echo "Deployment complete!"
            }
        }
    }

    post {
        success {
            echo "=============================="
            echo "  BUILD SUCCESSFUL!"
            echo "  Project : ${APP_NAME} v${APP_VERSION}"
            echo "=============================="
        }
        failure {
            echo "=============================="
            echo "  BUILD FAILED!"
            echo "  Check the logs above."
            echo "=============================="
        }
        always {
            echo "Pipeline finished. Cleaning workspace..."
            cleanWs()
        }
    }
}
