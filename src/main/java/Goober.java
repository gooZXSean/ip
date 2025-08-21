public class Goober {
    private static final String logo = """
           
            ░██████╗░░█████╗░░█████╗░██████╗░███████╗██████╗░
            ██╔════╝░██╔══██╗██╔══██╗██╔══██╗██╔════╝██╔══██╗
            ██║░░██╗░██║░░██║██║░░██║██████╦╝█████╗░░██████╔╝
            ██║░░╚██╗██║░░██║██║░░██║██╔══██╗██╔══╝░░██╔══██╗
            ╚██████╔╝╚█████╔╝╚█████╔╝██████╦╝███████╗██║░░██║
            ░╚═════╝░░╚════╝░░╚════╝░╚═════╝░╚══════╝╚═╝░░╚═╝
            """;

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
        PrintHelper.horizontalPrintln(msg);
    }

    private static void userLoop() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            if (line.trim().equalsIgnoreCase("bye")) break;
            PrintHelper.horizontalPrintln(line);
        }
    }

    private static void exit() {
        String msg = "Bye! Hope to see you again soon!";
        PrintHelper.horizontalPrintln(msg);
    }
}
