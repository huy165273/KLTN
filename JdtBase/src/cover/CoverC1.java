package cover;

import cfg.nodes.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoverC1 extends Cover {
    private List<Node> nodesCovered;
    private List<Node> subNodesCovered;

    public CoverC1(List<ICfgNode> cfgSubConditionNodes) {
        super(cfgSubConditionNodes);
        this.nodesCovered = new ArrayList<>();
        subNodesCovered = new ArrayList<>();
        createNodeCovered();
    }

    private void createNodeCovered() {
        for (ICfgNode cfgNode : cfgSubConditionNodes) {
            if (!(cfgNode instanceof ScopeCfgNode) && !(cfgNode instanceof BeginFlagCfgNode) && !(cfgNode instanceof EndFlagCfgNode)) {
                if(cfgNode instanceof ConditionCfgNode){
                    ConditionNode conditionNode = new ConditionNode(cfgNode.getId(), cfgNode.getContent());
                    this.subNodesCovered.add(conditionNode);
                    ICfgNode parentCondition = ((ConditionCfgNode) cfgNode).getParentCondition();
                    ConditionNode parentConditionNode;
                    while (parentCondition != null){
                        parentConditionNode = new ConditionNode(parentCondition.getId(), parentCondition.getContent());
                        conditionNode.setParentConditionNode(parentConditionNode);
                        parentCondition = parentCondition.getParent();
                        conditionNode = parentConditionNode;
                    }
                } else {
                    Node node = new Node(cfgNode.getId(), cfgNode.getContent());
                    this.subNodesCovered.add(node);
                }
            }
        }
        for (Node node : subNodesCovered) {
            if(node instanceof ConditionNode){
                ConditionNode conditionNode = (ConditionNode) node;
                while (conditionNode.getParentConditionNode() != null){
                    conditionNode = conditionNode.getParentConditionNode();
                }
                if(findNodeInList(conditionNode, nodesCovered) == null){
                    nodesCovered.add(conditionNode);
                }
            } else {
                nodesCovered.add(node);
            }
        }
    }

    @Override
    public void readMark(List<String> pathMarks) throws FileNotFoundException {
        for (String pathMark : pathMarks){
            FileInputStream fileInputStream = new FileInputStream(pathMark);
            Scanner scanner = new Scanner(fileInputStream);
            try {
                while (scanner.hasNextLine()) {
                    String lineMark = scanner.nextLine();
                    double id = findIdNode(lineMark);
                    for (Node subNode : subNodesCovered){
                        if(subNode.getId() == id){
                            if(subNode instanceof ConditionNode){
                                ConditionNode conditionNode = (ConditionNode) subNode;
                                while (conditionNode.getParentConditionNode() != null){
                                    conditionNode = conditionNode.getParentConditionNode();
                                }
                                Node node = findNodeInList(conditionNode, nodesCovered);
                                if( node != null){
                                    node.setNodeCovered(true);
                                }

                            } else {
                                subNode.setNodeCovered(true);
                            }
                        }
                    }
                }
            } finally {
                try {
                    scanner.close();
                    fileInputStream.close();
                } catch (IOException ex) {
                    ex.getMessage();
                }
            }
        }
    }

    @Override
    public boolean checkCoveredTestpath(List<ICfgNode> testpath) {
        for(ICfgNode cfgNode : testpath){
            if(cfgNode instanceof ConditionCfgNode){
                while (((ConditionCfgNode) cfgNode).getParentCondition() != null){
                    cfgNode = ((ConditionCfgNode) cfgNode).getParentCondition();
                }
            }
            for(Node node : nodesCovered){
                if(node.getId() == cfgNode.getId() && !node.isNodeCovered()){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void updateCoveredTestpath(List<ICfgNode> testpath) {
        for(ICfgNode cfgNode : testpath){
            if(cfgNode instanceof ConditionCfgNode){
                while (((ConditionCfgNode) cfgNode).getParentCondition() != null){
                    cfgNode = ((ConditionCfgNode) cfgNode).getParentCondition();
                }
            }
            for(Node node : nodesCovered){
                if(node.getId() == cfgNode.getId()){
                    node.setNodeCovered(true);
                }
            }
        }
    }

    @Override
    public void resetCovered() {
        for(Node node : nodesCovered){
            node.setNodeCovered(false);
        }
    }

    private Node findNodeInList(Node node, List<Node> allNodes){
        for(Node node1 : allNodes){
            if(node1.getId() == node.getId()){
                return node1;
            }
        }
        return null;
    }

    @Override
    public int numberCovered() {
        int count = 0;
        for(Node node : nodesCovered){
            if(node.isNodeCovered()){
                count ++;
            }
        }
        return count;
    }
    @Override
    public int numberAllCovered(){
        return nodesCovered.size();
    }

    @Override
    public boolean checkCoverAll(){
        return numberCovered() == numberAllCovered();
    }

    public List<Node> getNodesCovered() {
        return nodesCovered;
    }

    public void setNodesCovered(List<Node> nodesCovered) {
        this.nodesCovered = nodesCovered;
    }
}

