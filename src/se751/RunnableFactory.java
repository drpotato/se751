package se751;

import se751.counters.AtomicCounter;
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
        runnables.put("AtomicCounter", AtomicCounter.class);
        runnables.put("SynchronizedCounter", SynchronizedCounter.class);
    }

    public Set<String> getRunnableTypes() {
        return runnables.keySet();
    }

    public Runnable newRunnable(String runnableType) throws InstantiationException, IllegalAccessException {
        return (Runnable) runnables.get(runnableType).newInstance();
    }

}
