package se751;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class RunnableFactory {

    private Map<String, Class> runnables = new HashMap<String, Class>();

    public void mapRunnable(Class runnable) {
        runnables.put(runnable.getSimpleName(), runnable);
    }

    public Set<String> getRunnableTypes() {
        return runnables.keySet();
    }

    public Runnable newRunnable(String runnableType, Integer workload) throws InstantiationException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Constructor runnableConstructor = runnables.get(runnableType).getConstructor(Integer.class);
        return (Runnable) runnableConstructor.newInstance(workload);
    }

}
