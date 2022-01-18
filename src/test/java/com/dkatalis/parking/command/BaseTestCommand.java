package com.dkatalis.parking.command;

import com.dkatalis.parking.model.ParkingLot;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BaseTestCommand {

    protected PrintStream console;
    protected ByteArrayOutputStream bytes;

    protected final ParkingLot parkingLot = ParkingLot.getInstance();

    @Before
    public void setUp() {
        clearParking();

        Command createCommand = new CreateCommand(parkingLot);
        createCommand.validate(new String[]{"create", "1"});
        createCommand.execute();

        bytes = new ByteArrayOutputStream();
        console = System.out;
        System.setOut(new PrintStream(bytes));
    }

    @After
    public void tearDown() {
        System.setOut(console);
        parkingLot.clear();
    }

    private void clearParking() {
        parkingLot.clear();
    }
}
