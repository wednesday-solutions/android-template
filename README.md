<img align="left" src="https://github.com/wednesday-solutions/android-template/blob/master/android_template_github.svg" width="480" height="540" />

<div>
  <a href="https://www.wednesday.is?utm_source=gthb&utm_medium=repo&utm_campaign=serverless" align="left" style="margin-left: 0;">
    <img src="https://uploads-ssl.webflow.com/5ee36ce1473112550f1e1739/5f5879492fafecdb3e5b0e75_wednesday_logo.svg">
  </a>
  <p>
    <h1 align="left">Android Template
    </h1>
  </p>
  An Android template application showcasing: Multi modular clean architecture, reactive patterns, dependency injection, integration with jetpack libraries, testing and CI/CD.

  ___


  <p>
    <h4>
      Expert teams of digital product strategists, developers, and designers.
    </h4>
  </p>

  <div>
    <a href="https://www.wednesday.is/contact-us?utm_source=gthb&utm_medium=repo&utm_campaign=android-template" target="_blank">
      <img src="https://uploads-ssl.webflow.com/5ee36ce1473112550f1e1739/5f6ae88b9005f9ed382fb2a5_button_get_in_touch.svg" width="121" height="34">
    </a>
    <a href="https://github.com/wednesday-solutions/" target="_blank">
      <img src="https://uploads-ssl.webflow.com/5ee36ce1473112550f1e1739/5f6ae88bb1958c3253756c39_button_follow_on_github.svg" width="168" height="34">
    </a>
  </div>

  ___

  <span>We’re always looking for people who value their work, so come and join us. <a href="https://www.wednesday.is/hiring">We are hiring!</a></span>
</div>

<br/>
<br/>

## Getting Started
- Open the project in Android Studio and sync dependencies with gradle.
- Run the app by pressing the run button in android studio or by pressing `control + R`.
- Go through and setup the scripts in the [scripts](https://github.com/wednesday-solutions/android-template/tree/master/scripts) directory.

## Architecture
The architecture of the template facilitates separation of concerns and avoids tight coupling between it's various layers. The goal is to have the ability to make changes to individual layers without affecting the entire app. This architecture is an adaptation of concepts from [`The Clean Architecture`](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).

### Layers
The architecture is separated into the following layers
- `presentation`: All UI and state management elements like Activities / Fragments (soon composables), View Models, Components, etc.
- `navigation`: navigators to navigate between Screens.
- `interactor`: provides feature specific functionality and handle coroutine contexts.
- `domain`: use cases for individual pieces of work.
- `repository`: repositories to manage various data sources.
- `service`: services provide access to external elements such as databases, apis, etc.

### Module Structure

**Each layer has 3 modules:**
- A module for the interfaces. These modules have no suffix. For example: [service](service)
- A module for the implementation. These modules are suffixed with `-impl`. For example: [service-impl](service-impl)
- A module for Dependency Injection. These modules are suffixed with `-di`. For example: [service-di](service-di)

#### Entity Modules
The layers `presentation`, `domain` and `services` each have an `entity` module as well.
- [`presentation-entity`](presentation-entity): Data Classes that model the visual elements used by the widgets.
- [`domain-entity`](domain-entity): Data classes for performing business logic manipulations. They act as a abstraction to hide the local and remote data models.
- [`service-entity`](service-entity): Contains local models (data classes for the database) and remote models (data classes for the api).

#### Entity Naming Convention
- Presentation entities are prefixed with `UI` (eg: UICity).
- Domain entities do not have any prefix. (eg: City).
- Service entities are of 2 types:
  - Local / Database entities are prefixed with `Local` (eg: LocalCity).
  - Remote / API entities are prefixed with `Remote` (eg: RemoteCity).

#### Inter-module Dependencies
There is a pattern in which all these modules depend on each other.
- The `implementation` modules depend on the `interface` modules of the same layer and the layer directly below it. [`repo-impl`](repo-impl) depends on [`repo`](repo) and [`service`](service).
- The `interface` modules may depend on the `interface` modules of the layer below. [`repo`](repo) depends on [`service`](service).
- The `di` modules depend on the `interface` and `implementation` modules of the same layer. And may also depend on the `interface` module of the layer below. [`repo-di`](repo-di) depends on [`repo`](repo), [`repo-impl`](repo-impl) and [`service`](service).

Apart from these, the layer that have entity modules depend on entity module of the same layer. The layers that don't have entity modules depend on the entity modules of the layer above and below.

![Module-Dependencies (1)](https://user-images.githubusercontent.com/58199625/153711997-dbfa45fa-e535-4a9b-af93-cb4bfb9801f3.png)

## Gradle setup
- All the dependencies are listed in [`dependencies.gradle`](dependencies.gradle).
- Android related setup like build types and flavors is in [`android.gradle`](android.gradle) which is imported in each module.
- Linting setup is done in [`lint.gradle`](lint.gradle) which is imported in each module.
- The `build.gradle` files for each module are renamed to `module-name.gradle` so that it is easy to locate them. For example, the gradle file for `service` module is called `service.gradle`.

## Understanding the Presentation Layer
The presentation layer houses all the visual components and state management logic.

The [`base`](presentation/src/main/java/com/wednesday/template/presentation/base) directory has all the reusable and common elements used as building blocks for the UI like common components, base classes, extensions, etc.

### View Model
Each `View Model` is a sub class of the `BaseViewModel`. The [`BaseViewModel`](presentation/src/main/java/com/wednesday/template/presentation/base/viewmodel/BaseViewModel.kt). View Models also have the [`SavedStateHandle`](https://developer.android.com/topic/libraries/architecture/viewmodel-savedstate) injected into them. 

View Model exposes a LiveData of [`ScreenState`](#screen-state) from the SavedStateHandle. Along with the ScreenState it also exposes a LiveData of [`Effect`](#effect). 

Implementations of the BaseViewModel can also choose to handle [`Intents`](#intent).

### Screen State
[`ScreenState`](presentation-entity/src/main/java/com/wednesday/template/presentation/screen/ScreenState.kt) encapsulates all the state required by a Fragment. State is any data that represents the current situation of a Page.

For example, the [`HomeScreenState`](presentation/src/main/java/com/wednesday/template/presentation/weather/home/HomeScreenState.kt) holds the state required by the [`HomeFragment`](presentation/src/main/java/com/wednesday/template/presentation/weather/home/HomeFragment.kt).

### Effect
[`Effects`](presentation/src/main/java/com/wednesday/template/presentation/base/effect/Effect.kt) are events that take place on a fragment that are not part of the state of the screen. These usually deal with UI elements that are not part of the xml layout.

Showing a snackbar or hiding the keyboard are examples of an effect.


### Intent
Intent is any action takes place on fragment. It may or may not be user initiated.

[`SearchScreenIntent`](presentation/src/main/java/com/wednesday/template/presentation/weather/search/SearchScreenIntent.kt) has the actions that can happen on the [`SearchFragment`](presentation/src/main/java/com/wednesday/template/presentation/weather/search/SearchFragment.kt).

### Components
Components are reusable parts of UI. Components can be used in any fragment using the `by composable` delegate provided by the `BaseFragment`.

Components can be anything from a simple component to hide and show the loading indicator to a complex component like `ListComponent` that can manage normal and nested recycler view efficiently.

### Fragment 
Each Fragment must extend the `BaseFragment`. 
The `BaseFragment` provides the [`ViewModel`](#view-model) with the navigator and the [`Screen`](#screen). It listens to the screen state and effect live data from the view model and notifies the fragment about it. It also binds and unbinds all the components in the appropriate lifecycle callbacks.

Each Fragment may receive the [`Screen`](#screen) as arguments when navigating.

### Screen
A [`Screen`](presentation-entity/src/main/java/com/wednesday/template/presentation/screen/Screen.kt) is a class that represents a `Fragment` in the context of navigation. It holds the `path or id` used by the navigator to navigate to a `Fragment` and also holds any arguments required to navigate to that `Fragment`.

## Flavors
The template comes with built-in support for 3 flavors
- Dev - This flavour is used across the development stage 
- QA - This flavour would be used when the app is under review by the QA Team or the Project Manager
- Prod - This flavour would be used when the app is ready to be deployed to a store or shipped to the user

| Note: Flavour specific configurations can be made in the [app.gradle](app/app.gradle) file under the `buildTypes` block

Read the [scripts documentation](scripts/README.md) to learn about all the scrips used in the project.
 
## Content
The Android Template contains:
- An [`Android 12`](https://www.android.com/intl/en_in/) application.
- Built-in support for 3 [`flavors`](https://developer.android.com/studio/build/build-variants) - `dev`, `qa` and `prod`.
- A [`reactive base architecture`](#architecture) for your application.
- [`Room`](https://developer.android.com/training/data-storage/room) as local persistent database.
- [`Retrofit`](https://square.github.io/retrofit/) for api calls.
- [`Kotlinx serialization`](https://github.com/Kotlin/kotlinx.serialization) for json conversion.
- [`Koin`](https://insert-koin.io/) for dependency injection.
- [`Timber`](https://github.com/JakeWharton/timber) for logging.
- [`Chucker`](https://github.com/ChuckerTeam/chucker) for on device api call logging.
- [`Ktlint`](https://ktlint.github.io/) for linting the codebase.

## Continuous Integration and Deployment
The Android template comes with built in support for CI/CD using Github Actions.

### CI
The [`CI`](.github/workflows/ci.yml) workflow performs the following checks on every pull request:
- Lints the code with `./gradlew lintRelease`.
(As an additional process, we also run `./gradlew ktlint` which is based on the [Ktlint](https://github.com/pinterest/ktlint) Kotlin linter)
- Runs tests using `./gradlew testDebugUnitTest`.
- Build the android app as the `dev` flavor to check if the building process works.

### CD
The [`CD`](.github/workflows/cd.yml) workflow performs the following actions:
- Bump the `versionCode` by 1. For details read [Version your app](https://developer.android.com/studio/publish/versioning)
- Build a release apk (`prod` flavor).
- Sign the apk using `apksigner`
- Upload apk to app center.
- Upload apk as artifact to release tag.
- Commit the updated version to git.

> `Note`: It is recommended to keep your keystore and its essentials like: alias, password safe and encrypted, inside your [Github Secrets](https://docs.github.com/en/actions/security-guides/encrypted-secrets)

### Android CD setup
For the android CD workflow to run, we need to perform the following setup steps:
- Follow these instructions to [generate an upload keystore](https://developer.android.com/studio/publish/app-signing#generate-key). Note down the `store password`, `key alias` and `key password`. You will need these in later steps.
- Use `openssl` to convert the `jks` file to `Base64`.
```shell
openssl base64 < android_template_keystore.jks | tr -d '\n' | tee android_template_keystore_encoded.txt
```
- Store the `base64` output on [`Github Secrets`](https://docs.github.com/en/actions/security-guides/encrypted-secrets) with the key name `KEYSTORE`.
- Save the `store password` in github secrets with key name `KEYSTORE_PASSWORD`.
- Save the `key alias` in github secrets with key name `KEY_ALIAS`.
- Save the `key password` in github secrets with key name `KEY_PASSWORD`.
- [Create a distribution on app center](https://docs.microsoft.com/en-us/appcenter/distribution/) and get the upload key. You can get it from from [Settings](https://appcenter.ms/settings).
- Save the app center upload key on github secrets with key name `APP_CENTER_USER_API_TOKEN`.
- Save the group name as `GROUP_NAME` inside github secrets. Example: `Testers`
- Save the organisation name as `ORG_NAME` inside github secrets. Example: `Wednesday Solutions`
- Save the app name as `APP_NAME` inside github secrets. Example: `Android Template`

> `Caution`: Respect the of the value inside secrets or else AppCenter APIs might have problems looking for your app
