plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {
    implementation(project(":service"))
    implementation(project(":service-impl"))

    implementation(Dependencies.koinCore)
    implementation(Dependencies.koinAndroid)

    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx)

    implementation(Dependencies.retrofitCore)
}