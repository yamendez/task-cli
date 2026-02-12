package org.task;

import org.task.controller.CRUD;
import org.task.controller.Validation;
import org.task.models.Status;


public class TaskCli {
    public static void main(String[] args) {

        validParams(args.length == 0);
        if (!Validation.validCommand(args[0])) {
            System.out.println(args[0] + " is not a valid command.");
            Validation.errorMessage();
            System.exit(1);
        }
        switch (args[0]) {
            case "add" -> {
                validParams(!Validation.hasParameters(args));
                System.out.println(CRUD.add(args[1]));
            }
            case "update" -> {
                validParams(!Validation.hasParameters(args));
                try {
                    CRUD.updateTask(Integer.parseInt(args[1]), args[2]);

                } catch (NumberFormatException e) {
                    Validation.invalidType(args[1]);
                }
            }
            case "list" -> {
                validParams(!Validation.hasParameters(args));
                if (args.length == 2) {
                    String status = args[1].equals("in-progress") ? "in_progress" : args[1];
                    CRUD.listStatus(Status.valueOf(status.toUpperCase()));
                } else {
                    CRUD.list();
                }
            }
            case "mark-in-progress" -> {
                validParams(!Validation.hasParameters(args));
                try {
                    CRUD.updateStatus(Integer.parseInt(args[1]), Status.IN_PROGRESS);

                } catch (NumberFormatException e) {
                    Validation.invalidType(args[1]);
                }
            }
            case "mark-done" -> {
                validParams(!Validation.hasParameters(args));
                try {
                    CRUD.updateStatus(Integer.parseInt(args[1]), Status.DONE);
                } catch (NumberFormatException e) {
                    Validation.invalidType(args[1]);
                }
            }
            case "delete" -> {
                validParams(!Validation.hasParameters(args));
                try {
                    System.out.println(CRUD.delete(Integer.parseInt(args[1])));
                } catch (NumberFormatException e) {
                    Validation.invalidType(args[1]);
                }
            }
            case "help", "-h" -> System.out.println(CRUD.help());
        }

    }

    private static void validParams(boolean params) {
        if (params) {
            Validation.errorMessage();
            System.exit(1);
        }
    }
}
