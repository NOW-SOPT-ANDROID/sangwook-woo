enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")
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

rootProject.name = "NOW-SOPT-Android"
include(":app")
include(":feature:main")
include(":core:ui")
include(":core:model")
include(":core:designsystem")
include(":core:database")
include(":core:data")
include(":core:domain")
include(":core:common")
include(":core:datastore")
include(":core:domain-test")
include(":core:data-test")