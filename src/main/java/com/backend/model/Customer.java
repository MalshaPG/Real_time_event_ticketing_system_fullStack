package com.backend.model;

import com.backend.simutaltion.StopCondition;

/**
 * Customer class ,for creating customer objects
 */
public class Customer implements Runnable{
    private TicketPool ticketpool;
    private int customerRetrievalRate;
    private int quantity;
    private final StopCondition stopCondition;


    /**
     * Constructor
     *
     * @param ticketpool Where vendors add tickets to and customers buy ticket from
     * @param customerRetrievalRate How often customers purchase tickets.
     * @param quantity The number of tickets that the customer will buy at a time
     * @param stopCondition A stopCondition object to determine when to stop
     */
    public Customer(TicketPool ticketpool, int customerRetrievalRate, int quantity, StopCondition stopCondition) {
        this.ticketpool = ticketpool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.quantity = quantity;
        this.stopCondition = stopCondition;
    }


    @Override
    public void run(){
        for (int i = 0; i < quantity; i++) {
            //Stop the system if stop condition is true
            if (stopCondition.shouldStop()) {
                System.out.println("Vendor simulation stopped.");
                break;
            }
            //If stop condition is false call buyTicket method on the Ticketpool object.
            try{
                Ticket ticket = ticketpool.buyTicket();
                Thread.sleep(customerRetrievalRate * 1000);
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }

}
