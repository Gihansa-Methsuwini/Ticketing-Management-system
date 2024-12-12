package com.example.demo.service;

public class Customer implements Runnable {
    private final TicketPool ticketPool;

    public Customer(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            boolean success = ticketPool.removeTicket();
            if (success) {
                System.out.println("Customer bought a ticket. Tickets left: " + ticketPool.getTicketCount());
            } else {
                System.out.println("No tickets left to buy!");
            }
            try {
                Thread.sleep(500); // Simulate some delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer thread interrupted.");
            }
        }
    }
}
