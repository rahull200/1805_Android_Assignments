package codebind.example.viewpagerandfragments;

public class Profile {
    final static String name = "Ram";
    final static String number = "9822786553";
    final static String email = "ram@gmail.com";
    final static String pass1 = "123";
    final static String pass2 = "123";
    public Profile() {
    }

    public static String getName() {
        return name;
    }

    public static String getNumber() {
        return number;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPass1() {
        return pass1;
    }

    public static String getPass2() {
        return pass2;
    }
}
