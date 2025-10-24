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
                    // Inicia sesión en Docker Hub usando las credenciales seguras
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-token', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    }

                    // Construir y subir la imagen
                    sh "docker build -t ${REPO}:${IMAGE_TAG} ."
                    sh "docker push ${REPO}:${IMAGE_TAG}"
                }
            }
        }

        
    }
}
