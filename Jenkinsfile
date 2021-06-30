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
                sh 'sshpass -p "dc415@VM" scp univates@177.44.248.67:/home/univates /root'
                sh 'docker attach 4f7a3d478d9e'
            }
        }
    }
}
