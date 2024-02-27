
[![CircleCI](https://circleci.com/gh/petermwash/WeatherDaily/tree/develop.svg?style=svg)](https://circleci.com/gh/petermwash/WeatherDaily/tree/develop)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/2cbb0ce1dbde4e409ae1a95c18466b8c)](https://www.codacy.com/gh/petermwash/WeatherDaily/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=petermwash/WeatherDaily&amp;utm_campaign=Badge_Grade)


# Weather Daily

 - `Weather Daily` is a weather application that keeps users updated on the current status of weather at their current location. 
 
- It also gives the next five days' weather forecast for the same location. 
 
- It has the capability of displaying weather data even in offline mode with a dismissable notification informing the user on the last the data was updated from the [API](https://openweathermap.org/)



## Getting Started and Installation

1. Clone this repository to your local machine.
   `git clone https://github.com/petermwash/WeatherDaily.git`

2. Open the project in Android Studio; under the file menu select open, then select an existing project. Navigate to the folder you cloned then open the project.

4. Get an `API KEY` from [openweathermap.org](https://openweathermap.org/), create a file named `apikey.properties` to the root folder, and add the `API KEY` to the file.

5. Run the app on an emulator or on your Android device.

Enjoy the app 😄.



## Running the tests

1. Use these commands to run tests on your terminal
   `./gradlew test` or `./gradlew testDebugUnitTestCoverage`.



## Some of the tools used

1. [Kotlin](https://kotlinlang.org/) As a programing language

2. [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) For dependency injection

3. [Kotlin coroutines](https://developer.android.com/kotlin/coroutines) For asynchronous programming

4. [Kotlin flows](https://developer.android.com/kotlin/flow) For reactive programming
   
5. [Retrofit](https://square.github.io/retrofit/) For for my HTTP client

6. [Room](https://developer.android.com/jetpack/androidx/releases/room) As a persistence for SQLite database

etc.



## Architecture

The app follows an [MVVM](https://developer.android.com/jetpack/guide) architecture pattern



## APIs

The app consumes two APIs from [openweathermap.org](https://openweathermap.org/)

1. `https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}` for fetching current weather data and

2. `https://api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&appid={API key}` for fetching five days forecast weather data.
