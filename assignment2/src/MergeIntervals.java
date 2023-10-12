import java.util.ArrayList;
import java.util.List;

public class MergeIntervals {
    public static void mergeIntervals(List<int[]> intervals) {
        if (intervals.size() <= 1) {
            return; // No need to merge if there is only one interval or none.
        }

        int currentIndex = 0; // Index to keep track of the current interval.

        for (int i = 1; i < intervals.size(); i++) {
            int[] currentInterval = intervals.get(currentIndex);
            int[] nextInterval = intervals.get(i);

            int currentEnd = currentInterval[1];
            int nextStart = nextInterval[0];
            int nextEnd = nextInterval[1];

            if (currentEnd >= nextStart) {
                // Merge the two intervals by updating the end of the current interval.
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // Move to the next interval if no merge is possible.
                currentIndex++;
                intervals.set(currentIndex, nextInterval);
            }
        }

        // Remove any remaining intervals that were not merged.
        intervals.subList(currentIndex + 1, intervals.size()).clear();
    }

    public static void main(String[] args) {
        List<int[]> intervals = new ArrayList<>();
        intervals.add(new int[]{1, 3});
        intervals.add(new int[]{2, 6});
        intervals.add(new int[]{5, 10});
        intervals.add(new int[]{15, 18});

        mergeIntervals(intervals);

        // Print the merged intervals
        for (int[] interval : intervals) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }
}
