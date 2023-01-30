import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {
    private final static String USERNAME = "dimazaytsev91@gmail.com";
    private final static String PASSWORD = "Headway91QQWE18";
    private final static String NAME_PROJECT = "NewProject";
    private final static String REPOSITORY_NAME = "//tbody//tr[2]//td//a[text()='%s']";

    @BeforeClass
    public void url()   {
        open("https://app.qase.io/login");
    }

    @Test
    public void positiveLoginTest() {
        $(By.id("inputEmail")).setValue(USERNAME);
        $(By.id("inputPassword")).setValue(PASSWORD);
        $(By.id("btnLogin")).click();
        $(By.id("createButton")).shouldBe(visible);
        Assert.assertTrue($(By.id("createButton")).isDisplayed());
    }

    @Test
    public void projectTest(){
        $(By.id("inputEmail")).setValue(USERNAME);
        $(By.id("inputPassword")).setValue(PASSWORD);
        $(By.id("btnLogin")).click();
        $(By.id("createButton")).click();
        $(By.id("project-name")).setValue(NAME_PROJECT);
        $(By.id("project-code")).setValue("QWE");
        $(By.id("description-area")).setValue("HelloWorld");
        $(By.xpath("//span[text()='Create project']//ancestor::button")).click();
        Assert.assertTrue($(By.id("createButton")).isDisplayed());
    }

    @Test
    public void testCaseTest(){
        $(By.id("inputEmail")).setValue(USERNAME);
        $(By.id("inputPassword")).setValue(PASSWORD);
        $(By.id("btnLogin")).click();
        $(By.id("createButton")).click();
        $(By.id("project-name")).setValue(NAME_PROJECT);
        $(By.id("project-code")).setValue("ABC");
        $(By.id("description-area")).setValue("HelloWorld2");
        $(By.xpath("//span[text()='Create project']//ancestor::button")).click();
        $(By.xpath("//a[text()=' Case']")).click();
        $(By.id("title")).setValue("Authorization");
        $(By.xpath("//label[text()='Description']//ancestor::div[@class='col-12 form-group']//p")).
                setValue("HelloWorld");
        $(By.id("save-case")).click();
        $(By.id("create-case-button")).shouldBe(visible);
        Assert.assertTrue($(By.id("create-case-button")).isDisplayed());
    }

    @Test
    public void createDefectsTest() {
        $(By.id("inputEmail")).setValue(USERNAME);
        $(By.id("inputPassword")).setValue(PASSWORD);
        $(By.id("btnLogin")).click();
        $(By.xpath(String.format(REPOSITORY_NAME, NAME_PROJECT))).click();
        $(By.xpath("//span[text()='Defects']//ancestor::a")).click();
        $(By.xpath("//a[text()='Create new defect']")).click();
        $(By.id("title")).setValue("Paymant page have some problems");
        $(By.xpath("//button[text()='Create defect']")).click();
        $(By.xpath("//p[@class='Q9IhIQ']")).setValue("Payment not going through");
        $(By.xpath("//button[text()='Create defect']")).click();
        $(By.xpath("//a[text()='Export']")).shouldBe(visible);
        Assert.assertTrue($(By.xpath("//a[text()='Export']")).isDisplayed());
    }
}