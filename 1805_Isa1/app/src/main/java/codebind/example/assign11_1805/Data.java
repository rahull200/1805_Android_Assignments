package codebind.example.assign11_1805;

import java.util.ArrayList;

public class Data {
    static ArrayList<String> names = new ArrayList<String>();
    static ArrayList<String > genders = new ArrayList<>();

    public static ArrayList<String> getGender() {
        return genders;
    }

    public static void setGender(String gender) {
        genders.add(gender);
    }


    public ArrayList<String> getNames() {
        return names;
    }

    public String EditName(int pos,String newname){
        names.set(pos,newname);
        return newname;
    }

    public void resetNames(){
        names.clear();
    }
    public void resetGender(){
        genders.clear();
    }

    public void setNames(String name) {
        names.add(name);
    }
    public void delete(int pos){
        names.remove(pos);
    }
    public void deleteGender(int pos){
        genders.remove(pos);
    }


    public String getdataPos(int pos){
        return names.get(pos);
    }

    public void add(int pos,String value){
        names.add(pos,value);
    }



    public String getGenderPos(int pos){
        return genders.get(pos);
    }

    public void addGender(int pos,String value){
        genders.add(pos,value);
    }

    public Integer getScolarshipAmount(){
        Integer sum = 0;
        for(int i=0;i<genders.size();i++){
            if(genders.get(i).equals("Female")){
                sum += 50000;
            }
        }
        return sum;
    }
}
