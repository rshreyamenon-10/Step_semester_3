class SrmStudent {
    String name;
    String regNo;
    int attendance;

    public SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    public boolean isEligible() {
        return this.attendance >= 75;
    }

    public void addAttendanceUpdate(int newAttendance) {
        this.attendance = newAttendance;
    }

    public static double classAverage(SrmStudent[] students) {
        if (students == null || students.length == 0) return 0.0;
        
        double total = 0;
        for (SrmStudent s : students) {
            total += s.attendance;
        }
        return total / students.length;
    }
}

public class AttendanceSystem {
    public static void main(String[] args) {
        SrmStudent[] students = {
            new SrmStudent("Ravi", "REG101", 82),
            new SrmStudent("Anitha", "REG102", 68),
            new SrmStudent("Karthik", "REG103", 91),
            new SrmStudent("Meera", "REG104", 74),
            new SrmStudent("Suresh", "REG105", 60)
        };

        for (SrmStudent s : students) {
            String status = s.isEligible() ? "Eligible" : "Detained";
            System.out.println(s.name + " - " + s.attendance + "% - " + status);
        }

        double avg = SrmStudent.classAverage(students);
        System.out.println("Class average: " + avg + "%");
    }
}