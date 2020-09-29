package butler;

import butler.exception.*;
import butler.messages.Messages;
import butler.storage.Storage;
import butler.task.*;
import butler.ui.TextUI;
import butler.parser.Parser;
import java.io.IOException;
import java.util.Scanner;

public class Butler {

    public static String FORMAT = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    private static final String BYE_COMMAND = "bye";
    private Storage storage;
    private TaskList tasks;
    private TextUI textUI;

    /**
     * Constructor to initialize a new Duke class
     */
    public Butler() {
        textUI = new TextUI();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.readFile());
        } catch (DukeException | IOException e) {
            textUI.showLoadingError();
        }
    }

    /**
     * Method to start running the Duke class
     */
    public void run() {

        TextUI textUI = new TextUI();
        Storage storage = new Storage();
        Parser parser = new Parser();
        textUI.printHelloMessage();
        String[] stringArray;

        try {
            storage.readFile();
        } catch (IOException | DukeException e) {
            System.out.println(Messages.MESSAGE_READ_ERROR);
        }

        do {

            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            stringArray = line.trim().split(" ", 2);
            try {
                parser.parseInput(stringArray, tasks);
            } catch (EmptyDescriptionException e) {
                e.displayException();
            } catch (WrongCommandException e) {
                e.displayException();
            } catch (EmptyDateException e) {
                e.displayException();
            } catch (OutOfLimitException e) {
                e.displayException();
            }

        } while (!stringArray[0].equals(BYE_COMMAND));
    }

    /**
     * Main driver function for Butler
     *
     * @param args
     */
    public static void main(String[] args) {

        new Butler().run();

        }
}

