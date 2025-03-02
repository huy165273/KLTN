package result;

import java.io.*;
import json.JSONArray;
import json.JSONObject;
import json.parser.JSONParser;

public class Test {
public static boolean mark(String append) {
   try {
       FileWriter fileWriter = new FileWriter(TEST_PATH, true);
       BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

       bufferedWriter.write(append + '\n');

       bufferedWriter.close();
       fileWriter.close();
   } catch (IOException e) {
   e.printStackTrace();
  }
   return true;
}
public static char grade(int averageGrade){
  if ((mark("id$37.0#line-in-function$2#offset$50#condition$falseNode#statement$averageGrade > 90") && averageGrade > 90 && mark("id$37.0#line-in-function$2#offset$50#condition$trueNode#statement$averageGrade > 90")) && (mark("id$38.0#line-in-function$2#offset$71#condition$falseNode#statement$averageGrade < 100") && averageGrade < 100 && mark("id$38.0#line-in-function$2#offset$71#condition$trueNode#statement$averageGrade < 100"))) {
    mark("id$9.0#line-in-function$3#offset$97#statement$return 'A';");return 'A';
  }
 else   if ((mark("id$39.0#line-in-function$5#offset$125#condition$falseNode#statement$averageGrade > 80") && averageGrade > 80 && mark("id$39.0#line-in-function$5#offset$125#condition$trueNode#statement$averageGrade > 80")) && (mark("id$40.0#line-in-function$5#offset$146#condition$falseNode#statement$averageGrade < 90") && averageGrade < 90 && mark("id$40.0#line-in-function$5#offset$146#condition$trueNode#statement$averageGrade < 90"))) {
    mark("id$15.0#line-in-function$6#offset$171#statement$return 'B';");return 'B';
  }
 else   if ((mark("id$41.0#line-in-function$8#offset$199#condition$falseNode#statement$averageGrade > 70") && averageGrade > 70 && mark("id$41.0#line-in-function$8#offset$199#condition$trueNode#statement$averageGrade > 70")) && (mark("id$42.0#line-in-function$8#offset$220#condition$falseNode#statement$averageGrade < 80") && averageGrade < 80 && mark("id$42.0#line-in-function$8#offset$220#condition$trueNode#statement$averageGrade < 80"))) {
    mark("id$21.0#line-in-function$9#offset$245#statement$return 'C';");return 'C';
  }
 else   if ((mark("id$43.0#line-in-function$11#offset$273#condition$falseNode#statement$averageGrade > 60") && averageGrade > 60 && mark("id$43.0#line-in-function$11#offset$273#condition$trueNode#statement$averageGrade > 60")) && (mark("id$44.0#line-in-function$11#offset$294#condition$falseNode#statement$averageGrade < 70") && averageGrade < 70 && mark("id$44.0#line-in-function$11#offset$294#condition$trueNode#statement$averageGrade < 70"))) {
    mark("id$27.0#line-in-function$12#offset$319#statement$return 'D';");return 'D';
  }
 else   if ((mark("id$45.0#line-in-function$14#offset$347#condition$falseNode#statement$averageGrade > 0") && averageGrade > 0 && mark("id$45.0#line-in-function$14#offset$347#condition$trueNode#statement$averageGrade > 0")) && (mark("id$46.0#line-in-function$14#offset$367#condition$falseNode#statement$averageGrade < 60") && averageGrade < 60 && mark("id$46.0#line-in-function$14#offset$367#condition$trueNode#statement$averageGrade < 60"))) {
    mark("id$33.0#line-in-function$15#offset$392#statement$return 'F';");return 'F';
  }
  mark("id$35.0#line-in-function$17#offset$410#statement$return 'I';");return 'I';
}
public static String TEST_PATH = "";
public static void main(String[] args) {
   File file;
   try (FileReader reader = new FileReader("D:/Code/KLTN/ISM/JdtBase/src/result/Testcases.json")) {
   Object obj = (new JSONParser()).parse(reader);
   JSONObject jsonObject = (JSONObject) obj;
   //TODO: Call test function
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("grade"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int averageGrade;
            averageGrade = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            grade(averageGrade);
         }
      }
   }
   } catch (Exception e) {
      e.printStackTrace();
   }
}
}
