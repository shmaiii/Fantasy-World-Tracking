package model;

import org.json.JSONObject;
import persistence.Writable;

// Represent a FantasyWorld having name, category, status of
// beenTo, wantTo, and fav.
public class FantasyWorld implements Writable {

    private String name;
    private Category category;
    private boolean beenTo;
    private boolean wantTo;
    private boolean fav;

    // EFFECTS: construct a FantasyWorld with given name and category, with statuses
    // of beenTo, wantTo, and fav set to false
    public FantasyWorld(String name, Category category) {
        this.name = name;
        this.category = category;
        this.beenTo = false;
        this.wantTo = false;
        this.fav = false;
    }

    //getters
    public String getName() {
        return this.name;
    }

    public Category getCategory() {
        return category;
    }

    public boolean getBeenTo() {
        return beenTo;
    }

    public boolean getWantTo() {
        return wantTo;
    }

    public boolean getFav() {
        return fav;
    }

    //MODIFIES: this
    // EFFECTS: change the status beenTo of the world by flipping
    public void setBeenTo(boolean b) {
        beenTo = b;
    }

    //MODIFIES: this
    // EFFECTS: change the status beenTo of the world by flipping
    public void setWantTo(boolean b) {
        wantTo = b;
    }

    //MODIFIES: this
    // EFFECTS: change the status beenTo of the world by flipping
    public void setFav(boolean b) {
        fav = b;
    }

    //EFFECTS: turn the FantasyWorld obj to JSON object to be saved to data
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("category", category);
        return json;
    }

}
