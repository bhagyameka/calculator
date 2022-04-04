def call(String branch = 'null') {
	echo "This is shared library deploy: ${branch}"
pipeline {
     environment {
			DEPLOY_TO = "${branch}"
		}
agent any

stages {


 stage ('Download from Jfrog') {
      steps {
          echo '*******download from JFrog start*******'
       /*   rtDownload (
                   serverId: 'artifactory_idfcbank',
                   spec: '''{
                           "files": [
                            {
                               "pattern": "maven-local/idfc/digital_onboarding/master/",
                               "target": "D:/"
                             }
                          ]
                         }''',
             ) */
          echo '*******download from JFrog End*******'
      }
 }


        
 stage ('Deploy on Weblogic') {
      parallel { 
	stage('Deploy to DEV ENVIRONMENT') {
		when {
			expression{
				"$DEPLOY_TO" == 'origin/dev'
			}
			}
      steps {
          echo "*******deploy on weblogic Start to $DEPLOY_TO *******"
         //  sshagent(['ID_WEBLOGIC_DIGITAL_ONBOARDING']) {
          // sh "scp -v -o StrictHostKeyChecking=no /data/jenkins/workspace/Digital Onboarding/DemoPipelineAsCode/target/*.war deployer@10.5.25.170:7001/weblogicdomain/onboardapp/servers/AdminServer/upload/sample1.war/app/"
          echo '*******deploy on weblogic done*******'
        // }
      }
	}
	stage('Deploy to SIT ENVIRONMENT') {
		when {
			expression{
				"$DEPLOY_TO" == 'origin/sit'
			}
			}
      steps {
          echo "*******deploy on weblogic Start to $DEPLOY_TO *******"
         //  sshagent(['ID_WEBLOGIC_DIGITAL_ONBOARDING']) {
          // sh "scp -v -o StrictHostKeyChecking=no /data/jenkins/workspace/Digital Onboarding/DemoPipelineAsCode/target/*.war deployer@10.5.25.170:7001/weblogicdomain/onboardapp/servers/AdminServer/upload/sample1.war/app/"
          echo '*******deploy on weblogic done*******'
        // }
      }
	}      
 } 
}
}
}
}
