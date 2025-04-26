import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.task.TaskCli;
import java.io.IOException;

public class TestTask {

//    @Test
//    public void testAddTask() {
//        TaskCli.main(new String[]{"delete", "100"});
//        System.out.println("-------------");
//    }
//
//    @Test
//    public void testUpdateStatus() {
//        TaskCli.main(new String[]{"mark-in-progress", "4"});
//        System.out.println("-------------");
//        TaskCli.main(new String[]{"list", "todo"});
//        System.out.println("-------------");
//    }

    @Test
    public void testListar() {
        TaskCli.main(new String[]{"list"});
        System.out.println("-------------");
    }
}
