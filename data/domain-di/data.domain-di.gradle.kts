plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}


dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.repo))
    implementation(project(Modules.domainImpl))

    implementation(Dependencies.Koin.core)
}