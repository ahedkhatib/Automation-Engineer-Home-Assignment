package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebTablesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "addNewRecordButton")
    private WebElement addEmployeeButton;

    @FindBy(css = ".rt-tbody .rt-tr-group")
    private List<WebElement> rows;

    @FindBy(id = "searchBox")
    private WebElement searchBox;

    public WebTablesPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public RegistrationFormPage addEmployee(){
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeButton));
        clickWithJS(addEmployeeButton);
        return new RegistrationFormPage(driver);
    }

    public WebTablesPage searchByKeyword(String keyword) {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(keyword);
        return this;
    }

    public List<String> getAllNamesInTable() {
        waitForTable();
        List<String> names = new ArrayList<>();

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector(".rt-td"));
            if (!cells.isEmpty()) {
                String name = cells.getFirst().getText().trim();
                if (!name.isEmpty()) {
                    names.add(name);
                }
            }
        }
        return names;
    }

    public int countMatchingRecords(String firstName, String lastName, String age, String email, String salary, String department) {
        waitForTable();
        int count = 0;

        for (WebElement row : rows) {
            if (isMatchingRow(row, firstName, lastName, age, email, salary, department)) {
                count++;
            }
        }

        return count;
    }

    public RegistrationFormPage editEmployee(String firstName, String lastName, String age, String email, String salary, String department) {
        waitForTable();

        for (WebElement row : rows) {
            if (isMatchingRow(row, firstName, lastName, age, email, salary, department)) {
                WebElement editBtn = row.findElement(By.cssSelector("span[title='Edit']"));
                clickWithJS(editBtn);
                return new RegistrationFormPage(driver);
            }
        }

        return null;
    }

    public WebTablesPage deleteRecord(String firstName, String lastName, String age, String email, String salary, String department) {
        waitForTable();

        for (WebElement row : rows) {
            if (isMatchingRow(row, firstName, lastName, age, email, salary, department)) {
                WebElement deleteBtn = row.findElement(By.cssSelector("span[title='Delete']"));
                clickWithJS(deleteBtn);
                return this;
            }
        }

        return null; // no matching record found
    }

    // ----- Helpers -----

    private boolean isMatchingRow(WebElement row, String firstName, String lastName, String age, String email, String salary, String department) {
        List<WebElement> cells = row.findElements(By.cssSelector(".rt-td"));
        if (cells.size() < 6) return false;

        return cells.get(0).getDomProperty("textContent").equals(firstName) &&
                cells.get(1).getDomProperty("textContent").equals(lastName) &&
                cells.get(2).getDomProperty("textContent").equals(age) &&
                cells.get(3).getDomProperty("textContent").equals(email) &&
                cells.get(4).getDomProperty("textContent").equals(salary) &&
                cells.get(5).getDomProperty("textContent").equals(department);
    }

    private void clickWithJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private void waitForTable() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".rt-tbody .rt-tr-group")));
    }
}

