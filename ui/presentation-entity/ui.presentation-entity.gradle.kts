plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
    id(Plugins.PARCELIZE)
}

dependencies {

    implementation(Dependencies.Kotlin.stdLib)
    implementation(Dependencies.Android.coreKtx)

    implementation(Dependencies.Android.appCompat)
}