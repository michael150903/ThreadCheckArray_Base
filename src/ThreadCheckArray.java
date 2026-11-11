import java.util.ArrayList;

/**
 * Searches for a subset of shared integer values whose sum matches the target.
 *
 * <p>Each instance takes a synchronized snapshot of the shared array and target,
 * then explores candidate subsets recursively when run on a thread. The shared
 * flag stops further search after a solution is found, and the winning
 * positions are reported through the shared data object.</p>
 *
 * <p>The working values are stored in an {@link ArrayList}{@code <Integer>};
 * result markers remain a primitive {@code boolean[]}.</p>
 */
public class ThreadCheckArray implements Runnable {
    private boolean flag;
    private boolean[] winArray;
    SharedData sd;
    ArrayList<Integer> array;
    int b;

    /**
     * Creates a checker using a synchronized snapshot of the shared values and
     * target.
     *
     * @param sd shared data containing the values, target, completion flag,
     *           and result array
     */
    public ThreadCheckArray(SharedData sd) {
        this.sd = sd;
        synchronized (sd) {
            array = new ArrayList<>(sd.getArray());
            b = sd.getB();
        }
        winArray = new boolean[array.size()];
    }

    /**
     * Recursively searches the first {@code n} values for a subset with the
     * requested sum.
     *
     * <p>For each value, this method explores the branch that includes the
     * value and then, unless another thread has already found a solution, the
     * branch that excludes it. Access to the shared completion flag is
     * synchronized on {@code sd}.</p>
     *
     * @param n index of the current value being considered
     * @param b remaining sum required for the current branch
     */
    void rec(int n, int b) {
        synchronized (sd) {
            if (sd.getFlag()) return;
        }
        if (n == 1) {
            if(b == 0 || b == array.get(n-1)) {
                flag = true;
                synchronized (sd) { sd.setFlag(true); }
            }
            if (b == array.get(n-1)) winArray[n-1] = true;
            return;
        }

        rec(n-1, b - array.get(n-1));
        if (flag) winArray[n-1] = true;
        synchronized (sd) {
            if (sd.getFlag()) return;
        }
        rec(n-1, b);
    }

    /**
     * Runs the subset search for this thread and publishes its winning
     * positions to the shared data object if it finds a solution.
     */
    public void run() {
        if (array.size() != 1)
            if (Thread.currentThread().getName().equals("thread1"))
                rec(array.size()-1, b - array.get(array.size() - 1));
            else
                rec(array.size()-1, b);
        if (array.size() == 1)
            if (b == array.get(0) && !flag) {
                winArray[0] = true;
                flag = true;
                synchronized (sd) { sd.setFlag(true); }
            }
        if (flag) {
            if (Thread.currentThread().getName().equals("thread1"))
                winArray[array.size() - 1] = true;
            synchronized (sd) { sd.setWinArray(winArray); }
        }
    }
}
