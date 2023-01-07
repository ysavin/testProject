package frontendtests.frontend.methods;

import org.openqa.selenium.WebDriver;

public class Methods {

    WebDriver driver;

    public Methods(WebDriver driver) {
        this.driver = driver;
    }

    public void waitTime() throws InterruptedException {
        Thread.sleep(1000);
    }
}
