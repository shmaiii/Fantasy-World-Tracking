package persistence;

import model.AllFantasyWorld;
import static model.Category.*;
import model.FantasyWorld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
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

        }catch (IOException e){
            fail("There shouldn't be an exception here");
        }
    }

    @Test
    public void testWriterGeneralWorld(){
        try {
            writer = new JsonWriter("./data/testWriterGeneralWorld.json");
            myWorld.storeWorld(new FantasyWorld("HarryPotter", MOVIE));
            myWorld.storeWorld(new FantasyWorld("AliceInWonderLand", BOOK));
            writer.open();
            writer.write(myWorld);
            writer.close();

        } catch (IOException e){
            fail("There shouldn't be exception here");
        }
    }
}
