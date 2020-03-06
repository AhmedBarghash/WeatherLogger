# WeatherLogger
Android application to save weather conditions for your current location.

# Project setup
Clone the repo, open the project in Android Studio, hit "Run". Done!

### Verison
Android Studio : 3.6 ,
kotlin : 1.3.61 ,
Gradle : 3.6.1

The application is compatibility with Android 4.1 and onwards
# Architecture overview

The app is a client: where operations are performed by calling API endpoints over the network and local data is in effect mutable. Local data is only modified as a result of user request.
The app client must have location permission otherwise the API calls will not affect  local data

The domain model objects are used throughout the app. They are plain
Kotlin/Java objects. They should not be directly tied to persistence
details, neither should they be directly tied to network api
details. The persistence/network layer translates to the
local domain model as needed, the rest of the app should not have to
know about those implementation details.

Activities are for presentation logic only. Each
activity should have its own presenter where business
logic is placed. The presenter reacts to data changes via the event
bus, and tells the activity how to update itself.

TODO: this works, what we should replace the VIPER design pattern into MVVM: this will solve an issue We faced through development. The issue was passing the application context throw the Presenter -> Interactour -> AppDataManager to save data in using SQLite, which is not what VIPER aim to.

# Version control workflow

We loosely use the "Git flow" approach: master is the release 
branch - it should always be releasable, and only merged into 
when we have tested and verified that everything works and is 
good to go. 

Daily development is done in the development branch locally. Features, 
bugfixes and other tasks are done as branches off of develop, 
then merged back into develop directly or via pull requests

