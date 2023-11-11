package com.caw.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utility {

    @SneakyThrows
    public List<List<String>> convertJsonStringToListOfLists(String inputData) {
        // Convert JSON array to List of Maps
        List<Map<String, Object>> listOfMaps = new ObjectMapper()
                .readValue(inputData, new TypeReference<List<Map<String, Object>>>() {});

        List<List<String>> listOfLists = new ArrayList<>();
        /*Extract each key value from list of maps and
        put it into the listOfLists*/
        for (Map<String, Object> map : listOfMaps) {
            List<String> tempList = new ArrayList<>();

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                // Convert each value to a string and add it to the tempList
                tempList.add(String.valueOf(entry.getValue()));
            }

            // Add the tempList to the listOfLists
            listOfLists.add(tempList);
        }
        return listOfLists;
    }
}
