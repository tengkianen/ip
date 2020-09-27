package duke;

import duke.exception.*;
import duke.messages.Messages;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList <Task> taskArrayList = new ArrayList<Task>();
    public static String FORMAT = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    private static final String DONE_COMMAND = "done";
    private static final String BYE_COMMAND = "bye";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String LIST_COMMAND = "list";

    //Adds a task to the arraylist
    public static void taskAdder(ArrayList<Task> taskArrayList) {
        System.out.println(FORMAT);
        System.out.println("Got it. I've added this task: \n" + taskArrayList.get(taskArrayList.size() - 1));
        System.out.println("Now you have " + taskArrayList.size() + " tasks in the list");
        System.out.println(FORMAT);
    }

    //Removes particular task from arraylist
    public static void taskRemover(ArrayList<Task> taskArrayList, int index){
        System.out.println(FORMAT);
        System.out.println("Noted. I've removed this task: \n" + taskArrayList.get(index));
        taskArrayList.remove(index);
        System.out.println("Now you have " + taskArrayList.size() + " tasks in the list");
        System.out.println(FORMAT);
    }

    public static void taskLister(ArrayList<Task> taskArrayList) {
        int numberedPoint = 1;
        System.out.println(FORMAT);
        System.out.println(Messages.MESSAGE_LIST_TASKS);
        for(Task task : taskArrayList){
            System.out.println(numberedPoint + ". " + task);
            numberedPoint++;
        }
        System.out.println(FORMAT);
    }

    public static void taskFinisher(ArrayList<Task> taskArrayList, int doneItem) {
        taskArrayList.get(doneItem).isCompleted();
        System.out.println(FORMAT);
        System.out.println(Messages.MESSAGE_DONE_TASK);
        System.out.println(taskArrayList.get(doneItem));
        System.out.println(FORMAT);
    }

    public static void byeHandler() {
        System.out.println(FORMAT);
        System.out.println(Messages.MESSAGE_BYE);
        System.out.println(FORMAT);
    }

    public static void helloHandler() {
        System.out.println(FORMAT);
        System.out.println(Messages.MESSAGE_HELLO);
        System.out.println(FORMAT);
    }

    public static void doneHandler(ArrayList<Task> taskArrayList, String mainTask, String[] userInput)
            throws EmptyDescriptionException, OutOfLimitException {
        if (mainTask == null){
            throw new EmptyDescriptionException(userInput[0]);
        }
        int doneItem = Integer.parseInt(userInput[1]) - 1;
        if (doneItem >= taskArrayList.size()) {
            throw new OutOfLimitException();
        }
        taskFinisher(taskArrayList, doneItem);
        try {
            writeFile();
        } catch (IOException | DukeException e) {
            System.out.println(Messages.MESSAGE_SAVE_ERROR);
        }
    }

    public static void todoHandler(ArrayList<Task> taskArrayList, String mainTask, String[] userInput)
            throws EmptyDescriptionException {
        if (mainTask == null){
            throw new EmptyDescriptionException(userInput[0]);
        }
        taskArrayList.add(new ToDo (userInput[1]));
        taskAdder(taskArrayList);
        try {
            writeFile();
        } catch (IOException | DukeException e) {
            System.out.println(Messages.MESSAGE_SAVE_ERROR);
            System.out.println(FORMAT);
        }
    }

    public static void deadlineHandler(ArrayList<Task> taskArrayList, String mainTask, String[] userInput)
            throws EmptyDescriptionException, EmptyDateException {
        if (mainTask == null){
            throw new EmptyDescriptionException(userInput[0]);
        }
        if (!mainTask.contains("/by")){
            throw new EmptyDateException();
        }
        String[] deadlines = mainTask.trim().split("/by", 2);
        taskArrayList.add(new Deadline(deadlines[0],deadlines[1]));
        taskAdder(taskArrayList);
        try {
            writeFile();
        } catch (IOException | DukeException e) {
            System.out.println(Messages.MESSAGE_SAVE_ERROR);
            System.out.println(FORMAT);
        }
    }

    public static void deleteHandler(ArrayList<Task> taskArrayList, String mainTask, String[] userInput)
            throws EmptyDescriptionException, OutOfLimitException {
        if (mainTask == null) {
            throw new EmptyDescriptionException(userInput[0]);
        }
        int doneItem = Integer.parseInt(userInput[1]) - 1;
        if (doneItem >= taskArrayList.size()) {
            throw new OutOfLimitException();
        }
        taskRemover(taskArrayList, doneItem);
        try {
            writeFile();
        } catch (IOException | DukeException e) {
            System.out.println(Messages.MESSAGE_SAVE_ERROR);
            System.out.println(FORMAT);
        }
    }

    public static void eventHandler(ArrayList<Task> taskArrayList, String mainTask, String[] userInput)
            throws EmptyDescriptionException, EmptyDateException {
        if (mainTask == null){
            throw new EmptyDescriptionException(userInput[0]);
        }
        if (!mainTask.contains("/at")){
            throw new EmptyDateException();
        }
        String[] eventArray = mainTask.trim().split("/at", 2);
        taskArrayList.add(new Event (eventArray[0], eventArray[1]));
        taskAdder(taskArrayList);
        try {
            writeFile();
        } catch (IOException | DukeException e) {
            System.out.println(Messages.MESSAGE_SAVE_ERROR);
            System.out.println(FORMAT);
        }
    }

    public static void taskAssigner(String[] data) {
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

    //Parses input in order to process user command
    public static void parseInput(String[] userInput) throws EmptyDescriptionException, WrongCommandException,
            EmptyDateException, OutOfLimitException {

        String mainTask = userInput.length == 2 ? userInput[1] : null;

        if (userInput[0].equals(BYE_COMMAND)) { //EXITS PROGRAM
            byeHandler();
        } else if (userInput[0].equals(LIST_COMMAND)){ //LISTS ALL TASKS
            taskLister(taskArrayList);
        } else if (userInput[0].equals(DONE_COMMAND)){ //COMPLETING A TASK
            doneHandler(taskArrayList, mainTask, userInput);
        } else if (userInput[0].equals(TODO_COMMAND)){ //ADDING A TODO
            todoHandler(taskArrayList, mainTask, userInput);
        } else if (userInput[0].equals(DEADLINE_COMMAND)){ //ADDING A DEADLINE
            deadlineHandler(taskArrayList, mainTask, userInput);
        } else if (userInput[0].equals(EVENT_COMMAND)){ //ADDING AN EVENT
            eventHandler(taskArrayList, mainTask, userInput);
        } else if (userInput[0].equals("delete")) {
            deleteHandler(taskArrayList, mainTask, userInput);
        } else {
            throw new WrongCommandException();
        }
    }

    //Reads file and adds to the arraylist
    public static void readFile() throws DukeException, IOException {
        String dataPath = new File("saved_data").getAbsolutePath();

        if(Files.exists(Path.of(dataPath))) {
            File f = new File (dataPath + "/tasks.txt");
            Scanner sc = new Scanner(f);

            while(sc.hasNext()) {
                String partialString = sc.nextLine();
                String[] data = partialString.trim().split("\\|", 3);
                taskAssigner(data);
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

    //Writes file when called
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

        helloHandler();
        String[] stringArray;

        try {
            readFile();
        } catch (IOException | DukeException e) {
            System.out.println(Messages.MESSAGE_READ_ERROR);
        }

        do {

            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            stringArray = line.trim().split(" ", 2);
            try {
                parseInput(stringArray);
            } catch (EmptyDescriptionException e) {
                e.displayException();
            } catch (WrongCommandException e) {
                e.displayException();
            } catch (EmptyDateException e) {
                e.displayException();
            } catch (OutOfLimitException e) {
                e.displayException();
            }
            
        } while (!stringArray[0].equals(BYE_COMMAND));

    }
}

