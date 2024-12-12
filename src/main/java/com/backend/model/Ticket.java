package com.backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Ticket{
    @Id
    private int ticketID;
    private BigDecimal price;
    private String event;
    private String releaseDate;

    //Empty constructor required for JPA
    public Ticket(){}

    /**
     * Constructor
     *
     * @param ticketID The unique identifying number of a ticket
     * @param event The event associated with the ticket
     * @param price The price of the ticket
     * @param releaseDate The release date of the ticket
     */
    public Ticket(int ticketID,String event, BigDecimal price, String releaseDate) {
        this.ticketID = ticketID;
        this.event = event;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    //Getters and Setters
    public int getTicketID() {
        return ticketID;
    }
    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public String getReleaseDate(){
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }

    /**
     * @return The Ticket as a String
     */
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID=" + ticketID +
                ", price=" + price +
                ", event=" + event +
                ", releaseDate=" + releaseDate +
                '}';
    }
}

