package com.backend.model;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A shared ticket pool between vendors and customers
 * Use synchronized queue to manage tickets in a thread-safe manner.
 */
public class TicketPool {
    private Queue<Ticket> tickets; //Queue for storing tickets
    private int maxTicketCapacity;

    //Queue for storing  ticket transaction details
    private Queue<String> ticketTransaction = new LinkedList<>();

    /**
     * Constructor
     *
     * @param maximumCapacity Maximum ticket capacity of the ticket pool.
     */
    public TicketPool(int maximumCapacity) {
        this.maxTicketCapacity = maximumCapacity;
        this.tickets = new LinkedList<>(); //Create new LinkedList for each TicketPool object
    }

    /**
     *Add tickets to the ticket pool
     *
     * @param ticket The added ticket to the ticket pool by a vendor
     */
    public synchronized void addTicket(Ticket ticket) {

        //Wait if the ticket pool is full
        while (tickets.size() >= maxTicketCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        while (tickets.size() < maxTicketCapacity) {
            //Add the ticket to the ticket pool and notify other waiting threads
            this.tickets.add(ticket);
            notifyAll();

            System.out.println("Ticket added by " + Thread.currentThread().getName() + ".Ticket is " + ticket);

            //Write the ticket transaction log
            this.ticketTransaction.add(Thread.currentThread().getName() + " has added a ticket to the pool, current size is " + tickets.size());
        }
    }

    /**
     * Remove tickets from the ticket pool
     *
     * @return The ticket removed from the ticket pool
     */
    public synchronized Ticket buyTicket() {
        //Wait if the ticket pool is empty
        while (tickets.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        Ticket ticket = tickets.poll(); //Remove the ticket from the ticket pool
        notifyAll(); //Notify all other waiting threads

        System.out.println("Ticket bought by " + Thread.currentThread().getName() + ".Ticket is " + ticket);

        //Write ticket transaction log
        ticketTransaction.add(Thread.currentThread().getName() + " has bought a ticket from the pool, current size is " + tickets.size());

        return ticket;
    }

    /**
     * Get available ticket count
     *
     * @return The number of available tickets in the ticket pool
     */
    public int getTicketCount() {
        return tickets.size();
    }


    /**
     * Retrieve the ticket transaction log
     *
     * @return A queue which contains the ticket transaction logs
     */
    public Queue<String> getTicketTransactions() {
        return ticketTransaction;
    }

}
