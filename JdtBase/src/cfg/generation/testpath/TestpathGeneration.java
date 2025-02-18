package cfg.generation.testpath;

import cfg.ICFG;
import cfg.generation.testpath.Check.FullTestpath;
import cfg.generation.testpath.Check.FullTestpaths;
import cfg.generation.testpath.Check.ITestpath;
import cfg.nodes.FlagCfgNode;
import cfg.nodes.ICfgNode;
import cfg.nodes.LoopConditionCfgNode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TestpathGeneration implements ITestpathGeneration {

    private ICFG _cfg;
    private ArrayList<Testpath> _testpaths;
    private TestpathGenerationConfig _config;
    private int maxIterationsforEachLoop;
    private long totalRunningTime = 0;// ms
    private boolean checkLoop = false;// ms

    private FullTestpaths possibleTestpaths = new FullTestpaths();


    public TestpathGeneration(ICFG cfg, TestpathGenerationConfig config) {
        this._cfg = cfg;
        this._config = config;
    }

    public TestpathGeneration(ICFG cfg, int maxloop) {
        this._cfg = cfg;
        this._config = null;
        this.maxIterationsforEachLoop = maxloop;
    }

    /*
    OLD
    * */
    public TestpathGeneration(ICFG cfg) {
        this._cfg = cfg;
        this._config = null;
    }
    private void traverseCFG(ICfgNode stm, FullTestpath tp, FullTestpaths testpaths) throws Exception {
        tp.add(stm);
        if (FlagCfgNode.isEndNode(stm)) {
            testpaths.add((FullTestpath) tp.clone());
            tp.remove(tp.size() - 1);
        } else {
            ICfgNode trueNode = stm.getTrueNode();
            ICfgNode falseNode = stm.getFalseNode();

            if (stm.isCondition())

                if (stm instanceof LoopConditionCfgNode) {

                    int currentIterations = tp.count(trueNode);
                    if (currentIterations < 2) {
                        traverseCFG(falseNode, tp, testpaths);
                        traverseCFG(trueNode, tp, testpaths);

                    } else
                        traverseCFG(falseNode, tp, testpaths);
                } else {
                    traverseCFG(falseNode, tp, testpaths);
                    traverseCFG(trueNode, tp, testpaths);
                }
            else
                traverseCFG(trueNode, tp, testpaths);
            tp.remove(tp.size() - 1);
        }
    }
    /*
    OLD
    * */





    public void generateTestpaths() {
        Date startTime = Calendar.getInstance().getTime();
        FullTestpaths testpaths_ = new FullTestpaths();

        ICfgNode beginNode = this._cfg.getBeginNode();
        FullTestpath initialTestpath = new FullTestpath();
        initialTestpath.setFunctionNode(this._cfg.getFunctionNode());
        try {
            traverseCFG(beginNode, initialTestpath, testpaths_);
//            traverseCFGforIteraction(beginNode, initialTestpath, testpaths_);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (ITestpath tp : testpaths_)
            tp.setFunctionNode(this._cfg.getFunctionNode());

        possibleTestpaths = testpaths_;

        // Calculate the running time
        Date end = Calendar.getInstance().getTime();
        totalRunningTime = end.getTime() - startTime.getTime();
//        logger.debug("Total running time: " + totalRunningTime + " ms");
//        logger.debug("Solving time: " + solvingTime + " ms");
//        logger.debug("Number of solving calls: " + numberOfSolvingCalls + " ms");
//        logger.debug(
//                "Number of solving calls that does not have solution: " + numberOfSolvingCallsThatNoSolution + " ms");
    }

    private void traverseCFGforIteraction(ICfgNode stm, FullTestpath tp, FullTestpaths testpaths) throws Exception {
        tp.add(stm);
        if (FlagCfgNode.isEndNode(stm)) {
            if(checkLoop) {
                testpaths.add((FullTestpath) tp.clone());
                checkLoop = false;
            }
            tp.remove(tp.size() - 1);
        } else {
            ICfgNode trueNode = stm.getTrueNode();
            ICfgNode falseNode = stm.getFalseNode();

            if (stm.isCondition()) {
                if (stm instanceof LoopConditionCfgNode) {

                    int currentIterations = tp.count(trueNode);

                    if (currentIterations < maxIterationsforEachLoop) {
                        traverseCFGforIteraction(falseNode, tp, testpaths);
                        traverseCFGforIteraction(trueNode, tp, testpaths);
                    } else {
                        checkLoop = true;
                        traverseCFGforIteraction(falseNode, tp, testpaths);
//                        traverseCFGforIteraction(trueNode, tp, testpaths);
                    }
                } else {
                    traverseCFGforIteraction(falseNode, tp, testpaths);
                    traverseCFGforIteraction(trueNode, tp, testpaths);
                }
            } else {
                traverseCFGforIteraction(trueNode, tp, testpaths);
            }
            tp.remove(tp.size() - 1);
        }
    }

    private void filter(FullTestpaths testpaths) throws Exception {

    }


    //    public ArrayList<ITestpath> getPossibleTestpaths() {


    @Override
    public FullTestpaths getPossibleTestpaths() {
        return possibleTestpaths;
    }

    public ICFG getCfg() {
        return this._cfg;
    }

    public void setCfg(ICFG value) {
        this._cfg = value;
    }

    public ArrayList<Testpath> getTestpaths() {
        return this._testpaths;
    }

    public void setTestpaths(ArrayList<Testpath> value) {
        this._testpaths = value;
    }

    public TestpathGenerationConfig getConfig() {
        return this._config;
    }

    public void setConfig(TestpathGenerationConfig value) {
        this._config = value;
    }
}



