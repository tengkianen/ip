package butler.storage;

import butler.exception.DukeException;
import butler.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String PATH_DIRECTORY = new File("saved_data").getAbsolutePath();

    /**
     * Loads an ArrayList of tasks processed from Duke's storage
     *
     * @return ArrayList containing Tasks from Duke's storage
     * @throws DukeException If the data directory does not exist
     * @throws IOException If there is a problem reading the file
     */
    public ArrayList<Task> readFile() throws DukeException, IOException {

        TaskList taskList = new TaskList();

        if(Files.exists(Path.of(PATH_DIRECTORY))) {
            File f = new File (PATH_DIRECTORY + "/tasks.txt");
            Scanner sc = new Scanner(f);

            while(sc.hasNext()) {
                String partialString = sc.nextLine();
                String[] data = partialString.trim().split("\\|", 3);
                taskAssigner(data, taskList);
            }
        } else {
            File f = new File (PATH_DIRECTORY);
            boolean dirExists = f.mkdir();
            if (dirExists) {
                f = new File (PATH_DIRECTORY + "/tasks.txt");
                f.createNewFile();
            } else {
                throw new DukeException();
            }
        }

        return taskList.getTaskArrayList();
    }

    /**
     * Decodes data read from txt file in the ReadFile() method
     *
     * @param data String Array obtained from the txt file
     * @param taskList ArrayList to hold all the Tasks
     */
    public static void taskAssigner(String[] data, TaskList taskList) {
        String description;
        boolean isDone;

        switch(data[0]) {
        case "[T]":
            isDone = Boolean.parseBoolean(data[1]);
            description = data[2];
            taskList.getTaskArrayList().add(new ToDo(description, isDone));
            break;
        case "[D]":
            isDone = Boolean.parseBoolean(data[1]);
            String[] deadlineArray = data[2].trim().split("\\|", 2);
            description = deadlineArray[0];
            String by = deadlineArray[1];
            taskList.getTaskArrayList().add(new Deadline(description, by, isDone));
            break;
        case "[E]":
            isDone = Boolean.parseBoolean(data[1]);
            String[] eventArray = data[2].trim().split("\\|", 2);
            description = eventArray[0];
            String at = eventArray[1];
            taskList.getTaskArrayList().add(new Event(description, at, isDone));
            break;
        }

    }

    /**
     * Saves ArrayList of Tasks into Duke's storage following a specific format
     *
     * @param taskArrayList ArrayList of all Tasks
     * @throws IOException if error occurs while writing to a file
     * @throws DukeException if specified directory does not exist
     */
    public static void writeFile(ArrayList<Task> taskArrayList) throws IOException, DukeException {
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
}
