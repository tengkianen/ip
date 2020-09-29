package butler.task;

/**
 * Subclass of Task
 */
public class Event extends Task {

    public String at;

    /**
     * Constructor for an Event that is, by default, not done
     *
     * @param description String containing description of the Event
     * @param at String containing the time/date/place of the Event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for an Event with specified done status
     *
     * @param description String containing description of the Event
     * @param at String containing the time/date/place of the Event
     * @param isDone Boolean containing done status of the Event
     */
    public Event(String description, String at, boolean isDone){
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns the type icon of the Event
     *
     * @return String containing the type icon of the Event
     */
    @Override
    public String getTypeIcon(){
        return "[E]";
    }

    /**
     * Returns the String output of an Event
     *
     * @return the String output of Event
     */
    @Override
    public String toString() {
        return getTypeIcon() + super.toString() + " (at: " + at + ")";
    }

}
