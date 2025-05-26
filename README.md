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

## Installation & Execution Steps

Follow these simple steps to set up and run the test suite for this project, even if you're not a developer:

### 1. Clone the Repository

Open your terminal or command prompt and run:

```bash
git clone https://github.com/ahedkhatib/Automation-Engineer-Home-Assignment.git
cd Automation-Engineer-Home-Assignment
```

### 2. Install Dependencies

Use Maven to download and install all necessary libraries:

```bash
mvn clean install
```

### 3. Set Up Chrome or Firefox

- If you're using **Google Chrome**: Download ChromeDriver from [https://chromedriver.chromium.org/downloads](https://chromedriver.chromium.org/downloads)
- If you're using **Mozilla Firefox**: Download Firefox from [https://www.mozilla.org/en-US/firefox/new/](https://www.mozilla.org/en-US/firefox/new/)

Make sure the driver is added to your system's **PATH** or placed in the project root directory.

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

## Test Suites

- **Test suite for [Add a New Employee Record](https://docs.google.com/spreadsheets/d/1X9aiRBHGTIJhJaTmLXAabWVwY2EG4KApkRJcGIguG-s/edit#gid=0)**  
  This suite verifies that a new employee can be added and appears correctly in the web table.

- **Test suite for [Delete a Record](https://docs.google.com/spreadsheets/d/1YqfZT-OUPsxhgEQNfBK84jXJw20cbswqXDwEWpX6Rt0/edit#gid=0)**  
  This suite verifies that a deleted employee record is no longer displayed in the table.

- **Test suite for [Edit a Record’s Salary](https://docs.google.com/spreadsheets/d/1AEaxAQnwae4QNWixamdKRVSezYKPqCjC1lq9zRYr0a0/edit#gid=0)**  
  This suite verifies that updating the salary of an existing employee is correctly reflected in the table.

## Test Reports
A total of 48 automated tests were implemented and executed successfully. 
All tests passed, confirming that the core functionalities of the web table interface work as expected.

A test report is also available in **GitHub Actions** under the **UI testing** workflow.  
You can view it in the **Actions** tab of the repository after each test run.

[GitHub Actions - UI testing workflow](https://github.com/ahedkhatib/Automation-Engineer-Home-Assignment/actions)

## Viewing Test Results with Allure

To visualize your test results using Allure:

1. **Install Allure** (if not already installed):  
   Follow the setup guide here → [https://allurereport.org/docs/install/](https://allurereport.org/docs/install/)

2. **Generate and open the report** by running the following command after your tests complete:

```bash
allure serve
```

This will create a clean HTML report and open it automatically in your default browser.