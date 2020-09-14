package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.EmptyDateException;
import duke.exception.WrongCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Duke {

    static ArrayList <Task> taskArrayList = new ArrayList<Task>();
    public static String format = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    static int count = 0;

    public static int taskAdder(ArrayList<Task> taskArrayList, int count){
        System.out.println("Got it. I've added this task: \n" + taskArrayList.get(count));
        count++;
        System.out.println("Now you have " + count + " tasks in the list");
        return count;
    }

    public static int taskRemover(ArrayList<Task> taskArrayList, int index, int count){
        System.out.println("Noted. I've removed this task: \n" + taskArrayList.get(index));
        count--;
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
            for(Task task : taskArrayList){
                System.out.println(numberedPoint + ". " + task);
                numberedPoint++;
            }
            System.out.println(format);

        } else if (userInput[0].equals("done")){ //COMPLETING A TASK
            int doneItem = Integer.parseInt(userInput[1]) - 1;
            taskArrayList.get(doneItem).isCompleted();
            System.out.println(format);
            System.out.println("Nice! I've marked this task as done:\n");
            System.out.println(taskArrayList.get(doneItem));
            System.out.println(format);
            try {
                writeFile();
            } catch (IOException | DukeException e) {
                System.out.println("There was a problem saving your task! Please try again!");
            }
        }

        else if (userInput[0].equals("todo")){ //ADDING A TODO
            if (mainTask == null){
                throw new EmptyDescriptionException(userInput[0]);
            }
            System.out.println(format);
            taskArrayList.add(new ToDo (userInput[1]));
            count = taskAdder(taskArrayList, count);
            System.out.println(format);
            try {
                writeFile();
            } catch (IOException | DukeException e) {
                System.out.println("There was a problem saving your task! Please try again!");
                System.out.println(format);
            }
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
            taskArrayList.add(new Deadline(deadlineArray[0],deadlineArray[1]));
            count = taskAdder(taskArrayList, count);
            System.out.println(format);
            try {
                writeFile();
            } catch (IOException | DukeException e) {
                System.out.println("There was a problem saving your task! Please try again!");
                System.out.println(format);
            }
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
            taskArrayList.add(new Event (eventArray[0], eventArray[1]));
            count = taskAdder(taskArrayList, count);
            System.out.println(format);
        }

        else if (userInput[0].equals("delete")) {
            if (mainTask == null) {
                throw new EmptyDescriptionException(userInput[0]);
            }
            System.out.println(format);
            count = taskRemover(taskArrayList, Integer.parseInt(userInput[1]) - 1, count);
            taskArrayList.remove(Integer.parseInt(userInput[1]) - 1);
            System.out.println(format);
            try {
                writeFile();
            } catch (IOException | DukeException e) {
                System.out.println("There was a problem saving your task! Please try again!");
                System.out.println(format);
            }
        }

        else {
            throw new WrongCommandException();
        }
    }

    public static void readFile() throws DukeException, IOException {
        String dataPath = new File("saved_data").getAbsolutePath();

        if(Files.exists(Path.of(dataPath))) {
            File f = new File (dataPath + "/tasks.txt");
            Scanner sc = new Scanner(f);

            while(sc.hasNext()) {
                String partialString = sc.nextLine();
                String[] data = partialString.trim().split("\\|", 3);
                String description;
                boolean isDone;

                switch(data[0]) {
                case "[T]":
                    isDone = Boolean.parseBoolean(data[1]);
                    description = data[2];
                    taskArrayList.add(new ToDo(description, isDone));
                    break;
                case "[D]":
                    isDone = Boolean.parseBoolean(data[1]);
                    String[] deadlineArray = data[2].trim().split("\\|", 2);
                    description = deadlineArray[0];
                    String by = deadlineArray[1];
                    taskArrayList.add(new Deadline(description, by, isDone));
                    break;
                case "[E]":
                    isDone = Boolean.parseBoolean(data[1]);
                    String[] eventArray = data[2].trim().split("\\|", 2);
                    description = eventArray[0];
                    String at = eventArray[1];
                    taskArrayList.add(new Event(description, at, isDone));
                    break;
                }

            }
        } else {
            File f = new File (dataPath);
            boolean dirExists = f.mkdir();
            if (dirExists) {
                f = new File (dataPath + "/tasks.txt");
                f.createNewFile();
            } else {
                throw new DukeException();
            }
        }
    }

    public static void writeFile() throws IOException, DukeException {
        String dataPath = new File("saved_data/tasks.txt").getAbsolutePath();
        StringBuilder fullString = new StringBuilder();

        for(Task task : taskArrayList) {
            if (task instanceof ToDo) {
                ToDo todo = (ToDo) task;
                String todoString = todo.getTypeIcon() + "|" + todo.isDone + "|"
                        + todo.description + System.lineSeparator();
                fullString.append(todoString);
            } else if (task instanceof Event) {
                Event event = (Event) task;
                String eventString = event.getTypeIcon() + "|" + event.isDone + "|"
                        + event.description + "|" + event.at + System.lineSeparator();
                fullString.append(eventString);
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                String deadlineString = deadline.getTypeIcon() + "|" + deadline.isDone + "|"
                        + deadline.description + "|" + deadline.by + System.lineSeparator();
                fullString.append(deadlineString);
            } else {
                throw new DukeException();
            }
        }

        FileWriter fw = new FileWriter(dataPath);
        fw.write(String.valueOf(fullString));
        fw.close();
    }

    public static void main(String[] args) {

        System.out.println(format);
        System.out.println("Hey I'm Butler!\n");
        System.out.println("How can I help you Monsieur/Madame?\n");
        System.out.println(format);
        String[] stringArray;

        try {
            readFile();
        } catch (IOException | DukeException e) {
            System.out.println("There was an error reading saved data!");
        }

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

