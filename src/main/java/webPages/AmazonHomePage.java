package webPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utill.Common;

public class AmazonHomePage extends Common {
    static final  String  SearchBoxID="twotabsearchtextbox1";
    @FindBy(how= How.ID,using=SearchBoxID)
    public static WebElement SearchBox;
    static final  String  SearchBoxButtonID="nav-search-submit-button";
    @FindBy(how= How.ID,using=SearchBoxButtonID)
    public static WebElement SearchBoxButton;
}
