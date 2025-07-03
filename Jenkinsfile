pipeline {
    agent any

    environment {
        IMAGE_NAME = "medicure/patient-service"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/anjali-shette01/medicure-patient-service.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t %IMAGE_NAME% .'
            }
        }

        stage('Docker Run') {
            steps {
                bat 'docker stop patient-service || exit 0'
                bat 'docker rm patient-service || exit 0'
                bat 'docker run -d -p 8081:8080 --name patient-service %IMAGE_NAME%'
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
