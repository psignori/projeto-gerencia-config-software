pipeline{
    agent {
         docker {
             image 'ant:1.10.7' 
        } 
    }
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
