import cfg.ICFG;
import cfg.generation.CFGGeneration;
import cfg.generation.CFGGenerationSubCondition;
import cfg.generation.testpath.Check.FullTestpaths;
import cfg.generation.testpath.TestpathGeneration;
import mark.MethodMark;
import mark.MethodRefactor;
import mark.Utils;
import org.eclipse.jdt.core.dom.*;

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

public class TestDriverISM implements TestDriver {
    private List<Method> methods;
    private String fileContent;
    private int deltaIndex;
    public TestDriverISM(List<Path> filePathList){
        this.methods = new ArrayList<>();
        try {
            for (Path filePath : filePathList) {
                this.deltaIndex = 0;
                this.fileContent = Utils.readFileContent(filePath.toString());
                refactorClass();
                parse(fileContent);
            }
//            for (Method method : this.methods) {
//                System.out.println(method.getMethodName().toString());
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TestDriverISM(String filePath){
        this.methods = new ArrayList<>();
        try {
            this.deltaIndex = 0;
            this.fileContent = Utils.readFileContent(filePath);
            refactorClass();
            parse(fileContent);
//            for (Method method : this.methods) {
//                System.out.println(method.getMethodName().toString());
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doTestDriver() {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath.replace("\\", "/") + "/";
        createMethodMarksFolder();
        StringBuilder testDrive = new StringBuilder();
        testDrive.append("package result;\n\n");
        testDrive.append("import java.io.*;\n");
        testDrive.append("import json.JSONArray;\n");
        testDrive.append("import json.JSONObject;\n");
        testDrive.append("import json.parser.JSONParser;\n\n");
        testDrive.append("public class ").append("Test").append(" {");
        addMarkFunction(testDrive);
        for (Method method : methods) {
            testDrive.append( method.addStaticContentmethod());
        }
        testDrive.append("public static String TEST_PATH = \"\";\n");
        testDrive.append("public static void main(String[] args) {\n");
        testDrive.append("   File file;\n");
//        functionCode.append("   JSONParser jsonParser = new JSONParser();\n");
        testDrive.append("   try (FileReader reader = new FileReader(\"" + currentPath + "JdtBase/src/result/Testcases.json\")) {\n");
        testDrive.append("   Object obj = (new JSONParser()).parse(reader);\n");
        testDrive.append("   JSONObject jsonObject = (JSONObject) obj;\n");
        testDrive.append("   //TODO: Call test function\n");
        for (Method method : methods){
            testDrive.append("   {\n");
            int countVariable = method.getParameters().size();
//            for (Object parameter : method.getParameters()){
//                testDrive.append("      ").append(parameter).append(";\n");
//            }
            testDrive.append("      JSONArray jsonMethod = ((JSONArray) jsonObject.get(\""+ method.getMethodName() + "\"));\n");
            testDrive.append("      if(jsonMethod != null){\n");
            testDrive.append("         for(Object testpath : jsonMethod){\n");
            testDrive.append("            TEST_PATH = ((JSONObject) testpath).get(\"filepath\").toString();\n");
            testDrive.append("            file = new File(TEST_PATH);\n" +
                    "            if (file.exists()) {\n" +
                    "               file.delete();\n" +
                    "            }\n");


            testDrive.append("            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get(\"testcase\");\n");
            for (int i = 0; i < method.getParameters().size(); i++){
                testDrive.append("            ").append(method.getParameters().get(i)).append(";\n");
                Type type = ((SingleVariableDeclaration)  method.getParameters().get(i)).getType();
                String name = ((SingleVariableDeclaration)  method.getParameters().get(i)).getName().toString();
                testDrive.append("            " + name);
                switch (type.toString()) {
                    case "double":
                        testDrive.append(" = Double.parseDouble(((JSONObject) jsonTestcases.get(" + i + ")).get(\"value\").toString());\n");
                        break;
                    case "float":
                        testDrive.append(" = Float.parseFloat(((JSONObject) jsonTestcases.get(" + i + ")).get(\"value\").toString());\n");
                        break;
                    case "long":
                        testDrive.append(" = Long.parseLong(((JSONObject) jsonTestcases.get(" + i + ")).get(\"value\").toString());\n");
                        break;
                    case "int":
                        testDrive.append(" = Integer.parseInt(((JSONObject) jsonTestcases.get(" + i + ")).get(\"value\").toString());\n");
                        break;
                    case "short":
                        testDrive.append(" = Short.parseShort(((JSONObject) jsonTestcases.get(" + i + ")).get(\"value\").toString());\n");
                        break;
                    case "byte":
                        testDrive.append(" = Byte.parseByte(((JSONObject) jsonTestcases.get(" + i + ")).get(\"value\").toString());\n");
                        break;
                    case "boolean":
                        testDrive.append(" = Boolean.parseBoolean(((JSONObject) jsonTestcases.get(" + i + ")).get(\"value\").toString());\n");
                        break;
                    case "char":
                        testDrive.append(" = ((JSONObject) jsonTestcases.get(" + i + ")).get(\"value\").toString().charAt(0);\n");
                        break;
                    case "String":
                        testDrive.append(" = ((JSONObject) jsonTestcases.get(" + i + ")).get(\"value\").toString();\n");
                        break;
                }
            }
//            for(Variable variable : testcase){
//                functionCode.append("   ").append(variable.getName()).append(" = ");
//                if(variable.getType().equals("char")){
//                    functionCode.append("'").append(variable.getValue()).append("'");
//                } else if(variable.getType().equals("String")){
//                    functionCode.append("\"").append(variable.getValue()).append("\"");
//                } else if(variable.getType().equals("float")){
//                    functionCode.append(variable.getValue()).append("f");
//                } else if(variable.getType().equals("double")){
//                    functionCode.append(variable.getValue()).append("d");
//                } else if(variable.getType().equals("long")){
//                    functionCode.append(variable.getValue()).append("L");
//                } else {
//                    functionCode.append(variable.getValue());
//                }
//                functionCode.append(";\n");
//            }
            testDrive.append("            ").append(method.getMethodName()).append("(");
            for (int i = 0; i < countVariable; i++) {
                if(method.getParameters().get(i) instanceof  SingleVariableDeclaration){
                    SingleVariableDeclaration singleVariableDeclaration = (SingleVariableDeclaration) method.getParameters().get(i);
                    testDrive.append(singleVariableDeclaration.getName());
                    if (i != countVariable - 1) {
                        testDrive.append(", ");
                    }

                }
            }
            testDrive.append(");\n");
            testDrive.append("         }\n");
            testDrive.append("      }\n");
            testDrive.append("   }\n");
        }
        testDrive.append("   } catch (Exception e) {\n");
        testDrive.append("      e.printStackTrace();\n");
        testDrive.append("   }\n");
        testDrive.append("}\n");
        testDrive.append("}\n");

        String newJavaCode = testDrive.toString();
        String filePath =currentPath + "JdtBase/src/result/Test.java";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(newJavaCode);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int compileTestDriver(){
//        System.setProperty("java.home", "C:\\Program Files\\Java\\jdk-1.8");
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//        System.setProperty("java.home", "C:\\Program Files\\Java\\jdk-1.8\\jre");

        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath.replace("\\", "/") + "/";
        try {
            return compiler.run(null, null, null,currentPath +  "JdtBase/src/result/Test.java");
        } catch (Exception e) {
            System.out.println("Compilation failed");
        }
        return 0;
    }

    public boolean runTestDriver(){
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath.replace("\\", "/") + "/";
        try{
            Process pc = Runtime.getRuntime().exec("java -cp " + currentPath +  "JdtBase/src/result Test");
            int exitcode = pc.waitFor();
            return exitcode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void refactorClass(){
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
                String methodString = fileContent.substring(startPosition + deltaIndex,endPosition + deltaIndex);
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
                CFGGenerationSubCondition cfgGenerrationforsubcondition = new CFGGenerationSubCondition(cfg,1);

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
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath.replace("\\", "/") + "/";
//        currentPath = currentPath.substring(0, currentPath.length() - 5);

        String folderPath = currentPath + "JdtBase/src/result/marks";
        Path tempPath;
        try {
            for(Method method : methods){
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
