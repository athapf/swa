package de.thaso.swa.app.it.pages;

import de.thaso.swa.app.it.base.BasePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * StandardPageObject
 *
 * @author thaler
 * @since 27.02.17
 */
public abstract class StandardPage extends BasePO {

    public abstract String getPageId();

    @Override
    public boolean isCurrentPage() {
        final WebElement element = getWebDriver().findElement(By.id(getPageId()));
        return element != null;
    }
}
