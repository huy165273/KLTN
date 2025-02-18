import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class ConcolicMain {
    public static void main(String[] args) throws FileNotFoundException {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath.replace("\\", "/") + "/";

        String filePath = currentPath + "Test.java";

        Concolic concolic = new Concolic(filePath);
        concolic.generateTestcase();


    }

}
