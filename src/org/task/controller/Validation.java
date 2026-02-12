package org.task.controller;

import java.util.*;

import java.util.List;

public class Validation {
    private static final Map<String, Integer> commandsLength = new HashMap<String, Integer>(){{
        put("add",1);
        put("update",2);
        put("list", 1);
        put("mark-in-progress", 1);
        put("mark-done", 1);
        put("delete",1);
    }};
    private static final List<String> commands = Arrays.asList("help","-h","list","add","update",
            "delete","mark-in-progress","mark-done");

    public static boolean validCommand(String command){
        return commands.contains(command);
    }
    public static boolean hasParameters(String[] args){
        boolean hasParam = false;
        List<String> listStatus = Arrays.asList("todo","done","in-progress");
        if (commandsLength.containsKey(args[0]) && !args[0].equals("list")) {
            int cantParam = commandsLength.get(args[0]) + 1;
            hasParam = args.length == cantParam;
        }
        if (args[0].equals("list")) {
            hasParam = args.length == 1 || (args.length == 2  && listStatus.contains(args[1]));
        }

        return hasParam;
    }

    public static void invalidType(String param){
        System.out.println(param + " is not a valid ID.");
        errorMessage();
    }
    private static void validParams(boolean args) {
        if (args) {
            Validation.errorMessage();
            System.exit(1);
        }
    }
    public static void errorMessage() {
        System.out.println("Type -h, help to see the valid commands and parameters.");
    }
}
