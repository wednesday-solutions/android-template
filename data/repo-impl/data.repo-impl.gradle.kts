plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}

dependencies {
    implementation (project(Modules.domainEntity))
    implementation (project(Modules.serviceEntity))
    implementation (project(Modules.repo))
    implementation (project(Modules.service))
    implementation (project(Modules.serviceImpl))

    implementation(Dependencies.Kotlin.stdLib)
    implementation(Dependencies.Coroutines.core)

    implementation(Dependencies.Logging.timber)

    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.Test.kotlinTest)
    testImplementation(Dependencies.Test.mockito)
    testImplementation(Dependencies.Test.flowTest)
    testImplementation(Dependencies.Test.coroutines)
}