package mark;

import java.util.ArrayList;
import java.util.List;

public class Index {
    private int indexInContentMethod;
    private int indexInContentMethodMark;
    private int indexContinueInMethodMark;
    private List<Integer> indexForInContentMethods;
    public Index (){
        this.indexInContentMethod = 0;
        this.indexInContentMethodMark = 0;
        this.indexContinueInMethodMark = 0;
        this.indexForInContentMethods = new ArrayList<>();
    }

    public List<Integer> getIndexForInContentMethods() {
        return indexForInContentMethods;
    }

    public int getIndexInContentMethod() {
        return indexInContentMethod;
    }

    public int getIndexInContentMethodMark() {
        return indexInContentMethodMark;
    }

    public void setIndexForInContentMethods(List<Integer> indexForInContentMethods) {
        this.indexForInContentMethods = indexForInContentMethods;
    }

    public void setIndexInContentMethod(int indexInContentMethod) {
        this.indexInContentMethod = indexInContentMethod;
    }

    public void setIndexInContentMethodMark(int indexInContentMethodMark) {
        this.indexInContentMethodMark = indexInContentMethodMark;
    }

    public void setIndexContinueInMethodMark(int indexContinueInMethodMark) {
        this.indexContinueInMethodMark = indexContinueInMethodMark;
    }

    public int getIndexContinueInMethodMark() {
        return indexContinueInMethodMark;
    }
}
