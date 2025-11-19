// pipeline {
//     agent any

//     environment {
//         EC2_USER = "ec2-user"
//         EC2_HOST = "15.223.166.38"
//         EC2_PATH = "/var/www/html"
//         LOCAL_DIR = "Multiverse"
//     }

//     stages {

//         stage('Checkout') {
//             steps {
//                 git branch: 'main',
//                     url: 'https://github.com/gkj-git/Multiverse.git'
//             }
//         }

//         stage('Package (Optional)') {
//             steps {
//                 // Only needed if you want a tar.gz
//                 sh '''
//                     rm -f ${LOCAL_DIR}/${LOCAL_DIR}.tar.gz || true
//                     tar -czf ${LOCAL_DIR}/${LOCAL_DIR}.tar.gz -C ${LOCAL_DIR} index.html assets images
//                 '''
//             }
//         }

//         stage('Deploy to EC2') {
//             steps {
//                 sh '''
//                     echo "Deploying ${LOCAL_DIR} to EC2..."
//                     scp -o StrictHostKeyChecking=no -r ${LOCAL_DIR}/* ${EC2_USER}@${EC2_HOST}:${EC2_PATH}
//                     ssh -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_HOST} "sudo systemctl restart httpd"
//                     echo "Deployment completed successfully!"
//                 '''
//             }
//         }
//     }

//     post {
//         success {
//             echo "✅ Deployment Successful!"
//         }
//         failure {
//             echo "❌ Deployment Failed!"
//         }
//     }
// }
