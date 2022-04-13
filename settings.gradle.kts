rootProject.name = "Android Template"

include(":app")
include(":service")
include(":service-impl")
include(":service-di")
include(":service-entity")
include(":repo")
include(":repo-impl")
include(":repo-di")
include(":domain")
include(":domain-impl")
include(":domain-di")
include(":domain-entity")
include(":interactor")
include(":interactor-di")
include(":interactor-impl")
include(":presentation")
include(":presentation-di")
include(":presentation-entity")
include(":navigation")
include(":navigation-di")
include(":navigation-impl")
include(":resources")

for (project in rootProject.children) {
    project.buildFileName = project.name + ".gradle.kts"
}
