@Library('jenkins-pipeline-library@main') _
pipeline {
    agent { node { label 'java-8' } }
    options { timeout(time: 30, unit: 'MINUTES') }

    stages {
        stage('check env') {
            steps {
                checkEnv()
            }
        }
        stage('build artifact') {
            steps {
                buildArtifact()
            }
        }
        stage('upload artifact') {
            when {
                expression { return MASTER_BRANCH == 'false' }
            }
            steps {
                uploadArtifact()
            }
        }
        stage('build image') {
            agent { node { label 'buildah-ubi8' } }
            when {
                expression { return MASTER_BRANCH == 'false' }
            }
            steps {
                buildImage()
            }
        }
        stage('deploy') {
            when {
                expression { return MASTER_BRANCH == 'false' }
            }
            steps {
                deploy()
            }
        }
        stage('code review') {
            when {
                expression { return MASTER_BRANCH == 'false' && env.commitMessage.contains('codeReview')}
            }
            steps {
                codeReview()
            }
        }
        stage('unit') {
            // 执行单元测试，不忽略失败的单测，必须保障通过单测
            steps {
                unit()
            }
        }
        stage('sonar') {
            // 执行sonar静态代码扫描
            steps {
                sonar()
            }
        }
        stage('snapshot') {
            // 检查工程是否使用了快照版本的jar
            steps {
                snapshot()
            }
        }
        stage('snyk scan') {
            // 安全扫描
            steps {
                snykScan()
            }
        }
    }
}