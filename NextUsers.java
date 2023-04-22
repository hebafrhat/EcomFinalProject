package DDT;

public class NextUsers {
    private static String title;
    private static String firstName;
    private static String lastName;
    public static String email;
    private static String password;
    private static int phone;
    private static String apartment;
    private static int houseNumber;
    private static String city;
    private static String state;
    private static int zipCode;
    private static String country;

    public NextUsers(String title, String firstname, String lastName, String email, String password, int phone, String apartment, int houseNumber, String city, String state, int zipCode, String country) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.apartment = apartment;
        this.houseNumber = houseNumber;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }


    public static String getTitle() {
        return title;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getemail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    public static int getPhone() {
        return phone;
    }

    public static String getApartment() {
        return apartment;
    }

    public static int getHouseNumber() {
        return houseNumber;
    }

    public static String getCity() {
        return city;
    }

    public static String getState() {
        return state;
    }

    public static int getzipCode() {
        return zipCode;
    }

    public static String getCountry() {
        return country;
    }

    public static void setTitle(String title) {
        NextUsers.title = title;
    }

    public static void setFirstName(String firstName) {
        NextUsers.firstName = firstName;
    }

    public static void setLastName(String lastName) {
        NextUsers.lastName = lastName;
    }

    public static void setEmail(String email) {
        NextUsers.email = email;
    }

    public static void setPassword(String password) {
        NextUsers.password = password;
    }

    public static void setPhone(int phone) {
        NextUsers.phone = phone;
    }

    public static void setApartment(String apartment) {
        NextUsers.apartment = apartment;
    }

    public static void setHouseNumber(int houseNumber) {
        NextUsers.houseNumber = houseNumber;
    }

    public static void setCity(String city) {
        NextUsers.city = city;
    }

    public static void setState(String state) {
        NextUsers.state = state;
    }

    public static void setZipCode(int zipCode) {
        NextUsers.zipCode = zipCode;
    }

    public static void setCountry(String country) {
        NextUsers.country = country;
    }


    public static String passwordIsStrong(String Password) {
        if (password.length() >= 8 && password.length() <= 15) {
            return Password;
        } else
            return  "password is Not strong";
    }

    public static String IsEmailValid(String email) {
        if (email.contains("@") && email.endsWith("com")) {
            return email;
        } else
            return "email is wrong!";
    }

}
