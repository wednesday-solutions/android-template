plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
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