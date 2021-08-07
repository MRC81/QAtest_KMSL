# QAtest_KMSL

## About
Here is my solution for the provided QA test task. Since the task was very specific, I've tried to do exactly as it was asked and without any additional verification.

## Known Problems and Notes
**Scenario 1:** the 'professional email' value that is randomly generated might not be accepted by the site, since I don't know the rules of verification of this value it will be the game of chances. The value is generated as *{first name}@{first 6 chars of the Company Name without spaces and non-alphabetic chars}.com*.

There was an idea to add logic to the test that will re-generate and submit the values several times in case they were not accepted on the 1st attempt but I gave it up for the sake of simplicity and in the assumption that the test should receive a valid input data on the 1st attempt. But of course, such logic can be added.

**Scenario 2:** it seems this scenario is either outdated or tricky :) the pop-up which appears on the 'Self-Services' page has no 'Copy Link' button and this pop-up covers the frame with the video which is probably expected at the end of the scenario. In this case, I decided to verify the presence of the pop-up and the frame with video on the 'Self-Services' page, click on the 'Click Here' button on the pop-up and check that it leads on the specific page.


## Tech Stack
* Java 11
* Gradle 6.8
* JUnit 5
* Selenide 5.23
* Allure 2.14


## How to run
Pre-requisites: to run the project you will need Java 11 or higher, Gradle installation is not necessary since the project utilizes a Gradle wrapper.
The project can be run with different browsers, including the latest versions of Chrome, FireFox, and Edge.

To run the project without specifying a desired browser (the Chrome will be used as a default one) just execute the following command in command-line from the project's root folder:
```
.\gradlew clean test (on Windows)
```
or
```
./gradlew clean test (on Linux)
```
To run the project with the specific browser execute the following command:

```
.\gradlew {browser} (on Windows)
```
or
```
./gradlew {browser} (on Linux)
```

where the {browser} is either 'chrome', or 'firefox', or 'edge', e.g.:
```
.\gradlew firefox (on Windows)
```

You can add additional browsers via the Gradle build task (see build.gradle file) and updating the SetUp class, if these browsers are supported by [Selenide](https://selenide.org/documentation.html), of course.

## Test results

After tests execution will be completed, you can check the results with the *index.html* from the **/build/reports/tests** folder or run Allure report dashboard from the **/build** folder by executing the following command:
```
allure serve
```
but this will require [installing Allure](https://docs.qameta.io/allure/#_installing_a_commandline) on your system in the first place. You will also need to add the Allure to your $PATH environment variable to be able to call it from the **/build** folder.