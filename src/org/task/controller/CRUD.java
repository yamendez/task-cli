package org.task.controller;

import org.task.exceptions.TaskNotFoundException;
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
        return "Task added successfully (ID: " + (getList().indexOf(t) + 1) + ")";
    }

    /**
     * Lists all the tasks
     */
    public static void list() {
        if(file.exists() && getList().isEmpty()) {
            readJson();
        }
        getList().forEach(task -> System.out.println(task.getID()+ " " + task.getDescription() + " - "
                + task.getStatus().name().toLowerCase()));
    }

    /**
     * Lists tasks by the status
     */
    public static void listStatus(Status status) {
        if(file.exists() && getList().isEmpty()) {
            readJson();
        }
        getList().stream().filter(t -> t.getStatus() == status)
                .forEach(task -> System.out.println(task.getID()+ " " + task.getDescription() + " - "
                + task.getStatus().name().toLowerCase()));
    }

    /**
     * Updates the description of the task
     */
    public static void updateTask(int id, String newTask) throws TaskNotFoundException{
        if(file.exists() && getList().isEmpty()){
            readJson();
        }

        validId(id);

        Task oldTask = getList().get(id - 1);
        oldTask.setDescription(newTask);
        oldTask.setUpdateAt(LocalDateTime.now());
        getList().set(id - 1, oldTask);
        writeJson(getList());
        System.out.println("Task "+ id +" was updated");

    }

    /**
     * Updates the status of the task
     */
    public static void updateStatus(int id, Status status) throws TaskNotFoundException {
        if(file.exists() && getList().isEmpty()) {
            readJson();
        }
        validId(id);

        Task oldTask = getList().get(id - 1);
        oldTask.setStatus(status);
        oldTask.setUpdateAt(LocalDateTime.now());
        getList().set(id - 1, oldTask);
        writeJson(getList());
        System.out.println("Task "+ id +" is " + oldTask.getStatus().toString().toLowerCase());


    }

    /**
     * Deletes a task from the list
     */
    public static String delete(int id) throws TaskNotFoundException {
        if(file.exists() && getList().isEmpty()) {
            readJson();
        }

        validId(id);
        Task t = getList().get(id - 1);
        getList().remove(t);
        writeJson(getList());
        return "Task "+id+" has been deleted";
    }

    private static void validId(int id) throws TaskNotFoundException {
        if(id-1 > getList().size() || id-1 < 0){
            throw new TaskNotFoundException("Could not found the task (ID: "+id+").");
        }
    }

    // returns a help message with the accepted commands.
    public static String help(){
        return """
                Commands:\s
                \t --add <description>
                \t\t adds a new task.
                \t --update <id> <new desc>
                \t --mark-in-progress <id>
                \t --mark-done <id>
                \t --list
                \t --list [done|todo|in-progress]
                \t --delete <id>
                \t -h --help""";
    }

}
