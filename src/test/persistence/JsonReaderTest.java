package persistence;

import model.AllFantasyWorld;
import static model.Category.*;
import model.FantasyWorld;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//citation: modelled on the JsonSerializationDemo project provided on EDx
public class JsonReaderTest extends JsonTest {
    JsonReader reader;

    @Test
    public void testReaderInvalidFile(){
        reader = new JsonReader("./data/fileThatDoesntExist.json");
        try{
            AllFantasyWorld myWorld = reader.read();
            fail("There should be an exception here");
        } catch (IOException e){
            //pass
        }
    }

    @Test
    public void testReaderEmptyWorld(){
        reader = new JsonReader("./data/testReaderEmptyWorld.json");
        try{
            AllFantasyWorld w = reader.read();
            assertEquals(0, w.getAllWorld().size());
            assertEquals(0, w.getBeenTo().size());
            assertEquals(0, w.getWantTo().size());
            assertEquals(0, w.getFav().size());
            assertEquals(0, w.getCartoon().size());
            assertEquals(0, w.getBook().size());
            assertEquals(0, w.getMovie().size());
            assertEquals(0, w.getGame().size());

        } catch (IOException e){
            fail("There should be no exception");
        }
    }

    @Test
    public void testGeneralWorldNoSublist(){
        reader = new JsonReader("./data/testReaderGeneralWorldNoSublist.json");
        try {
            AllFantasyWorld w = reader.read();
            assertEquals(4, w.getAllWorld().size());
            assertEquals(1, w.getGame().size(), w.getCartoon().size());
            assertEquals(1, w.getMovie().size(), w.getBook().size());
            checkWorld("HarryPotter", BOOK, w.getAllWorld().get(0));
            checkWorld("Riverdale", MOVIE, w.getAllWorld().get(3));
            checkWorld("AdventureTime", CARTOON, w.getCartoon().get(0));

            assertEquals(0, w.getWantTo().size(), w.getBeenTo().size());
            assertEquals(0, w.getFav().size());

        } catch (IOException e){
            fail("There should be no exception here");
        }
    }

    @Test
    public void testReaderGeneralWorld(){
        reader = new JsonReader("./data/testReaderGeneralWorld.json");
        try {
            AllFantasyWorld w = reader.read();
            assertEquals(4, w.getAllWorld().size());
            assertEquals(1, w.getGame().size(), w.getCartoon().size());
            assertEquals(1, w.getMovie().size(), w.getBook().size());

            assertEquals(1, w.getBeenTo().size());
            assertEquals(2, w.getFav().size());
            assertEquals(1, w.getWantTo().size());
            checkWorld("HarryPotter", BOOK, w.getAllWorld().get(0));
            checkWorld("Riverdale", MOVIE, w.getAllWorld().get(3));
            checkWorld("AdventureTime", CARTOON, w.getCartoon().get(0));
            checkWorld("LOL", GAME, w.getFav().get(1));
        } catch (IOException e){
            fail("There should be no exception here");
        }
    }

    @Test
    public void testReaderGeneralInvalidSubWorld(){
        reader = new JsonReader("./data/testReaderInvalidSublist.json");
        try {
            AllFantasyWorld w = reader.read();
            assertEquals(1, w.getWantTo().size());
            checkWorld("AdventureTime", CARTOON, w.getWantTo().get(0));
            assertEquals(4, w.getAllWorld().size());

        } catch (IOException e){
            fail("There should be no exception here");
        }
    }

}
