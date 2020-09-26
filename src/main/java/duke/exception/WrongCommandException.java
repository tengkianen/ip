package duke.exception;

import duke.Duke;
import duke.messages.Messages;

public class WrongCommandException extends DukeException{

    @Override
    public void displayException(){
        System.out.println(Duke.FORMAT);
        System.out.println(Messages.MESSAGE_COMMAND_ERROR);
        System.out.println(Duke.FORMAT);
    }
}
