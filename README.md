# Weathy
A Demo weather app utilizing openweathermap.org api to show weather

**Features**:
1. Fetches weather by location.
2. Shows current weather and forecast for the next day.
3. Search bar to search and check weather for any US city.
4. Saves the last searched city and auto-loads it on the next app start. If not then uses the current location as specified above (1).

**Project composition**:
1. Data -> Which is the data classes where we handle the data.
2. DI -> Dependency injection implementation.
3. Domain -> Where we keep the data repositories and its implementation to provide the data as we like to the view.
4. Presentation -> Not to be confused with Presenter from MVP. This is just a name used for the view layer where we present/show
                   the data on the UI to the end user.

**Some Details**:
1. Project uses two api from openweather. One is used for the current weather and the other on is used for a forecast of weather.
          I wanted to show weather forecast for the day by hour but its a paid api and in free you only get forecast for every 4
          hours and it could not be filtered by date so i just showed forecast for the next day. Since i could show all the hours at once. 
2.  Project has a big js file which consists of almost all the cities in US along with their state to which they belong.
          This was basically used for the search function. My idea was to decrease the API usage by avoiding wrong city names to be entered.
         Also , free api of openweather is allowed to do limited no of network calls (i think 60 is the magic number). This also allows the
         user to see the cities in a nice suggestion box layout.
3. Uses preferences to save and fetch the last searched location.
4. Shows weather by the api returned weather codes.

5. Custom icons and app icon used.

**Some Improvements**:
1. We can use local db such as room db to decrease api calls and save the weather details fetching it in an interval or on app start only.
   Also we can tell room to update the database if values changed or don't bother.
2. We can a better api for hourly forecast which we can use to show current days forecast and also for the next day as well just like weather on google.
3. For bigger app and more images we can use image loading lib such as coil to load image directly off the network and decrease the
   app package size by removing drawable resources.
4. We can avoid using the local js file for the US cities to allow searching cities in other international countries as well if needed.
5. Animated weather icons to improve the UI even further. I personally wanted to use it but it was not high on my priority list at the end

**Tools/Resources used**:

| Type              | Used                 | Comments                                                                          |
|-------------------|----------------------|-----------------------------------------------------------------------------------|
| Coding Language   | Kotlin               | There is a js file due to which the project shows over 80% in js on repo page. That js file contains all the cities in US to search for.                        |
| Architecture      | MVVM                 |  Clean architechture where models are the data clases, seperate viewmodel and the presentation (just a name used and not MVP) view layer  .                        |
| Network Library   | Retrofit             |                                                                                   |
| Unit Test         | JUnit/Espresso       |                                                                                   |
| Network-Concurrency|Kotlin Coroutines    |                                                                                   |
| UI                |Jetpack Compose       |                                                                                   |
| Dependency Inject.|Dagger 2, Hilt        |                                                                                   |
