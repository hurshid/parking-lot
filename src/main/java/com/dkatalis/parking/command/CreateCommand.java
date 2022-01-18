package com.dkatalis.parking.command;

import com.dkatalis.parking.constants.Constants;
import com.dkatalis.parking.exceptions.ParkingLotException;
import com.dkatalis.parking.model.ParkingLot;

import static com.dkatalis.parking.constants.Constants.ALREADY_CREATED;
import static com.dkatalis.parking.constants.Constants.CREATED_PARKING;

public class CreateCommand implements Command {

    private final ParkingLot parkingLot;
    private int capacity;

    public CreateCommand(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void validate(String[] args) {
        Command.super.validate(args);

        boolean valid = true;
        try {
            capacity = Integer.parseInt(args[1]);
            if (capacity < 1) {
                valid = false;
            }
        } catch (NumberFormatException e) {
            valid = false;
        }

        if (!valid) {
            throw new ParkingLotException("Invalid command or param: " + String.join(" ", args));
        }

        if (parkingLot.getCapacity() > 0) {
            throw new ParkingLotException(ALREADY_CREATED);
        }
    }

    @Override
    public void execute() {
        parkingLot.setCapacity(capacity);
        for (int i = 1; i <= capacity; i++) {
            parkingLot.addSlot(i);
        }
        System.out.printf(CREATED_PARKING, capacity);
    }

    @Override
    public String name() {
        return Constants.CREATE_PARKING_LOT;
    }
}
