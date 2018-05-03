package de.thaso.swa.app.it.tests.common;

import de.thaso.swa.app.it.base.SeleniumTestBase;
import de.thaso.swa.app.it.pages.MainPage;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * OverviewPageTest
 *
 * @author thaler
 * @since 26.09.16
 */
public class MainPageIT extends SeleniumTestBase {

    @Test
    public void testMainPage() {
        updateDatabase();
        getDriver().get(getAppUrl() + "/main.xhtml");

        final WebElement mainTitle = getDriver().findElementByCssSelector("*[id$='swa-main-title']");
        assertThat(mainTitle.getText(),is("Sample Web Application"));
    }

    @Test
    public void testLoginPage_allElementsOnPage() {
        final MainPage mainPage = startBrowser("main.xhtml", MainPage.class);

        assertThat(mainPage.getTitleLabel().isVisible(), is(true));
        assertThat(mainPage.getSubTitleLabel().isVisible(), is(true));
        assertThat(mainPage.getMessageLabel().isVisible(), is(true));
        assertThat(mainPage.getTitleLabel().getText(), is("Sample Web Application"));
        assertThat(mainPage.getSubTitleLabel().getText(), is("to show software architecture"));
        assertThat(mainPage.getMessageLabel().getText(), is("Message: Hello, i'm the controller!"));
    }
}
