package codebind.example.assign11_1805;

import java.util.ArrayList;

public class Messages {
    static ArrayList<String> msgs = new ArrayList<String>();
    static ArrayList<String> names = new ArrayList<String>();
    static String currname;
    public Messages() {
    }

    public static String getCurrname() {
        return currname;
    }

    public static void setCurrname(String currname) {
        Messages.currname = currname;
    }

    public static ArrayList<String> getName() {
        return names;
    }

    public static void setName(String name) {
        names.add(name);
    }

    public static void setMsgs(String msg) {
        msgs.add(msg);
    }

    public static ArrayList<String> getMsgs() {
        return msgs;
    }
}
