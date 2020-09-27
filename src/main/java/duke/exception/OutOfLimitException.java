package duke.exception;

import duke.Duke;
import duke.messages.Messages;

public class OutOfLimitException extends DukeException{

    @Override
    public void displayException() {
        System.out.println(Duke.FORMAT);
        System.out.println(Messages.MESSAGE_INDEX_ERROR);
        System.out.println(Duke.FORMAT);
    }
}
