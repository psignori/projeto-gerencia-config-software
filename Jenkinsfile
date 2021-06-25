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
                sh 'git add .'
                sh 'git commit -m "build"'
                sh 'git push'
            }
        }
        stage ('docker'){
            echo 'Entrando na maquina de homologação e baixando imagem atualizada da aplicação'
            sh 'ssh univates@177.44.248.67 '
            sh 'dc415@VM'
            sh 'sudo su'
            sh 'dc415@VM'
            sh 'docker attach 5bdebd944073'
            sh 'cd projeto-gerencia-config-software'
            sh 'git pull'
        }
    }
}
