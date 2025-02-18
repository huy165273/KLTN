import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class ISMMain {
    public static void main(String[] args) throws FileNotFoundException {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath.replace("\\", "/") + "/";

        String folderPath = currentPath + "projectTest";
        ISM iSM = new ISM(folderPath);
        iSM.generateTestcase();

    }

}
