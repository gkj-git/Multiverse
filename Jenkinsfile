pipeline {
    agent any

    environment {
        NEXUS_URL = "http://15.222.22.214:8081/repository/mutiverse-raw"
        NEXUS_DIR = "website"   // folder in Raw repo
        ARTIFACT_NAME = "Multiverse.tar.gz"

        EC2_USER = "ec2-user"
        EC2_HOST = "15.223.166.38"
        EC2_PATH = "/var/www/html"

        SSH_KEY = "/var/lib/jenkins/.ssh/Multiverse.pem"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/gkj-git/Multiverse.git'
            }
        }

        stage('Package') {
            steps {
                sh '''
                    rm -f Multiverse/Multiverse.tar.gz || true
                    tar -czf Multiverse/Multiverse.tar.gz -C Multiverse index.html assets images
                '''
            }
        }

        stage('Upload to Nexus') {
            steps {
                sh '''
                    curl -v -u admin:admin \
                    --upload-file Multiverse/Multiverse.tar.gz \
                    ${NEXUS_URL}/${NEXUS_DIR}/${ARTIFACT_NAME}
                '''
            }
        }

        stage('Deploy to EC2') {
            steps {
                sh '''
                    chmod 400 ${SSH_KEY}

                    # Copy files to EC2
                    scp -o StrictHostKeyChecking=no \
                        -i ${SSH_KEY} -r Multiverse/* \
                        ${EC2_USER}@${EC2_HOST}:${EC2_PATH}

                    # Restart Apache (httpd)
                    ssh -o StrictHostKeyChecking=no \
                        -i ${SSH_KEY} ${EC2_USER}@${EC2_HOST} \
                        "sudo systemctl restart httpd"
                '''
            }
        }
    }

    post {
        success {
            echo "🚀 Deployment Successful!"
        }
        failure {
            echo "❌ Deployment Failed!"
        }
    }
}
