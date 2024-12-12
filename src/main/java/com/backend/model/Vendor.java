package com.backend.model;

import com.backend.model.TicketPool;
import com.backend.simutaltion.StopCondition;

import java.math.BigDecimal;

/**
 * Vendor class to create vendor objects
 */
public class Vendor implements Runnable {

    private final TicketPool ticketPool;
    private final int totalTickets;
    private final int ticketReleaseRate;
    private final StopCondition stopCondition;

    /**
     * Constructor
     *
     * @param ticketPool shared ticket pool between customers and vendors
     * @param totalTickets Number of tickets a vendor can add to the ticket pool
     * @param ticketReleaseRate How frequently vendors add tickets.
     * @param stopCondition A stopCondition object to determine when to stop
     */
    public Vendor(TicketPool ticketPool, int totalTickets, int ticketReleaseRate, StopCondition stopCondition) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.stopCondition = stopCondition;
    }

    /**
     * Run method call add tickets to the ticketpool
     * Add ticket to the ticket pool until
     * total tickets is reached
     * or stop condition is triggered
     * or catch an Interrupted exception
     */
    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            //Stop the system if stop condition is true
            if (stopCondition.shouldStop()) {
                System.out.println("Vendor simulation stopped.");
                break;
            }
            else {
                //Create new Ticket object and it to the ticket pool
                Ticket ticket = new Ticket(i,"Summer night", new BigDecimal(1000.00), "2024-12-27");
                ticketPool.addTicket(ticket);

                // Simulate the delay between releasing 2 tickets
                try {
                    Thread.sleep(ticketReleaseRate * 1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Vendor interrupted.");
                    break;
                }
            }
        }
    }

}
