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

public class EditRecordSalaryTest {

    private static final String BASE_URL = "https://demoqa.com/webtables";

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = getDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    private void editSalaryAndSubmit(String newSalary) {
        new WebTablesPage(driver)
                .editEmployee(NAME, LAST_NAME, AGE, EMAIL, INITIAL_SALARY, DEPARTMENT)
                .editSalary(newSalary)
                .submitForm();
    }

    private boolean assertFormRemainsOpen(String salaryInput) {
        RegistrationFormPage form = new WebTablesPage(driver)
                .editEmployee(NAME, LAST_NAME, AGE, EMAIL, INITIAL_SALARY, DEPARTMENT)
                .editSalary(salaryInput);
        form.submitForm();
        return form.isFormStillOpen();
    }

    @Test
    public void testEditEmployeeSalary() {
        editSalaryAndSubmit(NEW_SALARY);
        int actualResult = new WebTablesPage(driver)
                .countMatchingRecords(NAME, LAST_NAME, AGE, EMAIL, NEW_SALARY, DEPARTMENT);
        assertEquals(1, actualResult);
    }

    @Test
    public void testEditInValidSalary() {
        boolean actualResult = assertFormRemainsOpen(INVALID_SALARY);
        assertTrue(actualResult);
    }

    @Test
    public void testLongSalary() {
        String truncatedLongSalary = LONG_SALARY.substring(0, 10);
        editSalaryAndSubmit(LONG_SALARY);
        int actualResult = new WebTablesPage(driver)
                .countMatchingRecords(NAME, LAST_NAME, AGE, EMAIL, truncatedLongSalary, DEPARTMENT);
        assertEquals(1, actualResult);
    }

    @Test
    public void testLettersSalary() {
        boolean actualResult = assertFormRemainsOpen(NON_NUMERIC_SALARY);
        assertTrue(actualResult);
    }

    @Test
    public void testZeroSalary() {
        editSalaryAndSubmit(ZERO_SALARY);
        int actualResult = new WebTablesPage(driver)
                .countMatchingRecords(NAME, LAST_NAME, AGE, EMAIL, ZERO_SALARY, DEPARTMENT);
        assertEquals(1, actualResult);
    }

    @Test
    public void testEmptySalary() {
        boolean actualResult = assertFormRemainsOpen(EMPTY_SALARY);
        assertTrue(actualResult);
    }

    @Test
    public void testSalaryWithSpaces() {
        boolean actualResult = assertFormRemainsOpen(SPACED_SALARY);
        assertTrue(actualResult);
    }

    @Test
    public void testSalaryWithSpecialChar() {
        boolean actualResult = assertFormRemainsOpen(SPECIAL_CHAR_SALARY);
        assertTrue(actualResult);
    }

    @Test
    public void testEditSameSalary() {
        editSalaryAndSubmit(INITIAL_SALARY);
        int actualResult = new WebTablesPage(driver)
                .countMatchingRecords(NAME, LAST_NAME, AGE, EMAIL, INITIAL_SALARY, DEPARTMENT);
        assertEquals(1, actualResult);
    }

    @Test
    public void testEditMultipleTimes() {
        WebTablesPage page = new WebTablesPage(driver);
        page.editEmployee(NAME, LAST_NAME, AGE, EMAIL, INITIAL_SALARY, DEPARTMENT)
                .editSalary(MULTI_EDIT_SALARY_1)
                .editSalary(MULTI_EDIT_SALARY_2)
                .editSalary(MULTI_EDIT_SALARY_3)
                .submitForm();

        int actualResult = page.countMatchingRecords(NAME, LAST_NAME, AGE, EMAIL, MULTI_EDIT_SALARY_3, DEPARTMENT);
        assertEquals(1, actualResult);
    }

    @Test
    public void testSalaryWithLeadingZeros() {
        editSalaryAndSubmit(LEADING_ZEROS_SALARY);
        int actualResult = new WebTablesPage(driver)
                .countMatchingRecords(NAME, LAST_NAME, AGE, EMAIL, LEADING_ZEROS_SALARY, DEPARTMENT);
        assertEquals(1, actualResult);
    }

    @Test
    public void testEditNonExistingRecord() {
        RegistrationFormPage formPage = new WebTablesPage(driver)
                .editEmployee(FAKE_NAME, FAKE_LAST_NAME, FAKE_AGE, FAKE_EMAIL, FAKE_SALARY, FAKE_DEPT);
        assertNull(formPage);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}


