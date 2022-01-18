package com.dkatalis.parking.command;

import com.dkatalis.parking.constants.Constants;
import com.dkatalis.parking.exceptions.ParkingLotException;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LeaveCommandTest extends BaseTestCommand {

    @Test
    public void testLeave() {

        List<String> commands = Collections.singletonList("park KA-01-HH-1234");
        parkCars(commands);
        assertNull(parkingLot.getAvailableSlot());

        String[] leave = new String[]{"leave", "KA-01-HH-1234", "2"};
        Command command = new LeaveCommand(parkingLot);
        command.validate(leave);
        command.execute();

        String expected = String.format(Constants.ALLOCATED, 1) + String.format(Constants.LEAVE_CAR, "KA-01-HH-1234", 1, 10);
        assertEquals(expected, bytes.toString());
        assertEquals(Integer.valueOf(1), parkingLot.getAvailableSlot());
    }

    @Test(expected = ParkingLotException.class)
    public void testLeaveInvalidHour() {

        List<String> commands = Collections.singletonList("park KA-01-HH-1234");
        parkCars(commands);

        String[] leave = new String[]{"leave", "KA-01-HH-1234", "-2"};
        Command command = new LeaveCommand(parkingLot);
        command.validate(leave);
        command.execute();
    }

    @Test
    public void testLeaveCarNotFound() {

        List<String> commands = Collections.singletonList("park KA-01-HH-1234");
        parkCars(commands);

        String[] leave = new String[]{"leave", "KA-01-HH-1235", "2"};
        Command command = new LeaveCommand(parkingLot);
        command.validate(leave);
        command.execute();

        String expected = String.format(Constants.ALLOCATED, 1) + String.format(Constants.CAR_NOT_FOUND, "KA-01-HH-1235");
        assertEquals(expected, bytes.toString());
    }

    private void parkCars(List<String> commands) {
        for (String command : commands) {
            String[] parkCommand = command.split(" ");
            Command cmd = new ParkCommand(parkingLot);
            cmd.validate(parkCommand);
            cmd.execute();
        }
    }
}