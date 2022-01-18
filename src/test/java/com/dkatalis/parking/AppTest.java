package com.dkatalis.parking;


import com.dkatalis.parking.constants.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AppTest {

    private PrintStream console;
    private ByteArrayOutputStream bytes;
    private String file;

    @Before
    public void setUp() {
        bytes = new ByteArrayOutputStream();
        console = System.out;
        System.setOut(new PrintStream(bytes));

        URL resource = getClass().getClassLoader().getResource("test_command.txt");
        assertNotNull(resource);
        this.file = resource.getPath();
    }

    @After
    public void tearDown() {
        System.setOut(console);
    }

    @Test
    public void testBasicApp() {

        App.main(new String[]{this.file});

        assertEquals(String.format(Constants.CREATED_PARKING, 6) +
                String.format(Constants.ALLOCATED, 1) +
                String.format(Constants.LEAVE_CAR, "KA-01-BB-0001", 1, 30) +
                Constants.STATUS_TITLE, bytes.toString());
    }

    @Test
    public void testWithWrongArguments() {
        App.main(new String[]{this.file, "test"});

        assertEquals("Invalid argument\n", bytes.toString());
    }
}