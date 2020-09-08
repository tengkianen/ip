package duke.exception;

import duke.Duke;

public class EmptyDateException extends DukeException{

    @Override
    public void displayException(){
        System.out.println(Duke.format);
        System.out.println("Sorry! There's no date set! Please set a date using '/by' or '/at'!\n");
        System.out.println(Duke.format);
    }
}
