plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(Dependencies.androidAppCompat)
    implementation(Dependencies.androidCoreKtx)
    implementation(Dependencies.material)

    implementation(Dependencies.androidConstraintLayout)
    implementation(Dependencies.androidRecyclerView)

    implementation(Dependencies.androidNavigationFragment)
    implementation(Dependencies.androidNavigationUi)

}