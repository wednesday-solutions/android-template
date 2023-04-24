plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
    id(Plugins.PARCELIZE)
    id(Plugins.KOTLIN_SERIALIZATION)
}

dependencies {

    implementation(Dependencies.Kotlin.stdLib)
    implementation(Dependencies.Kotlin.immutableCollection)
    implementation(Dependencies.Kotlin.serialization)
    implementation(Dependencies.Android.coreKtx)
    implementation(platform(Dependencies.Compose.composeBOM))
    implementation(Dependencies.Compose.runtime)

    implementation(Dependencies.Android.appCompat)
}