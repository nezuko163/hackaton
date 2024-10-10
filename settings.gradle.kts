pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "hackaton"
include(":app")
include(":core")
include(":core:data")
include(":core:domain")
include(":core:ui")
include(":feature")
include(":feature:home")
include(":feature:biometry")
include(":feature:face")
include(":feature:voice")
include(":feature:faceHints")
