package de.thaso.fum.web.it.components;

import de.thaso.fum.web.it.base.BaseCO;

/**
 * LabelCO
 *
 * @author thaler
 * @since 03.03.17
 */
public class LabelCO extends BaseCO {

    public boolean isVisible() {
        return isPresent(getWebElement())&& getWebElement().isDisplayed();
    }

    public String getText() {
        waitForElement();
        return getWebElement().getText();
    }
}
