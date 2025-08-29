package goober;

import goober.storage.SaveData;
import goober.storage.Storage;
import goober.task.Deadline;
import goober.task.Event;
import goober.task.Task;
import goober.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Goober {
    private static SaveData saveData;
    private static final String saveFileName = "GooberTasks.ser";

    public static void main(String[] args) {
        startUp();
        greet();
        userLoop();
        exit();
    }

    private static void startUp() {
        saveData = Storage.getOrCreateSave(saveFileName);
    }

    private static void greet() {
        System.out.println(Ui.logo);
        String msg = "Hello, I'm Goober! How may I help you today?";
        Ui.printSection(msg);
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
                case "find":
                    find(line);
                    break;
                default:
                    throw new IllegalArgumentException("Sorry, I don't recognise that command! :(");
                }
            } catch (IllegalArgumentException e) {
                Ui.printSection((e.toString()));
            }
        }
    }

    private static void printTasks() {
        if (saveData.getTaskList().isEmpty()) {
            Ui.printSection("You've got no tasks!");
        } else {
            Ui.printListInSection(saveData.getTaskList(), "Here are the tasks in your list:");
        }
    }

    private static void addTodo(String line) {
        String flag = "todo";
        String description = Parser.getFlagArg(line, flag);
        if (description.isEmpty()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty!");
        }
        Task task = new Todo(description);
        addTask(task);
    }

    private static void addDeadline(String line) {
        String flag = "deadline";
        String byFlag = "/by";
        String description = Parser.getFlagArg(line, flag);
        String by = Parser.getFlagArg(line, byFlag);
        if (description.isEmpty()) {
            throw new IllegalArgumentException("The description of a deadline cannot be empty!");
        }
        if (by.isEmpty()) {
            throw new IllegalArgumentException("The " + byFlag + " of a deadline cannot be empty!");
        }

        try {
            LocalDateTime byDate = Parser.parseDateTime(by);
            Task task = new Deadline(description, byDate);
            addTask(task);
        } catch (DateTimeParseException e) {
            String msg = "Wrong date time format!: " + e + "\nAccepted date formats: ";
            Ui.printListInSection(Parser.formatList, msg);
        }
    }

    private static void addEvent(String line) {
        String flag = "event";
        String fromFlag = "/from";
        String toFlag = "/to";
        String description = Parser.getFlagArg(line, flag);
        String from = Parser.getFlagArg(line, fromFlag);
        String to = Parser.getFlagArg(line, toFlag);
        if (description.isEmpty()) {
            throw new IllegalArgumentException("The description of an event cannot be empty!");
        }
        if (from.isEmpty()) {
            throw new IllegalArgumentException("The " + fromFlag + " of a event cannot be empty!");
        }
        if (to.isEmpty()) {
            throw new IllegalArgumentException("The " + toFlag + " of a event cannot be empty!");
        }
        try {
            LocalDateTime fromDate = Parser.parseDateTime(from);
            LocalDateTime toDate = Parser.parseDateTime(to);
            Task task = new Event(description, fromDate, toDate);
            addTask(task);
        } catch (DateTimeParseException e) {
            String msg = "Wrong date time format!: " + e + "/nAccepted date formats: ";
            Ui.printListInSection(Parser.formatList, msg);
        }
    }

    private static void addTask(Task task) {
        saveData.getTaskList().add(task);
        String msg = "Got it. I've added this task:\n" +
                task.toString() +
                "\nNow you have " + saveData.getTaskList().size() + " tasks in the list.";
        updateSaveData();
        Ui.printSection(msg);
    }

    private static void deleteTask(String line) {
        String flag = "delete";
        String index = Parser.getFlagArg(line, flag);
        if (index.isEmpty()) {
            throw new IllegalArgumentException("The task number of a delete cannot be empty!");
        }
        deleteTask(Integer.parseInt(index));
    }

    private static void deleteTask(int index) {
        try {
            Task task = saveData.getTaskList().get(index - 1);
            saveData.getTaskList().remove(task);
            String msg = "Noted. I've removed this task:\n" +
                    task.toString() +
                    "\nNow you have " + saveData.getTaskList().size() + " tasks in the list.";
            updateSaveData();
            Ui.printSection(msg);
        } catch (IndexOutOfBoundsException e) {
            String msg = "Error! Index of task out of bounds!";
            Ui.printSection(msg);
        }
    }

    private static void markCompleteTask(String line) {
        String flag = "mark";
        String index = Parser.getFlagArg(line, flag);
        if (index.isEmpty()) {
            throw new IllegalArgumentException("The task number of a mark cannot be empty!");
        }
        markCompleteTask(Integer.parseInt(index));
    }

    private static void markCompleteTask(int index) {
        try {
            Task task = saveData.getTaskList().get(index - 1);
            task.markComplete();
            String msg = "Nice! I've marked this task as done:\n  " + task;
            updateSaveData();
            Ui.printSection(msg);
        } catch (IndexOutOfBoundsException e) {
            String msg = "Error! Index of task out of bounds!";
            Ui.printSection(msg);
        }
    }

    private static void unmarkCompleteTask(String line) {
        String flag = "unmark";
        String index = Parser.getFlagArg(line, flag);
        if (index.isEmpty()) {
            throw new IllegalArgumentException("The task number of a unmark cannot be empty!");
        }
        unmarkCompleteTask(Integer.parseInt(index));
    }

    private static void unmarkCompleteTask(int index) {
        try {
            Task task = saveData.getTaskList().get(index - 1);
            task.unmarkComplete();
            String msg = "OK, I've marked this task as not done yet:\n  " + task;
            updateSaveData();
            Ui.printSection(msg);
        } catch (IndexOutOfBoundsException e) {
            String msg = "Error! Index of task out of bounds!";
            Ui.printSection(msg);
        }
    }

    private static void updateSaveData() {
        Storage.saveToFile(saveData, saveFileName);
    }

    /**
     * Finds tasks whose description contains the given keyword (case-insensitive)
     * and prints them in a numbered list. If none match, prints an empty-state message.
     *
     * @param line user input containing the keyword (e.g., "find book")
     */
    private static void find(String line) {
        String flag = "find";
        String query = Parser.getFlagArg(line, flag);
        List<Task> searchResult = saveData.searchTask(query);

        String msg = "Here are the matching tasks in your list:\n";
        Ui.printListInSection(searchResult, msg);
    }

    private static void exit() {
        String msg = "Bye! Hope to see you again soon!";
        Ui.printSection(msg);
    }
}
