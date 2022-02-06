package ui;

import model.FantasyWorld;

import java.util.List;
import java.util.Scanner;

// My Fantasy World Application
public class WorldApp {

    // EFFECTS: construct a WorldApp that sets the initial state of the AllFantasyWorld.
    public WorldApp() {
        //stub
    }

    // EFFECTS: show available options and take users' command
    // parent menu including view and create world
    // view option includes view by categories and view list
    // view category includes 4 categories
    // view lists includes option to view everything, or to view fav beento wantto list
    public void chooseFromMenu() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: create a fantasy world and store this world in the all fantasy world
    public void createWorld() {
        //stub
    }

    // REQUIRES: lofw is one of the 4 category lists
    // EFFECTS: show every worlds exist inside the indicated category list
    // if the indicated list is empty then print "You have nothing here yet want to add a world?"
    // yes/no then leave it or createWorld.
    public void viewListCategory(List<FantasyWorld> lofw) {
        //stub
    }

    //EFFECTS: show all the worlds created, with each world having the option of mark as or delete
    // mark as will mark world as beenTo, wantTo, or fav. No marking is okay
    public void viewListallWorld() {
        //stub
    }

    //REQUIRES: lofw is one of allworld, beenTo, wantTo, or fav list
    //EFFECTS: show world in the indicated list, with each world having the option of remove
    public void viewListSubLists(List<FantasyWorld> lofw) {
        //stub
    }

    // REQUIRES: fw is in allWorld list
    //MODIFIES: this
    //EFFECT: delete fw from all lists ever
    public void delete(FantasyWorld fw) {
        //stub
    }

    //REQUIRES: fw is in lofw
    // MODIFEIS: this
    // EFFECTS: remove fw from the indicated list, but this fw will still be present in other lists
    public void remove(FantasyWorld fw, List<FantasyWorld> lofw) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: display options of lists to mark as, then adding the fw to user input indicated list
    public void markAs(FantasyWorld fw) {
        //stub
    }


}
