package codebind.example.viewpagerandfragments;

public class Profile {
    final static String name = "Ram";
    final static String number = "9822786553";
    final static String email = "ram@gmail.com";
    final static String pass1 = "123";
    final static String pass2 = "123";
    final static String gender = "Male";
    final static String birthday = "05/06/1996";
    final static String district = "South Goa";

    public static String getGender() {
        return gender;
    }

    public static String getBirthday() {
        return birthday;
    }

    public static String getDistrict() {
        return district;
    }

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
