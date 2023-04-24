plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}

dependencies {
    implementation(project(Modules.domainEntity))
    implementation(project(Modules.domain))
    implementation(project(Modules.Core.data))
    implementation(project(Modules.presentationEntity))

    implementation(Dependencies.Kotlin.stdLib)
    implementation(Dependencies.Coroutines.core)
}