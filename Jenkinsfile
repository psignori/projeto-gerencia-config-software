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
            stapes {
                sh 'scp -r workspace user@172.17.255.255:/bin/bash '
            }
        }
    }
}
