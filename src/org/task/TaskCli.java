package org.task;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(
        name = "task-cli",
        description = "Task tracker"
)
public class TaskCli implements Callable {
    @Option(names = "--add", description = "Adds a new task")
    private String taskName;



    @Override
    public Object call() throws Exception {
        return null;
    }
}
