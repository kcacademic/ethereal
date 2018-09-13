node {
   def mvnHome
   stage('Preparation') {
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'M3'
   }
   stage('SCM Checkout') {
      // Get some code from a GitHub repository
      git 'https://github.com/kcacademic/ethereal.git'
   }
   stage('Maven Build') {
      // Run the maven build
	  if (isUnix()) {
	      sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean compile"
	  } else {
	      bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean compile/)
	  }
   }
   stage('Unit Test') {
      // Run unit tests
      if (isUnix()) {
	      sh "'${mvnHome}/bin/mvn' test"
	  } else {
	      bat(/"${mvnHome}\bin\mvn" test/)
	  }
      junit '**/target/surefire-reports/TEST-*.xml'
      archive 'target/*.jar'
   }
   stage('JoCoCo Coverage') {
      //Run JoCoCo
      jacoco()
   }
   stage('Sonar Scanner') {
      //Run Sonar Scanner
      withSonarQubeEnv('SonarQube') {
	      if (isUnix()) {
	         sh "'${mvnHome}/bin/mvn' sonar:sonar"
	      } else {
	         bat(/"${mvnHome}\bin\mvn" sonar:sonar/)
	      }
      }
   }
   stage("Quality Gate"){
      timeout(time: 1, unit: 'HOURS') {
          def qg = waitForQualityGate()
          if (qg.status != 'OK') {
             error "Pipeline aborted due to quality gate failure: ${qg.status}"
          }
      }
   }
   stage('Maven Package') {
      // Run the maven package
	  if (isUnix()) {
	      sh "'${mvnHome}/bin/mvn' -DskipTests package"
	  } else {
	      bat(/"${mvnHome}\bin\mvn" -DskipTests package/)
	  }
   }
   stage('Deploy') {
      // Run the deployment
	  echo '## TODO DEPLOYMENT ##'
   }
}