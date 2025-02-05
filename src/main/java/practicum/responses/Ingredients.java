package practicum.responses;

import java.util.ArrayList;

public class Ingredients {
    private boolean success;

    private ArrayList<Datum> data;

    public ArrayList<Datum> getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }
}
