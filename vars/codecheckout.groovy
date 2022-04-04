def call(String username = 'null') {
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
}
