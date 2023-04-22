plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}

dependencies {
    implementation (project(":data:domain-entity"))
    implementation (project(":data:service-entity"))
    implementation (project(":data:repo"))
    implementation (project(":data:service"))
    implementation (project(":data:service-impl"))

    implementation(Dependencies.Kotlin.stdLib)
    implementation(Dependencies.Coroutines.core)

    implementation(Dependencies.Logging.timber)

    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.Test.kotlinTest)
    testImplementation(Dependencies.Test.mockito)
    testImplementation(Dependencies.Test.flowTest)
    testImplementation(Dependencies.Test.coroutines)
}