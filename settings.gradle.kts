pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "IRControl"

// Include all modules
include(":app")
include(":core")
include(":domain")
include(":data")
include(":ui")
include(":plugins")

// Configure project directory paths
project(":app").projectDir = file("modules/app")
project(":core").projectDir = file("modules/core")
project(":domain").projectDir = file("modules/domain")
project(":data").projectDir = file("modules/data")
project(":ui").projectDir = file("modules/ui")
project(":plugins").projectDir = file("modules/plugins")
