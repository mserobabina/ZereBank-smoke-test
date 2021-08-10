package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AccountActivityPage {
    public AccountActivityPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "aa_accountId")
    public WebElement showTransactionsAccount;

    @FindBy (xpath = "//thead/tr/th")
    public List<WebElement> accountColumnNames;


    public String selectedOption(){
        Select accountsDropDown = new Select(showTransactionsAccount);
        return accountsDropDown.getFirstSelectedOption().getText();
    }

    public List<WebElement> accountOptions (){
        Select accountsDropDown = new Select(showTransactionsAccount);
        List<WebElement> options = accountsDropDown.getOptions();
        return options;
    }
}
