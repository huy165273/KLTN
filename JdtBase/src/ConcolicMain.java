import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class ConcolicMain {
    public static void main(String[] args) throws FileNotFoundException {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath.replace("\\", "/") + "/";
//        currentPath = currentPath.substring(0, currentPath.length() - 5);

        String filePath = currentPath + "Test.java";

        Concolic concolic = new Concolic(filePath);
        concolic.generateTestcase();


    }

}
