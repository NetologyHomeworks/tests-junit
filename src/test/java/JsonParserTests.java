import org.junit.jupiter.api.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JsonParserTests {
    JsonParser sut;

    @BeforeEach
    public void init() {
        System.out.println("Test started");
        sut = new JsonParser();
    }

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }


    @AfterEach
    public void finished() {
        System.out.println("Test completed");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Tests completed");
    }

    @Test
    public void testReadJsonResultStringForEmployee() {
        var expected = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"country\": \"USA\",\n" +
                "    \"age\": 25\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"firstName\": \"Ivan\",\n" +
                "    \"lastName\": \"Petrov\",\n" +
                "    \"country\": \"RU\",\n" +
                "    \"age\": 23\n" +
                "  }\n" +
                "]\n";

        assertEquals(expected, sut.readJsonFromFile("new_data.json"));
    }

    @Test
    public void testReadJsonResultStringWrongFile() {
        assertNull(sut.readJsonFromFile("data.json"));
    }

    @Test
    public void testJsonToObjectListForEmployee() {
        List<Object> list = sut.jsonToObjectList(sut.readJsonFromFile("new_data.json"), new Employee());
        var expected = "Employee{id=1, firstName='John', lastName='Smith', country='USA', age=25}\n" +
                "Employee{id=2, firstName='Ivan', lastName='Petrov', country='RU', age=23}\n";
        var result = list.stream().map(obj -> (Employee) obj).map(item -> item.toString() + "\n").collect(Collectors.joining());
        assertEquals(expected, result);
    }

    @Test
    public void testJsonToObjectListWithWrongClass() {
        assertNull(sut.jsonToObjectList(sut.readJsonFromFile("new_data.json"), Employee.class));
    }

    @Test
    public void testJsonToObjectListWithWrongFile() {
        assertNull(sut.jsonToObjectList(sut.readJsonFromFile("data.json"), new Employee()));
    }

    @Test
    public void testJsonToObjectListWithWrongFileAndClass() {
        assertNull(sut.jsonToObjectList(sut.readJsonFromFile("data.json"), Employee.class));
    }
}
