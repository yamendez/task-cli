package org.task.controller;

import org.task.exceptions.WriteJSonException;
import org.task.models.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CRUD {

    private static final File file = new File(System.getProperty("user.dir"), "task.json");
    private static List<Task> list = new ArrayList<>();

    // Escribe las tareas en un archivo JSon
    // Writes the tasks in a JSon file
    public static void writeJson(List<Task> tasks){

        try {
            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            StringBuilder builder = new StringBuilder();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

            builder.append("{\n");
            builder.append("\t\"tasks\": [\n");

            tasks.forEach(t -> {
                builder.append("\t").append("{\n");
                builder.append("\t".repeat(2))
                        .append("\"id\":").append(t.getID()).append(",\n");
                builder.append("\t".repeat(2))
                        .append("\"description\":").append("\"").append(t.getDescription()).append("\",\n");
                builder.append("\t".repeat(2))
                        .append("\"status\":").append("\"").append(t.getStatus().name().toLowerCase()).append("\",\n");
                builder.append("\t".repeat(2))
                        .append("\"createdAt\":").append("\"").append(t.getCreatedAt().format(df)).append("\",\n");

                if(t.getUpdateAt() == null){
                    builder.append("\t".repeat(2))
                            .append("\"updatedAt\":").append("\"").append(t.getUpdateAt()).append("\"\n");
                }
                else{
                    builder.append("\t".repeat(2))
                            .append("\"updatedAt\":").append("\"").append(t.getUpdateAt().format(df)).append("\"\n");
                }

                builder.append("\t".repeat(1)).append("}");
                if(t.getID() < tasks.size()){
                    builder.append(",\n");
                }
            });

            builder.append("\n\t]");
            builder.append("\n}");

            bw.write(builder.toString());
            bw.close();
        } catch (IOException e) {
            throw new WriteJSonException("Error while writing the json file: " + e);
        }
    }

    // Agrega una nueva tarea
    // Adds a new task
    public static String add(String taskName){
        Task t = new Task(taskName);
        list.add(t);
        writeJson(list);
        return "Task added successfully (ID: " + (list.indexOf(t) + 1) + ")";
    }


    // Muestra todas las tareas
    // Lists all the tasks
    public static void list(){
        list.forEach(task -> System.out.println(task.getID()+ " " + task.getDescription() + " - "
                + task.getStatus().name().toLowerCase()));
    }

    // Muestra las tareas por el estado
    // Lists tasks by the status
    public static void listStatus(Status status){

    }

    public static void updateTask(int id, String newTask){
        Task oldTask = list.get(id - 1);
        oldTask.setDescription(newTask);
        oldTask.setUpdateAt(LocalDateTime.now());
        list.set(id - 1, oldTask);
        writeJson(list);
    }

    public static String delete(int id){
        return "Task n has been deleted";
    }

}
