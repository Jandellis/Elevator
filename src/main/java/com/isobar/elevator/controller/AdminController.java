package com.isobar.elevator.controller;

import com.isobar.elevator.ElevatorCallbackImplementation;
import com.isobar.elevator.log.AdminLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.test.elevator.api.ElevatorFacade;
import org.test.elevator.api.ElevatorFactory;


@Controller
@RequestMapping("/")
public class AdminController{

    @GetMapping("/admin")
    public String showAdminInterface(Model model) {
        ElevatorFacade one = ElevatorFactory.getElevatorFacade(1, new ElevatorCallbackImplementation());
        ElevatorFacade two = ElevatorFactory.getElevatorFacade(2, new ElevatorCallbackImplementation());


        model.addAttribute("elevators", ElevatorFactory.getElevatorFacades());
        model.addAttribute("log", AdminLog.getInstance().getLog());
        return "admin";
    }

}
