pipeline{
    agent any
    environment{
    RELEASE="20.0"
    }

    stages{
        stage("Build"){
            agent any
            environment{
                LEVEL="INFO"
            }
            steps{
                echo "Build release ${RELEASE} and Level ${LEVEL}."
            }
        }

        stage("Test"){
            steps{
                echo "I am in the release ${RELEASE} but not in the log level ${LEVEL}."
            }
        }
        stage("Deploy"){
            input{
                message "Deploy?"
                ok "Do it!"
                parameters{
                    string(name: 'TARGET_ENVIRONMENT', default_value: 'PROD', description: "This is the target environment for deployment")
                }
                steps{
                    echo "This is the release ${RELEASE} to the ${TARGET_ENVIRONMENT} environment"
                }
            }
        }

    }

    post{
        always{
            echo "Prints whether deploy happened or not, sucess or failure"
        }
    }
}