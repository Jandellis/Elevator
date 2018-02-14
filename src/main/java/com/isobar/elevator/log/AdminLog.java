package com.isobar.elevator.log;

import java.util.ArrayList;
import java.util.List;

public class AdminLog {
    private static AdminLog ourInstance = new AdminLog();

    private List<LogMessage> log = new ArrayList<>();

    public static AdminLog getInstance() {
        return ourInstance;
    }

    private AdminLog() {
    }

    public void addLog(LogMessage logMessage) {
        log.add(logMessage);
    }

    public List<LogMessage> getLog() {
        return log;
    }
}
