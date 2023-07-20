# Appium SS Project Template

## Tech Stack
- Java 11+
- Test NG 7+
- Maven 3+
- Appium-Java client 8.1.1+

## Setting up the project
- Make sure you have Appium-doctor installed and that Android testing requirements are met
- Make sure that you have and Android emulator  (Android 8.1) set in place
- Make sure you have Appium Inspector set and ready to use (tested by establishing connection with emulator)

## Running tests
### Running tests in local
Tests will target/create AppiumDriver in local
- `mvn clean test`
### Running tests over SauceLabs
Tests will target AppiumDriver in remote service
- `mvn clean test -Dremote -Dservice={remote driver provider}`
  - options for `-Dservice` are:
    - `sl` - SauceLabs (will run tests on SauceLabs)
    - `bs` - Browserstack (will run tests on Browserstack)
