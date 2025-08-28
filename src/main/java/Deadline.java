import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.dateTimeToString(by) + ")";
    }
}
