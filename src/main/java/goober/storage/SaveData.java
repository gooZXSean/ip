package goober.storage;

import goober.task.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaveData implements Serializable {
    private final List<Task> taskList = new ArrayList<>();

    public List<Task> getTaskList() {
        return taskList;
    }

    public List<Task> searchTask(String query) {
        List<Task> output = new ArrayList<>();
        for (Task t : taskList) {
            String desc = t.getDescription();
            if (desc != null && desc.toLowerCase().contains(query)) {
                output.add(t);
            }
        }
        return output;
    }
}
