plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}

dependencies {
    implementation(project(Modules.interactor))
    implementation(project(Modules.interactorImpl))
    implementation(project(Modules.domain))

    implementation(Dependencies.Koin.core)
}