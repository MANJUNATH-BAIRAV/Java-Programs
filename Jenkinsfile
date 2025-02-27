pipeline {
    agent any
    environment {
        REPO_URL = 'https://github.com/MANJUNATH-BAIRAV/Java-Programs'
        BRANCH_NAME = 'main'
        DOCKER_IMAGE = 'manjunathbairav/java-app'
        DOCKER_USER = 'manjunathbairav1'
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
                    // Find the JAR file correctly
                    def jarFile = bat(script: 'dir /b target\\*.jar', returnStdout: true).trim()

                    if (!jarFile || jarFile.isEmpty()) {
                        error("❌ JAR file not found in target/ directory!")
                    }

                    echo "✅ Found JAR file: ${jarFile}"

                    // Retrieve Docker password securely
                    withCredentials([string(credentialsId: 'docker-hub-password', variable: 'DOCKER_PASS')]) {
                        // Build the Docker image
                        bat "docker build -t %DOCKER_IMAGE%:latest --build-arg JAR_FILE=target/${jarFile} ."
                        
                        // Log in to Docker Hub securely
                        bat "echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin"
                        
                        // Push the Docker image
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
