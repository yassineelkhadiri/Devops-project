def gv

pipeline {
    agent any
    tools {
        maven 'Maven'
    } 
    stages {
        stage("INIT") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("Check JDK & maven version"){
            steps{
                script{
                    sh 'java --version'
                    sh 'mvn --version'
                }
            }
        }
        stage("Test Unitaire"){
            steps{
                script{
                    sh 'echo Test Unitaire'
                    gv.testunitaire()
                }
            }
        }
        stage("SonarQube Testing and Scan") {
            steps {
                script {
                    gv.sonarScan()
                    sh 'echo sonarqube'
                }
            }
        }
       
        stage("Push JAR to Nexus"){
            steps {
                script {
                    gv.pushToNexus()
                }
            }
        }
        // stage("build image") {
        //     steps {
        //         script {
        //             gv.buildImage()
        //         }
        //     }
        // }
    }
}
