import java.util.Arrays;

public final class DischargeSummary {

    private final String patientId;
    private final String[] medicationCodes;

    static {
        // Shared one-time static setup if required
    }

    public DischargeSummary(String patientId, String[] medicationCodes) {
        if (medicationCodes == null) {
            throw new IllegalArgumentException("Medication codes cannot be null");
        }

        for (String code : medicationCodes) {
            if (code == null || !code.matches("^MED-[A-Z]$")) {
                throw new IllegalArgumentException("Invalid medication code format: " + code);
            }
        }

        this.patientId = patientId;
        this.medicationCodes = Arrays.copyOf(medicationCodes, medicationCodes.length);
    }

    public String getPatientId() {
        return patientId;
    }

    public String[] getMedicationCodes() {
        return Arrays.copyOf(medicationCodes, medicationCodes.length);
    }

    public DischargeSummary withCorrectedMedication(int index, String newCode) {
        if (index < 0 || index >= medicationCodes.length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        String[] updatedCodes = Arrays.copyOf(medicationCodes, medicationCodes.length);
        updatedCodes[index] = newCode;
        return new DischargeSummary(this.patientId, updatedCodes);
    }

    public static String processNightlyBatch(DischargeSummary[] summaries) {
        if (summaries == null) {
            return "Processed: 0 | Critical Care: 0";
        }

        int totalCount = 0;
        int criticalCount = 0;

        for (DischargeSummary summary : summaries) {
            if (summary == null) {
                continue;
            }

            totalCount++;
            if (summary instanceof CriticalCareDischargeSummary) {
                criticalCount++;
            }
        }

        return "Processed: " + totalCount + " | Critical Care: " + criticalCount;
    }
}

class CriticalCareDischargeSummary extends DischargeSummary {

    private final int icuDays;

    public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }

    public int getIcuDays() {
        return icuDays;
    }
}