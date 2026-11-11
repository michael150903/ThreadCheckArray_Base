import java.util.ArrayList;

public class SharedData {
    private ArrayList<Integer> array;
    private boolean[] winArray;
    private boolean flag;
    private final int b;

    public SharedData(ArrayList<Integer> array, int b) {
        this.array = array;
        this.b = b;
        // Initialize winArray with the same size as the ArrayList
        winArray = new boolean[array.size()];
    }

    /**
     * Returns the ArrayList of integers.
     *
     * @return the ArrayList of integers
     */
    public ArrayList<Integer> getArray() { return array; }

    /**
     * Returns an array indicating whether certain conditions are met for each element in the ArrayList.
     *
     * @return an array of booleans indicating whether certain conditions are met for each element in the ArrayList
     */
    public boolean[] getWinArray() { return winArray; }

    /**
     * Sets the array indicating whether certain conditions are met for each element in the ArrayList.
     *
     * @param winArray an array of booleans indicating whether certain conditions are met for each element in the ArrayList
     */
    public void setWinArray(boolean[] winArray) { this.winArray = winArray; }

    /**
     * Returns a final integer value that was passed to the constructor.
     *
     * @return the final integer value that was passed to the constructor
     */
    public int getB() { return b; }

    /**
     * Returns whether a certain condition has been met.
     *
     * @return true if the condition has been met, false otherwise
     */
    public boolean getFlag() { return flag; }

    /**
     * Sets whether a certain condition has been met.
     *
     * @param flag true if the condition has been met, false otherwise
     */
    public void setFlag(boolean flag) { this.flag = flag; }
}
