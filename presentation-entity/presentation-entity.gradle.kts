plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {

    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.androidCoreKtx)

    implementation(Dependencies.androidAppCompat)
}