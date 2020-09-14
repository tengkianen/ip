package duke.task;

public class Deadline extends Task{

    public String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone){
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getTypeIcon(){
        return "[D]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.toString() + " (by: " + by + ")";
    }
}
