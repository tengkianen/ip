package duke.parser;

import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.exception.OutOfLimitException;
import duke.exception.WrongCommandException;
import duke.task.*;
import duke.ui.TextUI;

public class Parser {

    private static final String DONE_COMMAND = "done";
    private static final String BYE_COMMAND = "bye";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String LIST_COMMAND = "list";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";

    public Parser() {
    }

    public void parseInput(String[] userInput, TaskList taskList) throws EmptyDescriptionException, OutOfLimitException
            , EmptyDateException, WrongCommandException {
        TextUI textUI = new TextUI();
        String mainTask = userInput.length == 2 ? userInput[1] : null;

        switch (userInput[0]) {
        case BYE_COMMAND:  //EXITS PROGRAM
            textUI.printByeMessage();
            break;
        case LIST_COMMAND:  //LISTS ALL TASKS
            taskList.list();
            break;
        case DONE_COMMAND:  //COMPLETING A TASK
            taskList.setDoneStatus(mainTask, userInput);
            break;
        case TODO_COMMAND:  //ADDING A TODO
            taskList.addTodo(mainTask, userInput);
            break;
        case DEADLINE_COMMAND:  //ADDING A DEADLINE
            taskList.addDeadline(mainTask, userInput);
            break;
        case EVENT_COMMAND:  //ADDING AN EVENT
            taskList.addEvent(mainTask, userInput);
            break;
        case DELETE_COMMAND:
            taskList.deleteTask(mainTask, userInput);
            break;
        case FIND_COMMAND:
            textUI.printTaskList(taskList.findTasks(mainTask, userInput));
            break;
        default:
            throw new WrongCommandException();
        }
    }

    public String[] parseEvent(String mainTask) {
        return mainTask.trim().split("/at", 2);
    }

    public String[] parseDeadline(String mainTask) {
        return mainTask.trim().split("/by", 2);
    }

}
