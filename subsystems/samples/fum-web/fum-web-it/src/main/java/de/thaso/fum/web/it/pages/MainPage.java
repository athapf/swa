package de.thaso.fum.web.it.pages;

import de.thaso.fum.web.it.components.LabelCO;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * MainPage
 *
 * @author thaler
 * @since 27.02.17
 */
public class MainPage extends StandardPage {

    @FindBy(how = How.CSS, css = "*[id$='fum-main-title']")
    private LabelCO titleLabel;

    @FindBy(how = How.CSS, css = "*[id$='fum-main-subtitle']")
    private LabelCO subTitleLabel;

    @FindBy(how = How.CSS, css = "*[id$='fum-main-message']")
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
