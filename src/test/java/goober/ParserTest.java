package goober;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Nested
    @DisplayName("getFlagArg()")
    class GetFlagArgTests {

        @Test
        void extracts_arg_between_flag_and_next_slash() {
            String line = "deadline return book /by 2025-09-01 1800 /loc library";
            String arg = Parser.getFlagArg(line, "/by");
            assertEquals("2025-09-01 1800", arg);
        }

        @Test
        void returns_empty_string_if_flag_absent() {
            String line = "event party /from 2025-09-01 1400 /to 1600";
            assertEquals("", Parser.getFlagArg(line, "/by"));
        }

        @Test
        void trims_whitespace_around_value() {
            String line = "todo do stuff /tag    urgent   /note blah";
            assertEquals("urgent", Parser.getFlagArg(line, "/tag"));
        }

        @Test
        void empty_if_flag_is_last_token() {
            String line = "deadline return book /by";
            assertEquals("", Parser.getFlagArg(line, "/by"));
        }
    }

    @Nested
    @DisplayName("parseDateTime() with built-in formats")
    class ParseDateTimeTests {
        @Test
        void parses_yyyy_MM_dd_HHmm() {
            LocalDateTime dt = Parser.parseDateTime("2025-09-01 1800");
            assertEquals(LocalDateTime.of(2025, 9, 1, 18, 0), dt);
        }

        @Test
        void parses_HHmm_yyyy_MM_dd() {
            LocalDateTime dt = Parser.parseDateTime("1800 2025-09-01");
            assertEquals(LocalDateTime.of(2025, 9, 1, 18, 0), dt);
        }

        @Test
        void throws_for_unmatched_format() {
            assertThrows(DateTimeParseException.class,
                    () -> Parser.parseDateTime("01/09/2025 18:00"));
        }
    }

    @Nested
    @DisplayName("dateTimeToString()")
    class DateTimeToStringTests {
        @Test
        void formats_to_MMM_dd_yyyy_HHmmH() {
            LocalDateTime dt = LocalDateTime.of(2025, 9, 1, 18, 0);
            String expected = DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm").format(dt) + "H";
            assertEquals(expected, Parser.dateTimeToString(dt));
        }
    }

    @Nested
    @DisplayName("dTFormatterFromList()")
    class FormatterFromListTests {
        @Test
        void supports_multiple_optional_patterns() {
            var fmt = Parser.buildDateTimeFormatterFromPatterns(List.of("yyyy/MM/dd HHmm", "dd-MM-yyyy HHmm"));
            LocalDateTime a = LocalDateTime.parse("2025/09/01 1800", fmt);
            LocalDateTime b = LocalDateTime.parse("01-09-2025 1800", fmt);
            assertEquals(LocalDateTime.of(2025, 9, 1, 18, 0), a);
            assertEquals(LocalDateTime.of(2025, 9, 1, 18, 0), b);
        }
    }
}


