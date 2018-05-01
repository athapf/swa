package de.thaso.swa.app.it.tests.common;

import de.thaso.swa.app.it.base.SeleniumTestBase;
import org.junit.Test;

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
    }
}
