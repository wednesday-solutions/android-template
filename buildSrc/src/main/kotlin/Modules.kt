object Modules {
    const val service = ":data:service"
    const val serviceImpl = ":data:service-impl"
    const val serviceDI = ":data:service-di"
    const val serviceEntity = ":data:service-entity"
    const val repo = ":data:repo"
    const val repoImpl = ":data:repo-impl"
    const val repoDI = ":data:repo-di"
    const val domain = ":data:domain"
    const val domainImpl = ":data:domain-impl"
    const val domainDI = ":data:domain-di"
    const val domainEntity = ":data:domain-entity"


    const val home = ":ui:feature:home"
    const val interactor = ":ui:interactor"
    const val interactorDI = ":ui:interactor-di"
    const val interactorImpl = ":ui:interactor-impl"
    const val presentationEntity = ":ui:presentation-entity"
    const val coreUi = ":ui:core-ui"
    const val designSystem = ":ui:design-system"
    const val search = ":ui:feature:search"

    const val navigation = ":navigation"
    const val navigationDi = ":navigation-di"
    const val navigationImpl = ":navigation-impl"

    const val resources = ":resources"

    object Core {
        const val data = ":data:core"
        const val ui = ":ui:core"
    }
}