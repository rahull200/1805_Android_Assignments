package codebind.example.assign11_1805;

public class Layout {
    private String sender_name;
    private String gender;


    public Layout(String sender_name, String gender) {
        this.sender_name = sender_name;
        this.gender = gender;

    }

    public String getGender() {
        return gender;
    }

    public String getSender_name() {
        return sender_name;
    }

}
