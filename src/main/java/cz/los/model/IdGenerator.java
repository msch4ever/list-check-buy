package cz.los.model;

import java.util.Random;

public class IdGenerator {

    private static final long UPPER_BOUND = 9999999999999999L;

    private final Random random;

    private IdGenerator() {
        this.random = new Random();
    }
    public synchronized long generateId() {
        return random.nextLong(UPPER_BOUND);
    }
    public static IdGenerator getGenerator() {
        return Holder.instance;
    }

    private static class Holder {
        private static IdGenerator instance = new IdGenerator();
    }

}
