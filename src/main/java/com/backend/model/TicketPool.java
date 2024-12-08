package com.backend.model;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private Queue<Ticket> tickets;
    private int maxTicketCapacity;


    public TicketPool(int maximumCapacity) {
        this.maxTicketCapacity = maximumCapacity;
        this.tickets = new LinkedList<>();
    }

    //vendors add tickets to the ticket pool
    public synchronized void addTicket(Ticket ticket) {
            while (tickets.size() >= maxTicketCapacity) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();//for CLI
                    //throw new RuntimeException(e.getMessage());//For client-server application, throw the exception to the client

                }
            }

            while (tickets.size() < maxTicketCapacity) {
                this.tickets.add(ticket);
                notifyAll();
                //string queue.add("msg")
                System.out.println(Thread.currentThread().getName() + " has added a ticket to the pool, current size is " + tickets.size());
            }
    }

    //customers buy ticket from the ticket pool
    public synchronized Ticket buyTicket() {

            while (tickets.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e.getMessage());
                }
            }

            Ticket ticket = tickets.poll();
            notifyAll();
            //string queue.add("msg")
            System.out.println(Thread.currentThread().getName() + " has bought a ticket from the pool, current size is " + tickets.size());
            return ticket;
    }

    public int getTicketCount() {
        return tickets.size();
    }

}
