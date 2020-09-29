package butler.exception;

import butler.Butler;

/**
 * Thrown when error occurs due to user not inputting further information
 * upon using 'event' and 'deadline' commands
 */
public class EmptyDateException extends DukeException{

    @Override
    public void displayException(){
        System.out.println(Butler.FORMAT);
        System.out.println("Sorry! There's no date set! Please set a date using '/by' or '/at'!\n");
        System.out.println(Butler.FORMAT);
    }
}
