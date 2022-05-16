plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":repo"))
    implementation(project(":domain-impl"))

    implementation(Dependencies.Koin.core)
}