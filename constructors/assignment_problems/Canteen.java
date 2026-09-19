public class Canteen implements Comparable<Canteen> {
    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    public String getCanteenCode() {
        return this.canteenCode;
    }

    public String getCanteenName() {
        return this.canteenName;
    }

    public int getTrustScore() {
        return this.trustScore;
    }

    @Override
    public int compareTo(Canteen other) {
        if (other == null) {
            return -1;
        }

        int scoreCompare = Integer.compare(other.trustScore, this.trustScore);
        if (scoreCompare != 0) {
            return scoreCompare;
        }

        int codeCompare = this.canteenCode.compareToIgnoreCase(other.canteenCode);
        if (codeCompare != 0) {
            return codeCompare;
        }

        int caseCompare = this.canteenCode.compareTo(other.canteenCode);
        if (caseCompare != 0) {
            return caseCompare;
        }

        return Integer.compare(this.canteenName.length(), other.canteenName.length());
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {
        if (canteens == null || canteens.length <= 1) {
            return canteens;
        }

        int n = canteens.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (canteens[j].compareTo(canteens[j + 1]) > 0) {
                    Canteen temp = canteens[j];
                    canteens[j] = canteens[j + 1];
                    canteens[j + 1] = temp;
                }
            }
        }

        return canteens;
    }
}