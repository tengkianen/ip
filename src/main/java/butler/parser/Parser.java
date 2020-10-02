package butler.parser;

import butler.exception.EmptyDateException;
import butler.exception.EmptyDescriptionException;
import butler.exception.OutOfLimitException;
import butler.exception.WrongCommandException;
import butler.task.TaskList;
import butler.ui.TextUI;


public class Parser {

    private static final String DONE_COMMAND = "done";
    private static final String BYE_COMMAND = "bye";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String LIST_COMMAND = "list";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String HELP_COMMAND = "help";

    public Parser() {
    }

    /**
     * Handles the input entered by user
     *
     * @param userInput Input entered by the user
     * @param taskList ArrayList of Tasks
     * @throws EmptyDescriptionException If error occurs due to user not inputting a description
     * @throws OutOfLimitException If error occurs due to user inputting invalid values
     * @throws EmptyDateException If error occurs due to user not inputting date/time
     * @throws WrongCommandException If error occurs due to user inputting an invalid command
     */
    public void parseInput(String[] userInput, TaskList taskList) throws EmptyDescriptionException, OutOfLimitException
            , EmptyDateException, WrongCommandException {
        TextUI textUI = new TextUI();


        switch (userInput[0]) {
        case BYE_COMMAND:
            textUI.printByeMessage();
            break;
        case LIST_COMMAND:
            taskList.list();
            break;
        case DONE_COMMAND:
            if (userInput.length < 2) {
                throw new EmptyDescriptionException(userInput[0]);
            }
            taskList.setDoneStatus(userInput);
            break;
        case TODO_COMMAND:
            if (userInput.length < 2) {
                throw new EmptyDescriptionException(userInput[0]);
            }
            taskList.addTodo(userInput);
            break;
        case DEADLINE_COMMAND:
            if (userInput.length < 2) {
                throw new EmptyDescriptionException(userInput[0]);
            }
            taskList.addDeadline(userInput);
            break;
        case EVENT_COMMAND:
            if (userInput.length < 2) {
                throw new EmptyDescriptionException(userInput[0]);
            }
            taskList.addEvent(userInput);
            break;
        case DELETE_COMMAND:
            if (userInput.length < 2) {
                throw new EmptyDescriptionException(userInput[0]);
            }
            taskList.deleteTask(userInput);
            break;
        case FIND_COMMAND:
            if (userInput.length < 2) {
                throw new EmptyDescriptionException(userInput[0]);
            }
            textUI.printTaskList(taskList.findTasks(userInput));
            break;
        case HELP_COMMAND:
            textUI.printHelpMessage();
            break;
        default:
            throw new WrongCommandException();
        }
    }

    /**
     * Splits the String mainTask to acquire the date and time of the event
     *
     * @param mainTask String to be split
     * @return String Array containing split String
     */
    public String[] parseEvent(String mainTask) {
        return mainTask.trim().split("/at", 2);
    }

    /**
     * Splits the String mainTask to acquire the date and time of the deadline
     *
     * @param mainTask String to be split
     * @return String Array containing split String
     */
    public String[] parseDeadline(String mainTask) {
        return mainTask.trim().split("/by", 2);
    }

}
