package practicum.response;

import practicum.UserLogin;

public class UserProfile {
    private boolean success;
    private UserLogin user;
    private String accessToken;
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public UserLogin getUser() {
        return user;
    }

    public boolean isSuccess() {
        return success;
    }

}
