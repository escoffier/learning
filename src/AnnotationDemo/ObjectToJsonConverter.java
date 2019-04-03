package AnnotationDemo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectToJsonConverter {

    private void checkIfSerializable(Object obj) throws JsonSerializationException{
        if (Objects.isNull(obj)) {
            throw new JsonSerializationException("The object to serialize is null");
        }

        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(JsonSerializable.class)) {
            throw new JsonSerializationException("The class "
                    + clazz.getSimpleName()
                    + " is not annotated with JsonSerializable");
        }
    }

    private void initializeObject(Object object) throws Exception{
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Init.class)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

    private String getJsonString(Object object) throws Exception{
        Class<?> clazz = object.getClass();
        Map<String, String> jsonElementsMap = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonElement.class))
            jsonElementsMap.put(field.getName(), (String) field.get(object));
        }

        String jsonString = jsonElementsMap.entrySet().stream()
                .map(entry -> "\"" + entry.getKey() + "\":\""
                        + entry.getValue() + "\"")
                .collect(Collectors.joining(","));

        return "{" + jsonString + "}";
    }

    public String convertToJson(Object object) throws JsonSerializationException {
        try {
            checkIfSerializable(object);
            initializeObject(object);
            return getJsonString(object);

        } catch (Exception e) {
            throw new JsonSerializationException(e.getMessage());
        }
    }

//    @Test
//    public void givenObjectSerializedThenTrueReturned() throws JsonSerializationException {
//    Person person = new Person("soufiane", "cheouati", "34");
//    ObjectToJsonConverter serializer = new ObjectToJsonConverter();
//    String jsonString = serializer.getJsonString(person);
//    assertEquals(
//                      "{\"personAge\":\"34\",\"firstName\":\"Soufiane\",\"lastName\":\"Cheouati\"}",
//                      jsonString);
//    }

    public static void main(String[] args) throws Exception {
        Person person = new Person("soufiane", "cheouati", "34" , "road");
        ObjectToJsonConverter serializer = new ObjectToJsonConverter();
        String jsonString = serializer.convertToJson(person);
        System.out.println(jsonString);
    }
}
