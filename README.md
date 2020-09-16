 <img src="images/logo.png" alt="ArchiTecture logo"/>

![Tests status](https://github.com/envelop-app/envelop-android/workflows/test/badge.svg) 
![Lint status](https://github.com/envelop-app/envelop-android/workflows/lint/badge.svg)

## Clean architecture with 3 layers
- Data (for database, API and preferences code)
- Domain (for business logic and models)
- Presentation (for UI logic, with MVVM)

 <img src="images/AndroidTemplate-CleanArchitecture.png" alt="ArchiTecture logo"/>

## Tests
- Unit tests
- Application tests
- Activity tests (with [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/))
- Application has a testing flag
    
## Other useful features
- Dependency injection (with [Hilt](http://google.github.io/hilt/))
- Reactive programming with [Kotlin Flows](https://kotlinlang.org/docs/reference/coroutines/flow.html)
- Google [Material Design](https://material.io/blog/android-material-theme-color) library
- Logging (with [Timber](https://github.com/JakeWharton/timber))
- Android architecture components to share ViewModels during configuration changes
- Edge To Edge Configuration
- Resource defaults
    - themes.xml - app themes
    - themes.xml (v29) - app themes (for better edgeToEdge)
    - colors.xml - colors for the entire project
    - styles.xml - widget styles 
    - styles-text.xml - text appearances

# Getting started

1. Download this repository extract and open the template folder on Android Studio
2. Rename the app package `io.bloco.template`
3. Check if the manifest package was renamed along with the package
4. On `app`, change the package on the `mode` lazy var
5. On `app/build.gradle`, change the applicationId to the new app package
6. On `app/build.gradle`, update the dependencies Android Studio suggests
7. On `string.xml`, set your application name
8. On `theme.xml` & `colors.xml` set your application primary and secondary colors 

And you're ready to start working on your new app.

<br />

# Notes
- Make sure the method `checkTestMode` inside `AndroidApplication` includes a test class
  that exists
- Jetifier is enabled inside `gradle.properties`. Even though it's not necessary for the 
  current dependencies, it was left enabled so anyone can start using the template without 
  worrying when to turn it on. We do recommend you to check [Can I drop Jetifier?](https://github.com/plnice/can-i-drop-jetifier) 
  when expanding the template.
- Android Template contains `.github/workflows` for lint check and unit testing. You can easily take this project worflow and repurpose it with a few path changes, you can also find a commented example in test.yml for Instrumentation Testing and CodeCoverage that we advice to keep a clean project, you will however need to replace the secret keys with your own.

### 📝 To Do

- Rename package template Script
- Real world example application branch
