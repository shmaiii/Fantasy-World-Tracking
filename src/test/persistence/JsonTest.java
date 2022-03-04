package persistence;

import model.Category;
import model.FantasyWorld;

import static org.junit.jupiter.api.Assertions.assertEquals;

//citation: modelled on the JsonSerializationDemo project provided on EDx
public class JsonTest {
    protected void checkWorld(String name, Category cat, FantasyWorld fw){
        assertEquals(name, fw.getName());
        assertEquals(cat, fw.getCategory());
    }
}
