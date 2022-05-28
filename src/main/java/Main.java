import java.util.List;

public class Main {
    public static void main(String[] args) {
        JsonParser jsonParser = new JsonParser();
        String json = jsonParser.readJsonFromFile("new_data.json");
        List<Object> list = jsonParser.jsonToObjectList(json, new Employee());
        list.stream().map(obj -> (Employee) obj).forEach(System.out::println);
    }
}
