
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
            // Navigate to Google
            driver.get("https://www.google.com");
            driver.manage().window().maximize();
            // Locate the search bar and input a query
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("seo for contractors");
            searchBox.submit();

            // Wait for a few seconds (optional, could use WebDriverWait instead)
            Thread.sleep(2000);

            // Collect the top 10 search result links
            List<WebElement> searchResults = driver.findElements(By.cssSelector("div.yuRUbf a"));

            // Iterate through the top 10 search results
            boolean isPagePresent = false;
            String targetUrl = "ahrefs.com"; //

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