pipeline {
    agent any
    environment {
        REPO_URL = 'https://github.com/MANJUNATH-BAIRAV/Java-Programs'
        BRANCH_NAME = 'main'
        DOCKER_IMAGE = 'manjunathbairav/java-app'
        DOCKER_USER = 'manjunathbairav1'
        DOCKER_PASS = 'Vasco@123'
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
                bat "docker build -t %DOCKER_IMAGE%:latest ."
                bat "echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin"
                bat "docker push %DOCKER_IMAGE%:latest"
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
