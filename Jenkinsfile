pipeline{
    agent any 
    
    stages {
        stage ('build'){
            steps{
                sh 'ant clean compile test package build.xml'
            }
        }
        stage ('Test'){
            steps {
                sh 'ant test'
            }
            post {
                always {
                    junit 'caminho'
                }
            }
        }
    }
}
