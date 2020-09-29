package butler.task;

/**
 * Subclass of Task
 */
public class Deadline extends Task{

    public String by;

    /**
     * Constructor for a Deadline that is, by default, not done
     *
     * @param description String containing description of the Deadline
     * @param by String containing the time/date of the Deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for a Deadline with a specified done status
     *
     * @param description String containing description of the Deadline
     * @param by String containing the time/date of the Deadline
     * @param isDone Boolean containing done status of the Deadline
     */
    public Deadline(String description, String by, boolean isDone){
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the type icon of the Deadline
     *
     * @return String containing type icon of the Deadline
     */
    @Override
    public String getTypeIcon(){
        return "[D]";
    }

    /**
     * Returns the String output of a Deadline
     *
     * @return String output of Deadline
     */
    @Override
    public String toString() {
        return getTypeIcon() + super.toString() + " (by: " + by + ")";
    }
}
