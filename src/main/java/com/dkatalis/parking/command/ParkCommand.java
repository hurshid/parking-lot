package com.dkatalis.parking.command;

import com.dkatalis.parking.constants.Constants;
import com.dkatalis.parking.exceptions.ParkingLotException;
import com.dkatalis.parking.model.ParkingLot;

public class ParkCommand implements Command {

    private final ParkingLot parkingLot;
    private String plateNumber;

    public ParkCommand(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void validate(String[] args) {
        Command.super.validate(args);

        plateNumber = args[1];

        if (parkingLot.getCapacity() == 0) {
            throw new ParkingLotException(Constants.PARKING_CAPACITY_INVALID);
        }
    }

    @Override
    public void execute() {
        Integer freeSpace = parkingLot.getAvailableSlot();
        if (freeSpace == null) {
            System.out.print(Constants.PARKING_LOT_FULL);
        } else {
            parkingLot.addCar(plateNumber, freeSpace);
            System.out.printf(Constants.ALLOCATED, freeSpace);
        }
    }

    @Override
    public String name() {
        return Constants.PARK;
    }
}
