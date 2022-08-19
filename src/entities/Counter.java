package entities;

/**
 * The type Counter.
 */
public class Counter {
    private int count = 0;

    /**
     * Increase.
     * add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Decrease.
     * subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Gets value.
     * get current count.
     *
     * @return the value
     */
    public int getValue() {
        return this.count;
    }

    /**
     * Set.
     *
     * @param remainingBlocks the remaining blocks
     */
    public void set(int remainingBlocks) {
        this.count = remainingBlocks;
    }
}