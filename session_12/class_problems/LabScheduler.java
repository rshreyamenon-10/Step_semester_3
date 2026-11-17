import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;

public class LabScheduler {

    static class Job {
        String name;
        int remainingTime;
        int finishTime;

        Job(String name, int remainingTime) {
            this.name = name;
            this.remainingTime = remainingTime;
            this.finishTime = 0;
        }
    }

    static class FinishRecord {
        String name;
        int finishTime;

        FinishRecord(String name, int finishTime) {
            this.name = name;
            this.finishTime = finishTime;
        }
    }

    public static void simulate(int quantum, List<Job> initialJobs) {
        Queue<Job> queue = new LinkedList<>(initialJobs);
        List<String> timeSlices = new ArrayList<>();
        List<FinishRecord> finishedJobs = new ArrayList<>();
        int currentTime = 0;

        while (!queue.isEmpty()) {
            Job job = queue.poll();
            int runTime = Math.min(quantum, job.remainingTime);
            int start = currentTime;
            currentTime += runTime;
            job.remainingTime -= runTime;

            timeSlices.add(job.name + "(" + start + "-" + currentTime + ")");

            if (job.remainingTime == 0) {
                job.finishTime = currentTime;
                finishedJobs.add(new FinishRecord(job.name, job.finishTime));
            } else {
                queue.offer(job);
            }
        }

        // Print time slices
        System.out.println("Expected Output: " + String.join(" ", timeSlices));

        // Print finish order
        StringBuilder finishStr = new StringBuilder("finish ");
        for (int i = 0; i < finishedJobs.size(); i++) {
            FinishRecord fr = finishedJobs.get(i);
            finishStr.append(fr.name).append(" ").append(fr.finishTime);
            if (i < finishedJobs.size() - 1) {
                finishStr.append(", ");
            }
        }
        System.out.println(finishStr.toString());
    }

    public static void main(String[] args) {
        int quantum = 3;
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job("P1", 5));
        jobs.add(new Job("P2", 3));
        jobs.add(new Job("P3", 7));

        simulate(quantum, jobs);
    }
}
