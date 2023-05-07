package travel.travelagency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import travelagency.api.GsonConverter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GsonConverterTest {

    private GsonConverter gsonConverter;

    @BeforeEach
    public void setUp() {
        gsonConverter = new GsonConverter();
    }

    @Test
    public void testListToJSON() {
        List<String> testList = new ArrayList<>();
        testList.add("hello");
        testList.add("world");

        String expectedJson = "[\n  \"hello\",\n  \"world\"\n]";
        String actualJson = gsonConverter.listToJSON(testList);

        assertEquals(expectedJson, actualJson);
    }
}
