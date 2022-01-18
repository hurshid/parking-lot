package com.dkatalis.parking.utils;

import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UtilsTest {


    @Test
    public void parseFileTest() {
        List<String> expected = new ArrayList<>();
        expected.add("create 6");
        expected.add("park KA-01-HH-1234");
        expected.add("park KA-01-BB-0001");
        expected.add("leave KA-01-BB-0001 4");
        expected.add("status");
        expected.add("park KA-01-P-333");

        URL resource = getClass().getClassLoader().getResource("test_command2.txt");
        assertNotNull(resource);
        List<String> actual = Utils.readFromFile(resource.getPath());
        assertEquals(expected, actual);
    }

    @Test
    public void calculateParkingPrice() {
        assertEquals(30, Utils.calculatePrice(4));
        assertEquals(50, Utils.calculatePrice(6));
        assertEquals(90, Utils.calculatePrice(10));
    }
}
