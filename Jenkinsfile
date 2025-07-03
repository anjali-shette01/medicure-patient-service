pipeline {
    agent any

    environment {
        IMAGE_NAME = "medicure/patient-service"
    }

    stages {
        stage('Build') {
            steps {
                dir('patient-service') {   // <-- change directory to nested folder
                    bat 'mvn clean package'
                }
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t %IMAGE_NAME% patient-service'
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
