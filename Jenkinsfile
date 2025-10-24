pipeline {
    agent { label 'principal' }

    environment {
        REGISTRY = 'docker.io'
        REPO = 'sebasttti0716/grupo5-microservice'
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/sebasttti/springboot-microservice-cicd-devops-demo.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                script {
                    // Construir la imagen
                    def image = docker.build("${REPO}:${GIT_COMMIT}")

                    // Subir la imagen
                    image.push()
                }
            }
        }
    }
}
