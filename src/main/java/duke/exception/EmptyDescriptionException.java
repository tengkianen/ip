package duke.exception;

import duke.Duke;

public class EmptyDescriptionException extends DukeException{

    protected String wrongAction;

    public EmptyDescriptionException(String wrongAction){
        super();
        this.wrongAction = wrongAction;
    }

    @Override
    public void displayException(){
        System.out.println(Duke.FORMAT);
        System.out.println("The description of a " + "'" + wrongAction + "'" + " cannot be empty! Please try again!\n");
        System.out.println(Duke.FORMAT);
    }
}
