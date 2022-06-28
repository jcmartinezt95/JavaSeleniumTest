package Methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;

public class methods {

    static String windowsTab = "//a[@id='shellmenu_2']";
    static String searchBar = "//button[@id='search']";
    static String searchInput = "//input[@id='cli_shellHeaderSearchInput']";
    static String searchIcon = "//button[@id='search']";
    static String comprarTab = "//div[@id='coreui-pivotheader-s3sdtmy']/div/div/header/a[1]";
    static String aplicacionesTab = "//div[@id='refine-by-menu-title-Categorías']/div/ul/li[1]/a/span";
    static String webElements = "//div[@id='coreui-productplacementlist-1g76zxk']//div[@class='c-group f-wrap-items context-list-page']/div";
    static String nextPage = "//span[contains(text(),'Siguiente')]";
    static String firstPage = "//a[text() = '1']";
    static String regCloseWindow = "//div[@id='email-newsletter-dialog']/div[3]/div[1]";
    static String threeDotsBtn = "//button[@id='ButtonPanel_buttonPanel_OverflowMenuTrigger']";
    static String emptyCartMsg = "//p[contains(text(),'Tu carro está vacío')]";
    static String stayInMexico = "//button[contains(text(),'Stay in M')]";
    static String cartTxt = "//h2[contains(text(),'Carro')]";
    static String cartItems = "//div[@id='store-cart-root']//div[@class='cart-items']/div";
    static String removeItemBtn = "//button[contains(text(),'Quitar')]";

    public static void closeWindow(WebDriver driver, String identifier){
        int internalCount = 0;
        boolean regResult;
        By reg = By.xpath(identifier);
        regResult = elementExists(driver, reg);

        if(regResult = elementExists(driver, reg)){
            driver.findElement(By.xpath(regCloseWindow)).click(); sleepAction(2000);
            System.out.println("\nRegistration window closed");
        }
        while(!regResult){
            if(regResult = elementExists(driver, reg)){
                driver.findElement(By.xpath(regCloseWindow)).click(); sleepAction(2000);
                System.out.println("\nRegistration window closed");
            }
            else {
                internalCount++;
                sleepAction(1000);
            }
            if(internalCount > 5) {
                System.out.println("\nRegistration window DOESN'T exist");
                regResult = true;
            }
        }
    }

    //verify if web element exists
    public static boolean elementExists(WebDriver driver, By elementLocator){
        boolean result = false;
        try {
            WebElement element = driver.findElement(elementLocator);
            if(element.isDisplayed()){
                result = true;
                //System.out.println("\nElement exists");
            }else{
                result = false;
                //System.out.println("\nElement DOESN'T exists");
            }
        }catch (Exception e){
            result = false;
        }
        return result;
    }

    //sleep action
    public static void sleepAction(int i){
        try{
            Thread.sleep(i);
        }catch (InterruptedException e){
            //
        }
    }

    public static void seperator(){
        System.out.println("\n----------------------------------\n");
    }

    //compare prices
    public static boolean comparePrice(WebDriver driver, String identifier, double nonFreeAppPrice1) {
        boolean result;
        double d = 0;
        By elmnt = By.xpath(identifier);
        WebElement element = driver.findElement(elmnt);
        String inAppPrice = element.getText();

        System.out.println("\nConverting " + inAppPrice.replaceAll("[$(),]", ""));
        d = Double.parseDouble(inAppPrice.replaceAll("[$(),MXN]", ""));

        if (Double.compare(d, nonFreeAppPrice1) == 0) {
            System.out.println("\nPrices match");
            result = true;
        }
        else {
            System.out.println("\nPrices DON'T match");
            result = false;
        }
        System.out.println("\nFirst price: " + nonFreeAppPrice1+ "\nCurrent price: " + d);
        return result;
    }

    public static void addToCart(WebDriver driver) throws Exception {
        boolean enteredCart;
        By aux = By.xpath(cartTxt);
        enteredCart = elementExists(driver, aux);

        Robot robo = new Robot();
        driver.findElement(By.xpath(threeDotsBtn)).click(); sleepAction(2000);
        robo.keyPress(KeyEvent.VK_DOWN);
        sleepAction(1000);
        robo.keyPress(KeyEvent.VK_DOWN);
        sleepAction(1000);
        robo.keyPress(KeyEvent.VK_ENTER);
        sleepAction(4000);


        //driver.findElement(By.xpath(addTxt)).click(); sleepAction(2000);
    }


    public static void removeFromCart(WebDriver driver){
        boolean cartInfo;
        By aux = By.xpath(emptyCartMsg);
        cartInfo = elementExists(driver, aux);

        driver.findElement(By.xpath(removeItemBtn)).click(); sleepAction(2000);

        if (cartInfo = elementExists(driver, aux)) {
            System.out.println("\nCart is empty");
        }
        else{
            System.out.println("\nCart isn't empty");
        }
        sleepAction(2000);
    }

    public static void stayInMexico(WebDriver driver){
        boolean stay;
        By aux = By.xpath(stayInMexico);
        stay = elementExists(driver, aux);

        try {
            if (stay = elementExists(driver, aux)) {
                driver.findElement(By.xpath(stayInMexico)).click();
                sleepAction(2000);
                System.out.println("\nStayed in es-mx page for Spanish");
            }
            else {
                //
            }
        }catch (Exception e){
            //
        }
    }

    public static void verifyCart(WebDriver driver){
        boolean exists;
        By elmnt = By.xpath(cartTxt);

        if (exists = elementExists(driver, elmnt)) {
            System.out.println("\nInside cart");
            int cartCount = driver.findElements(By.xpath(cartItems)).size();
            System.out.println("\n\nNumber of items in cart: "+ cartCount); sleepAction(3000);
        }
    }

    public static void loadPage(WebDriver driver, String webSite){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\PC RTX 3070\\IdeaProjects\\chromedriver_win32_2\\chromedriver.exe");
        driver.get(webSite);
        System.out.println("Website Title: "+ driver.getTitle()); sleepAction(2000);
    }

    public static void windowsTabSearch(WebDriver driver, String searchOption){
        //Click on Windows tab & search for Xbox
        driver.findElement(By.xpath(windowsTab)).click(); sleepAction(4000);

        //Click on search bar
        driver.findElement(By.xpath(searchBar)).click(); sleepAction(2000);

        //Search for Xbox
        driver.findElement(By.xpath(searchInput)).sendKeys(searchOption); sleepAction(2000);
        driver.findElement(By.xpath(searchIcon)).click(); sleepAction(2000);
    }

    public static void buyAppTab(WebDriver driver){
        //Click on Buy tab
        driver.findElement(By.xpath(comprarTab)).click(); sleepAction(4000);

        //Click on Applications tab
        driver.findElement(By.xpath(aplicacionesTab)).click(); sleepAction(4000);
    }

    public static int countElements(WebDriver driver){

        int xpathCount1 = driver.findElements(By.xpath(webElements)).size();
        int totalCount = xpathCount1;
        System.out.println("Count 1st Page: "+ xpathCount1); sleepAction(2000);
        //Second page
        driver.findElement(By.xpath(nextPage)).click(); sleepAction(2000);
        int xpathCount2 = driver.findElements(By.xpath(webElements)).size();
        totalCount += xpathCount2;
        System.out.println("Count 2nd Page: "+ xpathCount2); sleepAction(2000);
        //Third page
        driver.findElement(By.xpath(nextPage)).click(); sleepAction(2000);
        int xpathCount3 = driver.findElements(By.xpath(webElements)).size();
        totalCount += xpathCount3;
        System.out.println("Count 3rd Page: "+ xpathCount3 +
                "\n\nTotal count: " + totalCount); sleepAction(2000);

        return xpathCount1;
    }

    public static double selectNonFreeElement(WebDriver driver, int xpathCount1) {

        //Back to first page and find first NON-FREE item
        sleepAction(4000);
        driver.findElement(By.xpath(firstPage)).click();
        sleepAction(4000);
        double nonFreeAppPrice1 = 0;

        for (int i = 0; i < xpathCount1; i++) {
            String elmtPrice = "//div[@id='coreui-productplacementlist-1g76zxk_"
                    + i + "']//div[@class='c-channel-placement-price']/div";
            By elmnt = By.xpath(elmtPrice);
            WebElement element = driver.findElement(elmnt);
            String elmtPriceTxt = element.getText();
            if (!elmtPriceTxt.toLowerCase().trim().contains("gratis".toLowerCase())) {
                System.out.println("Found a non free app in element: " + i);
                System.out.println("\nConverting " + elmtPriceTxt.replaceAll("[$(),]", ""));
                double d = Double.parseDouble(elmtPriceTxt.replaceAll("[$(),MXN]", ""));
                //f = (float) d;
                nonFreeAppPrice1 = d;
                System.out.println("Converted value of: " + elmtPriceTxt + " to: " + nonFreeAppPrice1);
                //Select app
                driver.findElement(By.xpath(elmtPrice)).click();
                sleepAction(2000);
                break;
            }
        }
        return nonFreeAppPrice1;
    }
}
