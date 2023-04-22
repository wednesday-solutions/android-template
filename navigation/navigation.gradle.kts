plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}

dependencies {
    implementation(project(Modules.presentationEntity))
    implementation(project(Modules.resources))

    implementation(Dependencies.Kotlin.stdLib)

    implementation(Dependencies.Android.appCompat)

    implementation(Dependencies.Android.navigationUi)
    implementation(Dependencies.Android.navigationFragment)
}