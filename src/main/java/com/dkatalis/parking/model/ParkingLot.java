package com.dkatalis.parking.model;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import static com.dkatalis.parking.constants.Constants.PARKING_STATUS;
import static com.dkatalis.parking.constants.Constants.STATUS_TITLE;

public class ParkingLot {

    private static ParkingLot instance;

    private final Map<String, Integer> parkedCarsMap = new HashMap<>(); //key-> plateNumber, value-> slotNumber
    private final Queue<Integer> queue = new PriorityQueue<>();
    private int capacity;

    private ParkingLot() {
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addCar(String plateNumber, int spaceNum) {
        parkedCarsMap.put(plateNumber, spaceNum);
    }

    public void removeCar(String plateNumber) {
        parkedCarsMap.remove(plateNumber);
    }

    public int getSlotNum(String plateNumber) {
        return parkedCarsMap.getOrDefault(plateNumber, 0);
    }

    public void addSlot(Integer slotNumber) {
        queue.offer(slotNumber);
    }

    public Integer getAvailableSlot() {
        Integer freeSlot = queue.poll();
        if (freeSlot == null) {
            assert capacity == parkedCarsMap.size();
        }
        return freeSlot;
    }

    public void showStatus() {
        System.out.print(STATUS_TITLE);

        for (int i = 1; i <= capacity; i++) {
            String plateNum = getCar(i);
            if (plateNum.equals("")) {
                continue;
            }
            Integer slotNum = i;
            System.out.print(String.format(PARKING_STATUS, slotNum, plateNum));
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void clear() { //for testing purpose
        this.capacity = 0;
        this.parkedCarsMap.clear();
        this.queue.clear();
    }

    private String getCar(int slotNumber) {
        if (parkedCarsMap.size() == 0) {
            return "";
        }
        for (Map.Entry<String, Integer> map : parkedCarsMap.entrySet()) {
            if (map.getValue().equals(slotNumber)) {
                return map.getKey();
            }
        }
        return "";
    }
}
