package se751;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class RunnableFactory {

    public static Runnable newRunnable(RunnableType type) {
        switch (type) {
            case AtomicCounter:
                return new AtomicCounter();
            case SynchronizedCounter:
                return new SynchronizedCounter();
        }
        return null;
    }

}
