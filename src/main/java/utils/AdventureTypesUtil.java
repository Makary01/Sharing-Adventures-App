package utils;

import java.util.ArrayList;

public class AdventureTypesUtil {

    private static final String[] adventureTypesArray = {"Other", "Hiking", "Rock Climbing", "Ice Climbing",
            "Scuba Diving", "Biking", "Skiing", "Snowboarding", "Horseback Riding", "Camping",
            "Hunting", "Fishing"};

    public static ArrayList<String> getTypes(){
        ArrayList<String> adventureTypes = new ArrayList<>();
        for (String s : adventureTypesArray) {
            adventureTypes.add(s);
        }
        return adventureTypes;
    }

    public static Boolean verifyType(String type){
        for (String s : adventureTypesArray) {
            if(type.equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;

    }

}
