pipeline{
    environment{
        BRANCH_NAME = "${env.BRANCH_NAME}"
    }
    agent any
    stages {
        stage ('testando'){
            steps{
                sh 'ant -version'
            }
        }
        stage ('GitHub Jenkins Ant Build'){
            steps {
                git 'https://github.com/psignori/projeto-gerencia-config-software.git'
                sh 'ant clean compile test package build.xml'
            }
        }
    }
}