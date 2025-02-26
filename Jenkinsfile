pipeline {
    agent any
    environment {
        REPO_URL = 'https://github.com/MANJUNATH-BAIRAV/Java-Programs'
        BRANCH_NAME = 'main'
        DOCKER_IMAGE = 'manjunathbairav/java-app'
        DOCKER_USER = credentials('docker-username')  // Use Jenkins credentials
        DOCKER_PASS = credentials('docker-password')
    }
    stages {
        stage('Clone Repository') {
            steps {
                script {
                    try {
                        git branch: BRANCH_NAME, url: REPO_URL
                    } catch (Exception e) {
                        echo "Error cloning repository: ${e.getMessage()}"
                        error("Stopping pipeline due to clone failure.")
                    }
                }
            }
        }

        stage('Build with Maven') {
            steps {
                script {
                    try {
                        bat 'mvn clean package'
                    } catch (Exception e) {
                        echo "Maven build failed: ${e.getMessage()}"
                        error("Stopping pipeline due to build failure.")
                    }
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    try {
                        bat 'mvn test'
                    } catch (Exception e) {
                        echo "Tests failed: ${e.getMessage()}"
                        error("Stopping pipeline due to test failure.")
                    }
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    try {
                        bat "docker build -t %DOCKER_IMAGE%:latest ."
                        bat "echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin"
                        bat "docker push %DOCKER_IMAGE%:latest"
                    } catch (Exception e) {
                        echo "Docker build or push failed: ${e.getMessage()}"
                        error("Stopping pipeline due to Docker failure.")
                    }
                }
            }
        }
    }

    post {
        failure {
            echo "Pipeline failed! Please check the logs."
        }
        success {
            echo "Pipeline completed successfully!"
        }
    }
}
