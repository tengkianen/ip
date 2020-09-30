package butler.ui;

import butler.messages.Messages;
import butler.task.Task;

import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;


public class TextUI {

    private static final String FORMAT = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

    /**
     * Constructor for TextUI
     */
    public TextUI() {
        this(System.in);
    }

    /**
     * Constructor for TextUI given an InputStream
     *
     * @param in InputStream
     */
    public TextUI(InputStream in) {
        Scanner in1 = new Scanner(in);
    }

    /**
     * Displays a message upon entering the program
     */
    public void printHelloMessage() {
        System.out.println(FORMAT);
        System.out.println(Messages.MESSAGE_HELLO);
        System.out.println(FORMAT);
    }

    /**
     * Displays a message upon exiting the program
     */
    public void printByeMessage() {
        System.out.println(FORMAT);
        System.out.println(Messages.MESSAGE_BYE);
        System.out.println(FORMAT);
    }

    /**
     * Displays a message upon adding a Task into the taskArrayList
     *
     * @param taskArrayList ArrayList containing Tasks
     */
    public void printAddMessage(ArrayList<Task> taskArrayList) {
        System.out.println(FORMAT);
        System.out.println("Got it. I've added this task: \n" + taskArrayList.get(taskArrayList.size() - 1));
        System.out.println("Now you have " + taskArrayList.size() + " task(s) in the list");
        System.out.println(FORMAT);
    }

    /**
     * Displays a message upon deleting a Task
     *
     * @param taskArrayList ArrayList containing Tasks
     * @param index Integer containing the Task's index to be deleted
     */
    public void printDeleteMessage(ArrayList<Task> taskArrayList, int index) {
        System.out.println(FORMAT);
        System.out.println("Noted. I've removed this task: \n" + taskArrayList.get(index));
        int pseudoSize = taskArrayList.size() - 1;
        System.out.println("Now you have " + pseudoSize + " task(s) in the list");
        System.out.println(FORMAT);
    }

    /**
     * Displays message upon setting a Task as done
     *
     * @param taskArrayList ArrayList containing Tasks
     * @param doneItem Integer containing the Task's index to be set as done
     */
    public void printFinishTaskMessage(ArrayList<Task> taskArrayList, int doneItem) {
        taskArrayList.get(doneItem).isCompleted();
        System.out.println(FORMAT);
        System.out.println(Messages.MESSAGE_DONE_TASK);
        System.out.println(taskArrayList.get(doneItem));
        System.out.println(FORMAT);
    }

    /**
     * Displays all possible commands that can be used
     */
    public void printHelpMessage() {
        System.out.println(FORMAT);
        System.out.println(Messages.MESSAGE_HELP_COMMAND);
        System.out.println(FORMAT);
    }

    /**
     * Displays an ArrayList of Tasks
     *
     * @param taskArrayList ArrayList containing Tasks
     */
    public void printTaskList(ArrayList<Task> taskArrayList) {
        int numberedPoint = 1;
        System.out.println(FORMAT);
        System.out.println(Messages.MESSAGE_LIST_TASKS);
        for(Task task : taskArrayList){
            System.out.println(numberedPoint + ". " + task);
            numberedPoint++;
        }
        System.out.println(FORMAT);
    }

    /**
     * Displays a message when there's an error loading the file
     */
    public void showLoadingError() {
        System.out.println(FORMAT);
        System.out.println(Messages.MESSAGE_READ_ERROR);
        System.out.println(FORMAT);
    }

}
