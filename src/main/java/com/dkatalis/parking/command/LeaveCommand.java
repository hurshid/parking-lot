package com.dkatalis.parking.command;

import com.dkatalis.parking.constants.Constants;
import com.dkatalis.parking.exceptions.ParkingLotException;
import com.dkatalis.parking.model.ParkingLot;
import com.dkatalis.parking.utils.Utils;

public class LeaveCommand implements Command {

    private final ParkingLot parkingLot;
    private String plateNumber;
    private int hour;

    public LeaveCommand(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void validate(String[] args) {
        Command.super.validate(args);

        plateNumber = args[1];

        boolean valid = true;
        try {
            hour = Integer.parseInt(args[2]);
            if (hour < 2) {
                valid = false;
            }
        } catch (NumberFormatException e) {
            valid = false;
        }
        if (!valid) {
            throw new ParkingLotException("Invalid command or param: " + String.join(" ", args));
        }
        if (parkingLot.getCapacity() == 0) {
            throw new ParkingLotException(Constants.PARKING_CAPACITY_INVALID);
        }
    }

    @Override
    public void execute() {
        Integer slotNumber = parkingLot.getSlotNum(plateNumber);
        if (slotNumber > 0) {
            parkingLot.removeCar(plateNumber);
            parkingLot.addSlot(slotNumber);
            System.out.printf(Constants.LEAVE_CAR, plateNumber, slotNumber, Utils.calculatePrice(hour));
        } else {
            System.out.printf(Constants.CAR_NOT_FOUND, plateNumber);
        }
    }

    @Override
    public String name() {
        return Constants.LEAVE;
    }
}
