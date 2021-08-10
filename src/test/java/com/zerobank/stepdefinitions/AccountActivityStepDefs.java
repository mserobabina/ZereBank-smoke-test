package com.zerobank.stepdefinitions;

import com.zerobank.pages.BasePage;
import io.cucumber.java.en.Given;

public class AccountActivityStepDefs {

    BasePage basePage = new BasePage();
    @Given("the user navigates to {string} menu")
    public void the_user_navigates_to_menu(String tab) {
       basePage.getTab(tab);
    }

}
