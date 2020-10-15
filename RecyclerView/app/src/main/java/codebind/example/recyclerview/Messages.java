package codebind.example.recyclerview;

import java.util.ArrayList;

public class Messages {
    static ArrayList<String> msgs = new ArrayList<String>();
    static ArrayList<String> names = new ArrayList<String>();
    public Messages() {
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
