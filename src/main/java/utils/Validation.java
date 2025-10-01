package utils;

import classBuilder.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class Validation {

    public static ArrayList<CashedClass> isValid(ArrayList<String> records) {

        if (records.isEmpty()) {
            throw new RuntimeException("InPut is empty");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<CashedClass> arrayFromCash = new ArrayList<>();

        for (String record : records) {
            try {
                JsonNode jsonNode = objectMapper.readTree(record);
                if (jsonNode.get("fullName") != null) arrayFromCash.add(Author.jsonBuild(record));
                if (jsonNode.get("tile") != null) arrayFromCash.add(Book.jsonBuild(record));
                if (jsonNode.get("name") != null) arrayFromCash.add(Publisher.jsonBuild(record));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        if (!arrayFromCash.isEmpty() && arrayFromCash.size() == records.size()) {
            return arrayFromCash;
        } else throw new RuntimeException("Invalid data in the array");
    }
}
