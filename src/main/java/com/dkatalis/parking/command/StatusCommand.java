package com.dkatalis.parking.command;

import com.dkatalis.parking.constants.Constants;
import com.dkatalis.parking.model.ParkingLot;

public class StatusCommand implements Command {

    private final ParkingLot parkingLot;

    public StatusCommand(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void validate(String[] args) {
        Command.super.validate(args);
    }

    @Override
    public void execute() {
        parkingLot.showStatus();
    }

    @Override
    public String name() {
        return Constants.STATUS;
    }
}
