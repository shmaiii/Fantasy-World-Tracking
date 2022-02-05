package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Category.*;
import static org.junit.jupiter.api.Assertions.*;

// tests for the FantasyWorld Class
public class FantasyWorldTest {
    FantasyWorld HarryPotter;
    FantasyWorld GumBall;
    FantasyWorld LeagueOfLegends;
    FantasyWorld Riverdale;

    @BeforeEach
    public void setup(){
        HarryPotter = new FantasyWorld("Harry Potter", BOOK);
        GumBall = new FantasyWorld("Gumball", CARTOON);
        LeagueOfLegends = new FantasyWorld("LeagueOfLegends", GAME);
        Riverdale = new FantasyWorld("Riverdale", MOVIE);

    }

    @Test
    public void FantasyWorldTest(){
        assertEquals(HarryPotter.getName(), "Harry Potter");
        assertEquals(GumBall.getCategory(), CARTOON);
        assertFalse(HarryPotter.getBeenTo());
        assertFalse(Riverdale.getFav());
        assertFalse(LeagueOfLegends.getWantTo());
    }

    @Test
    public void TestSetBeenTo(){
        assertFalse(HarryPotter.getBeenTo());
        HarryPotter.setBeenTo(true);
        assertTrue(HarryPotter.getBeenTo());
        assertFalse(GumBall.getBeenTo());
        HarryPotter.setBeenTo(false);
        assertFalse(HarryPotter.getBeenTo());
    }

    @Test
    public void TestChangeWantTo(){
        assertFalse(HarryPotter.getWantTo());
        HarryPotter.setWantTo(true);
        assertTrue(HarryPotter.getWantTo());
        assertFalse(Riverdale.getWantTo());
        HarryPotter.setWantTo(false);
        assertFalse(HarryPotter.getWantTo());
    }

    @Test
    public void TestChangeFav(){
        assertFalse(GumBall.getFav());
        GumBall.setFav(true);
        assertTrue(GumBall.getFav());
        assertFalse(HarryPotter.getFav());
        GumBall.setFav(false);
        assertFalse(GumBall.getFav());
    }

}
