package duke.task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTypeIcon(){
        return "[E]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.toString() + " (at: " + at + ")";
    }

}
