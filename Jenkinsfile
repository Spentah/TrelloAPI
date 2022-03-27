pipeline {
    agent any

    tools {
        jdk "JDK 1.8"
        maven "maven 3.6.3"
    }

    parameters{
        string(description: 'groups to run', name: 'group')
    }

    stages {
        stage('Run tests') {
            steps {
                bat "mvn -Dgroups=${params.group} clean test"
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