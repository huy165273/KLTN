import cfg.ICFG;
import cfg.generation.CFGGeneration;
import cfg.generation.CFGGenerationSubCondition;
import cfg.generation.testpath.Check.FullTestpaths;
import cfg.generation.testpath.TestpathGeneration;
import mark.MethodMark;
import mark.MethodRefactor;
import mark.Utils;
import org.eclipse.jdt.core.dom.*;
import solvingConditions.Variable;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestDriverConcolic {
    private List<Method> methods;
    private String fileContent;
    private int deltaIndex;

    public TestDriverConcolic(String filePath) {
        this.methods = new ArrayList<>();
        try {
            this.deltaIndex = 0;
            this.fileContent = Utils.readFileContent(filePath);
            refactorClass();
            parse(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doTestDriver(String filepathMark, List<Variable> testcase) {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath.replace("\\", "/") + "/";
//        currentPath = currentPath.substring(0, currentPath.length() - 5);

        createMethodMarksFolder();
        StringBuilder testDrive = new StringBuilder();
        testDrive.append("import java.io.*;\n");
        testDrive.append("public class ").append("Test").append(" {");
        addMarkFunction(testDrive);
        for (Method method : methods) {
            testDrive.append(method.addStaticContentmethod());
        }
        testDrive.append("public static String TEST_PATH = \"" + filepathMark + "\";\n");
        testDrive.append("public static void main(String[] args) {\n");
        testDrive.append("   File file;\n");
        testDrive.append("   //TODO: Call test function\n");
        for (Method method : methods) {
            testDrive.append("   {\n");
            int countVariable = method.getParameters().size();
            testDrive.append("        file = new File(TEST_PATH);\n" +
                    "        if (file.exists()) {\n" +
                    "            file.delete();\n" +
                    "        }\n");


            for (int i = 0; i < method.getParameters().size(); i++) {
                testDrive.append("        ").append(method.getParameters().get(i)).append(";\n");
                Type type = ((SingleVariableDeclaration) method.getParameters().get(i)).getType();
                String name = ((SingleVariableDeclaration) method.getParameters().get(i)).getName().toString();
                String value = testcase.get(i).getValue();
                testDrive.append("        " + name + "= " + value + ";\n");
            }

            testDrive.append("        ").append(method.getMethodName()).append("(");
            for (int i = 0; i < countVariable; i++) {
                if (method.getParameters().get(i) instanceof SingleVariableDeclaration) {
                    SingleVariableDeclaration singleVariableDeclaration = (SingleVariableDeclaration) method.getParameters().get(i);
                    testDrive.append(singleVariableDeclaration.getName());
                    if (i != countVariable - 1) {
                        testDrive.append(", ");
                    }

                }
            }
            testDrive.append(");\n");
            testDrive.append("   }\n");
        }
        testDrive.append("}\n");
        testDrive.append("}\n");

        String newJavaCode = testDrive.toString();
        String filePath = currentPath + "result/Test.java";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(newJavaCode);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int compileTestDriver(){
        System.setProperty("java.home", "C:\\Program Files\\Java\\jdk-1.8");
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        System.setProperty("java.home", "C:\\Program Files\\Java\\jdk-1.8\\jre");

        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath.replace("\\", "/") + "/";
//        currentPath = currentPath.substring(0, currentPath.length() - 5);
        try {
            return compiler.run(null, null, null,currentPath +  "result/Test.java");
        } catch (Exception e) {
            System.out.println("Compilation failed");
        }
        return 0;
    }

    public boolean runTestDriver(){
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath.replace("\\", "/") + "/";
//        currentPath = currentPath.substring(0, currentPath.length() - 5);
        try{
            Process pc = Runtime.getRuntime().exec("java -cp " + currentPath +  "result Test");
            int exitcode = pc.waitFor();
            return exitcode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void refactorClass() {
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setSource(fileContent.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        cu.accept(new ASTVisitor() {
            public boolean visit(MethodDeclaration node) {
                MethodRefactor method = new MethodRefactor(node);
                method.refactorContentMethod();

                int startPosition = node.getStartPosition();
                int endPosition = startPosition + node.getLength();
                String methodString = fileContent.substring(startPosition + deltaIndex, endPosition + deltaIndex);
                fileContent = fileContent.replace(methodString, method.getContentMethod().toString());
                deltaIndex += method.getContentMethod().length() - methodString.length();
                return true;
            }
        });
    }

    private void parse(String fileContent) {
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setSource(fileContent.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        cu.accept(new ASTVisitor() {
            public boolean visit(MethodDeclaration node) {
                // Lay tham so truyen vao
                String currentPath = Paths.get("").toAbsolutePath().toString();

                CFGGeneration cfgGenerration = new CFGGeneration(node);
                ICFG cfg = cfgGenerration.generateCFG();
                CFGGenerationSubCondition cfgGenerrationforsubcondition = new CFGGenerationSubCondition(cfg, 1);

                ICFG cfgsubcondition = cfgGenerrationforsubcondition.generateCFG();
                TestpathGeneration testpathGen = new TestpathGeneration(cfgsubcondition);
                testpathGen.generateTestpaths();
                FullTestpaths testpaths = testpathGen.getPossibleTestpaths();

                StringBuilder contentMethod = new StringBuilder(node.toString());
                MethodMark methodMark = new MethodMark(cfgsubcondition, contentMethod);

                SimpleName methodName = node.getName();
                Type returnValueType = node.getReturnType2();
                List parameters = node.parameters();
                List modifiers = node.modifiers();

                Method method = new Method(methodName, modifiers, returnValueType, parameters, methodMark.addMark(), methodMark.getAllNodes(), testpaths);
                methods.add(method);
                return true;
            }
        });
    }

    public StringBuilder addMarkFunction(StringBuilder functionCode) {
        //Add marking function from TestDriver.java to functionCode
        functionCode.append("\n" +
                "public static boolean mark(String append) {\n" +
                "   try {\n" +
                "       FileWriter fileWriter = new FileWriter(TEST_PATH, true);\n" +
                "       BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);\n\n" +
                "       bufferedWriter.write(append + '\\n');\n\n" +
                "       bufferedWriter.close();\n" +
                "       fileWriter.close();\n" +
                "   } catch (IOException e) {\n" +
                "   e.printStackTrace();\n  }\n" +
                "   return true;\n" +
                "}\n");

        return functionCode;
    }

    private void createMethodMarksFolder() {
        String folderPath = "result/marks";
        Path tempPath;
        try {
            for (Method method : methods) {
                tempPath = Paths.get(folderPath + "/" + method.getMethodName() + "Marks");
                Files.createDirectories(tempPath);
            }
        } catch (Exception e) {
            System.err.println("Can't create folders");
        }
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }
}
