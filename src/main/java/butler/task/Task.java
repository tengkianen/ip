package butler.task;

/**
 * Superclass of Todo, Deadline and Event
 */
public class Task {
    public String description;
    public boolean isDone;

    /**
     * Constructor for a Task that is, by default, not done
     *
     * @param description String containing description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for a Task that is done
     *
     * @param description String containing description of the Task
     * @param isDone Boolean to suggest if Task is done
     */
    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a String containing the status icon of the Task
     *
     * @return String containing status icon of the Task
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Sets the done status of the Task to done
     *
     */
    public void isCompleted(){
        isDone = true;
    }

    /**
     * Returns a String containing the type icon of the Task
     *
     * @return String containing type icon of the Task
     */
    public String getTypeIcon(){
        return "[ ]";
    }

    /**
     * Returns a String output of a Task
     *
     * @return String output of a Task
     */
    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

}
