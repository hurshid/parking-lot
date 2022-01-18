package com.dkatalis.parking.command;

import com.dkatalis.parking.constants.Constants;
import com.dkatalis.parking.exceptions.ParkingLotException;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CreateCommandTest extends BaseTestCommand {

    @Before
    public void setUp() {
        bytes = new ByteArrayOutputStream();
        console = System.out;
        System.setOut(new PrintStream(bytes));
    }

    @Test
    public void testCreateParkingLot() {
        Command command = new CreateCommand(parkingLot);
        command.validate(new String[]{"create", "10"});
        command.execute();

        assertEquals(String.format(Constants.CREATED_PARKING, 10), bytes.toString());
        assertEquals(10, parkingLot.getCapacity());
    }

    @Test(expected = ParkingLotException.class)
    public void testCreateParkingLotMultipleTimes() {
        Command command = new CreateCommand(parkingLot);
        command.validate(new String[]{"create", "10"});
        command.execute();

        assertEquals(String.format(Constants.CREATED_PARKING, 10), bytes.toString());

        Command cmd = new CreateCommand(parkingLot);
        cmd.validate(new String[]{"create", "5"});
        cmd.execute();
    }
}