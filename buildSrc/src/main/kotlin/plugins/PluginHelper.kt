package plugins

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope


val Project.androidExtension get() = project.extensions.getByName(PluginConstants.ANDROID) as? BaseExtension

internal fun DependencyHandlerScope.implementation(dependency: String) {
    add(PluginConstants.IMPLEMENTATION, dependency)
}

internal fun DependencyHandlerScope.ksp(dependency: String) {
    add(PluginConstants.KSP, dependency)
}

internal fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)
