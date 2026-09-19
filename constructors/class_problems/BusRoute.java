```java
public class BusRoute implements Comparable<BusRoute> {
    private String routeCode;
    private String routeName;
    private int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 0);
    }

    public String getRouteCode() {
        return this.routeCode;
    }

    public String getRouteName() {
        return this.routeName;
    }

    public int getPriority() {
        return this.priority;
    }

    @Override
    public int compareTo(BusRoute other) {
        if (other == null) {
            return 1;
        }

        int priorityCompare = Integer.compare(other.priority, this.priority);
        if (priorityCompare != 0) {
            return priorityCompare;
        }

        int codeCompare = this.routeCode.compareToIgnoreCase(other.routeCode);
        if (codeCompare != 0) {
            return codeCompare;
        }

        int caseCompare = this.routeCode.compareTo(other.routeCode);
        if (caseCompare != 0) {
            return caseCompare;
        }

        return this.routeName.compareTo(other.routeName);
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        if (routes == null || routes.length <= 1) {
            return routes;
        }

        int n = routes.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (routes[j].compareTo(routes[j + 1]) > 0) {
                    BusRoute temp = routes[j];
                    routes[j] = routes[j + 1];
                    routes[j + 1] = temp;
                }
            }
        }

        return routes;
    }
}
