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
       
          echo '*******download from JFrog End*******'
      }
 }


 /*      
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
         
          echo '*******deploy on weblogic done*******'
        
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
          echo '*******deploy on weblogic done*******'
        
      }
	} 
      stage('Deploy to UAT ENVIRONMENT') {
		when {
			expression{
				"$DEPLOY_TO" == 'origin/uat'
			}
			}
      steps {
          echo "*******deploy on weblogic Start to $DEPLOY_TO *******"
         echo '*******deploy on weblogic done*******'
       
      }
	}  
      stage('Deploy to PROD ENVIRONMENT') {
		when {
			expression{
				"$DEPLOY_TO" == 'origin/master'
			}
			}
      steps {
          echo "*******deploy on weblogic Start to PROD *******"
        echo '*******deploy on weblogic done*******'
      
      }
	}   
 } 
}*/
stage ('Deploy on Weblogic') {
	 steps {
		 script{
	if ("$DEPLOY_TO" == 'origin/dev') {
  
          echo "*******deploy on weblogic Start to $DEPLOY_TO *******"
          echo '*******deploy on weblogic done*******'
          }
		
} else if ("$DEPLOY_TO" == 'origin/sit') {
          echo "*******deploy on weblogic Start to $DEPLOY_TO *******"
          echo '*******deploy on weblogic done*******'
          }
		
} else if ("$DEPLOY_TO" == 'origin/uat') {
          echo "*******deploy on weblogic Start to $DEPLOY_TO *******"
          echo '*******deploy on weblogic done*******'
          }
		
} else {
          echo "*******deploy on weblogic Start to $DEPLOY_TO *******"
          echo '*******deploy on weblogic done*******'
          }
		}
}
}
	
}
}
}
