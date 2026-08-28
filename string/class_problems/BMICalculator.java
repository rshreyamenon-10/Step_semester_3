public class BMICalculator {

    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi <= 24.9) {
            return "Normal";
        } else if (bmi <= 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("Person\t\tHeight (m)\tWeight (kg)\tBMI\tStatus");

        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);

            double roundedBmi = Math.round(bmi * 100.0) / 100.0;
            
            String status = getBmiStatus(bmi);

            System.out.println("Person " + (i + 1) + "\t" + heights[i] + "\t\t" + weights[i] + "\t\t" + roundedBmi + "\t" + status);
        }
    }

    public static void main(String[] args) {
        // Sample test data
        double[] heights = {1.75, 1.60, 1.80, 1.55, 1.70};
        double[] weights = {70.0, 90.0, 65.0, 48.0, 85.0};

        printWellnessReport(heights, weights);
    }
}