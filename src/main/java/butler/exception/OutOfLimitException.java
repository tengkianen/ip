package butler.exception;

import butler.Butler;
import butler.messages.Messages;

/**
 * Thrown when error occurs due to user inputting an invalid value (out of limit)
 */
public class OutOfLimitException extends DukeException{

    @Override
    public void displayException() {
        System.out.println(Butler.FORMAT);
        System.out.println(Messages.MESSAGE_INDEX_ERROR);
        System.out.println(Butler.FORMAT);
    }
}
