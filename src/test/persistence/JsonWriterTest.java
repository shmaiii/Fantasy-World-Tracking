package persistence;

import model.AllFantasyWorld;
import static model.Category.*;
import model.FantasyWorld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//citation: modelled on the JsonSerializationDemo project provided on EDx
public class JsonWriterTest extends JsonTest {
    AllFantasyWorld myWorld;
    JsonWriter writer;

    @BeforeEach
    public void setup() {
        myWorld = new AllFantasyWorld();
    }

    @Test
    public void testInvalidFile(){
        try{
            writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("There should be an exception here");
        } catch (IOException e){
            // all good
        }
    }

    @Test
    public void testWriterEmptyWorld(){
        try {
            writer = new JsonWriter("./data/testWriterEmptyWorld.json");
            writer.open();
            writer.write(myWorld);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorld.json");
            myWorld = reader.read();
            assertEquals(0, myWorld.getAllWorld().size());
            assertEquals(0, myWorld.getBeenTo().size());
            assertEquals(0, myWorld.getWantTo().size());
            assertEquals(0, myWorld.getFav().size());

        }catch (IOException e){
            fail("There shouldn't be an exception here");
        }
    }

    @Test
    public void testWriterGeneralWorld(){
        try {
            writer = new JsonWriter("./data/testWriterGeneralWorld.json");
            FantasyWorld toAdd1 = new FantasyWorld("HarryPotter", BOOK);
            myWorld.storeWorld(toAdd1);
            myWorld.storeWorld(new FantasyWorld("AdventureTime", CARTOON));
            myWorld.add(myWorld.getFav(), toAdd1);
            writer.open();
            writer.write(myWorld);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorld.json");
            assertEquals(2, myWorld.getAllWorld().size());
            assertEquals(1, myWorld.getBook().size(), myWorld.getCartoon().size());
            assertEquals(0, myWorld.getGame().size(), myWorld.getMovie().size());
            checkWorld("HarryPotter", BOOK, myWorld.getAllWorld().get(0));
            checkWorld("AdventureTime", CARTOON, myWorld.getAllWorld().get(1));

            assertEquals(1, myWorld.getFav().size());
            checkWorld("HarryPotter", BOOK, myWorld.getFav().get(0));

        } catch (IOException e){
            fail("There shouldn't be exception here");
        }
    }
}
