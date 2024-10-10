package org.task.prueba;

import picocli.CommandLine;
import picocli.CommandLine.*;

@Command(
        name = "helloWorld",
        description = "Prints Hello World"
)
class HelloWorldCommand implements Runnable {
    // Prueba de la librería Picocli

    public static void main(String[] args){
        new CommandLine(new HelloWorldCommand()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("Hello World!");
    }
}
