package ;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    static Task[] taskArray = new Task[100];
    static String format = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    static int count = 0;

    public static int taskAdder(Task[] taskArray, int count){
        System.out.println("Got it. I've added this task: \n" + taskArray[count]);
        count++;
        System.out.println("Now you have " + count + " tasks in the list");
        return count;
    }

    public static void parseInput(String[] userInput) throws EmptyDescriptionException, WrongCommandException,
            EmptyDateException{
        String mainTask = userInput.length == 2 ? userInput[1] : null;
        if (userInput[0].equals("bye")) { //EXITS PROGRAM
            System.out.println(format);
            System.out.println("Bye. Hope to see you again soon!\n");
            System.out.println(format);
        } else if (userInput[0].equals("list")){ //LISTS ALL TASKS
            int numberedPoint = 1;
            System.out.println(format);
            System.out.println("Here are the tasks in your list:\n");
            for(int i = 0; i < count; i++){
                System.out.println(numberedPoint + ". " + taskArray[i]);
                numberedPoint++;
            }
            System.out.println(format);

        } else if (userInput[0].equals("done")){ //COMPLETING A TASK
            int doneItem = Integer.parseInt(userInput[1]) - 1;
            taskArray[doneItem].isCompleted();
            System.out.println(format);
            System.out.println("Nice! I've marked this task as done:\n");
            System.out.println(taskArray[doneItem]);
            System.out.println(format);
        }

        else if (userInput[0].equals("todo")){ //ADDING A TODO
            if (mainTask == null){
                throw new EmptyDescriptionException(userInput[0]);
            }
            System.out.println(format);
            taskArray[count] = new ToDo (userInput[1]);
            count = taskAdder(taskArray, count);
            System.out.println(format);
        }

        else if (userInput[0].equals("deadline")){ //ADDING A DEADLINE
            if (mainTask == null){
                throw new EmptyDescriptionException(userInput[0]);
            }
            if (!mainTask.contains("/by")){
                throw new EmptyDateException();
            }
            System.out.println(format);
            String[] deadlineArray = mainTask.trim().split("/by", 2);
            taskArray[count] = new Deadline (deadlineArray[0], deadlineArray[1]);
            count = taskAdder(taskArray, count);
            System.out.println(format);
        }

        else if (userInput[0].equals("event")){ //ADDING AN EVENT
            if (mainTask == null){
                throw new EmptyDescriptionException(userInput[0]);
            }
            if (!mainTask.contains("/at")){
                throw new EmptyDateException();
            }
            System.out.println(format);
            String[] eventArray = mainTask.trim().split("/at", 2);
            taskArray[count] = new Event (eventArray[0], eventArray[1]);
            count = taskAdder(taskArray, count);
            System.out.println(format);
        } else {
            throw new WrongCommandException();
        }
    }

    public static void main(String[] args) {

        System.out.println(format);
        System.out.println("Hey I'm Butler!\n");
        System.out.println("How can I help you Monsieur/Madame?\n");
        System.out.println(format);
        String[] stringArray;

        do {

            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            stringArray = line.trim().split(" ", 2);
            try {
                parseInput(stringArray);
            } catch (EmptyDescriptionException e){
                e.displayException();
            } catch (WrongCommandException e){
                e.displayException();
            } catch (EmptyDateException e){
                e.displayException();
            }
            
        } while (!stringArray[0].equals("bye"));

    }
}

