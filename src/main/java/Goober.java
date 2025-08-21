import java.util.ArrayList;
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
                default:
                    addTask(line);
                    break;
            }
        }
    }

    private static void printTasks() {
        PrintHelper.printListInSection(taskList, "Here are the tasks in your list:");
    }

    private static void addTask(String description) {
        taskList.add(new Task(description));
        PrintHelper.printSection("added: " + description);
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
