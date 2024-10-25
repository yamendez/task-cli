import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.task.controller.CRUD;
import org.task.models.Status;

import java.io.IOException;

public class TestTask {

    //@Test
    public void testWriteJson() throws IOException, ParseException {
        String result = CRUD.add("Buy groceries and make dinner");
        CRUD.add("Go shopping");
        CRUD.list();
        CRUD.updateTask(1, "Make dinner");
        System.out.println("-----");
        CRUD.list();
        Assert.assertEquals("Task added successfully (ID: 1)", result);
    }

    //@Test
    public void testUpdateStatus() throws IOException, ParseException {
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
    public void testReadJson(){
        try {
            CRUD.readJson();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
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
