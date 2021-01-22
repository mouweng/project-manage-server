pipeline {
//     agent {
//         // 此处设定构建环境，目前可选有
//         // default, java-8, python-3.5, ruby-2.3, go-1.11 等
//         // 详情请阅 https://dev.tencent.com/help/knowledge-base/how-to-use-ci#agents
//         label "java-8"
//     }
    agent any
    stages  {
        // 检出仓库
        stage("检出") {
            steps {
                // 这里sh调用ci-init 初始化
                sh 'ci-init'
                // 这里检出仓库，默认检出分支为环境变量中的GIT_BUILD_REF
                checkout(
                  [$class: 'GitSCM', branches: [[name: env.GIT_BUILD_REF]], 
                  userRemoteConfigs: [[url: env.GIT_REPO_URL]]]
                )
            }
        }
        // 构建jar包
        stage("构建") {
            steps {
                echo "构建中..."
                // 输出java版本
                sh 'java -version'
                // 调用maven 构建jar包
                sh 'mvn package'
                echo "构建完成."
                //收集构建产物，这一步成功，我们就可以在平台上看到构建产物
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true // 收集构建产物
            }
        }
        // 测试
        stage("测试") {
            steps {
                echo "单元测试中..."
                // 做单元测试
                sh 'mvn test'
                echo "单元测试完成."
            }
        }
        // 分发jar包，这里只是简单的通过scp分发jar包到目标机器指定目录
        stage("分发jar包") {
            steps {
                echo "分发中..."
                echo "chmod 600 pkey"
                sh 'chmod 600 authorized_keys.pem'
                echo "upload"
                  sh 'scp -i authorized_keys.pem ./target/*.jar root@120.27.223.34:/root/code/project-manage-server/'
                echo "准备部署"
            }
        }
        // 部署jar包
        stage("部署") {
            // 这里需要触发一个部署的webhook，可以是一个很简单的重启java进程的操作
            steps {
                // 用curl 来触发hook
                sh 'curl http://baidu.com'
                sh 'cd /root/code/project-manage-server'
                sh 'java -jar project-0.0.1-SNAPSHOT.jar'
                echo "请登录服务器手动部署"
            }
        }
    }
}