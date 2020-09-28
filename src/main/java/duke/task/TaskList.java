package duke.task;

import duke.exception.DukeException;
import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.exception.OutOfLimitException;
import duke.messages.Messages;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.TextUI;

import java.io.IOException;
import java.util.ArrayList;

import static duke.Duke.*;

public class TaskList {

    public static ArrayList<Task> taskArrayList;

    public TaskList() {
        taskArrayList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> inputTaskArrayList) {
        taskArrayList = inputTaskArrayList;
    }

    public static ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }

    public void list() {
        TextUI textUI = new TextUI();
        textUI.printTaskList(taskArrayList);
    }

    public void save() {
        Storage storage = new Storage();
        try {
            storage.writeFile(taskArrayList);
        } catch (IOException | DukeException e) {
            System.out.println(Messages.MESSAGE_SAVE_ERROR);
            System.out.println(FORMAT);
        }
    }

    public void addTodo (String mainTask, String[] userInput) throws EmptyDescriptionException {
        TextUI textUI = new TextUI();
        if (mainTask == null){
            throw new EmptyDescriptionException(userInput[0]);
        }
        taskArrayList.add(new ToDo (userInput[1]));
        textUI.printAddMessage(taskArrayList);
        save();
    }

    public void addDeadline (String mainTask, String[] userInput)
            throws EmptyDescriptionException, EmptyDateException {
        TextUI textUI = new TextUI();
        Parser parser = new Parser();
        if (mainTask == null){
            throw new EmptyDescriptionException(userInput[0]);
        }
        if (!mainTask.contains("/by")){
            throw new EmptyDateException();
        }
        String[] deadlines = parser.parseDeadline(mainTask);
        taskArrayList.add(new Deadline(deadlines[0],deadlines[1]));
        textUI.printAddMessage(taskArrayList);
        save();
    }

    public void addEvent (String mainTask, String[] userInput)
            throws EmptyDescriptionException, EmptyDateException {
        TextUI textUI = new TextUI();
        Parser parser = new Parser();
        if (mainTask == null){
            throw new EmptyDescriptionException(userInput[0]);
        }
        if (!mainTask.contains("/at")){
            throw new EmptyDateException();
        }
        String[] eventArray = parser.parseEvent(mainTask);
        taskArrayList.add(new Event (eventArray[0], eventArray[1]));
        textUI.printAddMessage(taskArrayList);
        save();
    }

    public void setDoneStatus (String mainTask, String[] userInput)
            throws EmptyDescriptionException, OutOfLimitException {
        TextUI textUI = new TextUI();
        if (mainTask == null){
            throw new EmptyDescriptionException(userInput[0]);
        }
        int doneItem = Integer.parseInt(userInput[1]) - 1;
        if (doneItem >= taskArrayList.size()) {
            throw new OutOfLimitException();
        }
        textUI.printFinishTaskMessage(taskArrayList, doneItem);
        save();
    }

    public void deleteTask (String mainTask, String[] userInput)
            throws EmptyDescriptionException, OutOfLimitException {
        TextUI textUI = new TextUI();
        if (mainTask == null) {
            throw new EmptyDescriptionException(userInput[0]);
        }
        int doneItem = Integer.parseInt(userInput[1]) - 1;
        if (doneItem >= taskArrayList.size()) {
            throw new OutOfLimitException();
        }
        textUI.printDeleteMessage(taskArrayList, doneItem);
        taskArrayList.remove(doneItem);
        save();
    }
}
