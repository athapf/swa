package de.thaso.fum.web.it.components;

import de.thaso.fum.web.it.base.BaseCO;

/**
 * InputCO
 *
 * @author thaler
 * @since 27.02.17
 */
public class InputCO extends BaseCO {

    public boolean isVisible() {
        return isPresent(getWebElement())&& getWebElement().isDisplayed();
    }

    public void setValue(final String value) {
        executeScript("arguments[0].value = arguments[1];", getWebElement(), value);
        triggerEvent("change");
        //waitForAjax();
    }

    public String getValue() {
        waitForElement();
        return getWebElement().getAttribute("value");
    }
}
