package com.example.demo.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private int tickets;
    private final Lock lock = new ReentrantLock();

    public TicketPool(int tickets) {
        this.tickets = tickets;
    }

    public boolean addTicket() {
        lock.lock();
        try {
            tickets++;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public boolean removeTicket() {
        lock.lock();
        try {
            if (tickets > 0) {
                tickets--;
                return true;
            } else {
                return false; // No tickets left
            }
        } finally {
            lock.unlock();
        }
    }

    public int getTicketCount() {
        return tickets;
    }
}
