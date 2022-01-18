package com.dkatalis.parking;

import com.dkatalis.parking.command.*;
import com.dkatalis.parking.constants.Constants;
import com.dkatalis.parking.model.ParkingLot;
import com.dkatalis.parking.utils.Utils;

import java.util.List;
import java.util.Objects;

public class App {


    public static void main(String[] args) {
        if (args.length == 0) {

            printHelpCommands();

        } else if (args.length == 1) {
            String filePath = args[0];

            List<String> commands = Utils.readFromFile(filePath);
            ParkingLot parkingLot = ParkingLot.getInstance();
            for (String command : commands) {
                Command cmd = null;

                String[] commandArr = command.split(" ");
                switch (Objects.requireNonNull(commandArr[0].trim().toLowerCase())) {
                    case Constants.CREATE_PARKING_LOT:
                        cmd = new CreateCommand(parkingLot);
                        break;
                    case Constants.PARK:
                        cmd = new ParkCommand(parkingLot);
                        break;
                    case Constants.LEAVE:
                        cmd = new LeaveCommand(parkingLot);
                        break;
                    case Constants.STATUS:
                        cmd = new StatusCommand(parkingLot);
                        break;
                    default:
                        break;
                }

                try {
                    if (cmd != null) {
                        cmd.validate(commandArr);
                        cmd.execute();
                    } else {
                        printHelpCommands();
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }

        } else {
            System.out.println("Invalid argument");
        }
    }

    private static void printHelpCommands() {
        String helpStr = "Please include following commands in file\n" +
                "create [size] - Creates parking lot of size n\n" +
                "park [car-number] - Parks a car\n" +
                "leave [car-number] [hours] - Removes (unpark) a car\n" +
                "status - Prints status of the parking lot\n";
        System.out.println(helpStr);
    }
}
