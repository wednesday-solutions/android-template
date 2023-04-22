plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
    id(Plugins.COMPOSE)
}

dependencies {
    implementation(project(Modules.presentationEntity))
    implementation(project(Modules.navigation))
    implementation(project(Modules.home))
    implementation(project(Modules.search))

    implementation(Dependencies.Kotlin.stdLib)

    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.navigationUi)
    implementation(Dependencies.Android.navigationFragment)

    testImplementation(Dependencies.Test.androidxTestCore)
    testImplementation(Dependencies.Test.androidxExt)
    testImplementation(Dependencies.Test.kotlinTest)
    testImplementation(Dependencies.Test.mockito)
}