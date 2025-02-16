package practicum.responses;

public class UserNameMail {

    private String email;

    private String name;

    public UserNameMail(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
