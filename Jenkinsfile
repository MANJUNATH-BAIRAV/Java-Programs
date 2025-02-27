pipeline {
    agent any
    environment {
        REPO_URL = 'https://github.com/MANJUNATH-BAIRAV/Java-Programs'
        BRANCH_NAME = 'main'
        DOCKER_IMAGE = 'manjunathbairav/java-app'
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'  // Replace with actual Jenkins credentials ID
    }
    
    stages {
        stage('Clone Repository') {
            steps {
                git branch: BRANCH_NAME, url: REPO_URL
            }
        }

        stage('Build with Maven') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    // Detect the JAR file dynamically
                    def jarFile = bat(script: 'dir /b target\\*.jar', returnStdout: true).trim()

                    if (!jarFile || jarFile.isEmpty()) {
                        error("❌ JAR file not found in target/ directory!")
                    }

                    echo "✅ Found JAR file: ${jarFile}"

                    withCredentials([usernamePassword(credentialsId: DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        bat "docker login -u %DOCKER_USER% -p %DOCKER_PASS%"
                        bat "docker build -t %DOCKER_IMAGE%:latest --build-arg JAR_FILE=target/${jarFile} ."
                        bat "docker push %DOCKER_IMAGE%:latest"
                    }
                }
            }
        }
    }

    post {
        failure {
            echo "🚨 Pipeline failed! Check the logs for errors."
        }
        success {
            echo "✅ Pipeline completed successfully!"
        }
    }
}
