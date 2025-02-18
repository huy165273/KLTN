package cfg.generation.testpath.Check;

import cfg.nodes.ICfgNode;
import cfg.nodes.LoopConditionCfgNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.ArrayList;
import java.util.List;

public class AbstractTestpath extends ArrayList<ICfgNode> implements ITestpath {
    private MethodDeclaration functionNode;
    private String description = "";
//    private boolean add;

    @Override
    public int count(ICfgNode stm) {
        int count = 0;
        for (ICfgNode item : this)
            if (item.equals(stm))
                count++;
        return count;
    }

    @Override
    public String toReducedString() {
        String output = "";
        for (int i = 0; i < size() - 1; i++)
            output += get(i).toString() + " -> ";
        output += get(size() - 1);
        return output;
    }

    @Override
    public boolean nextIsTrueBranch(ICfgNode currentNode, int indexofCurrentNode) {
        if (currentNode.isCondition()) {
            if (indexofCurrentNode == size() - 1 || indexofCurrentNode == -1 || !currentNode.isCondition())
                return false;
            else {
                int next = indexofCurrentNode + 1;
                if (currentNode.getTrueNode().getId() == get(next).getId())
                    return true;
                else
                    return false;
            }
        } else
            return true;
    }

    @Override
    public List<ICfgNode> getAllCfgNodes() {
        return this;
    }


//    public com.fit.cfg.testpath.IStaticSolutionGeneration generateTestdata() {
////        if (staticSolutionGen == null) {
////            com.fit.cfg.testpath.IStaticSolutionGeneration staticSolutionGeneration = new StaticSolutionGeneration();
////            staticSolutionGeneration.setFunctionNode(getFunctionNode());
////            staticSolutionGeneration.setTestpath(this);
////            try {
////                staticSolutionGeneration.generateStaticSolution();
////                staticSolutionGen = staticSolutionGeneration;
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        }
////        return staticSolutionGen;
//        return null;
//    }


    public MethodDeclaration getFunctionNode() {
        return functionNode;
    }

    @Override
    public void setFunctionNode(MethodDeclaration functionNode) {
        this.functionNode = functionNode;
    }

    @Override
    public int getRealSize() {
        int size = 0;
        for (ICfgNode cfgNode : this)
            if (cfgNode.isNormalNode())
                size++;
        return size;
    }

    @Override
    public int getNumConditionsIncludingNegativeConditon() {
        int numCondition = 0;
        for (ICfgNode cfgNode : this)
            if (cfgNode.isCondition())
                numCondition++;
        return numCondition;
    }

    @Override
    public int getNumLoopConditions() {
        List<ICfgNode> loopConditions = new ArrayList<>();
        for (ICfgNode cfgNode : this)
            if (cfgNode instanceof LoopConditionCfgNode && !loopConditions.contains(cfgNode))
                loopConditions.add(cfgNode);
        return loopConditions.size();
    }

    @Override
    public ICfgNode getConditionAt(int idCondition) {
        ICfgNode conditionNode = null;

        int numCondition = 0;
        for (ICfgNode cfgNode : this)
            if (cfgNode.isCondition()) {
                numCondition++;
                if (numCondition == idCondition) {
                    conditionNode = cfgNode;
                    break;
                }
            }
        return conditionNode;
    }

    @Override
    public boolean contains(Object o) {
        AbstractTestpath tp2 = (AbstractTestpath) o;
        return getFullPath().startsWith(tp2.getFullPath());
    }

    @Override
    public boolean equals(Object o) {
        AbstractTestpath tp2 = (AbstractTestpath) o;
        if (size() == tp2.size())
            return getFullPath().equals(tp2.getFullPath());
        else
            return false;
    }

    @Override
    public AbstractTestpath cast() {
        return this;
    }

    @Override
    public String getFullPath() {
        return getFullPath();
    }

//    @Override
//    public String getDescription() {
//        return description;
//    }

//    @Override
//    public void setDescription(String description) {
//        this.description = description;
//    }
}
