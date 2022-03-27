pipeline {
    agent any

    tools {
        jdk "JDK 1.8"
        maven "maven 3.6.3"
    }

    parameters{
        stringParam(description: 'groups to run', name: 'group')
    }

    stages {
        stage('Run tests') {
            steps {
                bat "mvn clean -Dgroups=$group test"
            }
        }
    }

    post{
        always{
            allure([
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'allure-results']]
            ])
        }
    }
}