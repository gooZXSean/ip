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
        exit();
    }

    private static void startUp() {
        System.out.println(logo);
    }

    private static void greet() {
        String msg = "Hello, I'm Goober!, how may I help you today?";
        printHelper.horizontalPrintln(msg);
    }

    private static void exit() {
        String msg = "Bye! Hope to see you again soon!";
        printHelper.horizontalPrintln(msg);
    }
}
