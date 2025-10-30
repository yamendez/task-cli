package org.task;

import org.task.controller.CRUD;
import org.task.controller.Validation;
import org.task.models.Status;


public class TaskCli{
    public static void main(String[] args) {

        if(args.length == 0) {
            Validation.errorMessage();
            System.exit(1);
        }
        if(!Validation.hasCommands(args)) {
            System.out.println(args[0]+" is not a valid command.");
            Validation.errorMessage();
            System.exit(1);
        }
        //if(commands.contains(args[0])){
            switch(args[0]){
                case "add" -> {
                    if(!Validation.hasParameters(args)) {
                        Validation.errorMessage();
                        System.exit(1);
                    }
                    System.out.println(CRUD.add(args[1]));
                }
                case "update" -> {
                    if(!Validation.hasParameters(args)) {
                        Validation.errorMessage();
                        System.exit(1);
                    }
                    CRUD.updateTask(Integer.parseInt(args[1]), args[2]);
                }
                case "list" -> {
                    if (!Validation.hasParameters(args)){
                        Validation.errorMessage();
                        System.exit(1);
                    }
                    if(args.length == 2){
                        String status = args[1].equals("in-progress")? "in_progress": args[1];
                        CRUD.listStatus(Status.valueOf(status.toUpperCase()));
                    } else {
                        CRUD.list();
                    }
                    /*if(args.length == 1){
                        CRUD.list();
                    } else {
                        try {
                            Status status = null;
                            switch (args[1]) {
                                case "todo" -> status = Status.TODO;
                                case "done" -> status = Status.DONE;
                                case "in-progress" -> status = Status.IN_PROGRESS;
                            }
                            
                            CRUD.listStatus(status);
                        } catch (IllegalArgumentException e) {
                            System.out.println(args[1] + " is not a valid status.");
                        }
                    }*/
                }
                case "mark-in-progress" -> {
                    if (!Validation.hasParameters(args)) {
                        Validation.errorMessage();
                        System.exit(1);
                    }
                    CRUD.updateStatus(Integer.parseInt(args[1]), Status.IN_PROGRESS);
                }
                case "mark-done" -> {
                    if (!Validation.hasParameters(args)) {
                        Validation.errorMessage();
                        System.exit(1);
                    }
                    CRUD.updateStatus(Integer.parseInt(args[1]), Status.DONE);
                }
                case "delete" -> {
                    if (!Validation.hasParameters(args)) {
                        Validation.errorMessage();
                        System.exit(1);
                    }
                    System.out.println(CRUD.delete(Integer.parseInt(args[1])));
                }
                case "help","-h" -> System.out.println(CRUD.help());
            }

//        }else{
//            System.out.println(args[0]+" is not a valid command. For more information type help");
//        }
    }
}
