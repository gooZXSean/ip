public class Task {
    private String name = "no name";
    private boolean completionStatus = false;

    public Task(String name) {
        this.name = name;
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
        return "[" + c + "] " + name;
    }
}
