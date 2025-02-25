pipeline {
    agent any  // Runs on any available agent

    environment {
        IMAGE_NAME = "my-java-app"
        DOCKER_REGISTRY = "docker.io/your-dockerhub-username"  // Change to your DockerHub
    }

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/MANJUNATH-BAIRAV/Java-Programs.git'  // Use your repo
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'java -jar target/*.jar'  // Run basic test
            }
        }

        stage('Push to DockerHub') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    sh 'docker tag $IMAGE_NAME $DOCKER_REGISTRY/$IMAGE_NAME:latest'
                    sh 'docker push $DOCKER_REGISTRY/$IMAGE_NAME:latest'
                }
            }
        }
    }
}
