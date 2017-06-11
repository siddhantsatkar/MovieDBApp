# MovieDBApp
An Android application with Movies information. The app has mainly 5 features.

![alt text](http://imgur.com/STBY8mP.png)

1. See the list of Top Rated Movies of all time.

![alt text](http://imgur.com/n1DyrGE.png)

2. See the list of Now Playing movies.

![alt text](http://imgur.com/ESWqcRK.png)

3. Save the movies in database and delete them from database(by long pressing the item in the saved movies list).

![alt text](http://imgur.com/E36MjzJ.png)

![alt text](http://imgur.com/VG6zWSw.png)

4. Search for movies using movie name.

![alt text](http://imgur.com/GBaxu9n.png)

![alt text](http://imgur.com/wr4rWay.png)

5. See a couple of movie names and their details from last visited feature(Top Rated or Now Playing movies) in case of loss of internet

![alt text](http://imgur.com/HhbaJhO.png)
connection.

## Project Structure
The project has two parent folders. com.example.moviedbapphomesdotcom is for code for app and com.example.moviedbapphomesdotcom(androidTest) is for testing.
### com.example.moviedbapphomesdotcom
1. The application has 7 main parts. Main or home page, Top Movies, Now Playing, Search results, Saved movies, Movie details, and Connection loss page. 
2. All these parts have their own activities. 
3. Top Movies, Now Playing, Search results, and Saved movies show the result in a recyclerView, hence they will need their adapter. 
4. Saved movies as well as Movie details uses data connection since Movie details page has the option to save the movie and Saved movies page has the option to display and delete the saved movies.
5. Top Movies, Now Playing, Search results, Movie details use models and Asynctask to fetch JSON data from the TMDB API.
6. This contains several folders-
#### activities
Activities folder contains all the activities.
#### adapter
Adapters folder contains all the adapters required for recycler views.
#### data
Data folder contains classes to make connection with database.
#### models
Models folder contains all the model classes needed to make different objects.
#### sync
Sync folder conatins classes to parse JSON data as well as Asynctask classes to do background processesing in child threads.

### com.example.moviedbapphomesdotcom(androidTest)
Although this folder contains only 3 files, all the important tests are covered in these files. Just need to repeat the tests for other files. The files are-
#### CheckContextTest
This is a simple test to check the context of the project.
#### MainScreenTest
This test file tests 3 things.
1. App should be able to launch main screen.
2. The EditText textbox should display the correct text.
3. Validate the intent which is being passed when Top Movies button is pressed.
#### SavedMoviesRecyclerViewTest
This test file checks if the correct item is being clicked or not.

## Prerequisites
1. First you need to have installed Android Studio on your computer. You can download it from [here](https://developer.android.com/studio/index.html)
2. Download the project

```git clone https://github.com/siddhantsatkar/MovieDBApp.git```

3. After you download the project open Android Studio. From there choose Open an existing Android Studio project or File -> New -> Import Project..
4. Open the MovieDBApp folder that was just cloned from git.
5. Wait until Android Studio finishes building and indexing.
6. You will need your API key from TMDB website. You can get it from [here](https://www.themoviedb.org/)
7. Proceed to installation.

## Installation
1. In the MainActivity.java and SavedMoviesActivity.java files, add your API key on Line 18 and Line 32 respectively.
2. To run the app, click on the 'Run' icon or press Shift+F10. You will either need emulator or Android device connected to the system.
3. To run the tests, go to com.example.moviedbapphomesdotcom (androidTest). Right click on any file and select 'Run' option. 

## Environment
This project has been build with Android Studio 2.3.3 
SDK Build Tools version 25.0.2 
Minimum SDK version for the app is 16. 
Target SDK version for the app is 25. 
Movie DB is supported on phones and tablets.

## Libraries
### [Picasso](http://square.github.io/picasso/) for image loading and displaying.
### [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/) for running UI tests.

## Author
This project is developed by [Siddhant Jain](https://siddhantsatkar.wixsite.com/siddhantportfolio/projects)

