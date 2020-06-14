package LocatrosHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Utilities.Driver;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

public class Locator {
    private WebDriver driver;
    @BeforeMethod
    public void setUp()
    {
        driver=Driver.getDriver("chrome");
    }
    @Test
    public void amazone() throws InterruptedException {
         /*
        1. Open go to amazon
        2. enter any search term
        3. click on search button
        4. verify page title contains the search term
        5. close browser
        */
        driver.get("https://www.amazon.com/");
        WebElement searchBox= driver.findElement(By.id("twotabsearchtextbox"));
        String item="teeth";
        searchBox.sendKeys(item);

        Thread.sleep(1000);
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        String title = driver.getTitle();

        Assert.assertTrue(title.contains(item));
        Thread.sleep(2000);

    }

    @Test
    public void wikipedia() throws InterruptedException {
        /*
        1. Go to wikipedia.org
        2. enter search term selenium webdriver
        3. click on search button
        4. click on search result Selenium (software)
        5. verify url ends with Selenium_(software)
        title="Selenium (software)"
         */
        driver.get("https://en.wikipedia.org/");
        WebElement search=driver.findElement(By.id("searchInput"));
        search.sendKeys("selenium webdriver");
        search.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        WebElement seleniumI=driver.findElement(By.xpath("//a[@title='Selenium (software)']"));
        seleniumI.click();
        Thread.sleep(1000);
        String url=driver.getCurrentUrl();
        Assert.assertTrue(url.endsWith("Selenium_(software)"));

    }
    @Test
    public void google() throws InterruptedException {
        List<String> searchStrs = Arrays.asList("Java", "cucumber bdd", "Selenium web browser automation" );
        driver.get("http://www.gooogle.com");
        //WebElement searchbar=driver.findElement(By.xpath("//input[@title='Search']"));
        for (String s: searchStrs)
        {
            WebElement searchbar=driver.findElement(By.xpath("//input[@title='Search']"));
            searchbar.sendKeys(s);
            Thread.sleep(1000);
            searchbar.sendKeys(Keys.ENTER);
            String exp=driver.findElement(By.xpath("//cite[1]")).getText();
            System.out.println(exp);
            Thread.sleep(1000);
            WebElement link=driver.findElement(By.xpath(("(//div[@class='rc']/div/a )[1]")));
            link.click();
            Thread.sleep(1000);
            String actual=driver.getCurrentUrl();
            System.out.println();
            Assert.assertTrue(actual.contains(exp));
            driver.navigate().back();
            driver.navigate().back();
            Thread.sleep(1000);


        }

    }
    @Test
    public void ebay() throws InterruptedException {
        driver.get("https://www.ebay.com/");
        // (//h1/span)[1]
        // //input[@placeholder='Search for anything']
        /*
        1. Open browser
        2. Go to https://ebay.com
        3. Search for wooden spoon
        4. Save the total number of results
        5. Click on link All under the categories on the left menu
        6. Verify that number of results is bigger than the number in step 4
        7. Navigate back to previous research results page
        8. Verify that wooden spoon is still displayed in the search box
        9. Navigate back to home page
        10. Verify that search box is blank
         */
        WebElement searchbox=driver.findElement(By.xpath("(//input[@type='text'])[1]"));
        searchbox.sendKeys("wooden spoon");
        searchbox.sendKeys(Keys.ENTER);
        String totalResult=driver.findElement(By.xpath("(//h1/span)[1]")).getText().replace(",","");
        System.out.println(totalResult);
        WebElement allCategorys=driver.findElement(By.xpath("//li[@class='srp-refine__category__item srp-refine__category__list--flush']/a"));
        allCategorys.click();
        Thread.sleep(1000);
        //Assert.assertTrue(Integer.parseInt(totalResult)>4);
        Thread.sleep(1000);
        driver.navigate().back();
        searchbox=driver.findElement(By.xpath("(//input[@type='text'])[1]"));
        Thread.sleep(1000);
        String actual=searchbox.getAttribute("value");
       System.out.println(actual);

       Assert.assertEquals(actual, "wooden spoon");
        Thread.sleep(1000);
        driver.navigate().back();
        searchbox=driver.findElement(By.xpath("(//input[@type='text'])[1]"));
       // System.out.println(searchbox.getText()+"!");
        Assert.assertEquals("" ,searchbox.getText());

    }
    @Test
    public void vytrackTitleTest() throws InterruptedException {
        driver.get("https://qa3.vytrack.com/");
        String user="user1";
        String pswd="UserUser123";
        WebElement userInput=driver.findElement(By.id("prependedInput"));
        WebElement pswInput=driver.findElement(By.id("prependedInput2"));
        userInput.sendKeys(user);
        pswInput.sendKeys(pswd);
        pswInput.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        WebElement userName=driver.findElement(By.xpath("//*[@id='user-menu']/a"));
        String uName= userName.getText();
        System.out.println(uName);
        userName.click();
        Thread.sleep(1000);
        WebElement myConfig= driver.findElement(By.linkText("My Configuration"));
        myConfig.click();
        Thread.sleep(3000);
        String titleH=driver.getTitle();
        System.out.println(titleH);
        Assert.assertTrue(titleH.startsWith(uName));
    }
    @AfterMethod
    public void tearDown()
    {
        Driver.closeDriver();
    }
}
