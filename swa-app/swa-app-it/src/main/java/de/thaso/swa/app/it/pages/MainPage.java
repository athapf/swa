package de.thaso.swa.app.it.pages;

import de.thaso.swa.app.it.components.LabelCO;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * LoginPage
 *
 * @author thaler
 * @since 27.02.17
 */
public class MainPage extends StandardPage {

    @FindBy(how = How.CSS, css = "*[id$='swa-main-title']")
    private LabelCO titleLabel;

    @FindBy(how = How.CSS, css = "*[id$='swa-main-subtitle']")
    private LabelCO subTitleLabel;

    @FindBy(how = How.CSS, css = "*[id$='swa-main-message']")
    private LabelCO messageLabel;


    @Override
    public String getPageId() {
        return "mainPage";
    }

    public LabelCO getTitleLabel() {
        return titleLabel;
    }

    public LabelCO getSubTitleLabel() {
        return subTitleLabel;
    }

    public LabelCO getMessageLabel() {
        return messageLabel;
    }
}
