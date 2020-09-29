package butler.exception;

import butler.Butler;

/**
 * Thrown when an error occurs due to user not inputting a description
 */
public class EmptyDescriptionException extends DukeException{

    protected String wrongAction;

    public EmptyDescriptionException(String wrongAction){
        super();
        this.wrongAction = wrongAction;
    }

    @Override
    public void displayException(){
        System.out.println(Butler.FORMAT);
        System.out.println("The description of a " + "'" + wrongAction + "'" + " cannot be empty! Please try again!\n");
        System.out.println(Butler.FORMAT);
    }
}
