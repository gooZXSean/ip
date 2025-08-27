import java.util.List;

public class Goober {
    private static SaveData saveData;
    private static List<Task> taskList;
    private static final String saveFileName = "GooberTasks.txt";

    public static void main(String[] args) {
        startUp();
        greet();
        userLoop();
        exit();
    }

    private static void startUp() {
        saveData = FileHelper.getOrCreateSave(saveFileName);
        taskList = saveData.getTaskList();
    }

    private static void greet() {
        System.out.println(PrintHelper.logo);
        String msg = "Hello, I'm Goober! How may I help you today?";
        PrintHelper.printSection(msg);
    }

    private static void userLoop() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        boolean ongoing = true;
        while (ongoing) {
            String line = sc.nextLine().trim();
            String cmd = line.split(" ", 2)[0].toLowerCase();
            try {
                switch (cmd) {
                case "":
                    break;
                case "bye":
                    ongoing = false;
                    break;
                case "list":
                    printTasks();
                    break;
                case "mark":
                    markCompleteTask(line);
                    break;
                case "unmark":
                    unmarkCompleteTask(line);
                    break;
                case "todo":
                    addTodo(line);
                    break;
                case "deadline":
                    addDeadline(line);
                    break;
                case "event":
                    addEvent(line);
                    break;
                case "delete":
                    deleteTask(line);
                    break;
                default:
                    throw new IllegalArgumentException("Sorry, I don't recognise that command! :(");
                }
            } catch (IllegalArgumentException e) {
                PrintHelper.printSection((e.toString()));
            }
        }
    }

    private static void printTasks() {
        if (taskList.isEmpty()) {
            PrintHelper.printSection("You've got no tasks!");
        } else {
            PrintHelper.printListInSection(taskList, "Here are the tasks in your list:");
        }
    }

    private static void addTodo(String line) {
        String[] args = line.split(" ", 2);
        if (args.length != 2) {
            throw new IllegalArgumentException("The description of a todo cannot be empty!");
        }
        String description = args[1];
        Task task = new Todo(description);
        addTask(task);
    }

    private static void addDeadline(String line) {
        String byFlag = "/by";
        int byIndex = line.indexOf(byFlag);
        String by = line.substring(byIndex + byFlag.length()).trim();
        Task task = new Deadline(line.substring("deadline ".length(), byIndex).trim(), by);
        addTask(task);
    }

    private static void addEvent(String line) {
        String fromFlag = "/from";
        String toFlag = "/to";
        int fromIndex = line.indexOf(fromFlag);
        int toIndex = line.indexOf(toFlag);
        String from = line.substring(fromIndex + fromFlag.length(), toIndex).trim();
        String to = line.substring(toIndex + toFlag.length()).trim();
        Task task = new Event(line.substring("event ".length(), fromIndex).trim(), from, to);
        addTask(task);
    }

    private static void addTask(Task task) {
        taskList.add(task);
        String msg = "Got it. I've added this task:\n" +
                task.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.";
        updateSaveData();
        PrintHelper.printSection(msg);
    }

    private static void deleteTask(String line) {
        String[] args = line.split(" ");
        deleteTask(Integer.parseInt(args[1]));
    }

    private static void deleteTask(int index) {
        Task task = taskList.get(index - 1);
        taskList.remove(task);
        String msg = "Noted. I've removed this task:\n" +
                task.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.";
        updateSaveData();
        PrintHelper.printSection(msg);

    }

    private static void markCompleteTask(String line) {
        String[] args = line.split(" ");
        markCompleteTask(Integer.parseInt(args[1]));
    }

    private static void markCompleteTask(int index) {
        Task task = taskList.get(index - 1);
        task.markComplete();
        String msg = "Nice! I've marked this task as done:\n  " + task;
        updateSaveData();
        PrintHelper.printSection(msg);
    }

    private static void unmarkCompleteTask(String line) {
        String[] args = line.split(" ");
        unmarkCompleteTask(Integer.parseInt(args[1]));
    }

    private static void unmarkCompleteTask(int index) {
        Task task = taskList.get(index - 1);
        task.unmarkComplete();
        String msg = "OK, I've marked this task as not done yet:\n  " + task;
        updateSaveData();
        PrintHelper.printSection(msg);
    }

    private static void updateSaveData() {
        FileHelper.saveToFile(saveData, saveFileName);
    }

    private static void exit() {
        String msg = "Bye! Hope to see you again soon!";
        PrintHelper.printSection(msg);
    }
}
