package se751;

import se751.counters.AtomicCounter;
import se751.counters.FairLockedCounter;
import se751.counters.SynchronizedCounter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class RunnableFactory {

    private Map<String, Class> runnables = new HashMap<String, Class>();

    public RunnableFactory() {
        mapRunnable(AtomicCounter.class);
        mapRunnable(SynchronizedCounter.class);
        mapRunnable(FairLockedCounter.class);
    }

    private void mapRunnable(Class runnable) {
        runnables.put(runnable.getSimpleName(), runnable);
    }

    public Set<String> getRunnableTypes() {
        return runnables.keySet();
    }

    public Runnable newRunnable(String runnableType) throws InstantiationException, IllegalAccessException {
        return (Runnable) runnables.get(runnableType).newInstance();
    }

}
