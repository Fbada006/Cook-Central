# Cook-Central

Cook Central is my capstone project in the Android Developer Nanodegree Program by Udacity. It is an app
that helps food lovers (and anyone really) to discover food recipes, save their favourite recipes,
and search for recipes based on the ingredients they have.

## Prerequisite

minSdkVersion -> 19

Gradle build system

This project uses firebase for analytics. You do not need to create any project on your end unless you
want to see the analytics of the app. Otherwise, everything is included here. The project also
includes the necessary files for signing it.


## TOC

- [Architecture](#architecture)
- [Flow](#flow)
- [Libraries](#libraries)
- [License](#license)

## Architecture


The presentation layer handles the UI work with the logic contained in the **ViewModel**.
The UI uses **LiveData** objects from the ViewModel and observes it using the **Observer Pattern**.
A ListAdapter handles the actual displaying of the news. Data over the network is retrieved using
**retrofit** and **RxJava** to handle background work asynchronously.
The data layer uses the recommended **Repository Pattern** to make the network calls and store the data using
**Room DB**. This layer is also responsible for mapping the entities that eventually get displayed in the UI.

## Flow

 **Main Recipe Screen**

  This screen is visible to the user once the app is launched and immediately shows a list of random
  recipes. Once clicked, the user is taken to the details screen where they have the option of
  marking a recipe as favourite or not.

 **Search and Favourites Screen**

 These two screens are accessible via the BottomNavigationView.


## Libraries

This app takes use of the following libraries:

- [Jetpack](https://developer.android.com/jetpack)🚀
  - [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI data to survive configuration changes and is lifecycle-aware
  - [Data Binding](https://developer.android.com/topic/libraries/data-binding) - Declaratively bind observable data to UI elements
  - [Navigation](https://developer.android.com/guide/navigation/) - Handle everything needed for in-app navigation
  - [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) - Manage your Android background jobs
  - [Room DB](https://developer.android.com/topic/libraries/architecture/room) - Fluent SQLite database access
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Notify views when underlying database changes
  - [Retrofit](https://square.github.io/retrofit/) - type safe http client with RxJava support
  - [Gson](https://github.com/google/gson) - A Java serialization/deserialization library to convert Java Objects into JSON and back
  - [RxJava2](https://github.com/ReactiveX/RxJava) - a library for composing asynchronous and event-based programs using observable sequences for the Java VM.
  - [Material Design](https://material.io/develop/android/docs/getting-started/) - build awesome beautiful UIs.🔥🔥
  - [Firebase](https://firebase.google.com/) - Backend As A Service for faster mobile development.
  - [Crashylitics](https://firebase.google.com/docs/crashlytics) - Provide Realtime crash reports from users end.
  - [Like Button](https://github.com/jd-alexander/LikeButton) - Twitter-like heart animation for Android
  - [Glide](https://github.com/bumptech/glide) - An image loading and caching library for Android focused on smooth scrolling


## License

 ```
   Copyright 2020 Ferdinand Bada

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 ```