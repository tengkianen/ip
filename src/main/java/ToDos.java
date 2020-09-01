public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon(){
        return "[T]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.toString();
    }
}
