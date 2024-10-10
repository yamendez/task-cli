package org.task.prueba;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "helloName",
        description = "Prints the name"
)
class HelloNameCommand implements Runnable {
    // Prueba de la librería Picocli
    @CommandLine.Parameters(index = "0", description = "the name to print")
    private String name;

    @CommandLine.Option(names = {"-c", "--caps"}, description = "Print name in all caps")
    private boolean allCaps = false;

    public static void main(String[] args){
        new CommandLine(new HelloNameCommand()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("Hello " + (allCaps ? name.toUpperCase() : this.name));
    }
}
