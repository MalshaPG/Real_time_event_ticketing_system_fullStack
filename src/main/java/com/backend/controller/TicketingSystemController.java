package com.backend.controller;

import com.backend.service.TicketSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ticketing-system")
public class TicketingSystemController {

    @Autowired
    private TicketSystemService ticketSystemService;

    // Start the ticketing system
    @PostMapping("/start")
    public String startSystem() {
        ticketSystemService.startTicketingSimulation();
        return "Ticketing system has started!";
    }

    // Stop the ticketing system
    @PostMapping("/stop")
    public String stopSystem() {
        ticketSystemService.stopTicketingSimulation();
        return "Ticketing system has stopped!";
    }

    @GetMapping("/ticketCount")
    public String getTicketCount() {
        int ticketCount = ticketSystemService.getTicketCount();
        return "Current ticket count in the pool: " + ticketCount;
    }

}
