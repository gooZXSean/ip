package goober.task;

import java.io.Serializable;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task implements Serializable {
    private String description = "no name";
    private boolean isCompleted = false;

    /**
     * Constructs a new Task.
     *
     * @param description the description
     */
    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void markComplete() {
        this.isCompleted = true;
    }


    public void unmarkComplete() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        char c = ' ';
        if (isCompleted) {
            c = 'X';
        }
        return "[" + c + "] " + description;
    }
}
