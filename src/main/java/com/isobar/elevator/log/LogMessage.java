package com.isobar.elevator.log;

public class LogMessage {

    private String message;
    private Integer elevatorId;
    private String command;

    public LogMessage(String message, Integer elevatorId, String command) {
        this.message = message;
        this.elevatorId = elevatorId;
        this.command = command;
    }

    public String getMessage() {
        return message;
    }

    public Integer getElevatorId() {
        return elevatorId;
    }

    public String getCommand() {
        return command;
    }
}
