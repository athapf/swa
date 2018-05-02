package de.thaso.swa.app.it.tests.common;

import de.thaso.swa.app.it.base.SeleniumTestBase;
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
}
