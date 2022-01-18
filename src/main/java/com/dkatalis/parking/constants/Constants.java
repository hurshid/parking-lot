package com.dkatalis.parking.constants;

public interface Constants {

    String CREATE_PARKING_LOT = "create";
    String PARK = "park";
    String LEAVE = "leave";
    String STATUS = "status";

    String CREATED_PARKING = "Created parking lot with %d slots\n";
    String ALLOCATED = "Allocated slot number: %d\n";
    String PARKING_LOT_FULL = "Sorry, parking lot is full\n";
    String LEAVE_CAR = "Registration Number %s from Slot %d has left with Charge %s\n";
    String CAR_NOT_FOUND = "Registration Number %s not found\n";
    String STATUS_TITLE = "Slot No. Registration No.\n";
    String PARKING_STATUS = "%-8d %s\n";


    //Error constants
    String PARKING_CAPACITY_INVALID = "Please create a parking-lot first";
    String ALREADY_CREATED = "Parking lot already created";
}
