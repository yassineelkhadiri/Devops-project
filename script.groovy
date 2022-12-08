
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
    sh 'mvn clean install -Dmaven.test.skip=true'
    nexusArtifactUploader artifacts: [[artifactId: 'devops', classifier: '', file: 'target/devops-0.0.1-SNAPSHOT.jar', type: 'jar']], credentialsId: 'nexus-credentials', groupId: 'org.springframework.boot', nexusUrl: 'localhost:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'maven-snapshots', version: '0.0.1-SNAPSHOT'
}

def sonarScan() {
    echo 'Running sonarQube scan...'
    sh 'docker compose up postgresqldb'
    sh 'mvn test'
    sh 'mvn sonar:sonar -D sonar.login=sqa_609e3916d9f0e4ea56067e7c37f866c31c02f9dd'
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

// def deployApp(String serverIp, String serverUser) {
//     echo 'deploying the application...'
//     def composeRun = '"export MYSQLDB_USER=root MYSQLDB_ROOT_PASSWORD=sofiene MYSQLDB_DATABASE=pet_store MYSQLDB_LOCAL_PORT=3306 MYSQLDB_DOCKER_PORT=3306 SPRING_LOCAL_PORT=8080 SPRING_DOCKER_PORT=8080 && docker-compose up -d"'
//     sshagent (credentials: ['deployment-server']) {
//         sh "ssh -o StrictHostKeyChecking=no ${serverUser}@${serverIp} ${composeRun}"
//     }
// }

// def cleanUntaggedImages(String serverIp, String serverUser){
//     def cleanImages = 'docker image prune --force --filter "dangling=true"'
//     sshagent (credentials: ['jenkins-server']) {
//         sh "ssh -o StrictHostKeyChecking=no ${serverUser}@${serverIp} ${cleanImages}"
//     }
// }

return this
