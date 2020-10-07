package codebind.example.recyclerview;

public class Layout {
    private String sender_name;
    private String sender_msg;

    public Layout(String sender_name, String sender_msg) {
        this.sender_name = sender_name;
        this.sender_msg = sender_msg;
    }

    public String getSender_name() {
        return sender_name;
    }

    public String getSender_msg() {
        return sender_msg;
    }
}
