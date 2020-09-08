package duke.exception;

public class EmptyDateException extends DukeException{

    @Override
    public void displayException(){
        System.out.println("Sorry! There's no date set! Please set a date using '/by' or '/at'!\n");
    }
}
