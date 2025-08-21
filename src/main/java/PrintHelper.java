import java.util.List;

public class PrintHelper {
    private static String line = """
                ____________________________________________________________
                """;

    public static void printHorizontalLine() {
        System.out.println(line);
    }

    public static void printSection(String msg) {
        printHorizontalLine();
        System.out.println(msg);
        printHorizontalLine();
    }

    public static void printListInSection(List<?> ls) {
        printHorizontalLine();
        for (int i = 0; i < ls.size(); i++) {
            System.out.println((i + 1) + "." + ls.get(i).toString());
        }
        printHorizontalLine();
    }
}
