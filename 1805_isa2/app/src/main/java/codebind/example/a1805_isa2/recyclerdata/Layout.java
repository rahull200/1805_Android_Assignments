package codebind.example.a1805_isa2.recyclerdata;

public class Layout {
    private String name;
    private String img;
    private String nickname;
    private String appearance;
    private String portrayed;

    public Layout(String name, String img, String nickname, String appearance, String portrayed) {
        this.name = name;
        this.img = img;
        this.nickname = nickname;
        this.appearance = appearance;
        this.portrayed = portrayed;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAppearance() {
        return appearance;
    }

    public String getPortrayed() {
        return portrayed;
    }

    public  void setNickname(String nickname){ this.nickname=nickname; }
}
