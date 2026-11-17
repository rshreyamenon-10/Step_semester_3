import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

public class CustomerRegistry {

    static class Customer {
        int id;
        String name;

        Customer(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Customer customer = (Customer) o;
            return id == customer.id && Objects.equals(name, customer.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static void main(String[] args) {
        Set<Customer> registry = new HashSet<>();

        Customer c1 = new Customer(101, "Asha");
        Customer c2 = new Customer(101, "Asha");
        Customer c3 = new Customer(102, "Ravi");

        System.out.println("Add (101, Asha) from Branch A: " + registry.add(c1));
        System.out.println("Add (101, Asha) from Branch B: " + registry.add(c2));
        System.out.println("Add (102, Ravi): " + registry.add(c3));

        System.out.println("Contains (101, Asha)?: " + registry.contains(new Customer(101, "Asha")));
        System.out.println("Total unique customer count: " + registry.size());
    }
}
