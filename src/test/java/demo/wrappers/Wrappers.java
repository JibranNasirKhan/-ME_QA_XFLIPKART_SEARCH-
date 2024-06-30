package demo.wrappers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Wrappers {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    private final JavascriptExecutor js;
    
    public Wrappers (WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }

    /*
     * Write your selenium wrappers here
     */
    public void PopularityCount(String URL) throws InterruptedException{
        driver.get(URL);
        Thread.sleep(3000);
        searchforWashingMachine();
        SortByPopularity();
        int count = countItemsWithRatingsLessThanOrEqualToFourStar();
        System.out.println("Count of Washing Machine with ratings 1 to 4 are : "+count);
    }   
    private void searchforWashingMachine() throws InterruptedException{
        WebElement Search = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
        Search.sendKeys("Washing Machine"+Keys.ENTER); //Searching for Washing Machine
        Thread.sleep(10000);        
    } 
    private void SortByPopularity() throws InterruptedException{
        WebElement SortByPopularity = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Popularity']")));
        SortByPopularity.click();
        Thread.sleep(3000);
    }
    private int countItemsWithRatingsLessThanOrEqualToFourStar() throws InterruptedException {
        int count = 0;
        boolean hasNextPage = true;
        int nextPageclick = 0;
    
        while (hasNextPage && nextPageclick < 41) {
            List<WebElement> ratings = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='XQDdHH']")));
    
            for (WebElement ratingElement : ratings) {
                try {
                    String ratingText = ratingElement.getText();
                    if (!ratingText.isEmpty() && !ratingText.isBlank()) {
                        double ratingValue = Double.parseDouble(ratingText);
                        if (ratingValue <= 4.0) {
                            count++;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipping a non-numeric rating: " + ratingElement.getText());
                }
            }
    
            try {
                WebElement nextPageButton = driver.findElement(By.xpath("//span[text()='Next']"));
                if (nextPageButton.isDisplayed()) {
                    nextPageButton.click();
                    nextPageclick++;
                    Thread.sleep(2000); // Adding a short sleep to allow the page to load
                } else {
                    hasNextPage = false;
                }
            } catch (Exception e) {
                hasNextPage = false;
                System.out.println("No more pages or an error occurred: " + e.getMessage());
            }
        }
    
        return count;
    }
    private void waitForPageLoad() {
        try {
            Thread.sleep(2000); // Adjust sleep as necessary
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public void Titlesofitemsmorethan17discount() throws InterruptedException{
            searchforiphone();
            printTileswithDiscount();
    }
    public void searchforiphone() throws InterruptedException{
        // driver.get(URL);
        WebElement home = driver.findElement(By.xpath("//img[@title='Flipkart']"));
        home.click();
        WebElement Search = driver.findElement(By.name("q"));
        Search.sendKeys("iPhone"+Keys.ENTER); //Searching for iPhone
        Thread.sleep(10000);        
    } 
    public void printTileswithDiscount(){
        int page = 1;
        boolean hasNextPage = true;
        int nextPageclicks = 0;
    
        while (hasNextPage && nextPageclicks < 4) {
        List<WebElement> titles = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='KzDlHZ']")));
        List<WebElement> Discount = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='UkUFwK']")));
        waitForPageLoad();
        for (int i = 0; i < Discount.size();i++){
            try{
            String discount = Discount.get(i).getText();
            int DiscountValue = Integer.parseInt(discount.replaceAll("[^0-9]", ""));
                if(DiscountValue >= 17){
                    System.out.println( titles.get(i).getText() + " with Doscount of : "+ DiscountValue + "%");
            }
        } catch (NumberFormatException e){
            System.out.println("Skipping a non-numeric discount value");
        }
    }  
     
                try {
                    WebElement nextPageButton = driver.findElement(By.xpath("//span[text()='Next']"));
                    if (nextPageButton.isDisplayed()) {
                        nextPageButton.click();
                        nextPageclicks++;
                        Thread.sleep(2000); // Adding a short sleep to allow the page to load
                    } else {
                        hasNextPage = false;
                    }
                    System.out.println("No more pages to navigate or 4 pages limit reached.");
                } catch (Exception e) {
                    hasNextPage = false;
                    System.out.println("No more pages or an error occurred: " + e.getMessage());
                }
            }
        } 
        public void TitlesAndImageUrlsOfTopFiveItems() throws InterruptedException {
            searchforCoffeeMug();
            FourStarRatings();
            printTitlewithhighestnoOfreview();
            waitForPageLoad();
        }

    public void searchforCoffeeMug() throws InterruptedException{
        // String URL = "https://www.flipkart.com/";
        // driver.get(URL);
        WebElement home = driver.findElement(By.xpath("//img[@title='Flipkart']"));
        home.click();
        WebElement Search = driver.findElement(By.name("q"));
        Search.sendKeys("Coffee Mug"+Keys.ENTER); //Searching for Iphone
        Thread.sleep(10000);        
    } 
    public void FourStarRatings() throws InterruptedException{
        WebElement FourStarRatings = driver.findElement(By.xpath("(//div[@class='_6i1qKy'])[2]"));
        FourStarRatings.click();  //clicking 4 star and above
        Thread.sleep(5000); //Waiting for the Page to load
        waitForPageLoad();
    }
    public void printTitlewithhighestnoOfreview(){
        waitForPageLoad();
        List<WebElement> products = driver.findElements(By.xpath("//a[@class='wjcEIp']")); 

        products.sort((product1, product2)-> {
            //  int reviews1 = Integer.parseInt(product1.getAttribute("//span[@class='Wphh3N']") !=null ? product1.getAttribute("//span[@class='Wphh3N']") : "0");
            //  int reviews2 = Integer.parseInt(product2.getAttribute("//span[@class='Wphh3N']")!=null ? product2.getAttribute("//span[@class='Wphh3N']"): "0");
            int reviews1 = extractReviewCount(product1);
            int reviews2 = extractReviewCount(product2);
             return Integer.compare(reviews1, reviews2);
        });

        for (int i = 0; i <Math.min(5, products.size()); i++){
            waitForPageLoad();
            WebElement product = products.get(i);
            String Title = product.getAttribute("title");
            String imgURL = product.findElement(By.xpath("//img[@class='DByuf4']")).getAttribute("src");
            
            System.out.println("Title"+Title);
            System.out.println("ImageURL"+imgURL);
            System.out.println();
        }
    }
    private int extractReviewCount(WebElement product) {
        // Extract the review count as an integer
        String reviewCountText = product.findElement(By.xpath("//span[@class='Wphh3N']")).getText();
        // Remove any non-numeric characters and parse to integer
        return Integer.parseInt(reviewCountText.replaceAll("[^0-9]", ""));
    }
}
