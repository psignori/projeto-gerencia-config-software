pipeline{
    agent any 
    
    stages {
        stage ('test'){
            steps{
                
            }
        }
        stage ('build'){
            steps {
                sh 'ant init'
                sh 'ant deps-clean'
                sh 'ant clean'
                sh 'ant init'
                sh 'ant deps-jar'
                sh 'ant compile'
                sh 'ant deploy'
                sh 'ant jar'
            }
            post {
                always {
                    junit 'caminho'
                }
            }
        }
    }
}
