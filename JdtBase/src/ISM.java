
import cfg.generation.testpath.Check.FullTestpaths;
import cfg.nodes.ICfgNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import solvingConditions.ConstraintSolver;
import solvingConditions.Variable;
import symbolicExecution.SymbolicExecutionTestpath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ISM {
    String folderPath;
    public ISM(String folderPath) {
        this.folderPath = folderPath;
    }

    public void generateTestcase () throws FileNotFoundException {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath.replace("\\", "/") + "/";
//        currentPath = currentPath.substring(0, currentPath.length() - 5);

        double startTime = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        double memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        List<Path> filePathList = getListFilepath(Paths.get(folderPath));
        TestDriver testDriver = new TestDriverISM(filePathList);
        testDriver.doTestDriver();

        testDriver.compileTestDriver();

        double totalCoveredC1 = 0;
        double totalCoveredC2 = 0;
        double totalCoveredC3 = 0;
        int totalTestcases = 0;
        DecimalFormat decimalFormat = new DecimalFormat( "#.##" );

        List<Method> methodList = testDriver.getMethods();
        JSONObject jsonObject = new JSONObject();
        for (Method method : methodList){
            JSONArray jsonMethod = new JSONArray();
            List<Variable> testcaseRandom = method.randomTestcase();
            method.getTestcases().add(testcaseRandom);
            String filepathMark = currentPath + "result/marks/" + method.getMethodName() + "Marks/TestDriverResult"
                    + (method.getAllTestcases().size() + method.getTestcases().size()) + ".txt";
            jsonMethod.add(addTestcaseJsonMethod(testcaseRandom, filepathMark));
            jsonObject.put(method.getMethodName(), jsonMethod);
            writeFileJson(currentPath + "result/Testcases.json", jsonObject);
            //            System.out.println("\nTest driver " + count);
            testDriver.runTestDriver();
            method.readMarkV1(false, false, false);

            boolean check = true;
            while (check){
                check = false;
                boolean checkC1 = true;
                boolean checkC2 = true;
                boolean checkC3 = true;
                jsonObject = new JSONObject();
                jsonMethod = new JSONArray();
                FullTestpaths testpaths = method.getTestpaths();
                while (!testpaths.isEmpty()) {
                    List<ICfgNode> testpath = testpaths.get(0).getAllCfgNodes();
                    checkC1 = method.getCoverC1().checkCoveredTestpath(testpath);
                    checkC2 = method.getCoverC2().checkCoveredTestpath(testpath);
                    checkC3 = method.getCoverC3().checkCoveredTestpath(testpath);
                    if(!checkC1 || !checkC2 || !checkC3){
                        String currentPath1 = Paths.get("").toAbsolutePath().toString();
                        SymbolicExecutionTestpath sym = new SymbolicExecutionTestpath(testpaths.get(0).getAllCfgNodes(), method.getParameters());
                        List<Expression> conditions = sym.symbolicExecution();
                        if(!conditions.isEmpty() && !conditions.get(0).toString().equals("false")){
                            ConstraintSolver constraintSolver = new ConstraintSolver(conditions, method.getParameters(), currentPath1);
                            List<Variable> result = constraintSolver.solveConditions();
                            List<Variable> testcase = new ArrayList<>();
                            for(Object parameter: method.getParameters()){
                                for(Variable variable : result){
                                    if(((SingleVariableDeclaration) parameter).getName().toString().equals(variable.getName())){
                                        variable.setType(((SingleVariableDeclaration) parameter).getType().toString());
                                        testcase.add(variable);
                                    }
                                }
                            }

                            if(!testcase.isEmpty()){
                                method.getTestcases().add(testcase);
                                filepathMark = currentPath + "result/marks/" + method.getMethodName() + "Marks/TestDriverResult"
                                        + (method.getAllTestcases().size() + method.getTestcases().size()) + ".txt";
                                jsonMethod.add(addTestcaseJsonMethod(testcase, filepathMark));
                                jsonObject.put(method.getMethodName(), jsonMethod);

                                check = true;
                                testpaths.remove(0);
                                break;
                            }
                        }
                    }
                    testpaths.remove(0);
                }

                if(check){
                    writeFileJson(currentPath + "result/Testcases.json", jsonObject);
                    //                System.out.println("\nTest driver " + count);
                    testDriver.runTestDriver();
                    method.readMarkV1(checkC1, checkC2, checkC3);
                }
            }

            System.out.println("Unit test: " + method.getMethodName());
            System.out.println("Testcase: " + method.getParameters());
            System.out.println("All testcase of C1: " + method.getCoverC1().getAllTestcasesString());
            System.out.println("Covered C1: " + decimalFormat.format((double)method.getCoverC1().numberCovered() / method.getCoverC1().numberAllCovered() * 100) + "%");
            System.out.println("All testcase of C2: " + method.getCoverC2().getAllTestcasesString());
            System.out.println("Covered C2: " + decimalFormat.format((double)method.getCoverC2().numberCovered() / method.getCoverC2().numberAllCovered() * 100) + "%");
            System.out.println("All testcase of C3: " + method.getCoverC3().getAllTestcasesString());
            System.out.println("Covered C3: " + decimalFormat.format((double)method.getCoverC3().numberCovered() / method.getCoverC3().numberAllCovered() * 100) + "%");
            System.out.println();

            totalCoveredC1 += (double)method.getCoverC1().numberCovered() / method.getCoverC1().numberAllCovered();
            totalCoveredC2 += (double)method.getCoverC2().numberCovered() / method.getCoverC2().numberAllCovered();
            totalCoveredC3 += (double)method.getCoverC3().numberCovered() / method.getCoverC3().numberAllCovered();
            totalTestcases += method.getAllTestcases().size();

        }


        System.out.println();
        System.out.println("Total number of test files: " + methodList.size());
        System.out.println("Total number testcase in project: " + totalTestcases);
        double endTime = System.currentTimeMillis(); // Kết thúc đo thời gian
        double duration = (endTime - startTime); // Tính thời gian chạy
        System.out.println("Run: " + duration + " milliseconds");
        double memoryAfter = runtime.totalMemory() - runtime.freeMemory(); // Bộ nhớ sau khi chạy
        double memoryUsed = memoryAfter - memoryBefore; // Bộ nhớ đã sử dụng
        System.out.println("Used memory: " + decimalFormat.format(memoryUsed / 1048576) + "MB");

        System.out.println("Average covered of C1: " + decimalFormat.format(totalCoveredC1 /methodList.size() * 100) + "%");
        System.out.println("Average covered of C2: " + decimalFormat.format(totalCoveredC2/methodList.size() * 100) + "%");
        System.out.println("Average covered of C3: " + decimalFormat.format(totalCoveredC3/methodList.size() * 100) + "%" );
    }

    public static List<Path> getListFilepath(Path folderPath) {
        List<Path> javaFiles = new ArrayList<>();

        try {
            Files.walk(folderPath)
                    .filter(path -> Files.isRegularFile(path) && path.toString().endsWith(".java"))
                    .forEach(javaFiles::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return javaFiles;
    }

    public static JSONObject addTestcaseJsonMethod(List<Variable> testcase, String filepath) {
        JSONObject jsonTestpath = new JSONObject();
        JSONArray jsonTestcase = new JSONArray();
        for (Variable variable : testcase){
            JSONObject jsonVar = new JSONObject();
            jsonVar.put("name", variable.getName());
            jsonVar.put("type", variable.getType());
            jsonVar.put("value", variable.getValue());
            jsonTestcase.add(jsonVar);
        }
        jsonTestpath.put("testcase", jsonTestcase);
        jsonTestpath.put("filepath", filepath);
        return jsonTestpath;
    }

    public static void writeFileJson(String filepath, JSONObject jsonObject){
        File jsonFile = new File(filepath);
        if(jsonFile.exists()){
            jsonFile.delete();
        }

        // Write JSON object to file
        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
            fileWriter.write(String.valueOf(jsonObject));
        } catch (Exception e){
            e.getMessage();
        }
    }
}
