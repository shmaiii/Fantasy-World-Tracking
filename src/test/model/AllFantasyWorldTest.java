package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Category.*;
import static org.junit.jupiter.api.Assertions.*;

// tests for methods in AllFantasyWorld

public class AllFantasyWorldTest {

    FantasyWorld HarryPotter;
    FantasyWorld GumBall;
    FantasyWorld LeagueOfLegends;
    FantasyWorld Riverdale;
    AllFantasyWorld all;


    @BeforeEach
    public void setup(){
        HarryPotter = new FantasyWorld("Harry Potter", BOOK);
        GumBall = new FantasyWorld("Gumball", CARTOON);
        LeagueOfLegends = new FantasyWorld("LeagueOfLegends", GAME);
        Riverdale = new FantasyWorld("Riverdale", MOVIE);

        all = new AllFantasyWorld();

    }

    @Test
    public void AllFantasyWorldTest(){
        assertEquals(all.getAllWorld().size(), 0);
        assertEquals(all.getBeenTo().size(), 0);
        assertEquals(all.getFav().size(), 0);
        assertEquals(all.getWantTo().size(), 0);
        assertEquals(all.getBook().size(), 0);
        assertEquals(all.getCartoon().size(), 0);
        assertEquals(all.getMovie().size(), 0);
        assertEquals(all.getGame().size(), 0);

    }

    @Test
    public void storeWorldTestAllWorld(){
        assertTrue(all.storeWorld(HarryPotter));
        assertEquals(all.getAllWorld().size(), 1);
        assertEquals(all.getAllWorld().get(0), HarryPotter);

        assertTrue(all.storeWorld(GumBall));
        assertEquals(all.getAllWorld().size(), 2);
        assertEquals(all.getAllWorld().get(1), GumBall);

        assertFalse(all.storeWorld(HarryPotter));
        assertEquals(all.getAllWorld().size(), 2);

    }

    @Test
    public void storeWorldTestBook(){
        assertTrue(all.storeWorld(HarryPotter));
        assertEquals(all.getBook().size(), 1);
        assertEquals(all.getBook().get(0), HarryPotter);

        FantasyWorld TheHobbit = new FantasyWorld("The Hobbit", BOOK);
        assertTrue(all.storeWorld(TheHobbit));
        assertEquals(all.getBook().size(), 2);
        assertEquals(all.getBook().get(1), TheHobbit);

        assertFalse(all.storeWorld(HarryPotter));
        assertEquals(all.getBook().size(), 2);
    }

    @Test
    public void storeWorldTestCartoon(){
        assertTrue(all.storeWorld(GumBall));
        assertEquals(all.getCartoon().size(), 1);
        assertEquals(all.getCartoon().get(0), GumBall);

        FantasyWorld AdventureTime = new FantasyWorld("Adventure Time", CARTOON);
        assertTrue(all.storeWorld(AdventureTime));
        assertEquals(all.getCartoon().size(), 2);
        assertEquals(all.getCartoon().get(1), AdventureTime);

        assertFalse(all.storeWorld(GumBall));
        assertEquals(all.getCartoon().size(), 2);
    }
    @Test
    public void storeWorldTestMovie(){
        assertTrue(all.storeWorld(Riverdale));
        assertEquals(all.getMovie().size(), 1);
        assertEquals(all.getMovie().get(0), Riverdale);

        FantasyWorld Umbrella = new FantasyWorld("Umbrella", MOVIE);
        assertTrue(all.storeWorld(Umbrella));
        assertEquals(all.getMovie().size(), 2);
        assertEquals(all.getMovie().get(1), Umbrella);

        assertFalse(all.storeWorld(Riverdale));
        assertEquals(all.getMovie().size(), 2);
    }
    @Test
    public void storeWorldTestGame(){
        assertTrue(all.storeWorld(LeagueOfLegends));
        assertEquals(all.getGame().size(), 1);
        assertEquals(all.getGame().get(0), LeagueOfLegends);

        FantasyWorld Gunny = new FantasyWorld("Gunny", GAME);
        assertTrue(all.storeWorld(Gunny));
        assertEquals(all.getGame().size(), 2);
        assertEquals(all.getGame().get(1), Gunny);

        assertFalse(all.storeWorld(LeagueOfLegends));
        assertEquals(all.getGame().size(), 2);
    }

    @Test
    public void deleteWorldTestBook(){
        all.storeWorld(HarryPotter);
        all.storeWorld(GumBall);

        all.deleteWorld(HarryPotter);
        assertEquals(all.getAllWorld().size(), 1);
        assertEquals(all.getAllWorld().get(0), GumBall);
        assertEquals(all.getBook().size(), 0);
        assertEquals(all.getCartoon().size(), 1);

    }

    @Test
    public void deleteWorldCartoonTest(){
        all.storeWorld(GumBall);
        all.storeWorld(HarryPotter);

        all.deleteWorld(GumBall);
        assertEquals(all.getAllWorld().size(), 1);
        assertEquals(all.getAllWorld().get(0), HarryPotter);
        assertEquals(all.getBook().size(), 1);
        assertEquals(all.getCartoon().size(), 0);
    }

    @Test
    public void deleteWorldGameTest() {
        all.storeWorld(LeagueOfLegends);
        all.storeWorld(HarryPotter);

        all.deleteWorld(LeagueOfLegends);
        assertEquals(all.getAllWorld().size(), 1);
        assertEquals(all.getAllWorld().get(0), HarryPotter);
        assertEquals(all.getBook().size(), 1);
        assertEquals(all.getGame().size(), 0);
    }

    @Test
    public void deleteWorldMovieTest() {
        all.storeWorld(Riverdale);
        all.storeWorld(HarryPotter);

        all.deleteWorld(Riverdale);
        assertEquals(all.getAllWorld().size(), 1);
        assertEquals(all.getAllWorld().get(0), HarryPotter);
        assertEquals(all.getBook().size(), 1);
        assertEquals(all.getMovie().size(), 0);
    }


    @Test
    public void removeTest(){
        all.add(all.getBeenTo(), GumBall);
        all.add(all.getBeenTo(), LeagueOfLegends);
        all.add(all.getFav(), GumBall);

        all.remove(all.getBeenTo(), GumBall);
        assertEquals(all.getBeenTo().size(), 1);
        assertFalse(all.getBeenTo().contains(GumBall));
        assertFalse(GumBall.getBeenTo());
        assertTrue(all.getFav().contains(GumBall));
        assertTrue(GumBall.getFav());

        all.remove(all.getBeenTo(), LeagueOfLegends);
        assertEquals(all.getBeenTo().size(), 0);
        assertFalse(all.getBeenTo().contains(LeagueOfLegends));
        assertFalse(LeagueOfLegends.getBeenTo());

    }

    @Test
    public void removeWantToTest(){
        all.add(all.getWantTo(), GumBall);
        all.add(all.getWantTo(), LeagueOfLegends);

        all.remove(all.getWantTo(), GumBall);
        assertEquals(all.getWantTo().size(), 1);
        assertFalse(all.getWantTo().contains(GumBall));
        assertFalse(GumBall.getWantTo());
        assertTrue(LeagueOfLegends.getWantTo());
    }

    @Test
    public void removeFavTest(){
        all.add(all.getFav(), GumBall);
        all.add(all.getFav(), LeagueOfLegends);

        all.remove(all.getFav(), GumBall);
        assertEquals(all.getFav().size(), 1);
        assertFalse(all.getFav().contains(GumBall));
        assertFalse(GumBall.getFav());
        assertTrue(LeagueOfLegends.getFav());
    }


    @Test
    public void addTest(){
        assertTrue(all.add(all.getBeenTo(), HarryPotter));
        assertEquals(all.getBeenTo().size(), 1);
        assertEquals(all.getBeenTo().get(0), HarryPotter);
        assertTrue(HarryPotter.getBeenTo());

        assertTrue(all.add(all.getBeenTo(), Riverdale));
        assertEquals(all.getBeenTo().get(1), Riverdale);

        assertFalse(all.add(all.getBeenTo(), HarryPotter));
        assertEquals(all.getBeenTo().size(), 2);
        assertTrue(HarryPotter.getBeenTo());

        assertTrue(all.add(all.getFav(), HarryPotter));
        assertEquals(all.getFav().size(), 1);
        assertTrue(HarryPotter.getFav());

        assertTrue(all.add(all.getWantTo(), HarryPotter));
        assertEquals(all.getWantTo().size(), 1);
        assertTrue(HarryPotter.getWantTo());
    }



}
