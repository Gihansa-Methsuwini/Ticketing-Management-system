import java.util.Scanner;

public class TicketSystemControl {
    public static void main(String[] args) {
        ConfigurationManager configManager = new ConfigurationManager();
        configManager.setupConfiguration();

        TicketPool ticketPool = new TicketPool(configManager.getTotalTickets(), configManager.getMaxTicketCapacity());
        TicketVendor ticketVendor = new TicketVendor(ticketPool, configManager.getTicketReleaseRate());
        TicketCustomer ticketCustomer = new TicketCustomer(ticketPool, configManager.getCustomerRetrievalRate());

        Thread vendorThread = null;
        Thread customerThread = null;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command (start, stop, exit): ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "start":
                    if (vendorThread == null || !vendorThread.isAlive()) {
                        vendorThread = new Thread(ticketVendor, "Vendor");
                        vendorThread.start();
                    }
                    if (customerThread == null || !customerThread.isAlive()) {
                        customerThread = new Thread(ticketCustomer, "Customer");
                        customerThread.start();
                    }
                    System.out.println("System started.");
                    break;

                case "stop":
                    if (vendorThread != null && vendorThread.isAlive()) {
                        ticketVendor.stop();
                        try {
                            vendorThread.join();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (customerThread != null && customerThread.isAlive()) {
                        ticketCustomer.stop();
                        try {
                            customerThread.join();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("System stopped.");
                    break;

                case "exit":
                    System.out.println("Exiting the system.");
                    System.exit(0);

                default:
                    System.out.println("Invalid command. Please enter 'start', 'stop', or 'exit'.");
            }
        }
    }
}
