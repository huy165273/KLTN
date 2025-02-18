import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class ISMv0Main {
    public static void main(String[] args) throws FileNotFoundException {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath.replace("\\", "/") + "/";

        String filePath = currentPath + "/JdtBase/src/Test.java";
        ISMv0 iSMv0 = new ISMv0(filePath);
        iSMv0.generateTestcase();
    }

}
