
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
					println "$BRANCH_NAME".drop(7)
					git branch: "$BRANCH_NAME".drop(7), url: "https://github.com/bhagyameka/calculator.git", credentialsId: 'c6947d68-906d-4126-a88a-d93c8d4a1ec8'
				}	
			}
		        stage('build using maven') {
				steps {
					echo "building using maven"
					sh 'mvn clean install'
				}	
			}
			stage('unit testing') {
    steps {
        echo '*******unit testing starts*******'
        junit skipPublishingChecks: true, testResults: '**/target/surefire-reports/TEST-*.xml'   
        echo '*******unit testing ends*******'
               
    }
}

stage ('Test using CheckMarX') {
   
    steps {
        echo '*******CheckMarX start*******'
        
   
        echo '*******CheckMarX end*******'
    }
   
}


stage ('Upload to Jfrog') {
     steps {
         echo '*******upload to JFrog start*******'
      
         echo '*******upload to JFrog End*******'
     }
}
		}
		
	}
}
