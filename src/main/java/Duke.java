import jdk.swing.interop.SwingInterOpUtils;

import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Task[] taskArray = new Task[100];
        int count = 0;
        String format = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

        System.out.println(format);
        System.out.println("Hey I'm Butler!\n");
        System.out.println("How can I help you Monsieur/Madame?\n");
        System.out.println(format);

        while(true) {

            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] stringArray = line.trim().split(" ", 2);
            String mainTask = stringArray.length == 2? stringArray[1] : null;
            String[] deadlineArray = mainTask != null? mainTask.trim().split("/by", 2): null;
            String[] eventdateArray = mainTask != null? mainTask.trim().split("/at", 2): null;

            if (stringArray[0].equals("bye")) { //EXITS PROGRAM
                System.out.println(format);
                System.out.println("Bye. Hope to see you again soon!\n");
                System.out.println(format);
                break;

            } else if (stringArray[0].equals("list")){ //LISTS ALL TASKS
                int numberedPoint = 1;
                System.out.println(format);
                System.out.println("Here are the tasks in your list:\n");
                for(int i = 0; i < count; i++){
                    if (taskArray[i].description != null) {
                        System.out.println(numberedPoint + ". " + taskArray[i]);
                        numberedPoint++;
                    }
                }
                System.out.println(format);

            } else if (stringArray[0].equals("done")){ //COMPLETING A TASK
                int doneItem = Integer.parseInt(stringArray[1]) - 1;
                taskArray[doneItem].isCompleted();
                System.out.println(format);
                System.out.println("Nice! I've marked this task as done:\n");
                System.out.println("[" + taskArray[doneItem].getStatusIcon() + "] " + taskArray[doneItem].description + "\n");
                System.out.println(format);
            }

            else if (stringArray[0].equals("todo")){ //ADDING A TODO
                System.out.println(format);
                taskArray[count] = new ToDos (stringArray[1]);
                System.out.println("Got it. I've added this task: \n" + taskArray[count]);
                count++;
                System.out.println("Now you have " + count + " tasks in the list");
                System.out.println(format);
            }

            else if (stringArray[0].equals("deadline")){ //ADDING A DEADLINE
                System.out.println(format);
                taskArray[count] = new Deadline (deadlineArray[0], deadlineArray[1]);
                System.out.println("Got it. I've added this task: \n" + taskArray[count]);
                count++;
                System.out.println("Now you have " + count + " tasks in the list");
                System.out.println(format);
            }

            else if (stringArray[0].equals("event")){ //ADDING AN EVENT
                System.out.println(format);
                taskArray[count] = new Event (eventdateArray[0], eventdateArray[1]);
                System.out.println("Got it. I've added this task: \n" + taskArray[count]);
                count++;
                System.out.println("Now you have " + count + " tasks in the list");
                System.out.println(format);
            }
        }
    }

}

