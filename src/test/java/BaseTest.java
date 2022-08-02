import configuration.YamlReader;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setUp(){
        YamlReader.setPropertiesFromYAML();
    }
}
