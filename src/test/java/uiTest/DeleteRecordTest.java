package uiTest;

import org.example.RegistrationFormPage;
import org.example.WebTablesPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.example.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.*;
import static org.example.TestConstants.*;

public class DeleteRecordTest {
    private static final String BASE_URL = "https://demoqa.com/webtables";


    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = getDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    private void addEmployee(String name, String lastName, String age, String email, String salary, String dept) {
        WebTablesPage page = new WebTablesPage(driver);
        RegistrationFormPage form = page.addEmployee();
        form.fillForm(name, lastName, age, email, salary, dept);
        form.submitForm();
    }

    @Test
    public void testDeleteRecord() {
        int actualResult = new WebTablesPage(driver)
                .deleteRecord(NAME_CIERRA, LAST_NAME, AGE, EMAIL, SALARY, DEPARTMENT)
                .countMatchingRecords(NAME_CIERRA, LAST_NAME, AGE, EMAIL, SALARY, DEPARTMENT);

        assertEquals(0, actualResult);
    }

    @Test
    public void testDeleteNonExisting() {
        WebTablesPage page = new WebTablesPage(driver)
                .deleteRecord("Amal", LAST_NAME, AGE, EMAIL, SALARY, DEPARTMENT);

        assertNull(page);
    }

    @Test
    public void testDeleteDuplicateRecord() {
        addEmployee(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT);
        addEmployee(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT);

        int actualResult = new WebTablesPage(driver)
                .deleteRecord(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT)
                .countMatchingRecords(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT);

        assertEquals(1, actualResult);
    }

    @Test
    public void testDeleteAllDuplicateRecords() {
        addEmployee(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT);
        addEmployee(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT);

        int actualResult = new WebTablesPage(driver)
                .deleteRecord(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT)
                .deleteRecord(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT)
                .countMatchingRecords(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT);

        assertEquals(0, actualResult);
    }

    @Test
    public void testDeleteAllRecords() {
        WebTablesPage page = new WebTablesPage(driver)
                .deleteRecord(NAME_CIERRA, LAST_NAME, AGE, EMAIL, SALARY, DEPARTMENT)
                .deleteRecord(NAME_ALDEN, LAST_NAME_ALDEN, AGE_ALDEN, EMAIL_ALDEN, SALARY_ALDEN, DEPT_ALDEN)
                .deleteRecord(NAME_KIERRA, LAST_NAME_KIERRA, AGE_KIERRA, EMAIL_KIERRA, SALARY_KIERRA, DEPT_KIERRA);

        int actualResult = page.getAllNamesInTable().size();

        assertEquals(0, actualResult);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

