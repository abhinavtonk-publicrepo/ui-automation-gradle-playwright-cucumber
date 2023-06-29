package step_defs;

import enums.AppBrowser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.PlaywrightImpl;

public class UiStepDefs {
    PlaywrightImpl pwWeb;

    @Before
    public void beforeScenario(){
        pwWeb = new PlaywrightImpl();
    }
    @After
    public void afterScenario(){
        pwWeb.closePage();
    }

    @Given("User has launched the application")
    public void applicationLaunch() {
        pwWeb.launchApplication("https://jqueryui.com", AppBrowser.CHROME,false);
    }

    @Then("User verifies the title of the application")
    public void verifyTitle(){
        pwWeb.assertPageTitle("jQuery UI");
    }

    @Then("User verifies the Draggable link")
    public void verifyDraggableLink(){
        pwWeb.assertThatWebElementIsVisible("//a[contains(text(),'Draggable')]");
    }

    @Then("User verifies the Droppable link")
    public void verifyDroppableLink(){
        pwWeb.assertThatWebElementIsVisible("//a[contains(text(),'Droppable')]");
    }

    @Then("User verifies the Resizable link")
    public void verifyResizableLink(){
        pwWeb.assertThatWebElementIsVisible("//a[contains(text(),'Resizable')]");
    }

    @Then("User verifies the Selectable link")
    public void verifySelectableLink(){
        pwWeb.assertThatWebElementIsVisible("//a[contains(text(),'Selectable')]");
    }
}

