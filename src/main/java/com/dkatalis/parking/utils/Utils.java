package com.dkatalis.parking.utils;

import com.dkatalis.parking.constants.Constants;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Utils {

    private static final Map<String, Integer> commandParamMap = new HashMap<>();
    static {
        commandParamMap.put( Constants.CREATE_PARKING_LOT, 1);
        commandParamMap.put(Constants.PARK, 1);
        commandParamMap.put(Constants.LEAVE, 2);
        commandParamMap.put(Constants.STATUS, 0);
    }

    public static int paramCount(String command) {
        if (commandParamMap.containsKey(command)){
            return commandParamMap.get(command);
        }
        return -1;
    }

    public static List<String> readFromFile(String filePath) {
        List<String> records = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(filePath), Charset.defaultCharset())) {
            lines.forEachOrdered(records::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static int calculatePrice(int hours) {
        if (hours <= 0) {
            return 0;
        }
        int price = 10;
        if (hours > 2) {
            price += (hours - 2) * 10;
        }
        return price;
    }
}
