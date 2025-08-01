import java.net.UnknownHostException;
import java.util.Scanner;

public class RouterSimulator {
    public static void main(String[] args) {
        Router router = new Router();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Simple Router Simulator (Console)");
        System.out.println("Commands: add_route, delete_route, show_routes, forward_packet, exit");

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            String[] tokens = line.split("\\s+");
            if (tokens.length == 0) continue;

            String command = tokens[0];

            try {
                switch (command) {
                    case "add_route":
                        if (tokens.length != 5) {
                            System.out.println("Usage: add_route <dest> <mask> <nextHop> <metric>");
                            break;
                        }
                        String dest = tokens[1];
                        String mask = tokens[2];
                        String hop = tokens[3];
                        int metric = Integer.parseInt(tokens[4]);
                        router.addRoute(new RouteEntry(dest, mask, hop, metric));
                        System.out.println("Route added.");
                        break;

                    case "delete_route":
                        if (tokens.length != 2) {
                            System.out.println("Usage: delete_route <dest>");
                            break;
                        }
                        router.deleteRoute(tokens[1]);
                        System.out.println("Route deleted.");
                        break;

                    case "show_routes":
                        router.showRoutes();
                        break;

                    case "forward_packet":
                        if (tokens.length != 2) {
                            System.out.println("Usage: forward_packet <dest_ip>");
                            break;
                        }
                        router.forwardPacket(tokens[1]);
                        break;

                    case "exit":
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Unknown command.");
                        break;
                }
            } catch (UnknownHostException e) {
                System.out.println("Invalid IP address.");
            } catch (NumberFormatException e) {
                System.out.println("Metric should be a number.");
            }
        }
    }
}

