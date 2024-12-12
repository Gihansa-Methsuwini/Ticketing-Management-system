import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ConfigurationManager {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public void setupConfiguration() {
        Scanner scanner = new Scanner(System.in);

        totalTickets = getValidInput(scanner, "Enter total tickets (must be greater than 0): ", 1, Integer.MAX_VALUE);
        ticketReleaseRate = getValidInput(scanner, "Enter ticket release rate (must be greater than 0): ", 1, Integer.MAX_VALUE);
        customerRetrievalRate = getValidInput(scanner, "Enter customer retrieval rate (must be greater than 0): ", 1, Integer.MAX_VALUE);

        while (true) {
            maxTicketCapacity = getValidInput(scanner, "Enter max ticket capacity (must be greater than total tickets): ", totalTickets, Integer.MAX_VALUE);
            if (maxTicketCapacity >= totalTickets) break;
            System.out.println("Error: Max ticket capacity must be greater than or equal to total tickets.");
        }

        saveConfigurationToFile();
    }

    private int getValidInput(Scanner scanner, String prompt, int min, int max) {
        int input;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= min && input <= max) return input;
            }
            scanner.nextLine();
            System.out.println("Invalid input. Please try again.");
        }
    }

    private void saveConfigurationToFile() {
        try (FileWriter writer = new FileWriter("configuration.txt", false)) {
            writer.write("Total Tickets: " + totalTickets + "\n");
            writer.write("Ticket Release Rate: " + ticketReleaseRate + "\n");
            writer.write("Customer Retrieval Rate: " + customerRetrievalRate + "\n");
            writer.write("Max Ticket Capacity: " + maxTicketCapacity + "\n");
            System.out.println("Configuration saved to file.");
        } catch (IOException e) {
            System.out.println("Failed to save configuration: " + e.getMessage());
        }
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
}
