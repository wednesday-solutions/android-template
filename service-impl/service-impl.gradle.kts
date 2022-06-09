plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

android {
    defaultConfig {
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }
}

dependencies {

    implementation(project(":service-entity"))
    implementation(project(":service"))

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

    testImplementation(Dependencies.Test.androidxTestCore)
    testImplementation(Dependencies.Test.androidxTestRunner)
    testImplementation(Dependencies.Test.androidxTestRules)
    testImplementation(Dependencies.Test.androidxExt)
    testImplementation(Dependencies.Test.kotlinTest)
    testImplementation(Dependencies.Test.flowTest)

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
}