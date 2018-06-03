package de.thaso.fum.web.it.base;

import de.thaso.fum.web.it.utils.PropertiesManager;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * SeleniumTestBase
 *
 * @author thaler
 * @since 26.09.16
 */
public class SeleniumTestBase {

    private static Logger LOG = LoggerFactory.getLogger(SeleniumTestBase.class);

    private static Properties properties;

    private FirefoxDriver driver;

    public static Properties readProperties() {
        return PropertiesManager.readDevelopProperties();
    }

    public static Properties getProperties() {
        if (properties == null) {
            properties = readProperties();
        }
        return properties;
    }

    @Rule
    public ScreenShotRule screenShotRule = new ScreenShotRule();

    @Before
    public void setUpSeleniumDriver() {
        final FirefoxBinary binary = new FirefoxBinary(new File(getProperties().getProperty("firefox.executable")));

        final String display = getProperties().getProperty("browser.display");
        if (StringUtils.isNotEmpty(display)) {
            binary.setEnvironmentProperty("DISPLAY", display);
        }
        driver = new FirefoxDriver(binary, null);

        screenShotRule.setBrowser(driver);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDownSeleniumDriver() {
        driver.quit();
    }

    public <T extends BasePO> T startBrowser(final String pageUrl, Class<T> pageClass) {
        final StringBuilder builder = new StringBuilder();
        builder.append(createAppUrl()).append("/");
        if(StringUtils.isNotBlank(pageUrl)) {
            builder.append(pageUrl);
            if(!StringUtils.endsWith(pageUrl, ".xhtml")) {
                builder.append(".xhtml");
            }
        }
        final String url = builder.toString();
        LOG.info("url: {}", url);
        driver.get(url);
        return BasePO.nextPage(driver, pageClass);
    }

    private String createAppUrl() {
        final StringBuilder builder = new StringBuilder();
        builder.append("http://localhost:")
                .append(properties.getProperty("app.server.http.port"))
                .append('/')
                .append(properties.getProperty("app.server.contextroot"));
        return builder.toString();
    }

    protected RemoteWebDriver getDriver() {
        return driver;
    }

    public String getAppUrl() {
        return createAppUrl();
    }
}
