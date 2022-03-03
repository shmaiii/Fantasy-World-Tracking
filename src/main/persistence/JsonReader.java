package persistence;

import model.AllFantasyWorld;
import model.Category;
import model.FantasyWorld;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

//citation: modelled on the JsonSerializationDemo project provided on EDx
//Represent a reader that reads world state from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads world from file and returns it, throw IOException if error occurs
    public AllFantasyWorld read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorld(jsonObject);
    }

    //EFFECTS: reads source file as string and return it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parse world from JSON object and return it
    private AllFantasyWorld parseWorld(JSONObject jsonObject) {
        AllFantasyWorld myWorld = new AllFantasyWorld();
        addMainWorlds(myWorld, jsonObject);
        addSubWorlds(myWorld, jsonObject);
        return myWorld;

    }

    // MODIFIES: w
    // EFFECTS: parses worlds from JSON object and add them to worldstate
    private void addMainWorlds(AllFantasyWorld w, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("allWorld");
        for (Object json : jsonArray) {
            JSONObject nextWorld = (JSONObject) json;
            addMainWorld(w, nextWorld);
        }
    }

    //MODIFIES: w
    //EFFECTS: parses worlds from JSON obj, add them to worldstate in 3 sublists
    private void addSubWorlds(AllFantasyWorld w, JSONObject jsonObject) {
        List<String> otherLists = new ArrayList<>();
        otherLists.add("allBeenTo");
        otherLists.add("allWantTo");
        otherLists.add("allFav");
        for (String l : otherLists) {
            JSONArray jsonArray = jsonObject.getJSONArray(l);
            for (Object json : jsonArray) {
                JSONObject nextWorld = (JSONObject) json;
                addSubWorld(w, nextWorld, l);
            }
        }
    }

    //MODIFIES: w
    //EFFECTS: parses world from JSON Object and adds it to worldstate
    private void addMainWorld(AllFantasyWorld w, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Category cat = Category.valueOf(jsonObject.getString("category"));
        FantasyWorld fw = new FantasyWorld(name, cat);
        w.storeWorld(fw);
    }

    //MODIFIES:W
    //EFFECTS: parses world from JSON Object, finds the world from existing worlds and adds to appropriate
    // sublists
    private void addSubWorld(AllFantasyWorld w, JSONObject jsonObject, String listToAdd) {
        String name = jsonObject.getString("name");
        Category cat = Category.valueOf(jsonObject.getString("category"));
        for (FantasyWorld fw : w.getAllWorld()) {
            if (fw.getName().equals(name) && fw.getCategory().equals(cat)) {
                if (listToAdd.equals("allBeenTo")) {
                    w.add(w.getBeenTo(), fw);
                }
                if (listToAdd.equals("allWantTo")) {
                    w.add(w.getWantTo(), fw);
                }
                if (listToAdd.equals("allFav")) {
                    w.add(w.getFav(), fw);
                }
            }
        }
    }


}
