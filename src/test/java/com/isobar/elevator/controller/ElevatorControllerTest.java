package com.isobar.elevator.controller;

import com.isobar.elevator.ElevatorCallbackImplementation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.elevator.api.ElevatorFactory;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by james_000 on 13/02/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElevatorControllerTest {

    @Autowired
    ElevatorController elevatorController;

    @Test
    public void moveElevatorUp() throws Exception {
        Boolean result = elevatorController.moveElevatorUp(1);
        assertThat(result, is(true));
    }

    @Test
    public void tryToMoveThroughRoof() throws Exception {
        elevatorController.moveElevatorUp(2); //move to 1
        elevatorController.moveElevatorUp(2); //move to 2
        elevatorController.moveElevatorUp(2); //move to 3
        elevatorController.moveElevatorUp(2); //move to 4
        elevatorController.moveElevatorUp(2); //move to 5
        Boolean result = elevatorController.moveElevatorUp(2); //move to 6, 5 is top floor

        assertThat(result, is(false));
    }

    @Test
    public void moveElevatorDown() throws Exception {
        elevatorController.moveElevatorUp(3);
        Boolean result = elevatorController.moveElevatorDown(3);
        assertThat(result, is(true));
    }

    @Test
    public void moveElevatorThroughFloor() throws Exception {
        Boolean result = elevatorController.moveElevatorDown(4);
        assertThat(result, is(false));
    }

    @Test
    public void elevatorStop() throws Exception {
        Boolean result = elevatorController.elevatorStop(5);
        assertThat(result, is(true));
    }

    @Test
    public void floorSelect() throws Exception {
        Boolean result = elevatorController.floorSelect(6, 5);
        assertThat(result, is(true));
        assertThat(ElevatorFactory.getElevatorFacade(6, new ElevatorCallbackImplementation()).getCurrentFloor(), is(5));
    }

    @Test
    public void floorSelectInvalidOption() throws Exception {
        Boolean result = elevatorController.floorSelect(7, 10);
        assertThat(result, is(false));
        assertThat(ElevatorFactory.getElevatorFacade(7, new ElevatorCallbackImplementation()).getCurrentFloor(), is(0));
    }

}