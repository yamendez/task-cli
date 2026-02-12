package org.task.controller;

import org.task.ConsoleColors;
import org.task.models.*;

import java.time.LocalDateTime;

import static org.task.controller.JsonHandler.*;

public class CRUD {


    /**
     * Adds a new task
     */
    public static String add(String taskName) {
        if(file.exists() && getList().isEmpty()) {
            readJson();
        }
        Task t = new Task(taskName);
        getList().add(t);
        writeJson(getList());
        return ConsoleColors.ANSI_GREEN+"Task added successfully (ID: " + (getList().indexOf(t) + 1) + ")"+ConsoleColors.ANSI_RESET;
    }

    /**
     * Lists all the tasks
     */
    public static void list() {
        if(file.exists() && getList().isEmpty()) {
            readJson();
        }
        if(getList().isEmpty()){
            System.out.println("There are no saved tasks.");
        } else {
            System.out.println("------ All Tasks ------ ");
            getList().forEach(task -> {
                String s = task.getStatus().name().toLowerCase();
                if (s.equals("in_progress")) {
                    s = "in-progress";
                }
                System.out.println(task.getID() + " " + task.getDescription() + " - " + s);
            });
        }
    }

    /**
     * Lists tasks by the status
     */
    public static void listStatus(Status status) {
        if(file.exists() && getList().isEmpty()) {
            readJson();
        }
        if(getList().isEmpty()) {
            System.out.println("There are no saved tasks.");
        } else {
            String stat = status.name().equalsIgnoreCase("in_progress")? "in-progress": status.name().toLowerCase();
            System.out.println("------ Tasks "+stat+" ------");
            getList().stream().filter(t -> t.getStatus() == status)
                    .forEach(task -> {
                        String s = task.getStatus().name().toLowerCase();
                        if (s.equals("in_progress")) {
                            s = "in-progress";
                        }
                        System.out.println(task.getID() + " " + task.getDescription() + " - " + s);
                    });
        }
    }

    /**
     * Updates the description of the task
     */
    public static void updateTask(int id, String newTask) {
        if(file.exists() && getList().isEmpty()){
            readJson();
        }

        if (isIdValid(id)) {
            Task oldTask = getList().get(id - 1);
            oldTask.setDescription(newTask);
            oldTask.setUpdateAt(LocalDateTime.now());
            getList().set(id - 1, oldTask);
            writeJson(getList());
            System.out.println(ConsoleColors.ANSI_GREEN+"Task " + id + " was updated"+ConsoleColors.ANSI_RESET);
        }
    }

    /**
     * Updates the status of the task
     */
    public static void updateStatus(int id, Status status) {
        if(file.exists() && getList().isEmpty()) {
            readJson();
        }

        if (isIdValid(id)) {
            Task oldTask = getList().get(id - 1);
            oldTask.setStatus(status);
            oldTask.setUpdateAt(LocalDateTime.now());
            getList().set(id - 1, oldTask);
            writeJson(getList());
            System.out.println(ConsoleColors.ANSI_GREEN+"Task " + id + " is " + oldTask.getStatus().toString().toLowerCase()+ConsoleColors.ANSI_RESET);
        }

    }

    /**
     * Deletes a task from the list
     */
    public static String delete(int id) {
        if(file.exists() && getList().isEmpty()) {
            readJson();
        }

        if (!isIdValid(id)) {
            return ConsoleColors.ANSI_RED+"Unable to delete task " +id+ConsoleColors.ANSI_RESET;
        }

        Task t = getList().get(id - 1);
        getList().remove(t);
        writeJson(getList());

        return ConsoleColors.ANSI_GREEN+"Task "+id+" has been deleted"+ConsoleColors.ANSI_RESET;
    }

    private static boolean isIdValid(int id) {
        //boolean isValid = true;
        if(id-1 > getList().size() || id-1 < 0){
            System.out.println(ConsoleColors.RED + "Could not found the task (ID: "+id+")." + ConsoleColors.ANSI_RESET);
            return false;
        }
        return true;
    }

    // returns a help message with the accepted commands.
    public static String help(){
        return """
                Commands:\s
                \t add <task-name> \tAdds a new task to the json file.
                \t update <id> <task-name> \tRename the task given an id.
                \t mark-in-progress <task-id> \tMark a task in progress.
                \t mark-done <task-id> \tMark a task as done.
                \t list \tList all tasks
                \t list [done|todo|in-progress] \tList tasks filter by status
                \t delete <id> \tDelete a task
                \t help, -h \tPrints this help message""";
    }

}
