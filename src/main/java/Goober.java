import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Goober {
    private static final String logo = """
           
            ░██████╗░░█████╗░░█████╗░██████╗░███████╗██████╗░
            ██╔════╝░██╔══██╗██╔══██╗██╔══██╗██╔════╝██╔══██╗
            ██║░░██╗░██║░░██║██║░░██║██████╦╝█████╗░░██████╔╝
            ██║░░╚██╗██║░░██║██║░░██║██╔══██╗██╔══╝░░██╔══██╗
            ╚██████╔╝╚█████╔╝╚█████╔╝██████╦╝███████╗██║░░██║
            ░╚═════╝░░╚════╝░░╚════╝░╚═════╝░╚══════╝╚═╝░░╚═╝
            """;

    public static List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        startUp();
        greet();
        userLoop();
        exit();
    }

    private static void startUp() {
        System.out.println(logo);
    }

    private static void greet() {
        String msg = "Hello, I'm Goober! How may I help you today?";
        PrintHelper.printSection(msg);
    }

    private static void userLoop() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        boolean ongoing = true;
        while (ongoing) {
            String line = sc.nextLine().trim();
            String[] args = line.split(" ");
            switch(args[0]) {
                case "":
                    break;
                case "bye":
                    ongoing = false;
                    break;
                case "list":
                    printTasks();
                    break;
                case "mark":
                    markCompleteTask(args);
                    break;
                case "unmark":
                    unmarkCompleteTask(args);
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
                default:
                    break;
            }
        }
    }

    private static void printTasks() {
        PrintHelper.printListInSection(taskList, "Here are the tasks in your list:");
    }

    private static void addTodo(String line) {
        String description = line.split(" ", 2)[1];
        Task task = new Todo(description);

        taskList.add(task);
        String msg = "Got it. I've added this task:\n" +
                task.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.";
        PrintHelper.printSection(msg);
    }

    private static void addDeadline(String line) {
        String byFlag = "/by";
        int byIndex = line.indexOf(byFlag);
        String by = line.substring(byIndex + byFlag.length()).trim();
        Task task = new Deadline(line.substring("deadline ".length(), byIndex).trim() , by);

        taskList.add(task);
        String msg = "Got it. I've added this task:\n" +
                task.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.";
        PrintHelper.printSection(msg);
    }

    private static void addEvent(String line) {
        String fromFlag = "/from";
        String toFlag = "/to";
        int fromIndex = line.indexOf("/from");
        int toIndex = line.indexOf("/to");
        String from = line.substring(fromIndex + fromFlag.length(), toIndex).trim();
        String to = line.substring(toIndex + toFlag.length()).trim();
        Task task = new Event(line.substring("event ".length(), fromIndex).trim(), from, to);

        taskList.add(task);
        String msg = "Got it. I've added this task:\n" +
                task.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.";
        PrintHelper.printSection(msg);
    }


    private static void markCompleteTask(String[] args) {
        markCompleteTask(Integer.parseInt(args[1]));
    }
    
    private static void markCompleteTask(int index) {
        Task task = taskList.get(index - 1);
        task.markComplete();
        String msg = "Nice! I've marked this task as done:\n  " + task.toString();
        PrintHelper.printSection(msg);
    }

    private static void unmarkCompleteTask(String[] args) {
        unmarkCompleteTask(Integer.parseInt(args[1]));
    }

    private static void unmarkCompleteTask(int index) {
        Task task = taskList.get(index - 1);
        task.unmarkComplete();
        String msg = "OK, I've marked this task as not done yet:\n  " + task.toString();
        PrintHelper.printSection(msg);
    }

    private static void exit() {
        String msg = "Bye! Hope to see you again soon!";
        PrintHelper.printSection(msg);
    }
}
