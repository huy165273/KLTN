package solvingConditions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class Z3Solving {
    public String solve(String filePath) throws IOException, InterruptedException {
        Path absolutePath = Paths.get("").toAbsolutePath();
        String addPath = absolutePath + "\\JdtBase\\src\\Z3-win\\bin\\z3.exe";
        String commandLine = addPath + " -smt2 " + "\"" + filePath + "\"";
//        System.out.println(commandLine);
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", commandLine);
        Process process = processBuilder.start();
//        long pid = process.pid();
//        System.out.println("PID: " + pid);
        Boolean finished = process.waitFor(10, TimeUnit.SECONDS);

        if (finished) {
            // Tiến trình hoàn thành trong thời gian chờ
//            System.out.println("Process completed successfully");
            int exitCode = process.exitValue();
//            System.out.println("Exit Code: " + exitCode);
        } else {
            // Tiến trình bị treo, cần ngắt
//            System.out.println("Process is hanging. Destroying process...");
            process.destroyForcibly();

            killZ3Process();

            // Kiểm tra nếu cần thiết phải ngắt mạnh
            if (!process.waitFor(5, TimeUnit.SECONDS)) {
                process.destroyForcibly();
//                System.out.println("Process destroyed forcibly");
            }

//            System.out.println("Process terminated");
            return "unsat\n";
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String result = "", line = "";
        while ((line = in.readLine()) != null) {
            result += line + "\n";
        }
        //Display Error if exists
        if (process.getErrorStream().available() > 0) {
            BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String err = "";
            boolean hasError = false;
            while ((err = error.readLine()) != null) {
                hasError = true;
//                System.out.println(err);
            }
        }
        return result;
    }

    private static void killZ3Process() {
        try {
            // Liệt kê các tiến trình và tìm `z3.exe`
            Process taskListProcess = new ProcessBuilder("cmd.exe", "/c", "tasklist /FI \"IMAGENAME eq z3.exe\"").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(taskListProcess.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("z3.exe")) {
                    // Lấy PID từ dòng này
                    String[] parts = line.trim().split("\\s+");
                    String pid = parts[1]; // PID thường là phần tử thứ hai
                    // Ngắt tiến trình `z3.exe` bằng PID
                    new ProcessBuilder("cmd.exe", "/c", "taskkill /F /PID " + pid).start();
//                    System.out.println("Killed process z3.exe with PID: " + pid);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
