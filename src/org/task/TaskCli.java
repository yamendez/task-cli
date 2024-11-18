package org.task;

import org.task.controller.CRUD;
import org.task.exceptions.TaskNotFoundException;
import org.task.models.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskCli{
    public static void main(String[] args) throws TaskNotFoundException {
        final List<String> commands = new ArrayList<>();
        Collections.addAll(commands, "--help","-h","--list","--add","--update",
                "--delete","--mark-in-progress","--mark-done");

        if(commands.contains(args[0])){
            switch(args[0]){
                case "--add" -> System.out.println(CRUD.add(args[1]));
                case "--update" -> CRUD.updateTask(Integer.parseInt(args[1]), args[2]);
                case "--list" -> {
                    if(args.length == 1){
                        CRUD.list();
                    } else {
                        CRUD.listStatus(Status.valueOf(args[1].toUpperCase()));
                    }
                }
                case "--mark-in-progress" -> CRUD.updateStatus(Integer.parseInt(args[1]), Status.IN_PROGRESS);
                case "--mark-done" -> CRUD.updateStatus(Integer.parseInt(args[1]), Status.DONE);
                case "--delete" -> System.out.println(CRUD.delete(Integer.parseInt(args[1])));
                case "--help","-h" -> System.out.println(CRUD.help());
            }

        }else{
            System.out.println(args[0]+" is not a valid command. For more information type --help or -h");
        }
    }
}
