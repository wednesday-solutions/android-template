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
        compose = true
    }
}

dependencies {

    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Material.material)

    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.recyclerView)

    implementation(Dependencies.Android.navigationFragment)
    implementation(Dependencies.Android.navigationUi)

}