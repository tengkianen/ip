package butler.messages;

/**
 * Class containing message strings Duke uses
 */
public class Messages {
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!\n";
    public static final String MESSAGE_LIST_TASKS = "Here are the tasks in your list:\n";
    public static final String MESSAGE_DONE_TASK = "Nice! I've marked this task as done:\n";
    public static final String MESSAGE_SAVE_ERROR = "There was a problem saving your task! Please try again!";
    public static final String MESSAGE_HELLO = "Hey I'm Butler!\nHow can I help you Monsieur/Madame?\n";
    public static final String MESSAGE_READ_ERROR = "There was an error reading saved data!";
    public static final String MESSAGE_COMMAND_ERROR = "Sorry! This is an invalid command, Please try again!\n" +
            "These are the available commands: \n- todo [todoName]\n- deadline [deadlineName] /by [deadlineDate]\n" +
            "- event [eventName] /at [eventTime]\n- list\n- done [listNumber]\n- bye\n- find [keyword]";
    public static final String MESSAGE_INDEX_ERROR = "Index out of range!";
    public static final String MESSAGE_HELP_COMMAND = "These are the available commands: \n- todo [todoName]\n" +
            "- deadline [deadlineName] /by [deadlineDate]\n" +
            "- event [eventName] /at [eventTime]\n- list\n- done [listNumber]\n- bye\n- find [keyword]";
}
