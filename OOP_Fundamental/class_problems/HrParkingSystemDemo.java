class Employee {
    private String empId;
    private String empName;
    private double salary;

    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public double getEffectivePay() {
        return getSalary();
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    public ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    @Override
    public double getEffectivePay() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    public InternEmployee(String empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    @Override
    public double getEffectivePay() {
        return Math.min(getSalary(), stipendCap);
    }
}

class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;

    public ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    public boolean allot() {
        if (occupiedCount < capacity) {
            occupiedCount++;
            return true;
        }
        return false;
    }
}

class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    public CompanyEmployeeRecord(String name, String empId, Employee employee) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = null;
        totalRecords++;
    }

    public void assignSlot(ParkingSlot slot) {
        if (slot != null && slot.allot()) {
            this.slot = slot;
        }
    }

    public String fullProfile() {
        String slotStr = (slot != null) ? slot.slotNo : "no parking assigned";
        return name + " | Pay: Rs " + employee.getEffectivePay() + " | Slot: " + slotStr;
    }
}

public class HrParkingSystemDemo {
    public static void main(String[] args) {
        ParkingSlot p1 = new ParkingSlot("A1", 1, 0);
        ParkingSlot p2 = new ParkingSlot("A2", 1, 0);

        CompanyEmployeeRecord r1 = new CompanyEmployeeRecord("Divya", "EMP001", new ManagerEmployee("EMP001", "Divya", 70000, 8000));
        CompanyEmployeeRecord r2 = new CompanyEmployeeRecord("Karan", "EMP002", new Employee("EMP002", "Karan", 40000));
        CompanyEmployeeRecord r3 = new CompanyEmployeeRecord("Meera", "EMP003", new InternEmployee("EMP003", "Meera", 12000, 10000));

        r1.assignSlot(p1);
        r2.assignSlot(p2);

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}