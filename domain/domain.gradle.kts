plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {
    implementation(project(":domain-entity"))

    implementation(Dependencies.Kotlin.stdLib)
    implementation(Dependencies.Coroutines.core)

    implementation(Dependencies.Logging.timber)
}