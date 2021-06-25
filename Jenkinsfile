pipeline{
    agent any 
    
    stages {
        stage ('test'){
            steps{
                echo 'ola'
                sh 'ant init'
                sh 'ant deps-jar'
                sh 'ant init'
                sh 'ant deps-clean'
                sh 'ant clean'
                sh 'ant compile'
                sh 'ant compile-test'
                sh 'ant test-report'
                sh 'ant test'
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
    }
}
