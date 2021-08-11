package com.zerobank.stepdefinitions;

import com.zerobank.pages.payBillsPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class payBillsStepDefs {

    Random random = new Random();
    payBillsPage payBillsPage = new payBillsPage();

    @When("Learn Date and DateFormat")
    public void learn_Date_and_DateFormat() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Date date = new Date();
        System.out.println(df.format(date));
        System.out.println(df2.format(date));
    }


    @When("user completes a successful Pay operation")
    public void userCompletesASuccessfulPayOperation() {
        Select payeeOptions = new Select(payBillsPage.payeeOptions);
        int rand = random.nextInt(payeeOptions.getOptions().size());
        payeeOptions.selectByIndex(rand);

        Select accountOptions = new Select(payBillsPage.accountOptions);
        int rand2 = random.nextInt(accountOptions.getOptions().size());
        accountOptions.selectByIndex(rand2);

        int ammountRandom = random.nextInt(100000);
        payBillsPage.amountInput.sendKeys(ammountRandom+"");

        DateFormat df = new SimpleDateFormat("yy-MM-dd");
        Date date = new Date();
        payBillsPage.dateInput.sendKeys(df.format(date));

        payBillsPage.descriptionInput.sendKeys("enjoy your money");

        payBillsPage.payButton.click();

    }

    @Then("{string} alert should be displayed")
    public void alertShouldBeDisplayed(String expectedMessage) {
        if(expectedMessage.contains("success")){
            Assert.assertTrue("Allert Box is not displayed", payBillsPage.alertMessageBox.isDisplayed());
            String actualMessage = payBillsPage.alertMessageBox.getText();
            Assert.assertEquals("message do not match", expectedMessage,actualMessage);
        }else{
            if(payBillsPage.amountInput.getAttribute("value").isEmpty()){
                String actualMessage = payBillsPage.amountInput.getAttribute("validationMessage");
                Assert.assertEquals("alert do not match",expectedMessage,actualMessage);
            }else{
                String actualMessage = payBillsPage.dateInput.getAttribute("validationMessage");
                Assert.assertEquals("alert do not match",expectedMessage,actualMessage);
            }

        }
    }

    @When("User tries to make a payment without entering the amount or date")
    public void userTriesToMakeAPaymentWithoutEnteringTheAmountOrDate() {
        int dateOrAmount = random.nextInt(2)+1;

        Select payeeOptions = new Select(payBillsPage.payeeOptions);
        int rand = random.nextInt(payeeOptions.getOptions().size());
        payeeOptions.selectByIndex(rand);

        Select accountOptions = new Select(payBillsPage.accountOptions);
        int rand2 = random.nextInt(accountOptions.getOptions().size());
        accountOptions.selectByIndex(rand2);
        if(dateOrAmount==1){
            int amountRandom = random.nextInt(100000);
            payBillsPage.amountInput.sendKeys(amountRandom+"");
        }else{
            DateFormat df = new SimpleDateFormat("yy-MM-dd");
            Date dateobj = new Date();
            payBillsPage.dateInput.sendKeys(df.format(dateobj));
        }
        payBillsPage.descriptionInput.sendKeys("enjoy your payment");
        payBillsPage.payButton.click();

    }

    @Given("Add New Payee tab")
    public void add_New_Payee_tab() {
        payBillsPage.AddNewPayee.click();
        BrowserUtils.sleep(2);
    }
    @Given("creates new payee using following information")
    public void creates_new_payee_using_following_information(Map<String,String> payeeDetails) {
        for(String key : payeeDetails.keySet() ) {
            String locator = "np_new_"+key.toLowerCase();
            Driver.getDriver().findElement(By.id(locator)).sendKeys(payeeDetails.get(key));
        }
        payBillsPage.addButton.click();
        BrowserUtils.sleep(1);
    }
    @Then("message {string} should be displayed")
    public void message_should_be_displayed(String string) {
        String actualMessage = Driver.getDriver().findElement(By.xpath("//div[@id='alert_content']")).getText();
        Assert.assertTrue(actualMessage.contains(string));
    }

    @Given("the user accesses the Purchase foreign currency cash tab")
    public void the_user_accesses_the_Purchase_foreign_currency_cash_tab() {
        payBillsPage.PFC.click();
        BrowserUtils.sleep(2);
    }

    @Then("following currencies should be available")
    public void following_currencies_should_be_available(List<String> dataTable) {
        Assert.assertTrue(payBillsPage.isContained(dataTable));
    }

    @When("user tries to calculate cost without selecting a currency")
    public void user_tries_to_calculate_cost_without_selecting_a_currency() {
        payBillsPage.calculateButton.click();
        BrowserUtils.sleep(1);
    }

    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {
        Alert alert = Driver.getDriver().switchTo().alert();
        String expected="Please, ensure that you have filled all the required fields with valid values.";
        Assert.assertEquals(expected,alert.getText());
        BrowserUtils.sleep(1);
        alert.accept();
    }



}
