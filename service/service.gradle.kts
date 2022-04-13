plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {
    implementation(project(":service-entity"))

    implementation(Dependencies.kotlinStdLib)

    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.retrofitCore)
    implementation(Dependencies.retrofitSerialization)
    implementation(Dependencies.retrofitLogging)
}