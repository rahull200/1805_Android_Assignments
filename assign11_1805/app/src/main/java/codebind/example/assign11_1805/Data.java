package codebind.example.assign11_1805;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {
    static ArrayList<String> names = new ArrayList<String>();

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(String name) {
        names.add(name);
    }
    public void delete(int pos){
        names.remove(pos);
    }

    public String getdataPos(int pos){
        return names.get(pos);
    }

    public void add(int pos,String value){
        names.add(pos,value);
    }
}
