public class WrongCommandException extends DukeException{

    @Override
    public void displayException(){
        System.out.println("Sorry! This is an invalid command, Please try again!\n");
    }
}
