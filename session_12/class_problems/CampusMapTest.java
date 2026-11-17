public class CampusMapTest {

    static class CampusPin {
        public static String banner = "Campus";
        public String name = "General Pin";

        public String getDetails() {
            return "Details: " + name;
        }
    }

    static class HostelPin extends CampusPin {
        public static String banner = "Hostel";
        public String name = "Hostel Pin";

        @Override
        public String getDetails() {
            return "Details: " + name;
        }
    }

    public static void main(String[] args) {
        CampusPin p = new HostelPin();

        // 1. banner: Static member, resolved at compile time by reference type (CampusPin)
        System.out.println("banner: " + p.banner + " (decided by reference type)");

        // 2. name: Field/instance variable, hidden (not overridden), resolved at compile time by reference type (CampusPin)
        System.out.println("name: " + p.name + " (decided by reference type)");

        // 3. getDetails(): Instance method, overridden, resolved at run time from the actual object type (HostelPin)
        System.out.println(p.getDetails() + " (decided by object type)");
    }
}