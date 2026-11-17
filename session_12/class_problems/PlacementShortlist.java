import java.util.Scanner;

public class PlacementShortlist {

    static class Candidate {
        String name;
        int[] scores = new int[6];
        int total = 0;
        int bestGain = Integer.MIN_VALUE;
        int bestStart = 0;
        int bestEnd = 0;
        int originalIndex;

        Candidate(String name, int originalIndex) {
            this.name = name;
            this.originalIndex = originalIndex;
        }

        void computeStats() {
            total = 0;
            for (int s : scores) {
                total += s;
            }

            // Find best run of consecutive improvement
            bestGain = Integer.MIN_VALUE;
            int currentGain = 0;
            int start = 0;

            for (int i = 0; i < 5; i++) {
                int diff = scores[i + 1] - scores[i];
                if (diff > 0) {
                    if (currentGain <= 0) {
                        start = i;
                        currentGain = diff;
                    } else {
                        currentGain += diff;
                    }
                } else {
                    currentGain = diff; // diff could be negative or 0
                    start = i + 1;
                }

                if (currentGain > bestGain) {
                    bestGain = currentGain;
                    bestStart = start + 1; // 1-based
                    bestEnd = i + 2;       // 1-based
                }
            }

            // Fallback if no positive increase (though scores can be flat/decreasing)
            if (bestGain == Integer.MIN_VALUE) {
                bestGain = 0;
                bestStart = 1;
                bestEnd = 1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        Candidate[] candidates = new Candidate[n];
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            candidates[i] = new Candidate(name, i);
            for (int j = 0; j < 6; j++) {
                candidates[i].scores[j] = scanner.nextInt();
            }
            candidates[i].computeStats();
        }

        // Custom Merge Sort: sort by total descending, then originalIndex ascending
        mergeSort(candidates, 0, n - 1);

        System.out.println("Shortlist:");
        for (int i = 0; i < n; i++) {
            Candidate c = candidates[i];
            System.out.println((i + 1) + ". " + c.name + " " + c.total + " | best climb +" + c.bestGain + " (test " + c.bestStart + " to " + c.bestEnd + ")");
        }

        while (scanner.hasNext()) {
            String cmd = scanner.next();
            if (cmd.equals("CUTOFF")) {
                int x = scanner.nextInt();
                int count = countCutoff(candidates, x);
                System.out.println(count);
            } else if (cmd.equals("PAIR")) {
                int target = scanner.nextInt();
                findPair(candidates, target);
            }
        }
        scanner.close();
    }

    private static void mergeSort(Candidate[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Candidate[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Candidate[] L = new Candidate[n1];
        Candidate[] R = new Candidate[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            // Higher total first; on tie, smaller originalIndex (earlier registration order) first
            if (L[i].total > R[j].total || (L[i].total == R[j].total && L[i].originalIndex < R[j].originalIndex)) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    private static int countCutoff(Candidate[] sorted, int x) {
        // Binary search for first element with total < x
        int low = 0, high = sorted.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (sorted[mid].total >= x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low; // number of candidates with total >= x
    }

    private static void findPair(Candidate[] sorted, int target) {
        int left = 0, right = sorted.length - 1;
        while (left < right) {
            int sum = sorted[left].total + sorted[right].total;
            if (sum == target) {
                System.out.println(sorted[left].name + " and " + sorted[right].name);
                return;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println("no pair");
    }
}