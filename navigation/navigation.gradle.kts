plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
    id(Plugins.COMPOSE)
}

dependencies {
    implementation(project(":ui:presentation-entity"))
    implementation(project(":resources"))

    implementation(Dependencies.Kotlin.stdLib)

    implementation(Dependencies.Android.appCompat)

    implementation(Dependencies.Android.navigationUi)
    implementation(Dependencies.Android.navigationFragment)
}