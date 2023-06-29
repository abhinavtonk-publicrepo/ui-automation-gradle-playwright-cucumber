package utils;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import enums.AppBrowser;
import enums.WebElementState;

import java.util.List;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PlaywrightImpl {
    Playwright playwrightWeb;
    BrowserType browserType;
    BrowserType.LaunchOptions launchOptions;
    com.microsoft.playwright.Browser browser;
    BrowserContext browserContext;
    Page page;

    // Attributes for Api Automation
    Playwright playwrightApi;
    APIRequestContext apiRequestContext;
    APIResponse apiResponse;

    /*****************************************
     *********** WebElement Actions **********
     *****************************************/

    public void launchApplication(String appUrl, AppBrowser appBrowser, boolean isHeadlessMode) {
        switch(appBrowser){
            case CHROME: default:
                playwrightWeb = Playwright.create();
                browserType = playwrightWeb.chromium();
                launchOptions = new BrowserType.LaunchOptions().setHeadless(isHeadlessMode);
                browser = browserType.launch(launchOptions);
                browserContext = browser.newContext();
                page = browserContext.newPage();
                page.navigate(appUrl);
                break;

            case FIREFOX:
                break;
        }

    }


    public void clickWebElement(String selector) {
        page.locator(selector).click();
    }


    public void checkWebElement(String selector) {
        page.locator(selector).check();
    }


    public void dblClickWebElement(String selector) {
        page.locator(selector).dblclick();
    }


    public void inputText(String selector, String text) {
        page.type(selector, text);
    }


    public void clearAndInputText(String selector, String text) {
        page.fill(selector, text);
    }


    public void setPageDefaultNavigationTimeout(double timeoutInMilliseconds) {
        page.setDefaultNavigationTimeout(timeoutInMilliseconds);
    }


    public void waitForElement(String selector, WebElementState webElementState, double timeToWaitForInMilliseconds) {
        switch(webElementState){
            case ATTACHED:
                page.locator(selector).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED).setTimeout(timeToWaitForInMilliseconds));
                break;
            case DETACHED:
                page.locator(selector).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.DETACHED).setTimeout(timeToWaitForInMilliseconds));

                break;
            case VISIBLE: default:
                page.locator(selector).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(timeToWaitForInMilliseconds));
                break;

            case HIDDEN:
                page.locator(selector).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(timeToWaitForInMilliseconds));
                break;
        }
    }


    public String getAttribute(String selector, String attributeName) {
        return page.locator(selector).getAttribute(attributeName);
    }


    public String getText(String selector) {
        return page.locator(selector).textContent();
    }


    public void scrollIntoView(String selector) {
        page.locator(selector).scrollIntoViewIfNeeded();
    }


    public void closePage() {
        page.close();
    }


    public void dragAndDrop(String sourceSelector, String targetSelector) {
        page.dragAndDrop(sourceSelector, targetSelector);
    }


    public String selectSingleValueFromDropdown(String selector, String value) {
        return page.selectOption(selector, value).toString();
    }

    public List<String> selectMultipleValuesFromDropdown(String selector, String[] values) {
        return page.selectOption(selector, values);
    }


    public int totalWebElements(String selector) {
        return page.locator(selector).count();
    }

    /*******************************************************
     ********* WebElement Checks and Assertions ************
     *******************************************************/

    public void assertThatWebElementIsChecked(String selector) {
        assertThat(page.locator(selector)).isChecked();
    }


    public boolean isWebElementChecked(String selector) {
        return page.locator(selector).isChecked();
    }


    public void assertThatWebElementIsDisabled(String selector) {
        assertThat(page.locator(selector)).isDisabled();
    }


    public boolean isWebElementDisabled(String selector) {
        return page.locator(selector).isDisabled();
    }


    public void assertThatWebElementIsEditable(String selector) {
        assertThat(page.locator(selector)).isEditable();
    }


    public boolean isWebElementEditable(String selector) {
        return page.locator(selector).isEditable();
    }


    public void assertThatWebElementIsEmpty(String selector) {
        assertThat(page.locator(selector)).isEmpty();
    }


    public boolean isWebElementEmpty(String selector) {
        boolean status = false;
        try{
            assertThat(page.locator(selector)).isEmpty();
            status = true;
        } catch (Exception e){

        }
        return status;
    }


    public void assertThatWebElementIsEnabled(String selector) {
        assertThat(page.locator(selector)).isEnabled();
    }


    public boolean isWebElementEnabled(String selector) {
        return page.locator(selector).isEnabled();
    }


    public void assertThatWebElementIsFocused(String selector) {
        assertThat(page.locator(selector)).isFocused();
    }


    public boolean isWebElementFocused(String selector) {
        boolean status = false;
        try{
            assertThat(page.locator(selector)).isFocused();
            status = true;
        } catch (Exception e){

        }
        return status;
    }


    public void assertThatWebElementIsHidden(String selector) {
        assertThat(page.locator(selector)).isHidden();
    }


    public boolean isWebElementHidden(String selector) {
        return page.locator(selector).isHidden();
    }


    public void assertThatWebElementIsVisible(String selector) {
        assertThat(page.locator(selector)).isVisible();
    }


    public boolean isWebElementVisible(String selector) {
        return page.locator(selector).isVisible();
    }


    public void assertPageTitle(String expectedTitle) {
        assertThat(page).hasTitle(expectedTitle);
    }


    public boolean isPageTitleContained(String expectedTitle) {
        boolean status = false;
        try{
            assertThat(page).hasTitle(expectedTitle);
            status = true;
        } catch (Exception e){

        }
        return status;
    }


    public void assertPageUrl(String expectedUrl) {
        assertThat(page).hasURL(expectedUrl);
    }


    public boolean isPageUrlContained(String expectedUrl) {
        boolean status = false;
        try{
            assertThat(page).hasURL(expectedUrl);
            status = true;
        } catch (Exception e){

        }
        return status;
    }

    /*******************************************************
     ****************** Api Actions ************************
     *******************************************************/


    public void setBaseUrl(String url, Map<String,String> headers) {
        playwrightApi = Playwright.create();
        apiRequestContext = playwrightApi.request().newContext(
                new APIRequest.NewContextOptions().setBaseURL(url).setExtraHTTPHeaders(headers)
        );
    }


    public void getRequest(String url) {
        apiResponse = apiRequestContext.get(url);
    }


    public void closeRequest() {
        apiRequestContext.dispose();
        playwrightApi.close();
    }

    /*******************************************************
     ****************** Api  Assertions ********************
     *******************************************************/

    public void assertOkResponse() {
        assertThat(apiResponse).isOK();
    }
}
