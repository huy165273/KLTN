package cfg.nodes;

import java.util.ArrayList;

public class AdditionalScopeCfgNode extends CfgNode{
    public static  String OPEN_SCOPE = "{";
    public static  String CLOSE_SCOPE= "}";

    public AdditionalScopeCfgNode(String content) {
        super(content);
    }

    public static CfgNode newCloseScope(ICfgNode branch) {
        AdditionalScopeCfgNode openScope = new AdditionalScopeCfgNode(CLOSE_SCOPE);
        openScope.setBranch(branch);
        return openScope;
    }

    public AdditionalScopeCfgNode newOpenScope(ArrayList<ICfgNode> branch) {
        AdditionalScopeCfgNode openScope = new AdditionalScopeCfgNode(this.OPEN_SCOPE);
        if (branch.size() == 1) {
            openScope.setBranch(branch.get(0));
        } else if (branch.size() > 1) {
//            ScopeCfgNode.logger.error("next brand of openScope greater than 1! this value must <= 1");
            System.out.println("next brand of openScope greater than 1! this value must <= 1");
        }
        return openScope;
    }

    public static AdditionalScopeCfgNode newOpenScope() {
        AdditionalScopeCfgNode openScope = new AdditionalScopeCfgNode(OPEN_SCOPE);
        return openScope;
    }

    public AdditionalScopeCfgNode newCloseScope(ArrayList<ICfgNode> branch) {
        AdditionalScopeCfgNode closeScope = new AdditionalScopeCfgNode(this.CLOSE_SCOPE);
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

    public static AdditionalScopeCfgNode newCloseScope() {
        AdditionalScopeCfgNode closeScope = new AdditionalScopeCfgNode(CLOSE_SCOPE);
        return closeScope;
    }
}
