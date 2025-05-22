# Retrospective: Web Tables Automation Testing Suite

## What Went Well
* I wrote 48 automated tests, and they all passed successfully.

* The test suite runs on both Chrome and Firefox, supporting cross-browser validation.

* I used the Page Object Model (POM) pattern, which helped organize the code and made it reusable and easier to maintain.

* I configured tests to run locally and remotely using a GRID_URL environment variable, which made it easy to switch between local runs and Selenium Grid execution.

* I reused common values by defining constants, which improved code readability and reduced duplication.


## What Was Challenging
* I had to fix flaky tests by adding explicit waits (synchronization) to avoid failures caused by timing issues.

* I had to remove or block ads on the demoqa.com website, because the ads were covering important elements like Delete, Edit, Search, and Submit buttons — which prevented Selenium from clicking or interacting with them correctly.


## What I Learned
I learned how to use JavaScriptExecutor to scroll and click elements in Selenium, which was useful when standard .click() didn’t work due to ads or hidden elements.


## What Could Be Improved
Connect the test suite to CI/CD with GitHub Actions
