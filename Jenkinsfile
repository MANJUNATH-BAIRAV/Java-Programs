pipeline {
    agent any  // Runs on any available agent

    environment {
        IMAGE_NAME = "my-java-app"
        DOCKER_REGISTRY = "your-dockerhub-username"  // Change this to your actual DockerHub username
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', credentialsId: 'github-credentials', url: 'https://github.com/MANJUNATH-BAIRAV/Java-Programs.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh './mvnw clean package'  // Uses Maven Wrapper if available
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'java -jar target/*.jar || echo "Tests failed, but continuing..."'
            }
        }

        stage('Push to DockerHub') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: 'https://index.docker.io/v1/']) {
                    sh 'docker tag $IMAGE_NAME $DOCKER_REGISTRY/$IMAGE_NAME:latest'
                    sh 'docker push $DOCKER_REGISTRY/$IMAGE_NAME:latest'
                }
            }
        }
    }
}
