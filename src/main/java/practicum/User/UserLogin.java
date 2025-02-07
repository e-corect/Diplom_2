package practicum.User;

public class UserLogin{

    String email;

    String password;

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
