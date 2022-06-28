package Flows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import Methods.methods;

public class flows {

    private methods methods;
    static String registrationText = "//h2[contains(text(),'Registrarme')]";
    static String inAppPrice = "//div[@id='productPrice']";




    private WebDriver driver;
    public flows (WebDriver driver){
        this.driver = driver;
    }

    //Open browser & go to https site
    public static void pageFlow(String webSite, String searchOption) throws Exception {
        //Load drive
        System.setProperty("webdriver.chrome.driver","C:\\Users\\PC RTX 3070\\IdeaProjects\\chromedriver_win32_2\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //variables
        int xpathCount1;
        String elmtPrice = null;
        String elmtPriceTxt = null;
        double nonFreeAppPrice1;
        float f = 0;
        double d = 0;

        //load Microsoft page
        Methods.methods.loadPage(driver, webSite);
        Methods.methods.seperator();

        //Click on Windows tab & search for Xbox
        Methods.methods.windowsTabSearch(driver, searchOption);

        //Check if language option appears
        Methods.methods.stayInMexico(driver);
        Methods.methods.seperator();

        //Click on buy tab >> app tab
        Methods.methods.buyAppTab(driver);

        //Count all elements in first 3 pages
        xpathCount1 = Methods.methods.countElements(driver);
        Methods.methods.seperator();

        //Go back to first page and find first NON-FREE item
        nonFreeAppPrice1 = Methods.methods.selectNonFreeElement(driver, xpathCount1);
        Methods.methods.seperator();

        //Close registration if it pops up
        Methods.methods.closeWindow(driver, registrationText);
        Methods.methods.seperator();

        //compare if price matches
        Methods.methods.comparePrice(driver, inAppPrice, nonFreeAppPrice1);
        Methods.methods.seperator();

        //add to cart
        Methods.methods.addToCart(driver);

        //verify item was added to cart
        Methods.methods.verifyCart(driver);
        Methods.methods.seperator();

        //remove item from cart and verify cart is empty
        Methods.methods.removeFromCart(driver);
        Methods.methods.seperator();


    }
}
