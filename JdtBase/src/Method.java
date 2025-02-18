import cfg.generation.testpath.Check.FullTestpaths;
import cfg.nodes.ICfgNode;
import cover.Cover;
import cover.CoverC1;
import cover.CoverC2;
import cover.CoverC3;
import org.eclipse.jdt.core.dom.*;
import solvingConditions.Variable;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Method {
    private List modifiers;
    private Type returnValueType;
    private SimpleName methodName;
    private List parameters;
    private StringBuilder contentmethod;
    private List<ICfgNode> allNodes;
    private FullTestpaths testpaths;
    private List<List<Variable>> testcases;
    private List<List<Variable>> allTestcases;
    private Cover coverC1;
    private Cover coverC2;
    private Cover coverC3;

    public Method(SimpleName name, List modifiers, Type returnValueType,
                  List parameters, StringBuilder contentmethod, List<ICfgNode> allNodes, FullTestpaths testpaths) {
        this.modifiers = modifiers;
        this.returnValueType = returnValueType;
        this.methodName = name;
        this.parameters = parameters;
        this.contentmethod = contentmethod;
        this.allNodes = allNodes;
        this.testpaths = testpaths;
        this.testcases = new ArrayList<>();
        this.allTestcases = new ArrayList<>();
        coverC1 = new CoverC1(allNodes);
        coverC2 = new CoverC2(allNodes);
        coverC3 = new CoverC3(allNodes);
    }

    public StringBuilder addStaticContentmethod(){
        StringBuilder content = new StringBuilder(contentmethod);
        int positionStatic = 0;
        boolean checkStatic = false;
        for (Object modifier : modifiers) {
            if (modifier.toString().equals("static")) {
                checkStatic = true;
                break;
            }
        }
        if(!checkStatic){
            if(!modifiers.isEmpty()){
                positionStatic += modifiers.get(0).toString().length();
            }
            content = content.replace(positionStatic, positionStatic, " static");
        }
        return content;
    }

    public List<Variable> randomTestcase(){
        List<Variable> testcase = new ArrayList<>();
//        System.out.println("Testcase random:");
        for (Object o : parameters) {
            SingleVariableDeclaration parameter = (SingleVariableDeclaration) o;
            Type type = parameter.getType();
            SimpleName name = parameter.getName();
            Random random = new Random();
            String var = null;
            // random các biến ban đầu < 20
            switch (type.toString()) {
                case "double":
                    var = String.valueOf(random.nextDouble() * 20);
//                    System.out.println("double " + name + " = " + var);
                    break;
                case "float":
                    var = String.valueOf(random.nextFloat() * 20);
//                    System.out.println("float " + name + " = " + var);
                    break;
                case "long":
                    var = String.valueOf(random.nextInt(20));
//                    System.out.println("long " + name + " = " + var);
                    break;
                case "int":
                    var = String.valueOf(random.nextInt(20));
//                    System.out.println("int " + name + " = " + var);
                    break;
                case "short":
                    var = String.valueOf(random.nextInt(20));
//                    System.out.println("short " + name + " = " + var);
                    break;
                case "byte":
                    var = String.valueOf(random.nextInt(20));
//                    System.out.println("byte " + name + " = " + var);
                    break;
                case "boolean":
                    var = String.valueOf(random.nextBoolean());
//                    System.out.println("boolean " + name + " = " + var);
                    break;
                case "char":
                    var = String.valueOf((char) (random.nextInt(95) + 32));
//                    System.out.println("char " + name + " = " + var);
                    break;
                case "String":
                    String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z

                    String alphaUpperCase = alpha.toUpperCase(); // A-Z

                    String digits = "0123456789"; // 0-9

                    String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
                    StringBuilder sb = new StringBuilder();
                    int numberOfCharactor = random.nextInt(15);
                    for (int j = 0; j < numberOfCharactor; j++) {
                        int number = random.nextInt(ALPHA_NUMERIC.length());
                        char ch = ALPHA_NUMERIC.charAt(number);
                        sb.append(ch);
                    }
                    var = sb.toString();
//                    System.out.println("String " + name + " = " + var);
                    break;
            }
            if (var != null) {
                testcase.add(new Variable(name.toString(), type.toString(), var));
            }
        }
        return testcase;
    }

    public void readMarkV1(boolean checkC1, boolean checkC2, boolean checkC3) throws FileNotFoundException {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath.replace("\\", "/") + "/";
//        currentPath = currentPath.substring(0, currentPath.length() - 5);

        List<String> pathMarks = new ArrayList<>();
        for(int i = 0; i < testcases.size(); i ++){
            pathMarks.add(currentPath + "result/marks/" + methodName + "Marks/TestDriverResult" + (allTestcases.size() + i + 1) + ".txt");
        }
//        System.out.println("File mark: " + pathMarks);
        if(!checkC1){
            coverC1.readMark(pathMarks);
            coverC1.getAllTestcases().addAll(testcases);
        }
        if (!checkC2){
            coverC2.readMark(pathMarks);
            coverC2.getAllTestcases().addAll(testcases);
        }
        if (!checkC3){
            coverC3.readMark(pathMarks);
            coverC3.getAllTestcases().addAll(testcases);
        }
        allTestcases.addAll(testcases);
//        System.out.println(testcases);
//        System.out.println("C1: " + coverC1.numberCovered() + "/" + coverC1.numberAllCovered());
//        System.out.println("C2: " + coverC2.numberCovered() + "/" + coverC2.numberAllCovered());
//        System.out.println("C3: " + coverC3.numberCovered() + "/" + coverC3.numberAllCovered());

        testcases = new ArrayList<>();
    }

    public void readMarkV2() throws FileNotFoundException {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath.replace("\\", "/") + "/";
//        currentPath = currentPath.substring(0, currentPath.length() - 3);

        List<String> pathMarksC1 = new ArrayList<>();
        List<String> pathMarksC2 = new ArrayList<>();
        List<String> pathMarksC3 = new ArrayList<>();

        for(int i = 0; i < coverC1.getAllTestcases().size(); i ++){
            int index = testcases.indexOf(coverC1.getAllTestcases().get(i));
            if(index != -1){
                pathMarksC1.add(currentPath + "result/marks/" + methodName + "Marks/TestDriverResult" + (index + 1) + methodName + ".txt");
            }
        }
        for(int i = 0; i < coverC2.getAllTestcases().size(); i ++){
            int index = testcases.indexOf(coverC2.getAllTestcases().get(i));
            if(index != -1){
                pathMarksC2.add(currentPath + "result/marks/" + methodName + "Marks/TestDriverResult" + (index + 1) + methodName + ".txt");
            }
        }
        for(int i = 0; i < coverC3.getAllTestcases().size(); i ++){
            int index = testcases.indexOf(coverC3.getAllTestcases().get(i));
            if(index != -1){
                pathMarksC3.add(currentPath + "result/marks/" + methodName + "Marks/TestDriverResult" + (index + 1) + methodName + ".txt");
            }
        }

        coverC1.readMark(pathMarksC1);
        coverC2.readMark(pathMarksC2);
        coverC3.readMark(pathMarksC3);

        System.out.println();
        System.out.println("Method " + methodName);

        System.out.println("File mark C1: " + pathMarksC1);
        System.out.println("All testcase method " + methodName + " C1: " + coverC1.getAllTestcases());
        System.out.println("C1: " + coverC1.numberCovered() + "/" + coverC1.numberAllCovered());

        System.out.println("File mark C2: " + pathMarksC2);
        System.out.println("All testcase method " + methodName + " C2: " + coverC2.getAllTestcases());
        System.out.println("C2: " + coverC2.numberCovered() + "/" + coverC2.numberAllCovered());

        System.out.println("File mark C3: " + pathMarksC2);
        System.out.println("All testcase method " + methodName + " C3: " + coverC3.getAllTestcases());
        System.out.println("C3: " + coverC3.numberCovered() + "/" + coverC3.numberAllCovered());

        allTestcases.addAll(testcases);
        testcases = new ArrayList<>();
    }

    public StringBuilder getContentmethod() {
        return contentmethod;
    }

    public void setContentmethod(StringBuilder contentmethod) {
        this.contentmethod = contentmethod;
    }

    public List getModifiers() {
        return modifiers;
    }

    public void setModifiers(List modifiers) {
        this.modifiers = modifiers;
    }
    public Type getReturnValueType() {
        return returnValueType;
    }

    public void setReturnValueType(Type returnValueType) {
        this.returnValueType = returnValueType;
    }

    public SimpleName getMethodName() {
        return methodName;
    }

    public void setMethodName(SimpleName methodName) {
        this.methodName = methodName;
    }

    public List getParameters() {
        return parameters;
    }

    public void setParameters(List parameters) {
        this.parameters = parameters;
    }

    public List<List<Variable>> getTestcases() {
        return testcases;
    }

    public void setTestcases(List<List<Variable>> testcases) {
        this.testcases = testcases;
    }

    public void setAllNodes(List<ICfgNode> allNodes) {
        this.allNodes = allNodes;
    }

    public List<ICfgNode> getAllNodes() {
        return allNodes;
    }

    public FullTestpaths getTestpaths() {
        return testpaths;
    }

    public void setTestpaths(FullTestpaths testpaths) {
        this.testpaths = testpaths;
    }

    public List<List<Variable>> getAllTestcases() {
        return allTestcases;
    }

    public void setAllTestcases(List<List<Variable>> allTestcases) {
        this.allTestcases = allTestcases;
    }

    public Cover getCoverC1() {
        return coverC1;
    }

    public void setCoverC1(Cover coverC1) {
        this.coverC1 = coverC1;
    }

    public Cover getCoverC2() {
        return coverC2;
    }

    public void setCoverC2(Cover coverC2) {
        this.coverC2 = coverC2;
    }

    public Cover getCoverC3() {
        return coverC3;
    }

    public void setCoverC3(Cover coverC3) {
        this.coverC3 = coverC3;
    }

}

