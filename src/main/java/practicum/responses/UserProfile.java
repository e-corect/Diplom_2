package practicum.responses;

public class UserProfile {
    private boolean success;
    private UserNameMail user;
    private String accessToken;
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public UserNameMail getUser() {
        return user;
    }

    public boolean isSuccess() {
        return success;
    }

}
