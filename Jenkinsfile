pipeline{
    agent {docker {image 'cameronmcnz/ant-jdk8-git:latest' } }
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
