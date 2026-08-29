class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    public FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
        }
    }

    public double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {
    public HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }
}

class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    public HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    public boolean allot() {
        if (occupied < beds) {
            occupied++;
            return true;
        }
        return false;
    }
}

class SrmStudent {
    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    public SrmStudent(String name, String regNo, HostelFeeAccount feeAccount) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = null;
        totalStudents++;
    }

    public void assignRoom(HostelRoom room) {
        if (room != null && room.allot()) {
            this.room = room;
        }
    }

    public String fullStatus() {
        String roomStr = (room != null) ? room.roomNo : "unallotted";
        return name + " | Due: Rs " + feeAccount.getDue() + " | Room: " + roomStr;
    }
}

public class MiniSystemDemo {
    public static void main(String[] args) {
        HostelRoom r1 = new HostelRoom("C-214", 2, 0);
        HostelRoom r2 = new HostelRoom("C-507", 2, 0);

        SrmStudent s1 = new SrmStudent("Ravi", "REG101", new HostelFeeAccount("REG101", 200000, 0));
        SrmStudent s2 = new SrmStudent("Anitha", "REG102", new HostelFeeAccount("REG102", 200000, 0));
        SrmStudent s3 = new SrmStudent("Karthik", "REG103", new HostelFeeAccount("REG103", 200000, 0));

        s1.assignRoom(r1);
        s2.assignRoom(r2);

        s1.feeAccount.pay(60000);
        s2.feeAccount.pay(20000);
        s3.feeAccount.pay(-5000); // Invalid negative payment (rejected)

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}