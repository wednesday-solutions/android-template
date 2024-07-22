plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}

dependencies {
    implementation(project(Modules.domainEntity))
    implementation(project(Modules.serviceEntity))
    implementation(project(Modules.service))


    implementation(Dependencies.Kotlin.stdLib)
    implementation(Dependencies.Coroutines.core)

    implementation(Dependencies.Logging.timber)
}