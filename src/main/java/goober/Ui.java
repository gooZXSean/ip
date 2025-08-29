package goober;

import java.util.List;

/**
 * Console UI helpers for printing sections, lists, and separators.
 */
public class Ui {
    private static String line = """
                ____________________________________________________________
                """;

    /**
     * ASCII logo shown in the greeting banner.
     */
    public static final String logo = """
            ─────────────────────────────────────────────────────────────────────────────────────────────────
            ─██████████████─██████████████─██████████████─██████████████───██████████████─████████████████───
            ─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██───██░░░░░░░░░░██─██░░░░░░░░░░░░██───
            ─██░░██████████─██░░██████░░██─██░░██████░░██─██░░██████░░██───██░░██████████─██░░████████░░██───
            ─██░░██─────────██░░██──██░░██─██░░██──██░░██─██░░██──██░░██───██░░██─────────██░░██────██░░██───
            ─██░░██─────────██░░██──██░░██─██░░██──██░░██─██░░██████░░████─██░░██████████─██░░████████░░██───
            ─██░░██──██████─██░░██──██░░██─██░░██──██░░██─██░░░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░░░██───
            ─██░░██──██░░██─██░░██──██░░██─██░░██──██░░██─██░░████████░░██─██░░██████████─██░░██████░░████───
            ─██░░██──██░░██─██░░██──██░░██─██░░██──██░░██─██░░██────██░░██─██░░██─────────██░░██──██░░██─────
            ─██░░██████░░██─██░░██████░░██─██░░██████░░██─██░░████████░░██─██░░██████████─██░░██──██░░██████─
            ─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░░░██─██░░░░░░░░░░██─██░░██──██░░░░░░██─
            ─██████████████─██████████████─██████████████─████████████████─██████████████─██████──██████████─
            ─────────────────────────────────────────────────────────────────────────────────────────────────
            """;

    /**
     * Prints a horizontal separator line.
     */
    public static void printHorizontalLine() {
        System.out.print(line);
    }

    /**
     * Prints a message wrapped between two horizontal lines.
     *
     * @param msg message to print
     */
    public static void printSection(String msg) {
        printHorizontalLine();
        System.out.println(msg);
        printHorizontalLine();
    }

    /**
     * Prints a list wrapped between two horizontal lines.
     *
     * @param ls list of items to print (1-based indexing)
     */
    public static void printListInSection(List<?> ls) {
        printHorizontalLine();
        for (int i = 0; i < ls.size(); i++) {
            System.out.println((i + 1) + "." + ls.get(i).toString());
        }
        printHorizontalLine();
    }

    /**
     * Prints a list wrapped between two horizontal lines, with a custom heading.
     *
     * @param ls  list of items to print (1-based indexing)
     * @param msg heading shown above the list
     */
    public static void printListInSection(List<?> ls, String msg) {
        printHorizontalLine();
        printList(ls, msg);
        printHorizontalLine();
    }

    /**
     * Prints a heading followed by a 1-based indexed list.
     *
     * @param ls  list of items to print
     * @param msg heading shown above the list
     */
    public static void printList(List<?> ls, String msg) {
        System.out.println(msg);
        for (int i = 0; i < ls.size(); i++) {
            System.out.println((i + 1) + "." + ls.get(i).toString());
        }
    }
}
