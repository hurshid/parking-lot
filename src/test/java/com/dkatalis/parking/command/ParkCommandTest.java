package com.dkatalis.parking.command;

import com.dkatalis.parking.constants.Constants;
import com.dkatalis.parking.exceptions.ParkingLotException;
import org.junit.Test;

import static com.dkatalis.parking.constants.Constants.PARKING_CAPACITY_INVALID;
import static com.dkatalis.parking.constants.Constants.PARKING_LOT_FULL;
import static org.junit.Assert.assertEquals;

public class ParkCommandTest extends BaseTestCommand {

    @Test
    public void testParkCar() {

        String[] parkCommand = new String[]{"park", "KA-01-HH-1234"};
        Command command = new ParkCommand(parkingLot);
        command.validate(parkCommand);
        command.execute();

        assertEquals(String.format(Constants.ALLOCATED, 1), bytes.toString());
    }

    @Test
    public void testParkCarWhenParkingFull() {

        String[] parkCommand = new String[]{"park", "KA-01-HH-1234"};
        Command command = new ParkCommand(parkingLot);
        command.validate(parkCommand);
        command.execute();
        assertEquals(String.format(Constants.ALLOCATED, 1), bytes.toString());

        parkCommand = new String[]{"park", "KA-01-HH-1235"};
        Command command2 = new ParkCommand(parkingLot);
        command2.validate(parkCommand);
        command2.execute();
        assertEquals(String.format(Constants.ALLOCATED, 1) + PARKING_LOT_FULL, bytes.toString());
    }

    @Test(expected = ParkingLotException.class)
    public void testParkWhenProvidedWrongCommandParam() {

        String[] parkCommand = new String[]{"park", "KA-01-HH-1234", "1"};
        Command command = new ParkCommand(parkingLot);
        command.validate(parkCommand);
        command.execute();
    }

    @Test(expected = ParkingLotException.class)
    public void testParkCarWhenCapacityZero() {

        parkingLot.clear();

        String[] parkCommand = new String[]{"park", "KA-01-HH-1234"};
        Command command = new ParkCommand(parkingLot);
        command.validate(parkCommand);
        command.execute();

        assertEquals(PARKING_CAPACITY_INVALID, bytes.toString());
    }
}