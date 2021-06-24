pipeline{
    environment{
        BRANCH_NAME = "${env.BRANCH_NAME}"
    }
    agent {docker {iamge 'cameronmcnz/ant-jdk8-git:latest' } }
    stages {
        stage ('Log the ant version'){
            steps{
                sh 'ant -version'
            }
        }
        stage ('GitHub Jenkins Ant Build'){
            steps {
                sh 'ant clean compile test package build.xml'
            }
        }
    }
}
