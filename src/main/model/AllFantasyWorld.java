package model;

import java.util.ArrayList;
import java.util.List;

import static model.Category.*;


// represents everything inside the app, containing list of all worlds,
// list of each category, list of wantTo beenTo and fav
public class AllFantasyWorld {
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
    // EFFECTS: add this fworld to list of all worlds, and add this world to according category list.
    // return true if added successfully, return false if the fworld has already existed in the list to avoid
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

            return true;
        }

        return false;
    }

    //REQUIRES: fw is already in the list of all world
    // MODIFIES: this
    // EFFECTS: delete a world from list of all world and appropriate category it belongs to
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
    }

    //REQUIRES: listToAdd is one of allBeenTom, allWantTo, allFav
    // MODIFIES: this
    //EFFECTS: add a world to the indicated list (beenTo, wantTo, fav)
    // and set the status accordingly, return true if success, else return false (if book has alr existed in the list
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
            return true;
        }
        return false;
    }

}
