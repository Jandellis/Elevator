package com.isobar.elevator;

import org.test.elevator.api.ElevatorCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ElevatorCallbackImplementation implements ElevatorCallback{

    private Logger LOGGER = Logger.getLogger(this.getClass().getName());

    private List<String> logMessages = new ArrayList<>();

    @Override
    public void elevatorArrived(int floor) {
        LOGGER.info("Elevator arrived at floor " + floor);
    }
}
