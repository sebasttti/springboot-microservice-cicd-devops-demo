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

        stage('Update Deployment YAML and Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'github-token', usernameVariable: 'GIT_USER', passwordVariable: 'GIT_TOKEN')]) {
                    sh """
                        git config user.email "jenkins@server.local"
                        git config user.name "Jenkins CI"

                        # Actualizar versión de la imagen
                        sed -i 's#image: ${REPO}:.*#image: ${REPO}:${IMAGE_TAG}#' src/main/resources/k8s/deployment.yaml

                        # Confirmar cambios
                        git add src/main/resources/k8s/deployment.yaml
                        git commit -m "Update image version to ${IMAGE_TAG}" || echo "No changes to commit"

                        # Configurar push con autenticación
                        git remote set-url origin https://${GIT_USER}:${GIT_TOKEN}@github.com/sebasttti/springboot-microservice-cicd-devops-demo.git
                        git push origin main
                    """
                }
            }
        }

        
    }
}
