import org.junit.Assert;
import org.junit.Test;
import org.task.controller.CRUD;

public class TestTask {

    @Test
    public void testWriteJson(){
        String result = CRUD.add("Buy groceries and make dinner");
        CRUD.add("Go shopping");
        CRUD.list();
        CRUD.updateTask(1, "Make dinner");
        System.out.println("-----");
        CRUD.list();
        Assert.assertEquals("Task added successfully (ID: 1)", result);
    }

    //@Test
    public void testList(){
        CRUD.add("Visit grandma");
        CRUD.list();
    }
}
