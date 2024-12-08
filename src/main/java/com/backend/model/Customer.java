package com.backend.model;

import com.backend.simutaltion.StopCondition;

public class Customer implements Runnable{
    private TicketPool ticketpool;
    private int customerRetrievalRate;
    private int quantity;
    private final StopCondition stopCondition;


    public Customer(TicketPool ticketpool, int customerRetrievalRate, int quantity, StopCondition stopCondition) {
        this.ticketpool = ticketpool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.quantity = quantity;
        this.stopCondition = stopCondition;
    }

    @Override
    public void run(){
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = ticketpool.buyTicket();
            System.out.println("Ticket bought by " + Thread.currentThread().getName() + ".Ticket is " + ticket);

            try{
                Thread.sleep(customerRetrievalRate * 1000);
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }

    //getters setters tostring
}
