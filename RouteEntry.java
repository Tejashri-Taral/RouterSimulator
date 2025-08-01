public class RouteEntry {
    private String destination;
    private String subnetMask;
    private String nextHop;
    private int metric;

    public RouteEntry(String destination, String subnetMask, String nextHop, int metric) {
        this.destination = destination;
        this.subnetMask = subnetMask;
        this.nextHop = nextHop;
        this.metric = metric;
    }

    public String getDestination() {
        return destination;
    }

    public String getSubnetMask() {
        return subnetMask;
    }

    public String getNextHop() {
        return nextHop;
    }

    public int getMetric() {
        return metric;
    }

    @Override
    public String toString() {
        return destination + "/" + subnetMask + " -> " + nextHop + " (metric: " + metric + ")";
    }
}
