# Android app starter template

## Features

- Clean architecture with 3 layers
    - Data (for models, database, API and preferences)
    - Domain (for business logic)
    - Presentation (for UI logic, with MVVM)
- Tests
    - Unit tests
    - Application tests
    - Activity tests (with [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/))
    - Application has a testing flag
- Dependency injection (with [Dagger v2](http://google.github.io/dagger/))
- Reactive programming with Kotlin Flows
- Preconditions
- Google Material Design library
- Android architecture components to share ViewModels during configuration changes
- Logging (with [Timber](https://github.com/JakeWharton/timber))
- Resource defaults
    - themes.xml
    - colors.xml

## Getting started

1. Download this repository and open it on Android Studio
1. Rename the app package `io.bloco.template`
1. On `app`, change the package on the `mode` lazy var
1. On `app/build.gradle`, change the applicationId to the new app package
1. On `app/build.gradle`, update the dependencies Android Studio suggests
1. On `string.xml`, set your application name 
1. On `theme.xml` & `theme.xml` set your application primary and secondary colors

And you're ready to start working on your new app.

## Notes

- Make sure the method `checkTestMode` inside `AndroidApplication` includes a test class
  that exists

## To Do

- Timber Logs
- Hilt
- Gesture Navigation
- Real World Example Application Branch
