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
    implementation(project(":presentation-entity"))

    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.coroutinesCore)
}