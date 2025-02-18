package cfg.generation.testpath;

import cfg.generation.testpath.Check.FullTestpaths;

import java.util.ArrayList;

public interface ITestpathGeneration {
    final static int DEFAULT_MAX_ITERATIONS_FOR_EACH_LOOP = 1;

    public  void generateTestpaths();
    public FullTestpaths getPossibleTestpaths();
}
