package codebind.example.a1805_isa2.Data;

import java.util.ArrayList;

import codebind.example.a1805_isa2.recyclerdata.Layout;

public class CharacterData {
    static ArrayList<Layout> characterData = new ArrayList<>(); //Data received from server

    public static ArrayList<Layout> getCharacterData() {
        return characterData;
    }

    public static void setCharacterData(ArrayList<Layout> characterData) {
        CharacterData.characterData = characterData;
    }


}
