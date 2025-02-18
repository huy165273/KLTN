import java.util.List;

public interface TestDriver {
    void doTestDriver();
    int compileTestDriver();
    boolean runTestDriver();

    List<Method> getMethods();

    void setMethods(List<Method> methods);
}
