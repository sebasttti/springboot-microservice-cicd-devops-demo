pipeline {
    agent {
        kubernetes {
            label 'kaniko-agent'
            defaultContainer 'kaniko'
            yaml """
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: kaniko
    image: gcr.io/kaniko-project/executor:latest
    command:
    - sleep
    - infinity
"""
        }
    }

    environment {
        REPO = 'sebasttti0716/grupo5-microservice'
        IMAGE_TAG = "${BUILD_NUMBER}" // etiqueta con el SHA del commit
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
                container('kaniko') {
                    sh """
                    /kaniko/executor \
                        --dockerfile=Dockerfile \
                        --context=/workspace/ \
                        --destination=${REPO}:${IMAGE_TAG}
                    """
                }
            }
        }
    }
}
