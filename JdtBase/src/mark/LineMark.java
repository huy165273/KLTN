package mark;

public class LineMark {
    private double id = 0;
    private int lineInFunction = 0;
    private int offset = 0;
    private StringBuilder statement;
    private String condition;

    public LineMark(double id, int lineInFunction, int offset, StringBuilder statement) {
        this.id = id;
        this.lineInFunction = lineInFunction;
        this.offset = offset;
        this.statement = statement;
    }

    public LineMark(double id, int lineInFunction, int offset, StringBuilder statement, String condition) {
        this.id = id;
        this.lineInFunction = lineInFunction;
        this.offset = offset;
        this.statement = statement;
        this.condition = condition;
    }

    public LineMark(int lineInFunction, int offset) {
        this.lineInFunction = lineInFunction;
        this.offset = offset;
    }

    public LineMark() {
    }

    public StringBuilder addMark() {
        StringBuilder mark = new StringBuilder("mark(\"");
        if (id != 0) {
            mark.append("id$").append(id);
        }
        if (lineInFunction != 0) {
            mark.append("#line-in-function$").append(lineInFunction);
        }
        if (offset != 0) {
            mark.append("#offset$").append(offset);
        }
        if (condition != null) {
            mark.append("#condition$").append(condition);
        }
        if (statement != null) {
            StringBuilder newStatement = new StringBuilder(statement);
            if(newStatement.charAt(newStatement.length() - 1) == '\n'){
                newStatement.deleteCharAt(newStatement.length() - 1);
            }
            newStatement.toString();
            mark.append("#statement$").append(newStatement.toString().replace("\"", "\\\""));
        }
        mark.append("\")");
        return mark;
    }
    public int getLineInFunction() {
        return lineInFunction;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public StringBuilder getStatement() {
        return statement;
    }

    public void setStatement(StringBuilder statement) {
        this.statement = statement;
    }

    public void setLineInFunction(int lineInFunction) {
        this.lineInFunction = lineInFunction;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }
}
