plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}

dependencies {
    implementation(project(Modules.repo))
    implementation(project(Modules.repoImpl))
    implementation(project(Modules.service))

    implementation(Dependencies.Koin.core)
}