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
    static Instant pauseTimestamp;
    static Boolean isStarted = false;
    static Boolean isPaused = false;

    static Map<String, Double> ticketTime = new HashMap<String, Double>();

    public static void main(String[] args) {
        System.out.println("Welcome to Stampy!");

        System.out.println("\n't': start working on ticket, \n'f': finish ticket, \n'p': pause,  \n's': show ticket-time, \n'q': quit.");
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
        if (!isStarted) {
            System.out.println("You have not started working on any tickets yet.");
            return;
        }
        Instant finishTimestamp = Instant.now();
        isStarted = false;
        Duration duration = Duration.between(currentTimestamp, finishTimestamp);
        Double timeElapsed = duration.toMinutes() / 60.0;
        ticketTime.put(currTicket, timeElapsed);
        System.out.println("You finished working on ticket DevOps#" + currTicket + ". It took you " + timeElapsed + " hours.");
    }

    private static void pause() {
        if (!isStarted) {
            System.out.println("You have not started working on any tickets yet.");
            return;
        }
        if (isPaused) {
            Duration duration = Duration.between(pauseTimestamp, Instant.now());
            currentTimestamp = currentTimestamp.plus(duration);
            isPaused = false;
            System.out.println("Time unpaused.");
            return;
        }
        pauseTimestamp = Instant.now();
        isPaused = true;
        System.out.println("Time paused.");
    }

    private static void show() {
        for (Map.Entry<String, Double> entry : ticketTime.entrySet()) {
            System.out.println("Ticket DevOps#" + entry.getKey() + " took " + entry.getValue() + " hours.");
        }
    }

    private static void quitGracefully() {
        System.out.println("Exiting stopwatch. Goodbye!");
    }
}
