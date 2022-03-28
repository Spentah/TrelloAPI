pipeline {
    agent any

    tools {
        jdk "JDK 1.8"
        maven "maven 3.6.3"
    }

    parameters{
        booleanParam(description: 'Use custom environment', defaultValue: false, name: 'env')
        choice(description: 'Choose stand', name: 'stand', choices: ['prod', 'dev'])
        string(description: 'Groups to run', name: 'group')
    }

    stages {
        stage('Run tests') {
            steps {
                script {
                    if (params.env == true) {
                        bat "mvn -P${params.stand} -Dgroups=${params.group} clean test"
                    } else {
                        bat "mvn -Dgroups=${params.group} clean test"
                    }
                }
            }
        }
    }

    post {
        always {
            allure([
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'allure-results']]
            ])
        }
    }
}