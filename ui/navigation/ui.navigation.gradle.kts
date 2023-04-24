plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}

dependencies {
    implementation(project(Modules.presentationEntity))
    implementation(project(Modules.resources))
    implementation(project(Modules.home))
    implementation(project(Modules.search))

    implementation(Dependencies.Kotlin.stdLib)
    implementation(Dependencies.Koin.core)

    implementation(Dependencies.Android.appCompat)

    implementation(Dependencies.Compose.composeDestinations)

}