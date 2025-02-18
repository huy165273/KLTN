package cover;

import cfg.nodes.ICfgNode;
import solvingConditions.Variable;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public abstract class Cover {
    protected List<ICfgNode> cfgSubConditionNodes;
    protected List<List<Variable>> allTestcases;
    public Cover(List<ICfgNode> cfgSubConditionNodes) {
        this.cfgSubConditionNodes = cfgSubConditionNodes;
        allTestcases = new ArrayList<>();
    }
    abstract public void readMark(List<String> pathMarks) throws FileNotFoundException;


    protected double findIdNode(String lineMark){
        double id = -1;
        int index = lineMark.indexOf("id");
        if(index != -1){
            int indexStart = index + 3;
            int indexEnd = lineMark.indexOf("#");
            String idString = lineMark.substring(indexStart, indexEnd);
            id = Double.parseDouble(idString);
        }
        return id;
    }
    protected String findStatement(String lineMark) {
        String statement = null;
        int index = lineMark.indexOf("statement");
        if(index != -1){
            int indexStart = index + 10;
            int indexEnd = lineMark.indexOf("#", indexStart);
            statement = lineMark.substring(indexStart, indexEnd);
        }
        return statement;
    }

    protected String findCondition(String lineMark) {
        String condition = null;
        int index = lineMark.indexOf("condition");
        if(index != -1){
            int indexStart = index + 10;
            int indexEnd = lineMark.indexOf("#", indexStart);
            condition = lineMark.substring(indexStart, indexEnd);
        }
        return condition;
    }

    protected int findOffset(String lineMark){
        int offset = -1;
        int index = lineMark.indexOf("offset");
        if(index != -1){
            int indexStart = index + 7;
            int indexEnd = lineMark.length();
            String offsetString = lineMark.substring(indexStart, indexEnd);
            offset = Integer.parseInt(offsetString);
        }
        return offset;
    }
    
    protected  int findLineInFunction(String lineMark){
        int lineInFunction = -1;
        int index = lineMark.indexOf("line-in-function");
        if(index != -1){
            int indexStart = index + 17;
            int indexEnd = lineMark.indexOf("#", indexStart);
            String lineInFunctionString = lineMark.substring(indexStart, indexEnd);
            lineInFunction = Integer.parseInt(lineInFunctionString);
        }
        return lineInFunction;
    }

    public abstract boolean checkCoveredTestpath(List<ICfgNode> testpath);
    public abstract void updateCoveredTestpath(List<ICfgNode> testpath);
    public abstract void resetCovered();
    public abstract boolean checkCoverAll();
    public abstract int numberCovered();
    public abstract int numberAllCovered();

    public List<ICfgNode> getCfgSubConditionNodes() {
        return cfgSubConditionNodes;
    }

    public void setCfgSubConditionNodes(List<ICfgNode> cfgSubConditionNodes) {
        this.cfgSubConditionNodes = cfgSubConditionNodes;
    }

    public List<List<Variable>> getAllTestcases() {
        return allTestcases;
    }

    public List<String> getAllTestcasesString() {
        List<String> testcases = new ArrayList<>();
        for (List<Variable> testcase : allTestcases) {
            String testcaseString = "(";
            for (Variable variable : testcase) {
                testcaseString += variable.getValue() + ", ";
            }
            testcaseString = testcaseString.substring(0, testcaseString.length() - 2);
            testcaseString += ")";
            testcases.add(testcaseString);
        }
        return testcases;
    }

    public void setAllTestcases(List<List<Variable>> allTestcases) {
        this.allTestcases = allTestcases;
    }
}
