import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Router {
    private List<RouteEntry> routingTable;

    public Router() {
        routingTable = new ArrayList<>();
    }

    public void addRoute(RouteEntry route) {
        routingTable.add(route);
    }

    public void deleteRoute(String destination) {
        routingTable.removeIf(r -> r.getDestination().equals(destination));
    }

    public void showRoutes() {
        if (routingTable.isEmpty()) {
            System.out.println("Routing table is empty.");
            return;
        }
        for (RouteEntry r : routingTable) {
            System.out.println(r);
        }
    }

    public void forwardPacket(String destIp) throws UnknownHostException {
        RouteEntry bestRoute = null;
        int longestPrefix = -1;

        for (RouteEntry route : routingTable) {
            if (IPUtils.isIpInSubnet(destIp, route.getDestination(), route.getSubnetMask())) {
                int prefixLength = subnetMaskToPrefix(route.getSubnetMask());
                if (prefixLength > longestPrefix) {
                    longestPrefix = prefixLength;
                    bestRoute = route;
                }
            }
        }

        if (bestRoute != null) {
            System.out.println("Packet to " + destIp + " forwarded to next hop " + bestRoute.getNextHop());
        } else {
            System.out.println("Packet to " + destIp + " dropped: No route found.");
        }
    }

    private int subnetMaskToPrefix(String mask) throws UnknownHostException {
        int maskInt = IPUtils.ipToInt(mask);
        int count = 0;
        for (int i = 31; i >= 0; i--) {
            if (((maskInt >> i) & 1) == 1) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
