package com.backend.controller;

import com.backend.model.TicketPool;
import com.backend.service.TicketSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Queue;

/**
 * REST controller to manage the ticketing system
 * End points to start, stop system, retrieve available ticket count and ticket transaction log
 */

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/ticketing-system")
public class TicketingSystemController {

    @Autowired
    private TicketSystemService ticketSystemService;

    /**
     * Start the ticketing system
     *
     * @return A confirmation message
     */
    @PostMapping("/start")
    public String startSystem() {
        ticketSystemService.startTicketingSimulation();
        return "Ticketing system has started!";
    }

    /**
     * Stop the ticketing system
     *
     * @return A confirmation message
     */
    @PostMapping("/stop")
    public String stopSystem() {
        ticketSystemService.stopTicketingSimulation();
        return "Ticketing system has stopped!";
    }

    /**
     * Retrieve available ticket count
     *
     * @return available ticket count in the ticket pool
     */
    @GetMapping("/ticketCount")
    public String getTicketCount() {
        int ticketCount = ticketSystemService.getTicketCount();
        return "Current ticket count in the pool: " + ticketCount;
    }

    /**
     * Retrieve the ticket transaction log
     *
     * @return A queue of ticket logs
     */
    @GetMapping("/ticketLog")
    public Queue<String> getTicketTransactions() {
        return ticketSystemService.ticketLog();
    }

}
