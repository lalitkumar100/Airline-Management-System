package com.crimsonlogic.arilinemanangmentsystem.entity;

public class Aircraft {

    private int aircraftId;
    private String aircraftName;
    private String aircraftModel;
    private int capacity;

    public Aircraft(int aircraftId, String aircraftName, String aircraftModel, int capacity) {
        this.aircraftId = aircraftId;
        this.aircraftName = aircraftName;
        this.aircraftModel = aircraftModel;
        this.capacity = capacity;
    }

    public int getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(int aircraftId) {
        this.aircraftId = aircraftId;
    }

    public String getAircraftName() {
        return aircraftName;
    }

    public void setAircraftName(String aircraftName) {
        this.aircraftName = aircraftName;
    }

    public String getAircraftModel() {
        return aircraftModel;
    }

    public void setAircraftModel(String aircraftModel) {
        this.aircraftModel = aircraftModel;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void displayInfo() {
        System.out.println("\n===== Aircraft Information =====");
        System.out.println("Aircraft ID    : " + aircraftId);
        System.out.println("Aircraft Name  : " + aircraftName);
        System.out.println("Aircraft Model : " + aircraftModel);
        System.out.println("Capacity       : " + capacity);
        System.out.println("================================");
    }
}