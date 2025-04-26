package org.task.controller;

import org.task.exceptions.ReadJsonException;
import org.task.exceptions.WriteJSonException;
import org.task.models.Status;
import org.task.models.Task;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JsonHandler {

    //public static final File file = new File(System.getProperty("user.dir"), "tasks.json");//
    public static final File file = new File(System.getProperty("user.dir"), "task.json");//
    private static final List<Task> list = new ArrayList<>();

    public static List<Task> getList() {
        return list;
    }

    /**
     * Read the json file and returns the data in an array of Strings
     */
    private static String[] getStrings() {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();

            while (br.ready()) {
                sb.append(br.readLine());
            }
            fr.close();
            br.close();

            String prueba = sb.toString().replaceAll("( *\"\\w*\":)", "");
//            String prueba = sb.toString().replaceAll("\"\\w*\":", "");
//            prueba = prueba.replaceAll("[\\[\\]\\t]", "");
            String p = prueba.replaceAll("[{\\[\\]]", "");
            p = p.replaceAll("( {2,}|\\t)", "");

            System.out.println(p);
            return p.split("}");
        } catch (IOException e){
            throw new ReadJsonException("Error while reading the json file.");
        }
    }

    /**
     * Adds the data of the json file to a List
     */
    public static void readJson() {
        String[] array = null;
        array = getStrings();

        Stream.of(array).forEach(arr -> {
            arr = arr.replace("\"", "");
            if(arr.charAt(0) == ' ' || arr.charAt(0) == ','){
                arr = arr.replaceFirst("[, ]", "");
            }
            String[] a = arr.split(",");
            //
            for (int i = 0; i < a.length; i++) {
                a[i] = a[i].replaceFirst(" ", "");
            }
            list.add(new Task(a[1], Status.valueOf(a[2].toUpperCase()), a[3], a[4]));
        });

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
            builder.append("\t\"tasks\": [\n");

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
            fileWriter.close();
        } catch (IOException e) {
            throw new WriteJSonException("Error while writing the json file.");
        }
    }
}
