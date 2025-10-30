package org.task.controller;

import java.util.*;

import java.util.List;

public class Validation {
    private static final Map<String, Integer> commandsLength = new HashMap<String, Integer>(){{
        put("add",1);
        put("update",2);
        put("list", 1);
        put("mark", 1);
        put("delete",1);
    }};
    private static final List<String> commands = Arrays.asList("help","-h","list","add","update",
            "delete","mark-in-progress","mark-done");

    public static boolean hasCommands(String[] args){
        return commands.contains(args[0]);
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

    public static boolean validTypes(String[] args){
        boolean validTypes = false;

        return validTypes;
    }

    public static void errorMessage() {
        System.out.println("Type -h, help to see the valid commands and parameters.");
    }
}
