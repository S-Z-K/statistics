package com.statistics.statistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class JsonProperties {
    public static void main(String[] args) throws IOException {

        CreateJson();
    }

    
    public static void CreateJson()
            throws IOException {
        JSONArray jsonArray = new JSONArray();
        String[] markers = Markers();
        int quantity = new Random().nextInt(19)+1;

        for (int i=0; i<markers.length; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Маркер", markers[i]);
            jsonObject.put("Количество", Quantity(quantity));

            jsonArray.put(jsonObject);
        }
        Files.write(Paths.get("C:/Users/Admin/input_statistics.json"), jsonArray.toString().getBytes());
    }
    

    public static String[] Markers(){
        int markers_quant = new Random().nextInt(39)+1; //количество найденных маркеров

        if(markers_quant==0)
            return null;
        
        String[] all_markers = new String[]{"Plur nouns", "nominalisations", "pronouns", "this as a pronoun", "generic one",
            "definite article", "demonstrative det", "Nouns", "noun plus noun", "participle II plus noun", "participle I plus noun",
            "common adj plus n", "n plus prep phrase", "n plus -ed clause", "n plus -ing clause", "n plus relative clause",
            "binominal phrases", "predicative adj-s", "derived adj-s", "be&become", "existence verbs", "lexical verbs", "prepositional verbs",
            "derived verbs", "v-s with inanimate subj", "present tenses", "passive voice", "specific amplifiers", "specific adverbs",
            "degree adverbs", "linking adverbials", "purp&conc adv-ls", "extraposed that cl-s", "extraposed to cl-s", "subj pred to-clause",
            "ing-cl-s contr by adj pred", "concessive clauses", "preposition plus which", "coordination tags", "quantifier each", "semideterminers"};
        
        Collections.shuffle(Arrays.asList(all_markers));

        String[] founded_markers = Arrays.asList(all_markers).toArray(String[]::new);
        return Arrays.copyOfRange(founded_markers, 0, markers_quant-1);
    }

    public static JSONArray Quantity(int quantity){
        Random rnd = new Random();
        JSONArray jsonArray = new JSONArray();

        for(int i=0; i<quantity; i++){
            jsonArray.put(rnd.nextInt(100));
        }
        return jsonArray;
    }  
}
