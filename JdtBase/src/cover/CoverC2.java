package cover;

import cfg.nodes.ConditionCfgNode;
import cfg.nodes.ICfgNode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoverC2 extends Cover {
    List<ConditionNode> nodeConditions;
    List<ConditionNode> nodeSubConditions;

    public CoverC2(List<ICfgNode> cfgSubConditionNodes) {
        super(cfgSubConditionNodes);
        this.nodeConditions = new ArrayList<>();
        this.nodeSubConditions = new ArrayList<>();
        createNodeConditions();
    }

    private void createNodeConditions(){
        for (ICfgNode cfgNode : cfgSubConditionNodes) {
            if(cfgNode instanceof ConditionCfgNode){
                ConditionNode conditionNode = new ConditionNode(cfgNode.getId(), cfgNode.getContent());
                this.nodeSubConditions.add(conditionNode);
                ICfgNode parentCondition = ((ConditionCfgNode) cfgNode).getParentCondition();
                ConditionNode parentConditionNode;
                while (parentCondition != null){
                    parentConditionNode = new ConditionNode(parentCondition.getId(), parentCondition.getContent());
                    conditionNode.setParentConditionNode(parentConditionNode);
                    parentCondition = parentCondition.getParent();
                    conditionNode = parentConditionNode;
                }
            }
        }
        for(ConditionNode conditionNode : nodeSubConditions){
            while (conditionNode.getParentConditionNode() != null){
                conditionNode = conditionNode.getParentConditionNode();
            }
            if(findConditionNodeInList(conditionNode, nodeConditions) == null){
                nodeConditions.add(conditionNode);
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
                if(conditionMark!= null && conditionMark.equals("falseNode")){
                    for (ConditionNode conditionNode : nodeSubConditions){
                        if(conditionNode.getId() == id){
                            while (conditionNode.getParentConditionNode() != null){
                                conditionNode = conditionNode.getParentConditionNode();
                            }

                            boolean check = true;
                            if (i + 1 != lineMarks.size()){
                                String conditionNextMark1 = findCondition(lineMarks.get(i + 1));
                                String conditionNextMark2 = null;
                                if (i + 2 != lineMarks.size()){
                                    conditionNextMark2 = findCondition(lineMarks.get(i + 2));
                                }
                                if(conditionNextMark1 != null && conditionNextMark1.equals("falseNode")){
                                    check = checkConditionNextMark(conditionNode, lineMarks.get(i + 1));
                                } else if (conditionNextMark2 != null && conditionNextMark2.equals("falseNode")){
                                    check = checkConditionNextMark(conditionNode, lineMarks.get(i + 2));
                                } else {
                                    check = false;
                                }
                            }

                            if(!check){
                                ConditionNode conditionNode1 = findConditionNodeInList(conditionNode, nodeConditions);
                                if (i + 1 != lineMarks.size()){
                                    String conditionNextMark = findCondition(lineMarks.get(i + 1));
                                    if(conditionNextMark != null && conditionNextMark.equals("trueNode")){
                                        if(conditionNode1 != null){
                                            conditionNode1.setTrueNodeCovered(true);
                                        }
                                        i ++;
                                    } else {
                                        if(conditionNode1 != null){
                                            conditionNode1.setFalseNodeCovered(true);
                                        }
                                    }
                                } else {
                                    if(conditionNode1 != null){
                                        conditionNode1.setFalseNodeCovered(true);
                                    }
                                }
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
                ConditionCfgNode conditionCfgNode = (ConditionCfgNode) testpath.get(i);
                while (conditionCfgNode.getParentCondition() != null){
                    conditionCfgNode = conditionCfgNode.getParentCondition();
                }
                boolean check = false;
                if(testpath.get(i + 1) instanceof ConditionCfgNode){
                    ConditionCfgNode conditionCfgNextNode = (ConditionCfgNode) testpath.get(i + 1);
                    while (conditionCfgNextNode.getParentCondition() != null){
                        conditionCfgNextNode = conditionCfgNextNode.getParentCondition();
                    }
                    if(conditionCfgNextNode.getId() != conditionCfgNode.getId()){
                        check = true;
                    }
                } else {
                    check = true;
                }
                if(check){
                    for(ConditionNode conditionNode : nodeConditions){
                        if(conditionNode.getId() == conditionCfgNode.getId()){
                            if(testpath.get(i).getTrueNode() == testpath.get(i + 1) && !conditionNode.isTrueNodeCovered()){
                                return false;
                            } else if(testpath.get(i).getFalseNode() == testpath.get(i + 1) && !conditionNode.isFalseNodeCovered()) {
                                return false;
                            }
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
                ConditionCfgNode conditionCfgNode = (ConditionCfgNode) testpath.get(i);
                while (conditionCfgNode.getParentCondition() != null){
                    conditionCfgNode = conditionCfgNode.getParentCondition();
                }
                boolean check = false;
                if(testpath.get(i + 1) instanceof ConditionCfgNode){
                    ConditionCfgNode conditionCfgNextNode = (ConditionCfgNode) testpath.get(i + 1);
                    while (conditionCfgNextNode.getParentCondition() != null){
                        conditionCfgNextNode = conditionCfgNextNode.getParentCondition();
                    }
                    if(conditionCfgNextNode.getId() != conditionCfgNode.getId()){
                        check = true;
                    }
                } else {
                    check = true;
                }
                if(check){
                    for(ConditionNode conditionNode : nodeConditions){
                        if(conditionNode.getId() == conditionCfgNode.getId()){
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
    }

    @Override
    public void resetCovered() {
        for(ConditionNode conditionNode : nodeConditions){
            conditionNode.setFalseNodeCovered(false);
            conditionNode.setTrueNodeCovered(false);
        }
    }

    private boolean checkConditionNextMark(ConditionNode conditionNode, String nextMark){
        double idNextNode = findIdNode(nextMark);
        for (ConditionNode conditionNodeNext : nodeSubConditions){
            if(conditionNodeNext.getId() == idNextNode) {
                while (conditionNodeNext.getParentConditionNode() != null) {
                    conditionNodeNext = conditionNodeNext.getParentConditionNode();
                }
                if(conditionNode.getId() != conditionNodeNext.getId()){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int numberAllCovered() {
        return nodeConditions.size() * 2;
    }

    @Override
    public int numberCovered() {
        int count = 0;
        for (ConditionNode conditionNode : nodeConditions){
            if(conditionNode.isTrueNodeCovered()){
                count++;
            }
            if (conditionNode.isFalseNodeCovered()){
                count++;
            }
        }
        return count;
    }

    private ConditionNode findConditionNodeInList(ConditionNode conditionNode, List<ConditionNode> allConditionNodes) {
        for (ConditionNode conditionNode1 : allConditionNodes) {
            if (conditionNode1.getId() == conditionNode.getId()) {
                return conditionNode1;
            }
        }
        return null;
    }

    @Override
    public boolean checkCoverAll() {
        return numberCovered() == numberAllCovered();
    }

    public List<ConditionNode> getNodeConditions() {
        return nodeConditions;
    }

    public void setNodeConditions(List<ConditionNode> nodeConditions) {
        this.nodeConditions = nodeConditions;
    }
}
