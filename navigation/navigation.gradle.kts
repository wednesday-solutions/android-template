plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {
    implementation(project(":presentation-entity"))
    implementation(project(":resources"))

    implementation(Dependencies.kotlinStdLib)

    implementation(Dependencies.androidAppCompat)

    implementation(Dependencies.androidNavigationUi)
    implementation(Dependencies.androidNavigationFragment)
}