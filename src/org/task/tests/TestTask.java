import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.task.TaskCli;
import org.task.controller.CRUD;
import org.task.controller.JsonHandler;
import org.task.exceptions.TaskNotFoundException;
import org.task.models.Status;

import java.io.IOException;

public class TestTask {

    //@Test
    public void testWriteJson() throws IOException, ParseException, TaskNotFoundException {
        String result = CRUD.add("Buy groceries and make dinner");
        CRUD.add("Go shopping");
        CRUD.list();
        CRUD.updateTask(1, "Make dinner");
        System.out.println("-----");
        CRUD.list();
        Assert.assertEquals("Task added successfully (ID: 1)", result);
    }

    //@Test
    public void testUpdateStatus() throws IOException, ParseException, TaskNotFoundException {
        System.out.println("-----");
        CRUD.add("Visit grandma");
        CRUD.add("Run in the morning");
        CRUD.add("Spanish class");
        CRUD.updateStatus(3, Status.DONE);
        CRUD.updateStatus(4, Status.IN_PROGRESS);
        CRUD.updateStatus(5, Status.IN_PROGRESS);
        CRUD.listStatus(Status.DONE);
    }

    @Test
    public void testMain() throws TaskNotFoundException {
        TaskCli.main(new String[]{"--list"});
        System.out.println("-------------");
        System.out.println("-------------");
        TaskCli.main(new String[]{"-h"});
//        TaskCli.main(new String[]{"mark-in-progress","0"});
    }
    //@Test
    public void testListar() throws IOException, ParseException {
        CRUD.listStatus(Status.DONE);
        System.out.println("-----");
        CRUD.listStatus(Status.TODO);
//        CRUD.add("Take out the dog");
//        System.out.println("-----");
//        CRUD.list();
    }
}
