import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class ISMv0Main {
    public static void main(String[] args) throws FileNotFoundException {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath.replace("\\", "/") + "/";
//        currentPath = currentPath.substring(0, currentPath.length() - 5);

        String filePath = currentPath + "Test.java";
        ISMv0 iSMv0 = new ISMv0(filePath);
        iSMv0.generateTestcase();
    }

}
