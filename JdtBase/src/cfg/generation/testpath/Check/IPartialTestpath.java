package cfg.generation.testpath.Check;

public interface IPartialTestpath extends ITestpath {
    @Override
    String getFullPath();

    boolean getFinalConditionType();


    void setFinalConditionType(boolean finalConditionType);
}
