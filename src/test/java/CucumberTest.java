import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.fiap.restaurant_management.bdd_cucumber_test", "com.fiap.restaurant_management.infra.config"}
)
public class CucumberTest {
}