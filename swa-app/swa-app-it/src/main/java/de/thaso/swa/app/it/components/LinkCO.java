package de.thaso.swa.app.it.components;


import de.thaso.swa.app.it.base.BaseCO;

/**
 * ButtonCO
 *
 * @author thaler
 * @since 27.02.17
 */
public class LinkCO extends BaseCO {

    public boolean isVisible() {
        return isPresent(getWebElement())&& getWebElement().isDisplayed();
    }

    public void click() {
        doClick(getWebElement());
    }
}
