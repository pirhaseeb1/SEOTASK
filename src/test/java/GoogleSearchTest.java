
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.List;
public class GoogleSearchTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.edgedriver().setup();

        WebDriver driver = new EdgeDriver();

        try {
            driver.get("https://www.google.com");
            driver.manage().window().maximize();
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("seo for contractors");
            searchBox.submit();
            Thread.sleep(2000);

            List<WebElement> searchResults = driver.findElements(By.cssSelector("div.yuRUbf a"));

            boolean isPagePresent = false;
            String targetUrl = "linkgraph.com"; //

            for (int i = 0; i < Math.min(searchResults.size(), 10); i++) {
                String resultUrl = searchResults.get(i).getAttribute("href");
                System.out.println("At Rank " + (i + 1) + ": " + resultUrl);

                if (resultUrl.contains(targetUrl)) {
                    isPagePresent = true;
                    System.out.println("Webpage Rank: " + (i + 1));
                    break;
                }
            }

            if (!isPagePresent) {
                System.out.println("Webpage is not found in the top 10 search results.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            Thread.sleep(2000);
            driver.quit();
        }
    }
}