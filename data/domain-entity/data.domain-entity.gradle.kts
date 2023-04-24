plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}

dependencies {

    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Material.material)

    testImplementation(Dependencies.Test.junit)
}