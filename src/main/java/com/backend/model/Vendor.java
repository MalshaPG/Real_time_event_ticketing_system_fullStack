package com.backend.model;

import com.backend.model.TicketPool;
import com.backend.simutaltion.StopCondition;


public class Vendor implements Runnable {

    private final TicketPool ticketPool;
    private final int totalTickets;
    private final int ticketReleaseRate;
    private final StopCondition stopCondition;

    public Vendor(TicketPool ticketPool, int totalTickets, int ticketReleaseRate, StopCondition stopCondition) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.stopCondition = stopCondition;
    }

    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            if (stopCondition.shouldStop()) {
                System.out.println("Vendor simulation stopped.");
                break;
            }

            System.out.println("Vendor is adding ticket " + i);
            try {
                Thread.sleep(ticketReleaseRate * 1000); // Simulate ticket release rate
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor interrupted.");
                break;
            }
        }
    }

}
