# User Guide

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, locate the `src/main/java/Butler.java` file, right-click it, and choose `Run Butler.main()`. If the setup is correct, you should see something like the below:
   ```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Hey I'm Butler!
How can I help you Monsieur/Madame?

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   ```

## Features 

### Displaying all commands: `help`
Displays all possible commands accepted by Butler

Format: `help`

### Adding a Todo Task: `todo`
Adds a Todo to the task list

Format: `todo DESCRIPTION`

Examples:
* `todo CS2113T Repl.it Exercises`
* `todo Order GrabFood for grandma`

Expected Outcome:
* ```
Got it. I've added this task: 
[T][✘] CS2113T Repl.it Exercises
Now you have _ task(s) in the list
```
* ```
Got it. I've added this task: 
[T][✘] Order GrabFood for grandma
Now you have _ task(s) in the list
```

### Adding a Deadline Task: `deadline`
Adds a Deadline to the task list

Format: `deadline DESCRIPTION /by TIME_DATE`

Examples:
* `deadline Implement 'List' function for tP /by Wednesday 2230`
* `deadline Submit CS2113T Assignment /by 20th Oct 9PM`

Expected Outcome:

* ```
Got it. I've added this task: 
[D][✘] Implement 'List' function for tP  (by:  Wednesday 2230)
Now you have _ task(s) in the list
```
* ```
Got it. I've added this task: 
[D][✘] Submit CS2113T Assignment  (by:  20th Oct 9PM)
Now you have _ task(s) in the list
```

### Adding an Event Task: `event`
Adds an Event to the task list

Format: `event DESCRIPTION /at TIME_DATE_PLACE`

Examples:
* `event 2021 Countdown Party /at Siloso Beach 2000`
* `event CS2113T Finals /at 1st Dec 1PM`

Expected Outcome:
* ```
Got it. I've added this task: 
[E][✘] 2021 Countdown Party  (at:  Siloso Beach 2000)
Now you have _ task(s) in the list
```
* ```
Got it. I've added this task: 
[E][✘] CS2113T Finals  (at:  1st Dec 1PM)
Now you have _ task(s) in the list
```

### Listing all tasks: `list`
Lists all tasks in the task list

Format: `list`

Example: 
* `list`

Expected Outcome:
* ```
Here are the tasks in your list:

1. [T][✘] CS2113T Repl.it Exercises
2. [T][✘] Order GrabFood for grandma
```

### Finding tasks by keyword: `find`
Given a keyword, find tasks with description that contain the specified keyword

Format: `find KEYWORD`

Examples:
* `find GrabFood`
* `find Countdown Party`

Expected Output:
* ```
Here are the tasks in your list:

1. [T][✘] Order GrabFood for grandma
```
* ```
Here are the tasks in your list:

1. [E][✘] 2021 Countdown Party  (at:  Siloso Beach 2000)
```

### Marking task as done: `done`
Set a task as done

Format: `done INDEX`

Examples:
* `done 2`
* `done 5`

Expected Output:
* ```
Nice! I've marked this task as done:

[T][✓] Order GrabFood for grandma
```
* ```
Nice! I've marked this task as done:

[E][✓] 2021 Countdown Party  (at:  Siloso Beach 2000)
```

### Deleting a task: `delete`
Deletes a task of given index

Format: `delete INDEX`

Examples:
* `delete 2`
* `delete 3`

Expected Output:
* ```
Noted. I've removed this task: 
[T][✓] Order GrabFood for grandma
Now you have _ task(s) in the list
```
* ```
Noted. I've removed this task: 
[D][✘] Submit CS2113T Assignment  (by:  20th Oct 9PM)
Now you have _ task(s) in the list
```

### Exiting Butler: `bye`
Ends the program

Format: `bye`

Expected Outcome:
* `Bye. Hope to see you again soon!`

### Saving data
All data are saved automatically after any command that manipulates the task list.

## Command Summary

Action | Format, Examples
-------|-----------------
help|`help`
list|`list` 
todo|`todo DESCRIPTION` e.g., `todo CG2271 Assignment`
deadline|`deadline DESCRIPTION /by DAY_DATE_TIME` e.g., `deadline Submit Assignment /by Friday 2359`
event|`event DESCRIPTION /at DAY_DATE_TIME_PLACE` e.g., `event CS2113T Finals /at MPSH2 1PM`
find|`find KEYWORD` e.g., `find assignment`
delete|`delete INDEX` e.g., `delete 5`
done|`done INDEX` e.g., `done 2`
bye|`bye`