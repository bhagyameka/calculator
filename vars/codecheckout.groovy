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
        
    //step([$class: 'CxScanBuilder', comment: '', configAsCode: true, credentialsId: 'CheckmarxAdmin', customFields: '', excludeFolders: '', exclusionsSetting: 'job', failBuildOnNewResults: false, filterPattern: '', fullScanCycle: 10, generatePdfReport: true, groupId: '14', isProxy: false, password: '{AQAAABAAAAAQuS0flM51g0FeEL8tg0KFkzAKx67QTxAk8RG33TyUuTw=}', preset: '7', projectName: 'IDFC-MS-Gateway', sastEnabled: true, serverUrl: ' https://checkmarx.idfcbank.com', sourceEncoding: '6', useOwnServerCredentials: true, username: '', waitForResultsEnabled: true])
    
        echo '*******CheckMarX end*******'
    }
   
}


stage ('Upload to Jfrog') {
     steps {
         echo '*******upload to JFrog start*******'
       /*  rtUpload (
                serverId: 'artifactory_idfcbank',
                spec:
                    '''{
                      "files": [
                        {
                          "pattern": "/data/jenkins/workspace/Digital Onboarding/DemoPipelineAsCode/target/cicd-0.0.1-SNAPSHOT.war",
                          "target": "maven-local/idfc/digital_onboarding/master/"
                        }
                     ]
                    }''',
            ) */
         echo '*******upload to JFrog End*******'
     }
}
		}
		
	}
}
