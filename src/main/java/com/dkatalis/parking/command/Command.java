package com.dkatalis.parking.command;

import com.dkatalis.parking.exceptions.ParkingLotException;
import com.dkatalis.parking.utils.Utils;

public interface Command {

    default void validate(String[] commandArr) {
        assert commandArr[0].equals(name());
        boolean valid = true;
        int paramCount = Utils.paramCount(name());
        if (paramCount < 0) {
            valid = false;
        }
        if (commandArr.length != paramCount + 1) {
            valid = false;
        }
        if (!valid) {
            throw new ParkingLotException("Invalid command: " + name());
        }
    }

    void execute();

    String name();

}
