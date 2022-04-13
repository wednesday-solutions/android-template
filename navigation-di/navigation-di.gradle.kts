plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {
    implementation(project(":navigation"))
    implementation(project(":navigation-impl"))

    implementation(Dependencies.kotlinStdLib)

    implementation(Dependencies.koinCore)

    implementation(Dependencies.androidAppCompat)

}