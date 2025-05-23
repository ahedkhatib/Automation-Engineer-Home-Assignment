# Web Tables Automation Testing Suite

## Project Goal
This project automates the testing of a web table management interface located at demoqa.com/webtables. The system supports key operations such as adding, editing, deleting, and searching employee records. The purpose of the automation suite is to ensure the reliability of these core functionalities using Selenium WebDriver and Java.

## Features & Test Scenarios
This automated test suite covers the following scenarios:

1- Add Record Test — Adds a new employee and verifies its presence in the table.

2- Edit Record Salary Test — Modifies an existing employee's salary and checks the updated value.

3- Delete Record Test — Deletes a record and ensures it is no longer visible.

4- Search Record Test — Filters the table by name or email and checks relevant results.

## Tech Stack
Language: Java 21

Build Tool: Maven

Test Framework: JUnit 5

Automation Tool: Selenium WebDriver

Browser: Chrome / Firefox

## How to Run Tests
## Prerequisites
Java 21

Maven installed and configured

Google Chrome and/or Mozilla Firefox

** The test suite supports both Chrome and Firefox. You can configure the browser in the DriverFactory.java file or pass it as an environment variable.

### ** Steps to Run the Tests**

#### Option 1: Run with Maven (default)

This will execute the test suite using the default browser specified in `DriverFactory.java`:

```bash
mvn clean test
```

#### Option 2: Run with Specific Browser

You can override the browser setting by passing an environment variable before running Maven:

```bash
BROWSER=chrome mvn clean test
```

or

```bash
BROWSER=firefox mvn clean test
```

This allows switching between browsers without modifying any code.

#### Option 3: Run on Selenium Grid

First, download the Selenium Server standalone JAR from the [official Selenium page](https://www.selenium.dev/downloads/), then start the grid:

```bash
java -jar selenium-server-<version>.jar standalone
```

After that, you can run your tests remotely by setting the grid URL and desired browser:

```bash
GRID_URL=http://localhost:4444 BROWSER=firefox mvn clean test
```

## Dependencies
All dependencies are managed via Maven. Relevant libraries include:

selenium-java: 4.27.0

junit-jupiter-engine: 5.9.0

These are declared in the pom.xml file.

## Test Reports
A total of 48 automated tests were implemented and executed successfully. 
All tests passed, confirming that the core functionalities of the web table interface work as expected.
