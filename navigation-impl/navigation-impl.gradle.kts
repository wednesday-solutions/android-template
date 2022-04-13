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
    implementation(project(":navigation"))

    implementation(Dependencies.kotlinStdLib)

    implementation(Dependencies.androidAppCompat)
    implementation(Dependencies.androidNavigationUi)
    implementation(Dependencies.androidNavigationFragment)

    testImplementation(Dependencies.testAndroidxTestCore)
    testImplementation(Dependencies.testAndroidxExt)
    testImplementation(Dependencies.testKotlinTest)
    testImplementation(Dependencies.testMockito)
}