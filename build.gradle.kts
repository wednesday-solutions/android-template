// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.Android.gradlePlugin)
        classpath(Dependencies.Google.services)
        classpath(Dependencies.Kotlin.gradlePlugin)
        classpath(Dependencies.Kotlin.serializationPlugin)
        classpath(Dependencies.Android.navigationSafeArgsPlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    // TODO: Remove once ExperimentalCoroutinesApi: runTest is stable
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    }
}
