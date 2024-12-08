package com.backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Ticket {
    @Id
    private int ticketID;//primary key
    private String eventName;
    private BigDecimal price;
    private String releaseDate;

    public Ticket() {}

    public Ticket(int ticketID, BigDecimal price, String releaseDate) {
        this.eventName = eventName;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public int getTicketID() {
        return ticketID;
    }
    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName){
        this.eventName = eventName;
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

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID=" + ticketID +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
