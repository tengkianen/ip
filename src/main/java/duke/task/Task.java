package duke.task;

public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void isCompleted(){
        isDone = true;
    }

    public String getTypeIcon(){
        return "[ ]";
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

}
