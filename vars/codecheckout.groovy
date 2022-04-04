/*def call(String username = 'null') {
	echo "This is shared library greetUser: ${username}"
	pipeline {
		environment {
			USER_NAME = "${username}"
		}
		agent any 
		stages {
			stage('SHARED-STAGE') {
				steps {
					echo "Hi $USER_NAME, How are you !"
				}	
			}
		}
	}
} */
def call(String branch = 'null') {
	echo "This is shared library codecheckout: ${branch}"
	pipeline {
		environment {
			BRANCH_NAME = "${branch}"
		}
		agent any 
		stages {
			stage('CODECHECKOUT from SCM') {
				steps {
					echo "You have chosen branch $BRANCH_NAME"
					git branch: "$BRANCH_NAME", url: "https://github.com/bhagyameka/calculator.git", credentialsId: 'c6947d68-906d-4126-a88a-d93c8d4a1ec8'
				}	
			}
		        stage('build using maven') {
				steps {
					echo "building using maven"
				}	
			}
		}
		
	}
}
