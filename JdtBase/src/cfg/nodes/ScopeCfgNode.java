package cfg.nodes;

import java.util.ArrayList;

public class ScopeCfgNode extends CfgNode {
//    public static logger = new MyLogger("ScopeCfgNode");

    public static String OPEN_SCOPE = "{";
    public static String CLOSE_SCOPE = "}";

    public ScopeCfgNode(String content) {
        super(content);
    }

    public static CfgNode newCloseScope(ICfgNode branch) {
        ScopeCfgNode openScope = new ScopeCfgNode(CLOSE_SCOPE);
        openScope.setBranch(branch);

        return openScope;
    }

    public ScopeCfgNode newOpenScope(ArrayList<ICfgNode> branch) {
        ScopeCfgNode openScope = new ScopeCfgNode(this.OPEN_SCOPE);
        if (branch.size() == 1) {
            openScope.setBranch(branch.get(0));
        } else if (branch.size() > 1) {
//            ScopeCfgNode.logger.error("next brand of openScope greater than 1! this value must <= 1");
            System.out.println("next brand of openScope greater than 1! this value must <= 1");
        }
        return openScope;
    }

    public static ScopeCfgNode newOpenScope() {
        ScopeCfgNode openScope = new ScopeCfgNode(OPEN_SCOPE);
        return openScope;
    }

    public ScopeCfgNode newCloseScope(ArrayList<ICfgNode> branch) {
        ScopeCfgNode closeScope = new ScopeCfgNode(this.CLOSE_SCOPE);
        if (branch.size() == 1) {
            closeScope.setBranch(branch.get(0));
        } else if (branch.size() > 1) {
            try {
                throw new Exception("next brand of closeScope greater than 1! this value must <= 1");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return closeScope;
    }

    public static ScopeCfgNode newCloseScope() {
        ScopeCfgNode closeScope = new ScopeCfgNode(CLOSE_SCOPE);
        return closeScope;
    }
}
