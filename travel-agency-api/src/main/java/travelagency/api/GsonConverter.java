package travelagency.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * A utility class to convert Java objects to JSON strings using Gson.
 */
public class GsonConverter {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Private constructor to prevent instantiation of the utility class.
     */
    public GsonConverter() {
    }

    /**
     * Converts a List of objects into a pretty-printed JSON String. It can convert any type of object.
     *
     * @param list The list of objects to be converted to JSON.
     * @return A JSON formatted string representation of the given list.
     */
    public static String listToJSON(List<?> list) {
        return GSON.toJson(list);
    }
}
