plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {
    implementation(project(":data:domain"))
    implementation(project(":data:repo"))
    implementation(project(":data:domain-impl"))

    implementation(Dependencies.Koin.core)
}