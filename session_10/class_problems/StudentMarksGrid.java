import java.util.HashMap;
import java.util.Map;

public class StudentMarksGrid {
    public static void main(String[] args) {
        String[] subjects = {"Math", "Sci", "Eng"};
        String[] studentNames = {"Anu", "Ravi", "Meena"};
        Integer[][] marks = {
            {80, 90, 70},
            {60, 85, null},
            {95, 75, 88}
        };

        Map<String, Integer> totals = new HashMap<>();
        for (int i = 0; i < studentNames.length; i++) {
            int total = 0;
            for (int j = 0; j < marks[i].length; j++) {
                if (marks[i][j] != null) {
                    total += marks[i][j];
                }
            }
            totals.put(studentNames[i], total);
        }

        System.out.print("Totals ");
        boolean first = true;
        for (String name : studentNames) {
            if (!first) System.out.print(", ");
            System.out.print(name + " " + totals.get(name));
            first = false;
        }
        System.out.println();

        System.out.print("toppers ");
        first = true;
        for (int j = 0; j < subjects.length; j++) {
            String bestStudent = "";
            int maxMark = -1;
            for (int i = 0; i < studentNames.length; i++) {
                if (j < marks[i].length && marks[i][j] != null) {
                    if (marks[i][j] > maxMark) {
                        maxMark = marks[i][j];
                        bestStudent = studentNames[i];
                    }
                }
            }
            if (!first) System.out.print(", ");
            System.out.print(subjects[j] + " " + bestStudent + " " + maxMark);
            first = false;
        }
        System.out.println();
    }
}
