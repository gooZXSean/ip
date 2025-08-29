package goober;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class Parser {
    public static final List<String> FORMAT_LIST = List.of("yyyy-MM-dd HHmm", "HHmm yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = buildDateTimeFormatterFromPatterns(FORMAT_LIST);

    public static DateTimeFormatter buildDateTimeFormatterFromPatterns(List<String> list) {
        DateTimeFormatterBuilder dTFBuilder = new DateTimeFormatterBuilder();
        list.forEach(str -> dTFBuilder.appendOptional(DateTimeFormatter.ofPattern(str)));
        return dTFBuilder.toFormatter();
    }

    public static String getFlagArg(String line, String flag) {
        if (line.contains(flag)) {
            return line.split(flag, 2)[1].split("/", 2)[0].trim();
        } else {
            return "";
        }
    }

    public static LocalDateTime parseDateTime(String str) {
        return LocalDateTime.parse(str, DATE_TIME_FORMATTER);
    }

    public static String dateTimeToString(LocalDateTime dTF) {
        return dTF.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm")) + "H";
    }
}
