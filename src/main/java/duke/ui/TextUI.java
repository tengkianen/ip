package duke.ui;

import duke.messages.Messages;
import duke.task.Task;

import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;


public class TextUI {

    private static final String FORMAT = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

    public TextUI() {
        this(System.in);
    }

    public TextUI(InputStream in) {
        Scanner in1 = new Scanner(in);
    }

    public void printHelloMessage() {
        System.out.println(FORMAT);
        System.out.println(Messages.MESSAGE_HELLO);
        System.out.println(FORMAT);
    }

    public void printByeMessage() {
        System.out.println(FORMAT);
        System.out.println(Messages.MESSAGE_BYE);
        System.out.println(FORMAT);
    }

    public void printAddMessage(ArrayList<Task> taskArrayList) {
        System.out.println(FORMAT);
        System.out.println("Got it. I've added this task: \n" + taskArrayList.get(taskArrayList.size() - 1));
        System.out.println("Now you have " + taskArrayList.size() + " task(s) in the list");
        System.out.println(FORMAT);
    }

    public void printDeleteMessage(ArrayList<Task> taskArrayList, int index) {
        System.out.println(FORMAT);
        System.out.println("Noted. I've removed this task: \n" + taskArrayList.get(index));
        int pseudoSize = taskArrayList.size() - 1;
        System.out.println("Now you have " + pseudoSize + " task(s) in the list");
        System.out.println(FORMAT);
    }

    public void printFinishTaskMessage(ArrayList<Task> taskArrayList, int doneItem) {
        taskArrayList.get(doneItem).isCompleted();
        System.out.println(FORMAT);
        System.out.println(Messages.MESSAGE_DONE_TASK);
        System.out.println(taskArrayList.get(doneItem));
        System.out.println(FORMAT);
    }

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

    public void showLoadingError() {
        System.out.println(FORMAT);
        System.out.println(Messages.MESSAGE_READ_ERROR);
        System.out.println(FORMAT);
    }

}
