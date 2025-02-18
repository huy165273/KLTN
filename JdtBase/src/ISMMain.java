import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class ISMMain {
    public static void main(String[] args) throws FileNotFoundException {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath.replace("\\", "/") + "/";
//        currentPath = currentPath.substring(0, currentPath.length() - 5);

        String folderPath = currentPath + "projectTest";
        ISM iSM = new ISM(folderPath);
        iSM.generateTestcase();

    }

}
