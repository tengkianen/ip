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
     * Finds tasks with description that matches with user input
     *
     * @param userInput String Array of the task type and description
     * @return an ArrayList of Tasks that contain the keyword
     */
    public ArrayList<Task> findTasks(String[] userInput) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : taskArrayList) {
            if (task.getDescription().contains(userInput[1])) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
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
     * @param userInput String Array of the task type and description
     */
    public void addTodo(String[] userInput) {
        TextUI textUI = new TextUI();
        taskArrayList.add(new ToDo (userInput[1]));
        textUI.printAddMessage(taskArrayList);
        save();
    }

    /**
     * Adds a Deadline into the taskArrayList
     *
     * @param userInput String Array of the task type, description and date/time
     * @throws EmptyDateException If error occurs due to user not inputting a date/time
     */
    public void addDeadline(String[] userInput)
            throws EmptyDateException {
        TextUI textUI = new TextUI();
        Parser parser = new Parser();
        if (!userInput[1].contains("/by")){
            throw new EmptyDateException();
        }
        String[] deadlines = parser.parseDeadline(userInput[1]);
        taskArrayList.add(new Deadline(deadlines[0],deadlines[1]));
        textUI.printAddMessage(taskArrayList);
        save();
    }

    /**
     * Adds an Event into the taskArrayList
     *
     * @param userInput String Array of the task type, description and date/time
     * @throws EmptyDateException If error occurs due to user not inputting a date/time
     */
    public void addEvent(String[] userInput)
            throws EmptyDateException {
        TextUI textUI = new TextUI();
        Parser parser = new Parser();
        if (!userInput[1].contains("/at")){
            throw new EmptyDateException();
        }
        String[] eventArray = parser.parseEvent(userInput[1]);
        taskArrayList.add(new Event (eventArray[0], eventArray[1]));
        textUI.printAddMessage(taskArrayList);
        save();
    }

    /**
     * Setting a task to be completed
     *
     * @param userInput String Array of the 'done' command and the index to be edited
     * @throws OutOfLimitException If error occurs due to user inputting an invalid index
     */
    public void setDoneStatus(String[] userInput)
            throws OutOfLimitException {
        TextUI textUI = new TextUI();
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
     * @param userInput String Array of the 'delete' command and the index to be deleted
     * @throws OutOfLimitException If error occurs due to user inputting an invalid index
     */
    public void deleteTask(String[] userInput)
            throws OutOfLimitException {
        TextUI textUI = new TextUI();
        int doneItem = Integer.parseInt(userInput[1]) - 1;
        if (doneItem >= taskArrayList.size()) {
            throw new OutOfLimitException();
        }
        textUI.printDeleteMessage(taskArrayList, doneItem);
        taskArrayList.remove(doneItem);
        save();
    }
}
