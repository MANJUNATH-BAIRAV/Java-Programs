pipeline {
    agent any
    
    environment {
        MAVEN_HOME = "C:\\Program Files\\Apache\\maven" // Adjust the path as per your system
        PATH = "${MAVEN_HOME}\\bin;${env.PATH}"
    }
    
    stages {
        stage('Clone Repository') {
            steps {
                script {
                    try {
                        git 'https://github.com/your-repo/square-root-java-app.git' // Replace with your repo
                    } catch (Exception e) {
                        echo "Error cloning repository: ${e.getMessage()}"
                        currentBuild.result = 'FAILURE'
                        throw e
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
                        echo "Build failed: ${e.getMessage()}"
                        currentBuild.result = 'FAILURE'
                        throw e
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
                        currentBuild.result = 'UNSTABLE'
                        throw e
                    }
                }
            }
        }
        
        stage('Docker Build & Push') {
            steps {
                script {
                    try {
                        bat 'docker build -t your-docker-image .'
                        bat 'docker tag your-docker-image your-dockerhub-account/your-docker-image:latest'
                        bat 'docker push your-dockerhub-account/your-docker-image:latest'
                    } catch (Exception e) {
                        echo "Docker build/push failed: ${e.getMessage()}"
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
        }
    }
    
    post {
        failure {
            echo 'Pipeline failed! Please check the logs.'
        }
        success {
            echo 'Pipeline executed successfully!'
        }
    }
}
