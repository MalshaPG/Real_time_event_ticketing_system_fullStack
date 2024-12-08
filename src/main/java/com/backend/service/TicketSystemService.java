package com.backend.service;

import com.backend.model.Configuration;
import com.backend.model.Customer;
import com.backend.model.TicketPool;
import com.backend.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketSystemService {
    @Autowired
    private ConfigurationService configurationService;
    private volatile boolean stopSignal = false;
    private TicketPool ticketPool;

    public void startTicketingSimulation() {
        // Retrieve configuration
        Configuration configuration = configurationService.getLatestConfiguration();

        int maximumCapacity = configuration.getMaxTicketCapacity();
        int ticketReleaseRate = configuration.getTicketReleaseRate();
        int customerRetrievalRate = configuration.getCustomerRetrievalRate();
        int totalTickets = configuration.getTotalTickets();

        TicketPool ticketPool = new TicketPool(maximumCapacity);
        System.out.println("TicketPool initialized with capacity: " + configuration.getMaxTicketCapacity());

        // Start vendor simulation
        Vendor[] vendors = new Vendor[5];  //simulate 5 vendors
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(ticketPool, totalTickets, ticketReleaseRate, () -> stopSignal);
            new Thread(vendors[i], "vendor-" + i).start();
        }

        // Start customer simulation
        Customer[] customers = new Customer[10];  //Simulate 10 customers
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(ticketPool, customerRetrievalRate, 2, () -> stopSignal);
            new Thread(customers[i], "customer-" + i).start();
        }
    }

    public void stopTicketingSimulation() {
        stopSignal = true;
        System.out.println("Ticketing system stopped.");
    }

    public int getTicketCount() {
        return ticketPool.getTicketCount();
    }
}
