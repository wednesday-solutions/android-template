plugins {
    id("com.android.library")
    id(Plugins.ANDROID)
}

dependencies {
    implementation(project(Modules.interactor))
    implementation(project(Modules.presentationEntity))
    implementation(project(Modules.domain))
    implementation(project(Modules.Core.data))
    implementation(project(Modules.domainEntity))
    implementation(project(Modules.resources))

    implementation(Dependencies.Kotlin.stdLib)

    implementation(Dependencies.Coroutines.core)

    implementation(Dependencies.Android.coreKtx)

    implementation(Dependencies.Logging.timber)

    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.Test.kotlinTest)
    testImplementation(Dependencies.Test.mockito)
    testImplementation(Dependencies.Test.flowTest)
    testImplementation(Dependencies.Test.coroutines)
    testImplementation(Dependencies.Test.androidxArchCore)
    testImplementation(Dependencies.Test.androidxTestRunner)
    testImplementation(Dependencies.Test.androidxTestRules)
    testImplementation(Dependencies.Test.androidxExt)
    testImplementation(Dependencies.Test.androidxCoreTesting)
}