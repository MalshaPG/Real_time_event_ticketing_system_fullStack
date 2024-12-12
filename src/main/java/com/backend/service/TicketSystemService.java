package com.backend.service;

import com.backend.model.Configuration;
import com.backend.model.Customer;
import com.backend.model.TicketPool;
import com.backend.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;

/**
 * Manage ticketing system simulation
 */
@Service
public class TicketSystemService {
    @Autowired
    private ConfigurationService configurationService;

    //Volatile flag to signal all the threads to stop
    private volatile boolean stopSignal = false;

    //Shared ticket pool by vendors and customers
    private TicketPool ticketPool;

    /**
     * Start the ticketing system
     * Retrieve configuration data, create ticket pool start vendor and customer threads
     */
    public void startTicketingSimulation() {
        // Retrieve configuration
        Configuration configuration = configurationService.getLatestConfiguration();

        //Assign retrieved configuration values
        int maximumCapacity = configuration.getMaxTicketCapacity();
        int ticketReleaseRate = configuration.getTicketReleaseRate();
        int customerRetrievalRate = configuration.getCustomerRetrievalRate();
        int totalTickets = configuration.getTotalTickets();

        //Initialize the ticketpool with the size equals to maximum capacity
        ticketPool = new TicketPool(maximumCapacity);

        //Start system if stopSignal is not active
        if(!stopSignal) {
            // Start vendor simulation
            Vendor[] vendors = new Vendor[5];  //simulate 5 vendors
            for (int i = 0; i < vendors.length; i++) {
                vendors[i] = new Vendor(ticketPool, totalTickets, ticketReleaseRate, () -> stopSignal);
                Thread vendorThread = new Thread(vendors[i], "vendor - " + i);
                vendorThread.start();
            }

            // Start customer simulation
            Customer[] customers = new Customer[10];  //Simulate 10 customers
            for (int i = 0; i < customers.length; i++) {
                customers[i] = new Customer(ticketPool, customerRetrievalRate, 2, () -> stopSignal);
                Thread customerThread = new Thread(customers[i], "customer - " + i);
                customerThread.start();
            }
        }
    }

    /**
     * Stop the system by setting the stop signal
     */
    public void stopTicketingSimulation() {
        stopSignal = true;
        System.out.println("Ticketing system stopped.");
    }

    /**
     * Retrieve the current ticket count in the ticket pool
     *
     * @return available ticket count
     */
    public int getTicketCount() {
        return ticketPool.getTicketCount();
    }

    /**
     * Retrieve the ticket transaction log
     *
     * @return A queue of ticket transaction log
     */
    public Queue<String> ticketLog(){
        return ticketPool.getTicketTransactions();
    }
}
