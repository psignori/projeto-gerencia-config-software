pipeline{
    agent any
    
    stages {
        stage ('test'){
            steps{
                echo 'ola'
            }
        }
        stage ('build'){
            steps {
                sh 'ant init'
                sh 'ant deps-clean'
                sh 'ant init'
                sh 'ant deps-jar'
                sh 'ant compile'
                sh 'ant deploy'
                sh 'ant jar'
            }
        }
        stage ('Deploy para homologacao'){
            steps {
                sh 'docker stop '
                sh 'docker-compose build'
                sh 'docker-compose up -d  '
            }
        }
    }
}
