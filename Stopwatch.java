import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

public class Stopwatch {
    static Scanner scanner = new Scanner(System.in);
    static String command;
    static String currTicket;
    static Instant currentTimestamp;
    static Boolean isStarted = false;
    static Boolean isPaused = false;

    static Map<String, Long> ticketTime = new HashMap<String, Long>();

    public static void main(String[] args) {
        System.out.println("Welcome to Stampy!");

        System.out.println("\n't': start working on ticket, \n'f': finish ticket, \n'p': start pause,  \n's': show ticket-time, \n'q': quit.");
        command = scanner.nextLine();

        while (!command.equalsIgnoreCase("q")) {
            if (command.equalsIgnoreCase("t")) {
                startTicket();
            } else if (command.equalsIgnoreCase("f")) {
                finish();
            } else if (command.equalsIgnoreCase("s")) {
                show();
            } else if (command.equalsIgnoreCase("p")) {
                pause();
            } else {
                System.out.println("Invalid command. Please try again.");
            }
            command = scanner.nextLine();
        }

        quitGracefully();
    }

    private static void startTicket() {
        System.out.println("DevOps#: ");
        currTicket = scanner.nextLine();

        isStarted = true;
        currentTimestamp = Instant.now();

        System.out.println("You started working on ticket DevOps#" + currTicket + ".");
    }

    private static void finish() {
        Instant finishTimestamp = Instant.now();
        long timeElapsed = Duration.between(currentTimestamp, finishTimestamp).toHours();
        ticketTime.put(currTicket, timeElapsed);

        System.out.println("You finished working on ticket DevOps#" + currTicket + ". It took you " + timeElapsed + " hours.");
    }

    private static void pause() {
        System.out.println("Time paused.");
    }

    private static void show() {
        for (Map.Entry<String, Long> entry : ticketTime.entrySet()) {
            System.out.println("Ticket DevOps#" + entry.getKey() + " took " + entry.getValue() + " hours.");
        }
    }

    private static void quitGracefully() {
        System.out.println("Exiting stopwatch. Goodbye!");
    }
}