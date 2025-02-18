package cfg.generation.testpath.Check;

import org.eclipse.jdt.core.dom.MethodDeclaration;

/**
 * Generate static solution of a test path
 *
 * @author ducanhnguyen
 */
public interface IStaticSolutionGeneration {
    final String NO_SOLUTION = "";
    final String EVERY_SOLUTION = " ";

    /**
     * Generate static solution
     *
     * @return
     * @throws Exception
     */
    void generateStaticSolution() throws Exception;

    /**
     * Get test path
     *
     * @return
     */
    ITestpath getTestpath();

    /**
     * Set test path
     *
     * @param testpath
     */
    void setTestpath(AbstractTestpath testpath);

    /**
     * Get function node
     *
     * @return
     */
    MethodDeclaration getFunctionNode();

    /**
     * Set function node
     *
     * @param functionNode
     */
    void setFunctionNode(MethodDeclaration functionNode);

    /**
     * Get static solution
     *
     * @return
     */
//    String getStaticSolution();

}