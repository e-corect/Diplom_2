package practicum;

import java.util.Random;

public class Constants {
    public static final String HOST = "https://stellarburgers.nomoreparties.site/api";
    public static final String USER_REGISTER_PATH = "/auth/register";
    public static final String USER_LOGIN_PATH = "/auth/login";
    public static final String USER_LOGOUT_PATH = "/auth/logout";
    public static final String AUTH_USER_PATH = "/auth/user";
    public static final String INGREDIENTS_PATH = "/ingredients";
    public static final String ALL_ORDERS_PATH = "/orders/all";
    public static final String ORDERS_PATH = "/orders";

    public static final String CONTENT_TYPE_HEADER_NAME = "Content-Type";
    public static final String JSON_HEADER_VALUE = "application/json";

    public static final String[] FIRST_LVL_DOMAINS = {"example", "track", "template", "etc", "exe", "jpg", "ico", "rar"};

    public static String getDomain(){
        Random random = new Random();
        return FIRST_LVL_DOMAINS[random.nextInt(FIRST_LVL_DOMAINS.length)];
    }

    public static final String USER_PWD = "qwertys1234567";

    public static final String USER_EXIST_ERROR = "User already exists";
    public static final String REQUIRED_FIELDS_ERROR = "Email, password and name are required fields";
    public static final String INCORRECT_CREDENTIALS_ERROR = "email or password are incorrect";
}
