plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {
    implementation(project(":interactor"))
    implementation(project(":interactor-impl"))
    implementation(project(":domain"))

    implementation(Dependencies.koinCore)
}