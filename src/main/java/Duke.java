import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line;
        String[] lists = new String[100];
        Task[] taskArray = new Task[100];
        int count = 0;

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.println("Hey I'm Butler!\n");
        System.out.println("How can I help you Monsieur/Madame?\n");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        while(true) {

            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] stringArray = line.trim().split(" ");

            if (stringArray[0].equals("bye")) { //EXITS PROGRAM
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                System.out.println("Bye. Hope to see you again soon!\n");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                break;

            } else if (stringArray[0].equals("list")){ //LISTS ALL TASKS
                int numberedPoint = 1;
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                System.out.println("Here are the tasks in your list:\n");
                for(int i = 0; i < count; i++){
                    if (taskArray[i].description != null) {
                        System.out.println(numberedPoint + ". " + "[" + taskArray[i].getStatusIcon() + "] " + taskArray[i].description + "\n");
                        numberedPoint++;
                    }
                }
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

            } else if (stringArray[0].equals("done")){ //COMPLETING A TASK
                int doneItem = Integer.parseInt(stringArray[1]) - 1;
                taskArray[doneItem].isCompleted();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                System.out.println("Nice! I've marked this task as done:\n");
                System.out.println("[" + taskArray[doneItem].getStatusIcon() + "] " + taskArray[doneItem].description + "\n");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            }

            else { //ADDING A TASK
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                System.out.println("added: " + line);
                taskArray[count] = new Task (line);
                count++;
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            }
        }
    }

}

