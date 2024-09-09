package org.example;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public WebDriver driver;

    @BeforeTest
    public void launchbrowser() {
        System.out.println("launching browser");
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void loginwithadmin() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
        String expected = "Dashboard";
        String actual = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[1]/span/h6")).getText();
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void forgetpassword() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[4]/p")).click();
        driver.findElement(By.name("username")).sendKeys("Admin");
        String expectedtext = "Reset Password link sent successfully";
        String actualtext = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div/h6")).getText();
        Assert.assertEquals(expectedtext, actualtext);
    }

    @Test
    public void loginwithvalidusernameandinvalidpassword() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[4]/p")).click();
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
        String expectedmessage = "Invalid credentials";
        String actualmessage = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/p")).getText();
        Assert.assertEquals(expectedmessage, actualmessage);

    }

    @Test
    public void loginwithinvalidusernameandvalidpassword() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[4]/p")).click();
        driver.findElement(By.name("username")).sendKeys("123");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
        String expectedmessage = "Invalid credentials";
        String actualmessage = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/p")).getText();
        Assert.assertEquals(expectedmessage, actualmessage);
    }
    @Test
    public void loginwithemptydata() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[4]/p")).click();
        driver.findElement(By.name("username")).sendKeys("");
        driver.findElement(By.name("password")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
        String expectedmessage = "Invalid credentials";
        String actualmessage = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/p")).getText();
        Assert.assertEquals(expectedmessage, actualmessage);
    }
    @Test
    public void logout(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/span")).click();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/ul/li[4]/a")).click();
        String expectedurl="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        String acualurl=driver.getCurrentUrl();
        Assert.assertEquals(expectedurl,acualurl);
    }
    @Test
    public void changepassword(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/span")).click();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/ul/li[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input")).sendKeys("2278174Hd");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/input")).sendKeys("admin123");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input")).sendKeys("2278174Hd");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]")).click();
        String expected="password is sucessfully updated ";
        String actual=driver.findElement(By.xpath("//*[@id=\"oxd-toaster_1\"]")).getText();
        Assert.assertEquals(expected,actual);

    }
    @Test
    public void Adduser(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button")).click();
        Select drp=new Select(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[1]")));
        drp.selectByVisibleText("ESS");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div/input")).sendKeys("Peter Mac Anderson");
        Select status=new Select(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div/div[1]")));
        status.selectByVisibleText("Enabled");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input")).sendKeys("hatem");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input")).sendKeys("2278174Hd");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input")).sendKeys("2278174Hd");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]")).click();
        List<WebElement> users=driver.findElements(By.className("orangehrm-container"));
        boolean expected=true;
        boolean actual= users.contains(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div/div[3]")));
        Assert.assertEquals(expected,actual);

    }
    @Test
    public void removeuser(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div/div[1]/div[2]/div/div/button[1]/i")).click();
        Alert a=driver.switchTo().alert();
        a.accept();
        List<WebElement> users=driver.findElements(By.className("orangehrm-container"));
        boolean expected=false;
        boolean actual=users.contains(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div/div[2]")));
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void Addskill(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        Select qualif=new Select(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[3]")));
        qualif.selectByVisibleText("Qualifications ");
        Select skill=new Select(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[3]"));
        skill.selectByVisibleText("Skills");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[1]/div/button")).click();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")).sendKeys("software testing");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/textarea")).sendKeys("testing software ");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]")).click();
        List<WebElement> skills=driver.findElements(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]"));
        boolean expected =true;
        boolean actual=skills.contains(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div/div[16]/div/div")));
        Assert.assertEquals(expected,actual);

}
@Test
    public void removeskill(){
    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
    Select qualif=new Select(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[3]")));
    qualif.selectByVisibleText("Qualifications ");
    Select skill=new Select(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[3]"));
    skill.selectByVisibleText("Skills");
    driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div/div[11]/div/div/div[1]/div[2]/div/div/button[1]/i")).click();
    Alert a=driver.switchTo().alert();
    a.accept();
    List<WebElement> skills=driver.findElements(By.className("orangehrm-container"));
    boolean expected =false;
    boolean actual=skills.contains(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div/div[11]")));
    Assert.assertEquals(expected,actual);
    }
    @Test
    public void checksupportbutton(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        Select drp=new Select(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/span")));
        drp.selectByVisibleText("Support");
        String expectedurl="https://opensource-demo.orangehrmlive.com/web/index.php/help/support";
        String actualurl=driver.getCurrentUrl();
        Assert.assertEquals(expectedurl,actualurl);
    }
    @Test
    public void checkaboutbutton(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        Select drp=new Select(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/span")));
        drp.selectByVisibleText("About");
        Alert a=driver.switchTo().alert();
        String alertmessage= a.getText();
        System.out.println(alertmessage);
        a.dismiss();
    }
    
}
