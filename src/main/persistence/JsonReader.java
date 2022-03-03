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
        addWorlds(myWorld, jsonObject);
        return myWorld;

    }

    // MODIFIES: w
    // EFFECTS: parses worlds from JSON object and add them to worldstate
    private void addWorlds(AllFantasyWorld w, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("allWorld");
        for (Object json : jsonArray) {
            JSONObject nextWorld = (JSONObject) json;
            addWorld(w, nextWorld);
        }
    }

    //MODIFIES: w
    //EFFECTS: parses world from JSON Object and adds it to worldstate
    private void addWorld(AllFantasyWorld w, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Category cat = Category.valueOf(jsonObject.getString("category"));
        FantasyWorld fw = new FantasyWorld(name, cat);
        w.storeWorld(fw);
    }


}
