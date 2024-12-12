package com.example.demo.service;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final String ticketName;

    public Vendor(TicketPool ticketPool, String ticketName) {
        this.ticketPool = ticketPool;
        this.ticketName = ticketName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            ticketPool.addTicket();
            System.out.println("Vendor added a " + ticketName + ". Tickets available: " + ticketPool.getTicketCount());
            try {
                Thread.sleep(500); // Simulate some delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor thread interrupted.");
            }
        }
    }
}
