public class PrintHelper {
    public static void horizontalPrintln(String msg) {
        String line = """
                ____________________________________________________________
                """;
        System.out.println(line);
        System.out.println(msg);
        System.out.println(line);
    }
}
