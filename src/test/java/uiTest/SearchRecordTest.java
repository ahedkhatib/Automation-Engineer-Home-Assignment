package uiTest;

import org.example.RegistrationFormPage;
import org.example.WebTablesPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.example.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.example.TestConstants.*;

public class SearchRecordTest {

    private static final String BASE_URL = "https://demoqa.com/webtables";

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = getDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    private List<String> search(String keyword) {
        return new WebTablesPage(driver)
                .searchByKeyword(keyword)
                .getAllNamesInTable();
    }

    @Test
    public void testSearchByName() {
        List<String> results = search(NAME_CIERRA);
        for (String name : results) {
            assertTrue(name.toLowerCase().contains(NAME_CIERRA.toLowerCase()));
        }
    }

    @Test
    public void testNumberSearchResult() {
        List<String> results = search(NAME_CIERRA);
        assertEquals(1, results.size());
    }

    @Test
    public void testDiffCase() {
        List<String> results = search(NAME_CIERRA_MIXED);
        for (String name : results) {
            assertTrue(name.toLowerCase().contains(NAME_CIERRA.toLowerCase()));
        }
    }

    @Test
    public void testNumberDiffCase() {
        List<String> results = search(NAME_CIERRA_MIXED);
        assertEquals(1, results.size());
    }

    @Test
    public void testPartialName() {
        List<String> results = search(PARTIAL_NAME);
        for (String name : results) {
            assertTrue(name.toLowerCase().contains(PARTIAL_NAME.toLowerCase()));
        }
    }

    @Test
    public void testNumPartialName() {
        List<String> results = search(PARTIAL_NAME);
        assertEquals(2, results.size());
    }

    @Test
    public void testInvalidKey() {
        List<String> results = search(INVALID_KEY);
        assertEquals(0, results.size());
    }

    @Test
    public void testSearchByEmail() {
        List<String> results = search(EMAIL);
        for (String name : results) {
            assertTrue(name.toLowerCase().contains(NAME_CIERRA.toLowerCase()));
        }
    }

    @Test
    public void testNumSearchByEmail() {
        List<String> results = search(EMAIL);
        assertEquals(1, results.size());
    }

    @Test
    public void testSearchPartialEmail() {
        List<String> results = search(PARTIAL_EMAIL);
        assertTrue(results.get(0).toLowerCase().contains("cierra"));
        assertTrue(results.get(1).toLowerCase().contains("alden"));
        assertTrue(results.get(2).toLowerCase().contains("kierra"));
    }

    @Test
    public void testNumSearchPartialEmail() {
        List<String> results = search(PARTIAL_EMAIL);
        assertEquals(3, results.size());
    }

    @Test
    public void testSearchAfterDelete() {
        new WebTablesPage(driver)
                .deleteRecord(NAME_CIERRA, LAST_NAME, AGE, EMAIL, SALARY, DEPARTMENT);
        List<String> results = search(NAME_CIERRA);
        assertEquals(0, results.size());
    }

    @Test
    public void testNumberSearchAfterAdd() {
        WebTablesPage page = new WebTablesPage(driver);
        RegistrationFormPage form = page.addEmployee();
        form.fillForm(NEW_FIRST_NAME, NEW_LAST_NAME, NEW_AGE, NEW_EMAIL, NEW_SALARY, NEW_DEPARTMENT).submitForm();

        List<String> results = search(NEW_FIRST_NAME);
        assertEquals(1, results.size());
    }

    @Test
    public void testSearchAfterAdd() {
        WebTablesPage page = new WebTablesPage(driver);
        RegistrationFormPage form = page.addEmployee();
        form.fillForm(NEW_FIRST_NAME, NEW_LAST_NAME, NEW_AGE, NEW_EMAIL, NEW_SALARY, NEW_DEPARTMENT).submitForm();

        List<String> results = search(NEW_FIRST_NAME);
        for (String name : results) {
            assertTrue(name.toLowerCase().contains(NEW_FIRST_NAME.toLowerCase()));
        }
    }

    @Test
    public void testSearchAfterEdit() {
        new WebTablesPage(driver)
                .editEmployee(NAME_CIERRA, LAST_NAME, AGE, EMAIL, SALARY, DEPARTMENT)
                .editSalary(UPDATED_SALARY)
                .submitForm();

        List<String> results = search(NAME_CIERRA);
        for (String name : results) {
            assertTrue(name.toLowerCase().contains(NAME_CIERRA.toLowerCase()));
        }
    }

    @Test
    public void testNumSearchAfterEdit() {
        new WebTablesPage(driver)
                .editEmployee(NAME_CIERRA, LAST_NAME, AGE, EMAIL, SALARY, DEPARTMENT)
                .editSalary(UPDATED_SALARY)
                .submitForm();

        List<String> results = search(NAME_CIERRA);
        assertEquals(1, results.size());
    }

    @Test
    public void testSearchWithSpaces() {
        List<String> results = search("  " + NAME_CIERRA + "  ");
        assertEquals(0, results.size());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

