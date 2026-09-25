import java.util.Arrays;

public class PatientVitals {
    private double[] readings = new double[0];

    public PatientVitals(double[] initialReadings) {
        if (initialReadings != null) {
            for (double reading : initialReadings) {
                recordReading(reading);
            }
        }
    }

    public void recordReading(double reading) {
        if (reading <= 0 || reading > 45) {
            return;
        }
        readings = Arrays.copyOf(readings, readings.length + 1);
        readings[readings.length - 1] = reading;
    }

    public double getAverage() {
        if (readings.length == 0) {
            return 0.0;
        }
        double sum = 0;
        for (double r : readings) {
            sum += r;
        }
        return sum / readings.length;
    }

    public double[] getAllReadings() {
        return Arrays.copyOf(readings, readings.length);
    }
}