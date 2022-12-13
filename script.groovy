
def buildImage() {
    echo 'building the docker image...'
    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t yassineelkhadiri/devops:spring-app-1.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push yassineelkhadiri/devops:spring-app-1.0'
    }
}

def pushToNexus() {
    echo 'pushing the jar file to Nexus maven-snapshots repo...'
    sh 'mvn clean install'
    nexusArtifactUploader artifacts: [[artifactId: 'devops', classifier: '', file: 'target/devops-0.0.1-SNAPSHOT.jar', type: 'jar']], credentialsId: 'nexus-credentials', groupId: 'org.springframework.boot', nexusUrl: '172.21.0.3:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'maven-snapshots', version: '0.0.1-SNAPSHOT'
}

def testunitaire() {
    echo 'Running Unit Tests'
    sh 'mvn test'
}

def sonarScan() {
    echo 'Running sonarQube scan...'
    withSonarQubeEnv('sonarqube') {
        sh 'mvn clean verify sonar:sonar -D sonar.projectKey=sonarqube -D maven.test.skip=true  -D sonar.login=sqa_d8c3b0c5c49abf38d007e5a3a550d6054722e07f'
        }
}

// def sonarScan() {
//         echo "Running sonarQube scan..."
//         withSonarQubeEnv('sonarqube') {
//            // sh "${scannerHome}/bin/sonar-scanner"
//               sh "mvn clean verify sonar:sonar -Dsonar.projectKey=sonarqube "
//         }
//        /* timeout(time: 10, unit: 'MINUTES') {
//             waitForQualityGate abortPipeline: true
//         } */

// }

return this
