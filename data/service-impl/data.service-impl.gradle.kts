plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
    id(Plugins.KSP)
}

ksp {
    arg(RoomSchemaArgProvider(File(projectDir, "schemas")))
}

dependencies {

    implementation(project(":data:service-entity"))
    implementation(project(":data:service"))

    implementation(Dependencies.Kotlin.stdLib)
    implementation(Dependencies.Kotlin.serialization)

    implementation(Dependencies.Coroutines.core)

    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.ktx)
    kapt(Dependencies.Room.compiler)

    implementation(Dependencies.Retrofit.core)
    implementation(Dependencies.Retrofit.serialization)
    implementation(Dependencies.Retrofit.logging)

    implementation(Dependencies.Logging.timber)
    implementation(Dependencies.Logging.chucker)

    androidTestImplementation(Dependencies.Test.androidxTestCore)
    androidTestImplementation(Dependencies.Test.androidxTestRunner)
    androidTestImplementation(Dependencies.Test.androidxTestRules)
    androidTestImplementation(Dependencies.Test.androidxExt)
    androidTestImplementation(Dependencies.Test.kotlinTest)
    androidTestImplementation(Dependencies.Test.flowTest)

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
}