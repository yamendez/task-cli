package org.task.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.task.exceptions.WriteJSonException;
import org.task.models.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CRUD {

    private static final File file = new File(System.getProperty("user.dir"), "task.json");
    private static List<Task> list = new ArrayList<>();


    /**
     * Reads the Json file
      */
    public static void readJson() throws IOException, ParseException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();

        while(br.ready()){
            sb.append(br.readLine()) ;
        }
        String pattern = "\"\\w*\":";
        String prueba = sb.toString().replaceAll(pattern, "");
        prueba = prueba.replaceAll("[\\[\\]\\t]", "");
//        prueba = prueba.replaceAll("[\\[\\]]", "");
//        prueba = prueba.replaceAll("[{\t]", "");
        String[] array = prueba.split("[}]");

        Stream.of(array).forEach(arr -> {
            arr = arr.replace("\"", "");
            if(arr.charAt(0) == ' ' || arr.charAt(0) == ','){
                arr = arr.replaceFirst("[, ]", "");
            }
            String[] a = arr.split(",");
            list.add(new Task(a[1], Status.valueOf(a[2].toUpperCase()), a[3], a[4]));
        });


        list.forEach(l -> System.out.println(l.getID()+", "+l.getDescription() +", "+ l.getStatus()
                +", "+ l.getCreatedAt()+", "+ l.getUpdateAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"))));

    }

    /**
     *  Writes the tasks in a JSon file
     */
    public static void writeJson(List<Task> tasks){

        try {
            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            StringBuilder builder = new StringBuilder();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

            builder.append("{\n");
            builder.append("\t\"task\": [\n");

            tasks.forEach(t -> {
                builder.append("\t").append("{\n");
                builder.append("\t".repeat(2))
                        .append("\"id\":").append(t.getID()).append(",\n");
                builder.append("\t".repeat(2))
                        .append("\"description\":").append("\"").append(t.getDescription()).append("\",\n");
                builder.append("\t".repeat(2))
                        .append("\"status\":").append("\"")
                        .append(t.getStatus().toString().toLowerCase()).append("\",\n");
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

    /**
     * Adds a new task
     */
    public static String add(String taskName) throws IOException, ParseException {
        if(file.exists() && list.isEmpty()) {
            readJson();
        }
        Task t = new Task(taskName);
        list.add(t);
        writeJson(list);
        return "Task added successfully (ID: " + (list.indexOf(t) + 1) + ")";
    }

    /**
     * Lists all the tasks
     */
    public static void list() throws IOException, ParseException {
        if(file.exists() && list.isEmpty()) {
            readJson();
        }
        list.forEach(task -> System.out.println(task.getID()+ " " + task.getDescription() + " - "
                + task.getStatus().name().toLowerCase()));
    }

    /**
     * Lists tasks by the status
     */
    public static void listStatus(Status status) throws IOException, ParseException {
        if(file.exists() && list.isEmpty()) {
            readJson();
        }
        list.stream().filter(t -> t.getStatus() == status)
                .forEach(task -> System.out.println(task.getID()+ " " + task.getDescription() + " - "
                + task.getStatus().name().toLowerCase()));
    }

    /**
     * Updates the description of the task
     */
    public static void updateTask(int id, String newTask) throws IOException, ParseException {
        //readJson();
        Task oldTask = list.get(id - 1);
        oldTask.setDescription(newTask);
        oldTask.setUpdateAt(LocalDateTime.now());
        list.set(id - 1, oldTask);
        writeJson(list);
    }

    // Actualiza el estado de la tarea

    /**
     * Updates the status of the task
     */
    public static void updateStatus(int id, Status status) throws IOException, ParseException {
        //readJson();
        Task oldTask = list.get(id - 1);
        oldTask.setStatus(status);
        oldTask.setUpdateAt(LocalDateTime.now());
        list.set(id - 1, oldTask);
        writeJson(list);
    }

    public static String delete(int id){
        return "Task "+id+" has been deleted";
    }

}
