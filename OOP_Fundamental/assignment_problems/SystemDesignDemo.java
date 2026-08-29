class BrokenSrmStudent {
    static String name;
    static String regNo;
    static int attendance;

    public BrokenSrmStudent(String name, String regNo, int attendance) {
        BrokenSrmStudent.name = name;
        BrokenSrmStudent.regNo = regNo;
        BrokenSrmStudent.attendance = attendance;
    }

    public void printName() {
        System.out.println(name);
    }
}

class FixedSrmStudent {

    String name;
    String regNo;
    int attendance;

    static String university = "SRM";
    static int admissionCount = 1010;

    public FixedSrmStudent(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        admissionCount++;
        this.regNo = "RA2311003010" + admissionCount;
    }

    public void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    public static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + (admissionCount - 1010));
    }
}

public class SystemDesignDemo {
    public static void main(String[] args) {
        BrokenSrmStudent student1 = new BrokenSrmStudent("Ravi", "RA101", 85);
        BrokenSrmStudent student2 = new BrokenSrmStudent("Meera", "RA102", 90);

        student1.printName();
        student2.printName();
        System.out.println("(Ravi's data was overwritten - both students now show \"Meera\")\n");


        FixedSrmStudent s1 = new FixedSrmStudent("Ravi", 85);
        FixedSrmStudent s2 = new FixedSrmStudent("Meera", 90);

        s1.printIdCard();
        s2.printIdCard();
        FixedSrmStudent.printTotalAdmissions();
    }
}