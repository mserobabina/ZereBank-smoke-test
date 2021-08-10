package com.zerobank.stepdefinitions;

import com.zerobank.pages.payBillsPage;
import io.cucumber.java.en.When;
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




    }
}
