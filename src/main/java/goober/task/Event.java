package goober.task;

import goober.Parser;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + Parser.dateTimeToString(from)
                + " to: " + Parser.dateTimeToString(to) + ")";
    }
}
