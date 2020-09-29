package butler.exception;

import butler.Butler;
import butler.messages.Messages;

/**
 * Thrown when error occurs due to an invalid command entered by user
 */
public class WrongCommandException extends DukeException{

    @Override
    public void displayException(){
        System.out.println(Butler.FORMAT);
        System.out.println(Messages.MESSAGE_COMMAND_ERROR);
        System.out.println(Butler.FORMAT);
    }
}
