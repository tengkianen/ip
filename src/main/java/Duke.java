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

        System.out.println("----------------------------------------------------------------------\n");
        System.out.println("Hello I'm Duke\n");
        System.out.println("What can I do for you?\n");
        System.out.println("----------------------------------------------------------------------\n");

        while(true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            if (line.equals("bye")) {
                System.out.println("----------------------------------------------------------------------\n");
                System.out.println("Bye. Hope to see you again soon!\n");
                System.out.println("----------------------------------------------------------------------\n");
                break;
            } else {
                System.out.println("----------------------------------------------------------------------\n");
                System.out.println(line);
                System.out.println("----------------------------------------------------------------------\n");
            }
        }
    }

}
