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
        // stage("SonarQube Testing and Scan") {
        //     environment {
        //         CI = 'true'
        //         scannerHome = tool 'sonarqube'
        //     }
        //     // agent{ docker { image 'maven'}  }
        //       steps {
        //         script {
        //            // sh 'mvn clean install -Dmaven.test.skip=true'
        //             gv.sonarScan()
        //         }
        //       }
        // } 
        stage("Push JAR to Nexus"){
            steps {
                script {
                    gv.pushToNexus()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    gv.buildImage()
                }
            }
        }
    }
}
