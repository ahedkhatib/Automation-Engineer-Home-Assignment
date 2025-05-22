package selenium;

import org.example.RegistrationFormPage;
import org.example.WebTablesPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.example.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.example.TestConstants.*;

public class AddRecordTest {
    private static final String BASE_URL = "https://demoqa.com/webtables";
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = getDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Test
    public void testAddValidEmployee() {
        WebTablesPage page = new WebTablesPage(driver);
        page.addEmployee()
                .fillForm(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT)
                .submitForm();

        int actualResult = page.countMatchingRecords(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT);
        assertEquals(1, actualResult);
    }

    @Test
    public void testFieldsMissing() {
        RegistrationFormPage formPage = new WebTablesPage(driver)
                .addEmployee()
                .fillForm(VALID_NAME, "", "", "", "", "");

        formPage.submitForm();
        assertTrue(formPage.isFormStillOpen());
    }

    @Test
    public void testAddDuplicateValidEmp() {
        WebTablesPage page = new WebTablesPage(driver);

        page.addEmployee()
                .fillForm(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT)
                .submitForm();

        page.addEmployee()
                .fillForm(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT)
                .submitForm();

        int actualResult = page.countMatchingRecords(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT);
        assertEquals(2, actualResult);
    }


    @Test
    public void testInValidEmail() {
        RegistrationFormPage formPage = new WebTablesPage(driver)
                .addEmployee()
                .fillForm(VALID_NAME, VALID_LAST_NAME, VALID_AGE, INVALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT);

        formPage.submitForm();
        assertTrue(formPage.isFormStillOpen());
    }

    @Test
    public void testWhitespaceName() {
        String nameWithSpace = "   Ahed";
        String lastWithSpace = "  kh  ";

        WebTablesPage page = new WebTablesPage(driver);
        page.addEmployee()
                .fillForm(nameWithSpace, lastWithSpace, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT)
                .submitForm();

        int actualResult = page.countMatchingRecords(nameWithSpace, lastWithSpace, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT);
        assertEquals(1, actualResult);
    }

    @Test
    public void testAddMixedCaseEmail() {
        String mixedEmail = "Ahed@FfF.CoM";

        WebTablesPage page = new WebTablesPage(driver);
        page.addEmployee()
                .fillForm(VALID_NAME, VALID_LAST_NAME, VALID_AGE, mixedEmail, VALID_SALARY, VALID_DEPARTMENT)
                .submitForm();

        int actualResult = page.countMatchingRecords(VALID_NAME, VALID_LAST_NAME, VALID_AGE, mixedEmail, VALID_SALARY, VALID_DEPARTMENT);
        assertEquals(1, actualResult);
    }

    @Test
    public void testNumInName() {
        String name = "Ahed123";

        WebTablesPage page = new WebTablesPage(driver);
        page.addEmployee()
                .fillForm(name, "Kh", VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT)
                .submitForm();

        int actualResult = page.countMatchingRecords(name, "Kh", VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT);
        assertEquals(1, actualResult);
    }

    @Test
    public void testInValidSalary() {
        RegistrationFormPage formPage = new WebTablesPage(driver)
                .addEmployee()
                .fillForm(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, INVALID_SALARY, VALID_DEPARTMENT);

        formPage.submitForm();
        assertTrue(formPage.isFormStillOpen());
    }

    @Test
    public void testSpecialCharInName() {
        String name = "Ahed@%";

        WebTablesPage page = new WebTablesPage(driver);
        page.addEmployee()
                .fillForm(name, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT)
                .submitForm();

        int actualResult = page.countMatchingRecords(name, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT);
        assertEquals(1, actualResult);
    }

    @Test
    public void testAddLongFirstName() {
        WebTablesPage page = new WebTablesPage(driver);
        String expectedName = LONG_NAME.substring(0, 25);

        page.addEmployee()
                .fillForm(LONG_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT)
                .submitForm();

        int actualResult = page.countMatchingRecords(expectedName, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT);
        assertEquals(1, actualResult);
    }

    @Test
    public void testAddLongLastName() {
        WebTablesPage page = new WebTablesPage(driver);
        String expectedLast = LONG_LAST_NAME.substring(0, 25);

        page.addEmployee()
                .fillForm(VALID_NAME, LONG_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT)
                .submitForm();

        int actualResult = page.countMatchingRecords(VALID_NAME, expectedLast, VALID_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT);
        assertEquals(1, actualResult);
    }

    @Test
    public void testAddLongAge() {
        WebTablesPage page = new WebTablesPage(driver);
        String expectedAge = LONG_AGE.substring(0, 2);

        page.addEmployee()
                .fillForm(VALID_NAME, VALID_LAST_NAME, LONG_AGE, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT)
                .submitForm();

        int actualResult = page.countMatchingRecords(VALID_NAME, VALID_LAST_NAME, expectedAge, VALID_EMAIL, VALID_SALARY, VALID_DEPARTMENT);
        assertEquals(1, actualResult);
    }

    @Test
    public void testAddLongSalary() {
        WebTablesPage page = new WebTablesPage(driver);
        String expectedSalary = LONG_SALARY.substring(0, 10);

        page.addEmployee()
                .fillForm(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, LONG_SALARY, VALID_DEPARTMENT)
                .submitForm();

        int actualResult = page.countMatchingRecords(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, expectedSalary, VALID_DEPARTMENT);
        assertEquals(1, actualResult);
    }

    @Test
    public void testAddLongDepartment() {
        WebTablesPage page = new WebTablesPage(driver);
        String expectedDep = LONG_DEPARTMENT.substring(0, 25);

        page.addEmployee()
                .fillForm(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, LONG_DEPARTMENT)
                .submitForm();

        int actualResult = page.countMatchingRecords(VALID_NAME, VALID_LAST_NAME, VALID_AGE, VALID_EMAIL, VALID_SALARY, expectedDep);
        assertEquals(1, actualResult);
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

