# Android app starter template

## Features

- Clean architecture with 3 layers
    - Data (for models, database, API and preferences)
    - Domain (for business logic)
    - Presentation (for views and presenters)
- Tests
    - Unit tests
    - Application tests
    - Activity tests (with [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/))
    - Application has a testing flag
- Dependency injection (with [Dagger v2](http://google.github.io/dagger/))
- View injection (with [Butterknife](http://jakewharton.github.io/butterknife/))
- Preconditions
- Google Design library
- Logging (with [Timber](https://github.com/JakeWharton/timber))
- Resource defaults
    - styles.xml
    - dimens.xml
    - colors.xml

## Getting started

1. Clone this repository and open it on Android Studio
1. Rename package ```io.bloco.template```
1. On app/build.gradle, replace dependencies with ```+``` for their most recent versions
1. Set your application name on string.xml
1. Set your application primary and secondary colors on colors.xml

And you're ready to start working on your new app.

## Notes

- Make sure the method ```checkTestMode``` inside ```AndroidApplication``` includes a test class
  that exists

## To Do

- Analytics (Google Analytics and Answers, at least)
- Crashlytics
- SharedPreferences helper
- Manifest
    - backup.xml
    - ACTION_VIEW index
