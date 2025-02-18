package mark;

import cfg.ICFG;
import cfg.nodes.*;

import java.util.HashMap;
import java.util.List;

public class MethodMark {
    private List<ICfgNode> allNodes;
    private StringBuilder contentMethod;
    private HashMap<ICfgNode, ICfgNode> parentUpdaterCfgNode = new HashMap<>(); // Nốt trước nốt update 2 nốt để kiểm tra xem có là continue, break, return không
    public MethodMark(ICFG cfg, StringBuilder contentMethod){
        this.allNodes = cfg.getAllNodes();
        this.contentMethod = contentMethod;
        arrangeAllNodes();
    }

    public StringBuilder addMark(){
        StringBuilder contentMethodMark = new StringBuilder(contentMethod.toString());
        Index index = new Index();

        for(ICfgNode cfgNode : allNodes){
            if(cfgNode instanceof ForInitializerCfgNode){
                addMarkForInitializerCfgNode(contentMethodMark, cfgNode, index);
            } else if(cfgNode instanceof ForUpdaterCfgNode){
               addMarkForUpdaterCfgNode(contentMethodMark, (ForUpdaterCfgNode) cfgNode, index, parentUpdaterCfgNode);
            } else if ((cfgNode instanceof ConditionCfgNode)){
                addMarkConditionCfgNode(contentMethodMark, cfgNode, index);
            } else if(cfgNode instanceof SimpleCfgNode){
                addMarkSimpleCfgNode(contentMethodMark, cfgNode, index, parentUpdaterCfgNode);
            } else if(cfgNode instanceof ScopeCfgNode){
                index.setIndexInContentMethodMark(contentMethodMark.indexOf(cfgNode.getContent(), index.getIndexInContentMethodMark()));
            }
        }
//        System.out.println(contentMethodMark);
        return contentMethodMark;
    }

    public void addMarkForInitializerCfgNode(StringBuilder contentMethodMark, ICfgNode cfgNode, Index index){
        String contentNode = cfgNode.getContent();

        int offset = contentMethod.indexOf(contentNode, index.getIndexInContentMethod());
        int lineInFunction = contentMethod.substring(0, offset).split("\n").length;
        LineMark lineMark = new LineMark(cfgNode.getId(), lineInFunction, offset, new StringBuilder(contentNode));
        StringBuilder mark = lineMark.addMark().append(";");

        index.setIndexInContentMethodMark(contentMethodMark.indexOf("for", index.getIndexInContentMethodMark()));
        index.getIndexForInContentMethods().add(contentMethod.indexOf("for", index.getIndexInContentMethod()));
        index.setIndexInContentMethod(offset + contentNode.length());
        contentMethodMark.replace(index.getIndexInContentMethodMark(), index.getIndexInContentMethodMark(), mark.toString());
    }

    public void addMarkForUpdaterCfgNode(StringBuilder contentMethodMark, ForUpdaterCfgNode forUpdaterCfgNode, Index index, HashMap<ICfgNode, ICfgNode> parentUpdaterCfgNode){
        String contentNode = forUpdaterCfgNode.getContent();
        if (parentUpdaterCfgNode.get(forUpdaterCfgNode) instanceof BreakCfgNode || parentUpdaterCfgNode.get(forUpdaterCfgNode) instanceof ReturnCfgNode){
            index.setIndexInContentMethodMark(index.getIndexInContentMethodMark() + 1); // 1 là độ dài của "}"
            index.getIndexForInContentMethods().remove(index.getIndexForInContentMethods().size() - 1);
            return;
        }

        int offset = contentMethod.indexOf(contentNode, index.getIndexForInContentMethods().get(index.getIndexForInContentMethods().size() - 1));
        if(offset == -1){
            System.out.println(index.getIndexForInContentMethods().get(index.getIndexForInContentMethods().size() - 1));
            System.out.println("-1 " + contentNode);
        }
        int lineInFunction = contentMethod.substring(0, offset).split("\n").length;
        LineMark lineMark = new LineMark(forUpdaterCfgNode.getId(), lineInFunction, offset, new StringBuilder(contentNode));
        StringBuilder mark = lineMark.addMark().append(";");
        if (parentUpdaterCfgNode.get(forUpdaterCfgNode) instanceof ContinueCfgNode){
            contentMethodMark.replace(index.getIndexContinueInMethodMark(), index.getIndexContinueInMethodMark(), mark.toString());
        } else {
            contentMethodMark.replace(index.getIndexInContentMethodMark(), index.getIndexInContentMethodMark(), mark.toString());
        }

        index.setIndexInContentMethodMark(index.getIndexInContentMethodMark() + mark.length() + 1); // 1 là độ dài của "}"
        index.getIndexForInContentMethods().remove(index.getIndexForInContentMethods().size() - 1);
    }
    public void addMarkSimpleCfgNode(StringBuilder contentMethodMark, ICfgNode cfgNode, Index index, HashMap<ICfgNode, ICfgNode> parentUpdaterCfgNode){
        String contentNode = cfgNode.getContent();
        int offset = contentMethod.indexOf(contentNode, index.getIndexInContentMethod());
        if (offset == -1){
            System.out.println("-1 :" + cfgNode.getContent());
        }
        int lineInFunction = contentMethod.substring(0, offset).split("\n").length;
        LineMark lineMark = new LineMark(cfgNode.getId(), lineInFunction, offset, new StringBuilder(contentNode));
        StringBuilder mark = lineMark.addMark().append(";").append(contentNode);

        index.setIndexInContentMethod(offset + contentNode.length());
        index.setIndexInContentMethodMark(contentMethodMark.indexOf(contentNode, index.getIndexInContentMethodMark()));
        contentMethodMark.replace(index.getIndexInContentMethodMark(), index.getIndexInContentMethodMark() + contentNode.length(), mark.toString());
        index.setIndexInContentMethodMark(index.getIndexInContentMethodMark() + mark.length());
        if(cfgNode instanceof ContinueCfgNode) {
            for (ICfgNode key : parentUpdaterCfgNode.keySet()) {
                if (parentUpdaterCfgNode.get(key) == cfgNode) {
                    index.setIndexContinueInMethodMark(index.getIndexInContentMethodMark() - contentNode.length());
                }
            }
        }
    }

    public void addMarkConditionCfgNode(StringBuilder contentMethodMark, ICfgNode cfgNode, Index index){
        String contentNode = cfgNode.getContent();

        int offset = contentMethod.indexOf(contentNode, index.getIndexInContentMethod());
        if (offset == -1){
            System.out.println("-1 " + contentNode + " " + index.getIndexInContentMethod());
        }
        int lineInFunction = contentMethod.substring(0, offset).split("\n").length;
        LineMark lineMark = new LineMark(cfgNode.getId(), lineInFunction, offset, new StringBuilder(contentNode), "falseNode");
        StringBuilder mark = new StringBuilder("(");
        mark.append(lineMark.addMark()).append(" && ").append(contentNode).append(" && ");
        lineMark.setCondition("trueNode");
        mark.append(lineMark.addMark()).append(")");

        index.setIndexInContentMethod(offset);
        index.setIndexInContentMethodMark(contentMethodMark.indexOf(contentNode, index.getIndexInContentMethodMark()));
        if(index.getIndexInContentMethodMark() == -1){
            System.out.println("-1 " + contentNode);
        }
        contentMethodMark.replace(index.getIndexInContentMethodMark(), index.getIndexInContentMethodMark() + contentNode.length(), mark.toString());
        index.setIndexInContentMethodMark(index.getIndexInContentMethodMark() + mark.length());
    }

    private void arrangeAllNodes(){
        for (int i = 0; i < allNodes.size(); i++){
            if(allNodes.get(i) instanceof ForUpdaterCfgNode){
                parentUpdaterCfgNode.put(allNodes.get(i), allNodes.get(i - 2));
            }
        }
        for (int i = 0; i < allNodes.size() - 1; i++){
            for (int j =  allNodes.size() - 1; j > i; j--){
                if(!(allNodes.get(j) instanceof ForUpdaterCfgNode)) {
                    if(allNodes.get(j) instanceof ConditionCfgNode && allNodes.get(j - 1) instanceof ConditionCfgNode){
                        ConditionCfgNode condition1 = (ConditionCfgNode) allNodes.get(j - 1);
                        ConditionCfgNode condition2 = (ConditionCfgNode) allNodes.get(j);
                        while (true){
                            if(condition1.getId() > condition2.getId()){
                                if(condition1.getParentCondition() == null){
                                    swapCfgNode(j - 1, j, allNodes);
                                    break;
                                } else {
                                    condition1 = condition1.getParentCondition();
                                }
                            } else {
                                if(condition2.getParentCondition() != null){
                                    condition2 = condition2.getParentCondition();
                                } else {
                                    break;
                                }
                            }
                        }
                    } else if ((allNodes.get(j) instanceof ConditionCfgNode) && !(allNodes.get(j - 1) instanceof ConditionCfgNode)){
                        ConditionCfgNode condition = (ConditionCfgNode) allNodes.get(j);
                        while (true){
                            if(condition.getParentCondition() != null){
                                condition = condition.getParentCondition();
                            } else {
                                break;
                            }
                        }
                        if(condition.getId() < allNodes.get(j - 1).getId()){
                            swapCfgNode(j - 1, j, allNodes);
                        }
                    } else if (!(allNodes.get(j) instanceof ConditionCfgNode) && (allNodes.get(j - 1) instanceof ConditionCfgNode)){
                        ConditionCfgNode condition = (ConditionCfgNode) allNodes.get(j - 1);
                        while (true){
                            if(condition.getParentCondition() != null){
                                condition = condition.getParentCondition();
                            } else {
                                break;
                            }
                        }
                        if(allNodes.get(j).getId() < condition.getId()){
                            swapCfgNode(j - 1, j, allNodes);
                        }
                    } else {
                        if(allNodes.get(j).getId() < allNodes.get(j - 1).getId()){
                            swapCfgNode(j - 1, j, allNodes);
                        }
                    }
//                    if(allNodes.get(j).getParentCondition() != null && allNodes.get(j - 1).getParentCondition() != null){
//                        if((allNodes.get(j).getParentCondition().getId() < allNodes.get(j - 1).getParentCondition().getId())
//                                || ((allNodes.get(j).getParentCondition().getId() == allNodes.get(j - 1).getParentCondition().getId()) && (allNodes.get(j).getId() < allNodes.get(j - 1).getId()))){
//                            swapCfgNode(j - 1, j, allNodes);
//                        }
//                    } else if (allNodes.get(j).getParentCondition() != null && allNodes.get(j - 1).getParentCondition() == null) {
//                        if(allNodes.get(j).getParentCondition().getId() < allNodes.get(j - 1).getId()){
//                            swapCfgNode(j - 1, j, allNodes);
//                        }
//                    } else if (allNodes.get(j).getParentCondition() == null && allNodes.get(j - 1).getParentCondition() != null) {
//                        if(allNodes.get(j).getId() < allNodes.get(j - 1).getParentCondition().getId()){
//                            swapCfgNode(j - 1, j, allNodes);
//                        }
//                    } else {
//                        if(allNodes.get(j).getId() < allNodes.get(j - 1).getId()){
//                            swapCfgNode(j - 1, j, allNodes);
//                        }
//                    }
                }
            }
        }
        arrangeForUpdaterNode(allNodes, parentUpdaterCfgNode);
    }

    private void arrangeForUpdaterNode(List<ICfgNode> allNodes, HashMap<ICfgNode, ICfgNode> parentUpdaterCfgNode){
        for (ICfgNode key : parentUpdaterCfgNode.keySet()) {
            allNodes.remove(key);
            int index = allNodes.indexOf(parentUpdaterCfgNode.get(key));
            allNodes.add(index + 2, key);
        }
    }

    private void swapCfgNode(int i, int j, List<ICfgNode> allNodes){
        ICfgNode newNode = allNodes.get(j);
        allNodes.set(j, allNodes.get(i));
        allNodes.set(i, newNode);
    }

    public StringBuilder getContentMethod() {
        return contentMethod;
    }

    public void setContentMethod(StringBuilder contentMethod) {
        this.contentMethod = contentMethod;
    }

    public List<ICfgNode> getAllNodes() {
        return allNodes;
    }

    public void setAllNodes(List<ICfgNode> allNodes) {
        this.allNodes = allNodes;
    }
}
