plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {

    implementation(Dependencies.androidCoreKtx)
    implementation(Dependencies.androidAppCompat)
    implementation(Dependencies.material)

    testImplementation(Dependencies.testJunit)
}