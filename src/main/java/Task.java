public class Task {
    private String description = "no name";
    private boolean completionStatus = false;

    public Task(String description) {
        this.description = description;
    }

    public void markComplete() {
        this.completionStatus = true;
    }

    public void unmarkComplete() {
        this.completionStatus = false;
    }

    @Override
    public String toString() {
        char c = ' ';
        if (completionStatus) c = 'X';
        return "[" + c + "] " + description;
    }
}
