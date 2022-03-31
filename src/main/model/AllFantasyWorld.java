package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

import static model.Category.*;


// represents everything inside the app, containing list of all worlds,
// list of each category, list of wantTo beenTo and fav
public class AllFantasyWorld implements Writable {
    private List<FantasyWorld> allWorld;

    private List<FantasyWorld> allBeenTo;
    private List<FantasyWorld> allWantTo;
    private List<FantasyWorld> allFav;

    private List<FantasyWorld> cartoon;
    private List<FantasyWorld> movie;
    private List<FantasyWorld> book;
    private List<FantasyWorld> game;

    //EFFECTS: construct a world that has the all of above lists set to empty.
    public AllFantasyWorld() {
        allWorld = new ArrayList<>();
        allBeenTo = new ArrayList<>();
        allFav = new ArrayList<>();
        allWantTo = new ArrayList<>();
        cartoon = new ArrayList<>();
        movie = new ArrayList<>();
        book = new ArrayList<>();
        game = new ArrayList<>();
    }

    //getters
    public List<FantasyWorld> getCartoon() {
        return cartoon;
    }

    public List<FantasyWorld> getGame() {
        return game;
    }

    public List<FantasyWorld> getMovie() {
        return movie;
    }

    public List<FantasyWorld> getBook() {
        return book;
    }

    public List<FantasyWorld> getBeenTo() {
        return allBeenTo;
    }

    public List<FantasyWorld> getWantTo() {
        return allWantTo;
    }

    public List<FantasyWorld> getFav() {
        return allFav;
    }

    public List<FantasyWorld> getAllWorld() {
        return allWorld;
    }

    //MODIFIES: this
    // EFFECTS: add this fw to list of all worlds, and add this world to according category list.
    // return true if added successfully, return false if the fw has already existed in the list to avoid
    // duplication.
    public boolean storeWorld(FantasyWorld fw) {
        if (allWorld.contains(fw) == false) {
            allWorld.add(fw);

            Category cat = fw.getCategory();
            if (cat == CARTOON) {
                cartoon.add(fw);
            } else if (cat == BOOK) {
                book.add(fw);
            } else if (cat == MOVIE) {
                movie.add(fw);
            } else {
                game.add(fw);
            }
            EventLog.getInstance().logEvent(new Event("Store " + fw.getName()
                    + " with category " + cat.toString()));
            return true;
        }

        return false;
    }

    //REQUIRES: fw is already in the list of all world
    // MODIFIES: this
    // EFFECTS: delete a world from list of all world and appropriate category it belongs to and other sublists
    public void deleteWorld(FantasyWorld fw) {
        allWorld.remove(fw);

        Category cat = fw.getCategory();
        if (cat == CARTOON) {
            cartoon.remove(fw);
        } else if (cat == BOOK) {
            book.remove(fw);
        } else if (cat == MOVIE) {
            movie.remove(fw);
        } else {
            game.remove(fw);
        }
        if (allBeenTo.contains(fw)) {
            allBeenTo.remove(fw);
        }
        if (allWantTo.contains(fw)) {
            allWantTo.remove(fw);
        }
        if (allFav.contains(fw)) {
            allFav.remove(fw);
        }

        EventLog.getInstance().logEvent(new Event("Delete " + fw.getName() + " from My Fantasy World."));
    }

    //REQUIRES: fw is in the listToRemove, listToRemove is one of allBeenTom, allWantTo, allFav
    //MODIFIES: this
    //EFFECTS: remove a world from the indicated list beenTo/wantTo/fav, setting the status appropriately
    public void remove(List<FantasyWorld> listToRemove, FantasyWorld fw) {
        listToRemove.remove(fw);

        if (listToRemove == allBeenTo) {
            fw.setBeenTo(false);
        } else if (listToRemove == allWantTo) {
            fw.setWantTo(false);
        } else {
            fw.setFav(false);
        }

        EventLog.getInstance().logEvent(new Event("Remove " + fw.getName() + " from a sublist"));
    }

    //REQUIRES: listToAdd is one of allBeenTom, allWantTo, allFav
    // MODIFIES: this
    //EFFECTS: add a world to the indicated list (beenTo, wantTo, fav)
    // and set the status accordingly, return true if success, else return false (if world has alr existed in the list)
    public boolean add(List<FantasyWorld> listToAdd, FantasyWorld fw) {
        if (listToAdd.contains(fw) == false) {

            listToAdd.add(fw);

            if (listToAdd == allBeenTo) {
                fw.setBeenTo(true);
            } else if (listToAdd == allWantTo) {
                fw.setWantTo(true);
            } else {
                fw.setFav(true);
            }
            EventLog.getInstance().logEvent(new Event("Add " + fw.getName() + " to a sublist"));
            return true;
        }
        return false;
    }

    //  EFFECTS: turning the main features of the world state to json obj
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("allWorld", turnToJson(allWorld));
        json.put("allBeenTo", turnToJson(allBeenTo));
        json.put("allWantTo", turnToJson(allWantTo));
        json.put("allFav", turnToJson(allFav));

        json.put("cartoon", turnToJson(cartoon));
        json.put("book", turnToJson(book));
        json.put("movie", turnToJson(movie));
        json.put("game", turnToJson(game));

        return json;
    }

    //EFFECTS: turning lists to jsonArray
    private JSONArray turnToJson(List<FantasyWorld> low) {
        JSONArray jsonArray = new JSONArray();

        for (FantasyWorld fw : low) {
            jsonArray.put(fw.toJson());
        }

        return jsonArray;
    }

}
