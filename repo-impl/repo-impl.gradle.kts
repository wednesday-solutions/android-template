plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply {
    from("${rootProject.projectDir}/android.gradle")
    from("${rootProject.projectDir}/lint.gradle")
}

dependencies {
    implementation (project(":domain-entity"))
    implementation (project(":service-entity"))
    implementation (project(":repo"))
    implementation (project(":service"))
    implementation (project(":service-impl"))

    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.coroutinesCore)

    implementation(Dependencies.loggingTimber)

    testImplementation(Dependencies.testJunit)
    testImplementation(Dependencies.testKotlinTest)
    testImplementation(Dependencies.testMockito)
    testImplementation(Dependencies.testFlowTest)
    testImplementation(Dependencies.testCoroutines)
}