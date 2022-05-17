plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {
    implementation(project(":repo"))
    implementation(project(":repo-impl"))
    implementation(project(":service"))

    implementation(Dependencies.Koin.core)
}