plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {
    implementation(project(":presentation"))
    implementation(project(":interactor"))
    implementation(project(":navigation"))

    implementation(Dependencies.koinCore)
    implementation(Dependencies.koinAndroid)
}