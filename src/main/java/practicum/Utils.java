package practicum;

import java.util.Random;
import com.github.javafaker.Faker;
import org.jetbrains.annotations.Nullable;


import static practicum.Constants.getDomain;

public class Utils {

    public static String generateFirstName(){
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    public static String generateLastName(){
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    public static String generateEmail(@Nullable String userName){
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        if (userName == null){
            userName = generateFirstName();
        }
        sb.append(userName + "." + generateLastName().toLowerCase());
        sb.append(rnd.nextInt(100));
        sb.append("@");
        sb.append(String.format("hostname-%d.", rnd.nextInt(100)));
        sb.append(getDomain());
        return sb.toString();
    }
}
