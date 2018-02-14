package com.isobar.elevator.controller;

import com.isobar.elevator.ElevatorCallbackImplementation;
import com.isobar.elevator.log.AdminLog;
import com.isobar.elevator.log.LogMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.test.elevator.api.ElevatorFacade;
import org.test.elevator.api.ElevatorFactory;

@RestController
@RequestMapping("/elevator/{elevatorId}")
public class ElevatorController {

    private int lowerFloor;
    private int upperFloor;

    @Autowired
    public ElevatorController(@Value("${building.floor.lower}") int lowerFloor, @Value("${building.floor.upper}") int upperFloor) {
        this.lowerFloor = lowerFloor;
        this.upperFloor = upperFloor;
    }

    /**
     * Moves the given elevator up one floor
     * @param elevatorId the ID of the elevator to move
     * @return true if moved successfully
     */
    @RequestMapping(method = RequestMethod.POST, value = "/up")
    Boolean moveElevatorUp(@PathVariable Integer elevatorId) {
        ElevatorFacade elevator = ElevatorFactory.getElevatorFacade(elevatorId, new ElevatorCallbackImplementation());
        if (elevator.getCurrentFloor() < upperFloor) {
            elevator.unlockBreaks();
            elevator.moveUpOneFloor();
            AdminLog.getInstance().addLog(
                    new LogMessage("Moving elevator up floor to : " + elevator.getCurrentFloor(),
                            elevatorId,
                            "up"));
            return true;
        }
        AdminLog.getInstance().addLog(
                new LogMessage("Unable to move elevator up as at top of building",
                        elevatorId,
                        "up"));
        return false;
    }

    /**
     * Moves the given elevator down one floor
     * @param elevatorId the ID of the elevator to move
     * @return true if moved successfully
     */
    @RequestMapping(method = RequestMethod.POST, value = "/down")
    Boolean moveElevatorDown(@PathVariable Integer elevatorId) {
        ElevatorFacade elevator = ElevatorFactory.getElevatorFacade(elevatorId, new ElevatorCallbackImplementation());
        if (elevator.getCurrentFloor() > lowerFloor) {
            elevator.unlockBreaks();
            elevator.moveDownOneFloor();
            AdminLog.getInstance().addLog(
                    new LogMessage("Moving elevator down floor to : " + elevator.getCurrentFloor(),
                            elevatorId,
                            "down"));
            return true;
        }
        AdminLog.getInstance().addLog(
                new LogMessage("Unable to move elevator down as at bottom of building",
                        elevatorId,
                        "down"));
        return false;
    }

    /**
     * Stops the given elevator
     * @param elevatorId the ID of the elevator to stop
     * @return true if stopped successfully
     */
    @RequestMapping(method = RequestMethod.POST, value = "/stop")
    Boolean elevatorStop(@PathVariable Integer elevatorId) {
        ElevatorFacade elevator = ElevatorFactory.getElevatorFacade(elevatorId, new ElevatorCallbackImplementation());
        elevator.lockBreaks();
        AdminLog.getInstance().addLog(
                new LogMessage("Breaks applied",
                        elevatorId,
                        "stop"));
        return true;
    }

    /**
     * Moves the elevator to the given floor
     * @param elevatorId the ID of the elevator to move
     * @param floor the floor to move the elevator to
     * @return true if moved to that floor
     */
    @RequestMapping(method = RequestMethod.POST, value = "/floor/{floor}")
    Boolean floorSelect(@PathVariable Integer elevatorId, @PathVariable Integer floor) {
        if (floor > upperFloor || floor < lowerFloor) {
            AdminLog.getInstance().addLog(
                    new LogMessage("Invalid floor selected : " + floor,
                            elevatorId,
                            "floor select"));
            return false;
        }
        ElevatorFacade elevator = ElevatorFactory.getElevatorFacade(elevatorId, new ElevatorCallbackImplementation());
        AdminLog.getInstance().addLog(
                new LogMessage("Moving elevator from floor " + elevator.getCurrentFloor() + " to floor : " + floor,
                        elevatorId,
                        "floor select"));
        while (elevator.getCurrentFloor() != floor) {
            if (elevator.getCurrentFloor() > floor) {
                moveElevatorDown(elevatorId);
            } else {
                moveElevatorUp(elevatorId);
            }
        }
        return true;
    }
}
