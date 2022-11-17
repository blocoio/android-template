 <img src="images/logo.png" alt="ArchiTecture logo"/>

[![test](https://github.com/blocoio/android-template/workflows/test/badge.svg?branch=master)](https://github.com/blocoio/android-template/actions?query=workflow%3Atest+branch%3Amaster)
[![lint](https://github.com/blocoio/android-template/workflows/lint/badge.svg?branch=master)](https://github.com/blocoio/android-template/actions?query=workflow%3Alint+branch%3Amaster)

The goal of this Template is to be our starting point for new projects, following the best development practices. It's our interpretation and adaptation of the official [architecture](https://developer.android.com/topic/architecture) guidelines provided by Google. And it's inspired by Google's [NowInAndroid](https://github.com/android/nowinandroid).

## Clean architecture with 3 main modules
- Data (for database, API and preferences code)
- Domain (for business logic and models)
- AndroidApp (for UI logic, with MVVM)

 <img src="images/AndroidTemplate-CleanArchitecture.jpg" alt="ArchiTecture logo"/>

## Tests
- [Mockk](https://mockk.io/) library 
- Unit tests
- Application tests
  - example on how to work with tests
- Activity tests (with [Compose Testing](https://developer.android.com/jetpack/compose/testing))
  - example on how to work with coroutine scopes in tests
    
## Other useful features
- This version brings [Modularization](https://developer.android.com/topic/modularization). With that also came:
- [Version catalog](https://docs.gradle.org/current/userguide/platforms.html) type safe list of dependencies
- [Convention plugins](https://docs.gradle.org/current/samples/sample_convention_plugins.html) to share build logic between modules.
- Dependency injection (with [Hilt](http://google.github.io/hilt/))
- Network calls (with [Ktor](https://ktor.io/docs/http-client-engines.html#minimal-version))
- Reactive programming with [Kotlin Flows](https://kotlinlang.org/docs/reference/coroutines/flow.html)
- Android architecture components to share ViewModels during configuration changes
- Google [Material Design](https://material.io/blog/android-material-theme-color) library
- UI with [Jetpack Compose](https://developer.android.com/jetpack/compose)
- Compose Navigation (with [Hilt Support](https://developer.android.com/jetpack/compose/libraries#hilt-navigation) and Assisted Inject Example)
- Edge To Edge Configuration
- [Splash Screen](https://developer.android.com/develop/ui/views/launch/splash-screen) Support

# Getting started

1. Download this repository extract and open the template folder on Android Studio
2. Rename the app package `io.bloco.template`
3. Check if the manifest package was renamed along with the package
4. Replace composables with the Template name
5. On `androidApp/build.gradle`, change the applicationId to the new app package
6. On `androidApp/build.gradle`, update the dependencies Android Studio suggests
7. On `string.xml`, set your application name

**If you use the Project NewProjectCI all the above steps are done by you automatically**

8. On `Theme.kt` & `Color.kt` set your application style
9. Replace the App Icons
10. Delete unwanted example files
11. Run `./gradlew dependencyUpdates` and check for dependencies
12. Ready to Use

And you're ready to start working on your new app.

# Notes
- Android Template contains `.github/workflows` for lint check, unit testing and dependency checks. You can easily take this project worflow and repurpose it with a few path changes, you can also find a commented example in test.yml for Instrumentation Testing and CodeCoverage that we advice to keep a clean project, you will however need to replace the secret keys with your own.
