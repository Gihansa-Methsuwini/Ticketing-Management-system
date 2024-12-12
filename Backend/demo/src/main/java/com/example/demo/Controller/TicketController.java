package com.example.demo.Controller;

import com.example.demo.service.TicketPool;
import com.example.demo.service.Vendor;
import com.example.demo.service.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
    private final TicketPool ticketPool = new TicketPool(10); // Initialize TicketPool with 10 tickets

    @GetMapping("/start")
    public String startThreads() {
        // Create and start vendor and customer threads
        Thread vendorThread = new Thread(new Vendor(ticketPool, "Ticket"));
        Thread customerThread = new Thread(new Customer(ticketPool));

        vendorThread.start();
        customerThread.start();

        return "Vendor and Customer threads started!";
    }

    @GetMapping("/status")
    public String getPoolStatus() {
        // Return the current status of the ticket pool
        return "Current tickets in pool: " + ticketPool.getTicketCount();
    }
}




