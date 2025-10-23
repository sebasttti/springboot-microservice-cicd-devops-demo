pipeline {
    agent { label 'principal' }

    environment {
        DOCKER_HUB_USER = 'sebasttti0716'
        IMAGE_NAME = 'grupo5-microservice'
        TAG = 'latest'
    }

    stages {

        stage('Build') {
              steps {
                        echo 'Corriendo en el nodo principal'
                    }
              }
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/sebasttti/springboot-microservice-cicd-devops-demo.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_HUB_USER}/${IMAGE_NAME}:${TAG} ."
            }
        }

        stage('Push to Docker Hub') {
            steps {
                sh "docker push ${DOCKER_HUB_USER}/${IMAGE_NAME}:${TAG}"
            }
        }
    }
}
