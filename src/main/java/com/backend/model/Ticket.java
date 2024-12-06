package com.backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketID;//primary key
    private BigDecimal price;
    private String releaseDate;

    public Ticket() {}

    public Ticket(BigDecimal price, String releaseDate) {
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public Long getTicketID() {
        return ticketID;
    }
    public void setTicketID(Long ticketID) {
        this.ticketID = ticketID;
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
