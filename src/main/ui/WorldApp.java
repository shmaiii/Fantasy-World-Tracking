package ui;

import model.AllFantasyWorld;

import static model.Category.*;

import model.FantasyWorld;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// My Fantasy World Application
public class WorldApp extends Frame {
    protected static int WIDTH = 1200;
    protected static int HEIGHT = 1200;

    private static final String JSON_FILE = "./data/worldApp.json";
    protected AllFantasyWorld myWorld;
    private Scanner input;
    private List<FantasyWorld> onQueueDelete;
    private List<FantasyWorld> queueRemoveFromBeenTo;
    private List<FantasyWorld> queueRemoveFromWantTo;
    private List<FantasyWorld> queueRemoveFromFav;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: construct a WorldApp that sets the initial state of the AllFantasyWorld, with an empty onQueue list
    public WorldApp() {
        myWorld = new AllFantasyWorld();
        input = new Scanner(System.in);
        onQueueDelete = new ArrayList<>();
        queueRemoveFromBeenTo = new ArrayList<>();
        queueRemoveFromWantTo = new ArrayList<>();
        queueRemoveFromFav = new ArrayList<>();
        jsonReader = new JsonReader(JSON_FILE);
        jsonWriter = new JsonWriter(JSON_FILE);
    }

    //MODIFIES: this
    //EFFECTS: adding a world to appropriate queue
    public void addToDeleteQueue(FantasyWorld fw) {
        onQueueDelete.add(fw);
    }

    //MODIFIES: this
    //EFFECTS: adding a world to appropriate queue
    public void addToRemoveQueue(List<FantasyWorld> sublist, FantasyWorld fw) {
        if (sublist.equals(myWorld.getBeenTo())) {
            queueRemoveFromBeenTo.add(fw);
        }
        if (sublist.equals(myWorld.getWantTo())) {
            queueRemoveFromWantTo.add(fw);
        }
        if (sublist.equals(myWorld.getFav())) {
            queueRemoveFromFav.add(fw);
        }
    }

    //getters
    public AllFantasyWorld getWorld() {
        return myWorld;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    // EFFECTS: display the initial welcome message and two big parent options - view/create world
    // show available options and take users' command
    public void displayHomePage() {
        new SplashScreenWorld();
        new HomePage(this);
//        System.out.println("Welcome to My Fantasy World");
//        System.out.println("Let's create our own adventures!");
//
//        System.out.println("Choose view-world / view-category / create/ exit");
//        String command = input.next();
//        while (true) {
//            if (command.equals("view-category")) {
//                viewListCategory();
//                break;
//            } else if (command.equals("create")) {
//                createWorld();
//                break;
//            } else if (command.equals("view-world")) {
//                optionForViewWorld();
//                break;
//            } else if (command.equals("exit")) {
//                break;
//            } else {
//                System.out.println("Not a valid command, enter again");
//                command = input.next();
//            }
//        }
    }

    // EFFECTS: show available options for view-world choice
    public void optionForViewWorld() {
        System.out.println("view-all-world // view-been-to // view-want-to // view-fav");
        String option = input.next();
        if (option.equals("view-all-world")) {
            viewListAllWorld();
        } else if (option.equals("view-been-to")) {
            viewListSubLists(myWorld.getBeenTo());
        } else if (option.equals("view-want-to")) {
            viewListSubLists(myWorld.getWantTo());
        } else if (option.equals("view-fav")) {
            viewListSubLists(myWorld.getFav());
        }
    }

    //MODIFIES: this
    //EFFECTS: create a fantasy world and store this world in the all fantasy world
    @SuppressWarnings("methodlength")
    public void createWorld() {
        FantasyWorld fw;
        System.out.println("Name:");
        String name = input.next();
        while (true) {
            System.out.println("Choose from cartoon/game/movie/book");
            String cat = input.next();
            if (cat.equals("cartoon")) {
                fw = new FantasyWorld(name, CARTOON);
                myWorld.storeWorld(fw);
                break;
            } else if (cat.equals("game")) {
                fw = new FantasyWorld(name, GAME);
                myWorld.storeWorld(fw);
                break;
            } else if (cat.equals("book")) {
                fw = new FantasyWorld(name, BOOK);
                myWorld.storeWorld(fw);
                break;
            } else if (cat.equals("movie")) {
                fw = new FantasyWorld(name, MOVIE);
                myWorld.storeWorld(fw);
                break;
            } else {
                System.out.println("Invalid");
            }
        }
        saveWorldState();
        displayHomePage();
    }

    // EFFECTS: prompt user to choose from 4 categories, and then show every world in the indicated category list
    // if the indicated list is empty then print "You have nothing here yet want to add a world?"
    // yes/no then leave it or createWorld.
    public void viewListCategory() {
        System.out.println("Choose from cartoon/ game/ movie/ book");
        String command = input.next();
        while (true) {
            if (command.equals("cartoon")) {
                printACategory(myWorld.getCartoon());
                break;
            } else if (command.equals("movie")) {
                printACategory(myWorld.getMovie());
                break;
            } else if (command.equals("book")) {
                printACategory(myWorld.getBook());
                break;
            } else if (command.equals("game")) {
                printACategory(myWorld.getGame());
                break;
            } else {
                System.out.println("Invalid command, try again");
                command = input.next();
            }
        }
    }

    //EFFECTS: print all elements in the lofw, if the lofw is empty then prompt a message
    public void printACategory(List<FantasyWorld> lofw) {
        checkEmptyListView(lofw);
//        JList list = new JList((ListModel) lofw);
//        list.setLayoutOrientation(JList.VERTICAL);
//        list.setVisibleRowCount(-1);
//        frame.setContentPane(list);
//        frame.setVisible(true);
        for (FantasyWorld w : lofw) {
            System.out.println(w.getName());
        }
        backToHomepage();

    }


    //EFFECTS: show all the worlds created, with each world having the option of mark as or delete
    // mark as will mark world as beenTo, wantTo, or fav.
    public void viewListAllWorld() {
        checkEmptyListView(myWorld.getAllWorld());

        for (FantasyWorld fw : myWorld.getAllWorld()) {
            System.out.println(fw.getName());
            System.out.println("mark-as/delete");
            String command = input.next();
            if (command.equals("mark-as")) {
                markAs(fw);
            }
            if (command.equals("delete")) {
                onQueueDelete.add(fw);
            }
        }
        deleteItemsOnQueue();

        backToHomepage();
    }

    //REQUIRES: lofw is one of beenTo, wantTo, or fav list
    //EFFECTS: show world in the indicated list, with each world having the option of remove
    public void viewListSubLists(List<FantasyWorld> lofw) {
        if (lofw.size() == 0) {
            System.out.println("Nothing to show");
        }

        for (FantasyWorld fw : lofw) {
            System.out.println(fw.getName());
            System.out.println("remove? yes/no");
            String command = input.next();
            if (command.equals("yes")) {
                addToRemoveQueue(lofw, fw);
            }
        }
        removeItemsOnQueue();
        backToHomepage();
    }

    //MODIFIES: this
    //EFFECT: delete fws inside onQueue from all lists ever, remove that world from onQueue after execution
    public void deleteItemsOnQueue() {
        if (onQueueDelete.size() > 0) {
            for (FantasyWorld fw : onQueueDelete) {
                myWorld.deleteWorld(fw);
            }
            onQueueDelete.clear();
        }
        //saveWorldState();
    }

    //REQUIRES: fw in onQueue is in lofw
    // MODIFIES: this
    // EFFECTS: remove fws inside onQueue from the indicated list, but this fw will still be present in other lists
    // remove these fws from onQueue after each execution
    public void removeItemsOnQueue() {
        if (queueRemoveFromBeenTo.size() > 0) {
            for (FantasyWorld fw : queueRemoveFromBeenTo) {
                myWorld.remove(myWorld.getBeenTo(), fw);
            }
            queueRemoveFromBeenTo.clear();
        }
        if (queueRemoveFromWantTo.size() > 0) {
            for (FantasyWorld fw : queueRemoveFromWantTo) {
                myWorld.remove(myWorld.getWantTo(), fw);
            }
            queueRemoveFromWantTo.clear();
        }
        if (queueRemoveFromFav.size() > 0) {
            for (FantasyWorld fw : queueRemoveFromFav) {
                myWorld.remove(myWorld.getFav(), fw);
            }
            queueRemoveFromFav.clear();
        }
        //saveWorldState();
    }

    // MODIFIES: this
    // EFFECTS: display options of lists to mark as, then adding the fw to user input indicated list
    public void markAs(FantasyWorld fw) {
        System.out.println("beenTo // wantTo // fav");
        String choice = input.next();
        if (choice.equals("beenTo")) {
            myWorld.add(myWorld.getBeenTo(), fw);
        } else if (choice.equals("wantTo")) {
            myWorld.add(myWorld.getWantTo(), fw);
        } else if (choice.equals("fav")) {
            myWorld.add(myWorld.getFav(), fw);
        }
        System.out.println("Added!");
        saveWorldState();
    }

    //EFFECT: see if the list is empty, if it is then prompt message
    public void checkEmptyListView(List<FantasyWorld> low) {
        if (low.size() == 0) {
            System.out.println("Nothing to display. Add a world? yes/no");
            String command = input.next();
            if (command.equals("yes")) {
                createWorld();
            }
        }
    }

    //EFFECTS: ask the user whether to want to go back to homepage at the end of each command
    public void backToHomepage() {
        System.out.println("Go back to home page?yes/no");
        String command = input.next();
        if (command.equals("yes")) {
            displayHomePage();
        }
    }

    // EFFECTS: save the worldState to file
    public void saveWorldState() {
        try {
            jsonWriter.open();
            jsonWriter.write(myWorld);
            jsonWriter.close();
        } catch (IOException e) {
            System.out.println("There is an error - exception thrown");
        }
    }

    //EFFECTS: load the worldState from file
    public void loadWorldState() {
        try {
            myWorld = jsonReader.read();
        } catch (IOException e) {
            System.out.println("There is an error - IOException thrown");
        }
    }
}
