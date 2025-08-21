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
            String line = sc.nextLine();
            switch(line) {
                case "bye":
                    ongoing = false;
                    break;
                case "list":
                    printTasks();
                    break;
                default:
                    addTask(line);
                    break;
            }
        }
    }

    private static void printTasks() {
        PrintHelper.printListInSection(taskList);
    }

    private static void addTask(String description) {
        taskList.add(new Task(description));
        PrintHelper.printSection("added: " + description);
    }

    private static void exit() {
        String msg = "Bye! Hope to see you again soon!";
        PrintHelper.printSection(msg);
    }
}
