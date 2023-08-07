# RandomUsers
Assignment application to get the random users from https://randomuser.me/api

# Requirement
## Goals
Create a universal phone / tablet application while respecting good Android development practices (Kotlin / Java).

The application includes 2 screens:
- A main screen with a list of items in the form of a list.
- A detail view.

When clicking on a list item, the view with more details on the selected item is displayed.

On the first screen, in order to facilitate development, we just keep these properties: User name, first name and image. When we click on it, we will display the job and the email address again in the new screen (passing parameters).

## Instructions
- The use of external lib / frameworks is allowed and highly recommended.
    - RetroFit, Dagger, Picasso, DataBinding, RxAndroid, ...
    - Implementation of a clear architecture: Clean Architecture, MVP, MVC, ...
- Comply with the ergonomic rules defined in the Android guidelines (Material Design). However, it is not necessary to have a complex interface.
- Depending on the progress in the test and depending on the profile you can replace part of the objective with one of the following elements:
    - Add an offline mode in the application
    - Use Firebase, Realm, ... in the app
    - Add buttons to remove / add new item in list.
    - Add pagination in the application.

It's not mandatory, I content myself with a few comments explaining at the low level what we could do later and which method of which lib we would use.

## API
-Random Users:
- https://randomuser.me/api (REST/JSON)
- https://randomuser.me/documentation
We will take 10 users.

## Common validation points:
- Using the builder interface of Android Studio
- Image / graphics management with Android Studio
- Buttons / Actions / Layouts, ...
- Layouts: Linear, Relative, Constraints, ...
- Permissions management (Camera, Location, Bluetooth, …)
- Intents
- Background Tasks: Threads, Background services, ...
- Adb Tools, debugging (breakpoints, step by step, ..)
- Firebase (or explain in comment how later you can integrate it and where, crashlytics? analytics? ...),
- Testing (Mockito, JUnit, Espresso, )
- ...

## Validation of JAVA notions (to ignore if you are full kotlin and it's better):
- Last version used.
- Lambda...