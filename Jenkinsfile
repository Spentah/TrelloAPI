pipeline {
    agent any

    tools {
        jdk "JDK 1.8"
        maven "maven 3.6.3"
    }

    parameters{
        choice(description: 'Choose stand', name: 'stand', choices: ['prod', 'dev'])
        string(description: 'Groups to run', name: 'group')
    }

    stages {
        stage('Run tests') {
            steps {
                bat "mvn -P${params.stand} -Dgroups=${params.group} clean test"
            }
        }
        stage('Create report') {
            steps {
                always{
                    allure([
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'allure-results']]
                    ])
                }
            }
        }
    }

//     post{
//         always{
//             allure([
//                 reportBuildPolicy: 'ALWAYS',
//                 results: [[path: 'allure-results']]
//             ])
//         }
//     }
// }