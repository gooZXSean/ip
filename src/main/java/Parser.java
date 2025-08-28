import java.util.Arrays;

public class Parser {
    public static String getFlagArg(String line, String flag) {
        if (line.contains(flag)){
            return line.split(flag, 2)[1].split("/", 2)[0].trim();
        } else {
            return "";
        }
    }
}
