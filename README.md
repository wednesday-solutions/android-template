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
The architecture of the template facilitates seperation of concerns and avoids tight coupling between it's various layers. The goal is to have the ability to make changes to individual layers without affecting the entire app. This architecture is an adaptation of concepts from [`The Clean Architecture`](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).

### Layers
The architecture is separeted into the following layers
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
  - Local / Database entites are prefixed with `Local` (eg: LocalCity).
  - Remote / API entitiess are prefixed with `Remote` (eg: RemoteCity).

#### Inter-module Dependencies
There is a pattern in which all these modules depend on each other.
- The `implementation` modules depend on the `interface` modules of the same layer and the layer directly below it. [`repo-impl`](repo-impl) depends on [`repo`](repo) and [`service`](service).
- The `interface` modules may depend on the `interface` modules of the layer below. [`repo`](repo) depends on [`service`](service).
- The `di` modules depend on the `interface` and `implementation` moduels of the same layer. And may also depend on the `interface` module of the layer below. [`repo-di`](repo-di) depends on [`repo`](repo), [`repo-impl`](repo-impl) and [`service`](service).

Apart from these, the layer that have entitiy modules depend on entity module of the same layer. The layers that don't have entity modules depend on the entity modules of the layer above and below.

![Module-Dependencies (1)](https://user-images.githubusercontent.com/58199625/153711997-dbfa45fa-e535-4a9b-af93-cb4bfb9801f3.png)

### Other Directories
Apart from the main layers, the template has
- [`lib/foundation`](lib/foundation): Extentions on primitive data types, loggers, global type alias etc.
- [`lib/flavors`](lib/flavors): Flavor i.e. Environment reledated classes.
- [`lib/entrypoints`](lib/entrypoints): Target files for flutter to run for each flavor.
- [`lib/app.dart`](lib/app.dart): App initialization code.

## Understanding the Presentation Layer
The presentation layer houses all the visual components and state management logic.

The [`base`](lib/presentation/base) directory has all the resuable and common elements used as building blocks for the UI like common widgets, app theme data, exceptions, base view models etc.

### View Model
State Management is done using the [`riverpod`](https://riverpod.dev/) along with [`state_notifier`](https://pub.dev/packages/state_notifier). The class that manages state is called the `View Model`. 

Each `View Model` is a sub class of the `BaseViewModel`. The [`BaseViewModel`](lib/presentation/base/view_model_provider/base_view_model.dart) is a `StateNotifier` of [`ScreenState`](#screen-state). Along with the ScreenState it also exposes a stream of [`Effect`](#effect). 

Implementations of the BaseViewModel can also choose to handle [`Intents`](#intent).

### Screen State
[`ScreenState`](lib/presentation/entity/screen/screen_state.dart) encapsulates all the state required by a [`Page`](#page). State is any data that represents the current situtation of a Page.

For example, the [`HomeScreenState`](lib/presentation/destinations/weather/home/home_screen_state.dart) holds the state required by the [`HomePage`](lib/presentation/destinations/weather/home/home_page.dart).

### Effect
[`Effects`](lib/presentation/entity/effect/effect.dart) are events that take place on a page that are not part of the state of the screen. These usually deal with UI elements that are not part of the widget tree.

Showing a snackbar or hiding the keyboard are examples of an effect.


### Intent
Intent is any action takes place on page. It may or may not be user initiated. 

[`SearchScreenIntent`](lib/presentation/destinations/weather/search/search_screen_intent.dart) has the actions that can happen on the [`SearchPage`](lib/presentation/destinations/weather/search/search_page.dart).

### Page
A page is a widget that the navigator can navigate to. It should return the [`BasePage`](lib/presentation/base/page/base_page.dart) widget. 

The `BasePage` creates the structure for the page, initialises the [`ViewModel`](#view-model) and provides the view model in the widget tree so that all the children have access to it. It also listens to the effects from the view model and notifies the page about it.

Each page accepts the [`Screen`](#screen) object as input.

### Screen
A [`Screen`](lib/presentation/entity/screen/screen.dart) is a class that represents a `Page` in the context of navigation. It holds the `path` used by the navigator to navigate to a `Page` and also holds any arguments required to navigate to that `Page`.

## Flavors
The template comes with built-in support for 3 flavors. Each flavor uses a diffrent `main.dart` file.
- Dev - [`main_dev.dart`](lib/entrypoints/main_dev.dart)
- QA - [`main_qa.dart`](lib/entrypoints/main_qa.dart)
- Prod - [`main_prod.dart`](lib/entrypoints/main_prod.dart)

You can setup any environment specific values in the respective `main.dart` files.

To run a specific falvor you need to specify the flavor and target file.
```shell
 flutter run --flavor qa -t lib/entrypoints/main_qa.dart
```

**To avoid specifying all the flags every time, use the [`run.sh`](scripts/README.md#run) script**

Read the [scripts documentation](scripts/README.md) to learn about all the scrips used in the project.
 
## Content
The Flutter Template contains:
- A [`Flutter`](https://flutter.dev/) application.
- Built-in support for 3 [`flavors`](https://docs.flutter.dev/deployment/flavors) - `dev`, `qa` and `prod`.
- A [`reactive base architectire`](#architecture) for your application.
- [`Riverpod`](https://riverpod.dev/) along with [`state_notifier`](https://pub.dev/packages/state_notifier) for state management.
- [`Drift`](https://drift.simonbinder.eu/) as local database for storage.
- [`Dio`](https://github.com/flutterchina/dio) for making API calls.
- [`Freezed`](https://pub.dev/packages/freezed) for data class functionality.
- [`Get It`](https://pub.dev/packages/get_it) for dependency injection.
- [`Flutter Lints`](https://pub.dev/packages/flutter_lints) for linting.
