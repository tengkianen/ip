package duke.exception;

import duke.Duke;

public class WrongCommandException extends DukeException{

    @Override
    public void displayException(){
        System.out.println(Duke.format);
        System.out.println("Sorry! This is an invalid command, Please try again!\n");
        System.out.println("These are the available commands: \n");
        System.out.println(" todo [todoName]\n");
        System.out.println(" deadline [deadlineName] /by [deadlineDate]\n");
        System.out.println(" event [eventName] /at [eventTime]\n");
        System.out.println(" list\n");
        System.out.println(" done [listNumber]\n");
        System.out.println(" bye\n");
        System.out.println(Duke.format);
    }
}
