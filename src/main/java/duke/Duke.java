package duke;

import duke.exception.*;
import duke.messages.Messages;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.TextUI;
import duke.parser.Parser;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static String FORMAT = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    private static final String BYE_COMMAND = "bye";
    private Storage storage;
    private TaskList tasks;
    private TextUI textUI;

    public Duke() {
        textUI = new TextUI();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.readFile());
        } catch (DukeException | IOException e) {
            textUI.showLoadingError();
        }
    }

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

    public static void main(String[] args) {

        new Duke().run();

        }
}

