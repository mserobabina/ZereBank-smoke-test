package com.zerobank.stepdefinitions;

import com.zerobank.pages.payBillsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class payBillsStepDefs {

    Random random = new Random();
    payBillsPage payBillsPage = new payBillsPage();

    @When("Learn Date and DateFormat")
    public void learn_Date_and_DateFormat() {
        DateFormat df = new SimpleDateFormat("yy-MM-dd");
        DateFormat df2 = new SimpleDateFormat("dd-MM-yy hh:mm:ss");
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


}
