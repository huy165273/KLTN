package cover;

import cfg.nodes.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoverC3 extends Cover{
    List<ConditionNode> nodeSubConditions;

    public CoverC3(List<ICfgNode> cfgSubConditionNodes) {
        super(cfgSubConditionNodes);
        this.nodeSubConditions = new ArrayList<>();
        createNodeConditions();
    }

    private void createNodeConditions(){
        for (ICfgNode cfgNode : cfgSubConditionNodes) {
            if(cfgNode instanceof ConditionCfgNode){
                ConditionNode conditionNode = new ConditionNode(cfgNode.getId(), cfgNode.getContent());
                nodeSubConditions.add(conditionNode);
            }
        }
    }

    @Override
    public void readMark(List<String> pathMarks) throws FileNotFoundException {
        for (String pathMark : pathMarks){
            FileInputStream fileInputStream = new FileInputStream(pathMark);
            Scanner scanner = new Scanner(fileInputStream);
            List<String> lineMarks = new ArrayList<>();
            try {
                while (scanner.hasNextLine()) {
                    lineMarks.add(scanner.nextLine());
                }
            } finally {
                try {
                    scanner.close();
                    fileInputStream.close();
                } catch (IOException ex) {
                    ex.getMessage();
                }
            }

            for (int i = 0; i < lineMarks.size(); i++){
                double id = findIdNode(lineMarks.get(i));
                String conditionMark = findCondition(lineMarks.get(i));
                if(conditionMark != null && conditionMark.equals("falseNode")){
                    for (ConditionNode conditionNode : nodeSubConditions){
                        if(conditionNode.getId() == id){
                            if (i + 1 != lineMarks.size()){
                                String conditionNextMark = findCondition(lineMarks.get(i + 1));
                                if(conditionNextMark != null && conditionNextMark.equals("trueNode")){
                                    conditionNode.setTrueNodeCovered(true);
                                    i ++;
                                } else {
                                    conditionNode.setFalseNodeCovered(true);
                                }
                            } else {
                                conditionNode.setFalseNodeCovered(true);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean checkCoveredTestpath(List<ICfgNode> testpath) {
        for(int i = 0; i < testpath.size(); i++){
            if(testpath.get(i) instanceof ConditionCfgNode){
                for(ConditionNode conditionNode : nodeSubConditions){
                    if(testpath.get(i).getId() == conditionNode.getId()){
                        if(testpath.get(i).getTrueNode() == testpath.get(i + 1) && !conditionNode.isTrueNodeCovered()){
                            return false;
                        } else if(testpath.get(i).getFalseNode() == testpath.get(i + 1) && !conditionNode.isFalseNodeCovered()) {
                            return false;
                        }
                    }
                }
            }

        }
        return true;
    }

    @Override
    public void updateCoveredTestpath(List<ICfgNode> testpath) {
        for(int i = 0; i < testpath.size(); i++){
            if(testpath.get(i) instanceof ConditionCfgNode){
                for(ConditionNode conditionNode : nodeSubConditions){
                    if(testpath.get(i).getId() == conditionNode.getId()){
                        if(testpath.get(i).getTrueNode() == testpath.get(i + 1)){
                            conditionNode.setTrueNodeCovered(true);
                        } else if(testpath.get(i).getFalseNode() == testpath.get(i + 1)) {
                            conditionNode.setFalseNodeCovered(true);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void resetCovered() {
        for(ConditionNode conditionNode : nodeSubConditions){
            conditionNode.setFalseNodeCovered(false);
            conditionNode.setTrueNodeCovered(false);
        }
    }

    @Override
    public int numberAllCovered() {
        return nodeSubConditions.size() * 2;
    }

    @Override
    public int numberCovered() {
        int count = 0;
        for (ConditionNode conditionNode : nodeSubConditions){
            if(conditionNode.isTrueNodeCovered()){
                count++;
            }
            if (conditionNode.isFalseNodeCovered()){
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean checkCoverAll() {
        return numberAllCovered() == numberCovered();
    }

    public List<ConditionNode> getNodeSubConditions() {
        return nodeSubConditions;
    }

    public void setNodeSubConditions(List<ConditionNode> nodeSubConditions) {
        this.nodeSubConditions = nodeSubConditions;
    }
}
