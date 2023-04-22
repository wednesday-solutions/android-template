plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}

dependencies {
    implementation(project(Modules.navigation))
    implementation(project(Modules.navigationImpl))

    implementation(Dependencies.Kotlin.stdLib)

    implementation(Dependencies.Koin.core)

    implementation(Dependencies.Android.appCompat)

}