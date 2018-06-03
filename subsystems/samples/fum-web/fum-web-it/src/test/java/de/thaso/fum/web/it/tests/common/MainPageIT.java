package de.thaso.fum.web.it.tests.common;

import de.thaso.fum.web.it.base.SeleniumTestBase;
import de.thaso.fum.web.it.pages.MainPage;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * MainPageIT
 *
 * @author thaler
 * @since 26.09.16
 */
public class MainPageIT extends SeleniumTestBase {

    @Test
    public void testMainPage() {
        getDriver().get(getAppUrl() + "/main.xhtml");

        final WebElement mainTitle = getDriver().findElementByCssSelector("*[id$='fum-main-title']");
        assertThat(mainTitle.getText(),is("Fancy User Management"));
    }

    @Test
    public void testLoginPage_allElementsOnPage() {
        final MainPage mainPage = startBrowser("main.xhtml", MainPage.class);

        assertThat(mainPage.getTitleLabel().isVisible(), is(true));
        assertThat(mainPage.getSubTitleLabel().isVisible(), is(true));
        assertThat(mainPage.getMessageLabel().isVisible(), is(true));
        assertThat(mainPage.getTitleLabel().getText(), is("Fancy User Management"));
        assertThat(mainPage.getSubTitleLabel().getText(), is("sample for rest service and resource adapter"));
        assertThat(mainPage.getMessageLabel().getText(), is("Message: Hello, i'm the controller!"));
    }
}
