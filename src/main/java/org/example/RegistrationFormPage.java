package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegistrationFormPage {
    private WebDriver driver;

    @FindBy(css = ".modal-content")
    private List<WebElement> modalElements;

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "userEmail")
    private WebElement emailField;

    @FindBy(id = "age")
    private WebElement ageField;

    @FindBy(id = "salary")
    private WebElement salaryField;

    @FindBy(id = "department")
    private WebElement departmentField;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public RegistrationFormPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public RegistrationFormPage fillForm(String firstNewName, String lastNewName, String newAge, String newEmail, String newSalary, String newDepartment){
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        ageField.clear();
        salaryField.clear();
        departmentField.clear();

        firstNameField.sendKeys(firstNewName);
        lastNameField.sendKeys(lastNewName);
        emailField.sendKeys(newEmail);
        ageField.sendKeys(newAge);
        salaryField.sendKeys(newSalary);
        departmentField.sendKeys(newDepartment);

        return this;
    }

    public void submitForm() {
        submitButton.click();
    }

    public RegistrationFormPage editSalary(String newSalary){
        salaryField.clear();
        salaryField.sendKeys(newSalary);

        return this;
    }

    public boolean isFormStillOpen() {
        return !modalElements.isEmpty();
    }
}
