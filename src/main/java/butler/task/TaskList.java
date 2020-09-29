package butler.task;

import butler.exception.DukeException;
import butler.exception.EmptyDateException;
import butler.exception.EmptyDescriptionException;
import butler.exception.OutOfLimitException;
import butler.messages.Messages;
import butler.parser.Parser;
import butler.storage.Storage;
import butler.ui.TextUI;

import java.io.IOException;
import java.util.ArrayList;

import static butler.Butler.*;

/**
 * Contains methods used for handling the ArrayList of Tasks
 */
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

    /**
     * Prints taskArrayList using TextUI.printTaskList() method
     */
    public void list() {
        TextUI textUI = new TextUI();
        textUI.printTaskList(taskArrayList);
    }

    /**
     * Saves taskArrayList into tasks.txt using Storage.writeFile() method
     */
    public void save() {
        Storage storage = new Storage();
        try {
            storage.writeFile(taskArrayList);
        } catch (IOException | DukeException e) {
            System.out.println(Messages.MESSAGE_SAVE_ERROR);
            System.out.println(FORMAT);
        }
    }

    /**
     * Adds a Todo into the taskArrayList
     *
     * @param mainTask Description of the task to be added
     * @param userInput String Array of the task type and description
     * @throws EmptyDescriptionException If error occurs due to user not inputting a description
     */
    public void addTodo (String mainTask, String[] userInput) throws EmptyDescriptionException {
        TextUI textUI = new TextUI();
        if (mainTask == null){
            throw new EmptyDescriptionException(userInput[0]);
        }
        taskArrayList.add(new ToDo (userInput[1]));
        textUI.printAddMessage(taskArrayList);
        save();
    }

    /**
     * Adds a Deadline into the taskArrayList
     *
     * @param mainTask Description of the task to be added
     * @param userInput String Array of the task type, description and date/time
     * @throws EmptyDescriptionException If error occurs due to user not inputting a description
     * @throws EmptyDateException If error occurs due to user not inputting a date/time
     */
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

    /**
     * Adds an Event into the taskArrayList
     *
     * @param mainTask Description of the task to be added
     * @param userInput String Array of the task type, description and date/time
     * @throws EmptyDescriptionException If error occurs due to user not inputting a description
     * @throws EmptyDateException If error occurs due to user not inputting a date/time
     */
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

    /**
     * Setting a task to be completed
     *
     * @param mainTask Index of the task to be edited
     * @param userInput String Array of the 'done' command and the index to be edited
     * @throws EmptyDescriptionException If error occurs due to user not inputting an index
     * @throws OutOfLimitException If error occurs due to user inputting an invalid index
     */
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

    /**
     * Deletes a Task from the taskArrayList
     *
     * @param mainTask Index of the task to be deleted
     * @param userInput String Array of the 'delete' command and the index to be deleted
     * @throws EmptyDescriptionException If error occurs due to user not inputting an index
     * @throws OutOfLimitException If error occurs due to user inputting an invalid index
     */
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
