package butler.task;

/**
 * Subclass of Task
 */
public class ToDo extends Task{

    /**
     * Constructor for a Todo that is, by default, not done
     *
     * @param description String containing description of the Todo
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for a Todo with a specified done status
     *
     * @param description String containing description of the Todo
     * @param isDone Boolean containing done status of the Todo
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns type icon of Todo
     *
     * @return String containing type icon of Todo
     */
    @Override
    public String getTypeIcon(){
        return "[T]";
    }

    /**
     * Returns String output of Todo
     *
     * @return String output of Todo
     */
    @Override
    public String toString() {
        return getTypeIcon() + super.toString();
    }
}
