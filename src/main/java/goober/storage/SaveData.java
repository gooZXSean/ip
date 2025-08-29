package goober.storage;

import goober.task.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Serializable container for persistent data.
 */
public class SaveData implements Serializable {
    private final List<Task> taskList = new ArrayList<>();

    /**
     * Returns task list.
     *
     * @return task list
     */
    public List<Task> getTaskList() {
        return taskList;
    }
}
