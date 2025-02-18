package cfg.nodes;

public class FlagCfgNode  extends  CfgNode{
    public static String BEGIN_FLAG = "Begin";
    public static String END_FLAG = "End";

//    public FlagCfgNode(String endFlag) {
//    }

//    public static FlagCfgNode createBeginFlagCfgNode() {
//        return new FlagCfgNode(FlagCfgNode.BEGIN_FLAG);
//    }
//
//    public static FlagCfgNode createEndFlagCfgNode () {
//        return new FlagCfgNode(FlagCfgNode.END_FLAG);
//    }

//    boolean isNormalNode() {
//
//        return false;
//    }

    public static boolean isBeginNode (ICfgNode cfgNode) {
        if (cfgNode instanceof FlagCfgNode && cfgNode.getContent() == "Begin") return true;
        else return false;
    }

    public static boolean isEndNode (ICfgNode cfgNode) {
        if (cfgNode instanceof FlagCfgNode && cfgNode.getContent() == "End") return true;
        else return false;
    }
}
