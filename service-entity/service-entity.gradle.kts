plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {

    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.kotlinSerialization)
    kapt(Dependencies.roomCompiler)
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx)
}