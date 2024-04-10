pluginManagement {
    repositories {
        google()
        mavenCentral()
        // 添加国内阿里云仓库
        maven("https://maven.aliyun.com/repository/google")
        maven("https://maven.aliyun.com/repository/jcenter")
        // 或者使用其他国内镜像源
        // maven("https://mirrors.tencent.com/maven/repository/android/public/")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // 同样添加国内阿里云仓库或者其他你信任的国内源
        maven("https://maven.aliyun.com/repository/google")
        maven("https://maven.aliyun.com/repository/jcenter")
    }
}
rootProject.name = "ParkApp"
include(":app")
 