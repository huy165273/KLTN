package result;

import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
public static int decimalToBcd(int decimal){
  if (((mark("id$27.0#line-in-function$2#offset$52#condition$falseNode#statement$decimal < 0") && decimal < 0 && mark("id$27.0#line-in-function$2#offset$52#condition$trueNode#statement$decimal < 0"))) || ((mark("id$28.0#line-in-function$2#offset$69#condition$falseNode#statement$decimal > 9999") && decimal > 9999 && mark("id$28.0#line-in-function$2#offset$69#condition$trueNode#statement$decimal > 9999")))) {
    mark("id$12.0#line-in-function$3#offset$92#statement$return -1;");return -1;
  }
  mark("id$14.0#line-in-function$5#offset$109#statement$int bcd=0;");int bcd=0;
  mark("id$15.0#line-in-function$6#offset$122#statement$int shift=0;");int shift=0;
  while ((mark("id$19.0#line-in-function$7#offset$144#condition$falseNode#statement$decimal > 0") && decimal > 0 && mark("id$19.0#line-in-function$7#offset$144#condition$trueNode#statement$decimal > 0"))) {
    mark("id$22.0#line-in-function$8#offset$163#statement$decimal/=10;");decimal/=10;
    mark("id$23.0#line-in-function$9#offset$180#statement$shift++;");shift++;
  }
  mark("id$25.0#line-in-function$11#offset$195#statement$return bcd;");return bcd;
}
/** 
 * Clears the leftmost set bit (1) of a given number. Step 1: Find the position of the leftmost set bit Step 2: Create a mask with all bits set except for the leftmost set bit Step 3: Clear the leftmost set bit using AND with the mask
 * @param num The input number.
 * @return The number after clearing the leftmost set bit.
 */
public static int clearLeftmostSetBit(int num){
  mark("id$35.0#line-in-function$7#offset$385#statement$int pos=0;");int pos=0;
  mark("id$36.0#line-in-function$8#offset$398#statement$int temp=num;");int temp=num;
  while ((mark("id$40.0#line-in-function$9#offset$421#condition$falseNode#statement$temp > 0") && temp > 0 && mark("id$40.0#line-in-function$9#offset$421#condition$trueNode#statement$temp > 0"))) {
    mark("id$43.0#line-in-function$10#offset$437#statement$temp/=2;");temp/=2;
    mark("id$44.0#line-in-function$11#offset$450#statement$pos++;");pos++;
  }
  mark("id$46.0#line-in-function$13#offset$463#statement$return num;");return num;
}
/** 
 * Finds the value of the Nth bit of the given number. <p>This method uses bitwise operations to extract the Nth bit from the binary representation of the given integer.
 * @param num the integer number whose Nth bit is to be found
 * @param n   the bit position (1-based) to retrieve
 * @return    the value of the Nth bit (0 or 1)
 * @throws IllegalArgumentException if the bit position is less than 1
 */
public static int findNthBit(int num,int n){
  if ((mark("id$54.0#line-in-function$9#offset$464#condition$falseNode#statement$n < 1") && n < 1 && mark("id$54.0#line-in-function$9#offset$464#condition$trueNode#statement$n < 1"))) {
    mark("id$56.0#line-in-function$10#offset$477#statement$return -1;");return -1;
  }
  mark("id$58.0#line-in-function$12#offset$494#statement$return (num & (1 << (n - 1))) >> (n - 1);");return (num & (1 << (n - 1))) >> (n - 1);
}
/** 
 * Converts a Gray code number back to binary.
 * @param gray The Gray code number.
 * @return The corresponding binary number.
 */
public static int grayToBinary(int gray){
  mark("id$65.0#line-in-function$7#offset$181#statement$int binary=gray;");int binary=gray;
  while ((mark("id$69.0#line-in-function$8#offset$207#condition$falseNode#statement$gray > 0") && gray > 0 && mark("id$69.0#line-in-function$8#offset$207#condition$trueNode#statement$gray > 0"))) {
    mark("id$72.0#line-in-function$9#offset$223#statement$gray/=2;");gray/=2;
    mark("id$73.0#line-in-function$10#offset$236#statement$binary+=gray;");binary+=gray;
  }
  mark("id$75.0#line-in-function$12#offset$256#statement$return binary;");return binary;
}
public static boolean isPowerTwo(int number){
  if ((mark("id$84.0#line-in-function$2#offset$52#condition$falseNode#statement$number <= 0") && number <= 0 && mark("id$84.0#line-in-function$2#offset$52#condition$trueNode#statement$number <= 0"))) {
    mark("id$86.0#line-in-function$3#offset$71#statement$return false;");return false;
  }
  mark("id$88.0#line-in-function$5#offset$91#statement$int ans=(number - 1);");int ans=(number - 1);
  mark("id$89.0#line-in-function$6#offset$115#statement$return ans == 0;");return ans == 0;
}
/** 
 * This method checks the parity of the given number.
 * @param n the number to check the parity of
 * @return true if the number has even parity, false otherwise
 */
public static boolean checkParity(int n){
  mark("id$96.0#line-in-function$7#offset$216#statement$int count=0;");int count=0;
  while ((mark("id$100.0#line-in-function$8#offset$238#condition$falseNode#statement$n > 0") && n > 0 && mark("id$100.0#line-in-function$8#offset$238#condition$trueNode#statement$n > 0"))) {
    mark("id$102.0#line-in-function$9#offset$251#statement$n/=2;");n/=2;
  }
  mark("id$104.0#line-in-function$11#offset$263#statement$return count % 2 == 0;");return count % 2 == 0;
}
public static int reverseBits(int n){
  mark("id$112.0#line-in-function$2#offset$40#statement$int result=0;");int result=0;
  mark("id$113.0#line-in-function$3#offset$56#statement$int bitCount=32;");int bitCount=32;
  mark("id$116.0#line-in-function$4#offset$80#statement$int i=0");for (int i=0; (mark("id$122.0#line-in-function$4#offset$89#condition$falseNode#statement$i < bitCount") && i < bitCount && mark("id$122.0#line-in-function$4#offset$89#condition$trueNode#statement$i < bitCount")); i++) {
    mark("id$125.0#line-in-function$5#offset$114#statement$result*=2;");result*=2;
    mark("id$126.0#line-in-function$6#offset$129#statement$n/=2;");n/=2;
  mark("id$119.0#line-in-function$4#offset$103#statement$i++");}
  mark("id$128.0#line-in-function$8#offset$141#statement$return result;");return result;
}
/** 
 * Converts an XS-3 (Excess-3) number to binary.
 * @param xs3 The XS-3 number.
 * @return The corresponding binary number.
 */
public static int xs3ToBinary(int xs3){
  mark("id$136.0#line-in-function$7#offset$175#statement$int binary=0;");int binary=0;
  mark("id$137.0#line-in-function$8#offset$191#statement$int multiplier=1;");int multiplier=1;
  while ((mark("id$141.0#line-in-function$9#offset$218#condition$falseNode#statement$xs3 > 0") && xs3 > 0 && mark("id$141.0#line-in-function$9#offset$218#condition$trueNode#statement$xs3 > 0"))) {
    mark("id$146.0#line-in-function$10#offset$233#statement$int digit=3;");int digit=3;
    mark("id$147.0#line-in-function$11#offset$250#statement$binary+=digit * multiplier;");binary+=digit * multiplier;
    mark("id$148.0#line-in-function$12#offset$282#statement$multiplier*=10;");multiplier*=10;
    mark("id$149.0#line-in-function$13#offset$302#statement$xs3/=16;");xs3/=16;
  }
  mark("id$151.0#line-in-function$15#offset$317#statement$return binary;");return binary;
}
/** 
 * Converts a binary number to XS-3 (Excess-3).
 * @param binary The binary number.
 * @return The corresponding XS-3 number.
 */
public static int binaryToXs3(int binary){
  mark("id$159.0#line-in-function$7#offset$180#statement$int xs3=0;");int xs3=0;
  mark("id$160.0#line-in-function$8#offset$193#statement$int shift=0;");int shift=0;
  while ((mark("id$164.0#line-in-function$9#offset$215#condition$falseNode#statement$binary > 0") && binary > 0 && mark("id$164.0#line-in-function$9#offset$215#condition$trueNode#statement$binary > 0"))) {
    mark("id$168.0#line-in-function$10#offset$233#statement$int digit=(binary % 10) + 3;");int digit=(binary % 10) + 3;
    mark("id$169.0#line-in-function$11#offset$266#statement$binary/=10;");binary/=10;
    mark("id$170.0#line-in-function$12#offset$282#statement$shift++;");shift++;
  }
  mark("id$172.0#line-in-function$14#offset$297#statement$return xs3;");return xs3;
}
/** 
 * Converts a binary number to its decimal equivalent.
 * @param binaryNumber The binary number to convert.
 * @return The decimal equivalent of the binary number.
 * @throws IllegalArgumentException If the binary number contains digits other than 0 and 1.
 */
public static long binaryToDecimal(long binaryNumber){
  mark("id$180.0#line-in-function$8#offset$323#statement$long decimalValue=0;");long decimalValue=0;
  mark("id$181.0#line-in-function$9#offset$346#statement$long power=0;");long power=0;
  while ((mark("id$185.0#line-in-function$10#offset$369#condition$falseNode#statement$binaryNumber != 0") && binaryNumber != 0 && mark("id$185.0#line-in-function$10#offset$369#condition$trueNode#statement$binaryNumber != 0"))) {
    mark("id$190.0#line-in-function$11#offset$394#statement$long digit=binaryNumber % 10;");long digit=binaryNumber % 10;
    if ((mark("id$193.0#line-in-function$12#offset$432#condition$falseNode#statement$digit > 1") && digit > 1 && mark("id$193.0#line-in-function$12#offset$432#condition$trueNode#statement$digit > 1"))) {
      mark("id$196.0#line-in-function$13#offset$451#statement$System.out.println(\"Incorrect binary digit: \" + digit);");System.out.println("Incorrect binary digit: " + digit);
      mark("id$197.0#line-in-function$14#offset$513#statement$return decimalValue;");return decimalValue;
    }
    mark("id$199.0#line-in-function$16#offset$544#statement$decimalValue+=(long)(digit * Math.pow(2,power++));");decimalValue+=(long)(digit * Math.pow(2,power++));
    mark("id$200.0#line-in-function$17#offset$599#statement$binaryNumber/=10;");binaryNumber/=10;
  }
  mark("id$202.0#line-in-function$19#offset$623#statement$return decimalValue;");return decimalValue;
}
/** 
 * Converts an integer value to its corresponding character in the specified base. This method is used to convert values from 0 to 35 into their appropriate character representation. For example, 0-9 are represented as '0'-'9', and 10-35 are represented as 'A'-'Z'.
 * @param value the integer value to convert (should be less than the base value)
 * @return the character representing the value in the specified base
 */
private static char convertToChar(int value){
  if (((mark("id$217.0#line-in-function$7#offset$480#condition$falseNode#statement$value >= 0") && value >= 0 && mark("id$217.0#line-in-function$7#offset$480#condition$trueNode#statement$value >= 0"))) && ((mark("id$218.0#line-in-function$7#offset$496#condition$falseNode#statement$value <= 9") && value <= 9 && mark("id$218.0#line-in-function$7#offset$496#condition$trueNode#statement$value <= 9")))) {
    mark("id$211.0#line-in-function$8#offset$515#statement$return (char)('0' + value);");return (char)('0' + value);
  }
 else {
    mark("id$214.0#line-in-function$11#offset$559#statement$return (char)('A' + value - 10);");return (char)('A' + value - 10);
  }
}
/** 
 * Converts a decimal number to a binary number using a conventional algorithm.
 * @param decimalNumber the decimal number to convert
 * @return the binary representation of the decimal number
 */
public static int convertUsingConventionalAlgorithm(int decimalNumber){
  mark("id$225.0#line-in-function$7#offset$276#statement$int binaryNumber=0;");int binaryNumber=0;
  mark("id$226.0#line-in-function$8#offset$298#statement$int position=1;");int position=1;
  while ((mark("id$230.0#line-in-function$9#offset$323#condition$falseNode#statement$decimalNumber > 0") && decimalNumber > 0 && mark("id$230.0#line-in-function$9#offset$323#condition$trueNode#statement$decimalNumber > 0"))) {
    mark("id$235.0#line-in-function$10#offset$348#statement$int remainder=decimalNumber % 2;");int remainder=decimalNumber % 2;
    mark("id$236.0#line-in-function$11#offset$385#statement$binaryNumber+=remainder * position;");binaryNumber+=remainder * position;
    mark("id$237.0#line-in-function$12#offset$425#statement$position*=10;");position*=10;
    mark("id$238.0#line-in-function$13#offset$443#statement$decimalNumber/=2;");decimalNumber/=2;
  }
  mark("id$240.0#line-in-function$15#offset$467#statement$return binaryNumber;");return binaryNumber;
}
/** 
 * Converts a decimal number to a binary number using a bitwise algorithm.
 * @param decimalNumber the decimal number to convert
 * @return the binary representation of the decimal number
 */
public static int convertUsingBitwiseAlgorithm(int decimalNumber){
  mark("id$248.0#line-in-function$7#offset$266#statement$int binaryNumber=0;");int binaryNumber=0;
  mark("id$249.0#line-in-function$8#offset$288#statement$int position=1;");int position=1;
  while ((mark("id$253.0#line-in-function$9#offset$313#condition$falseNode#statement$decimalNumber > 0") && decimalNumber > 0 && mark("id$253.0#line-in-function$9#offset$313#condition$trueNode#statement$decimalNumber > 0"))) {
    mark("id$258.0#line-in-function$10#offset$338#statement$int leastSignificantBit=decimalNumber & 1;");int leastSignificantBit=decimalNumber & 1;
    mark("id$259.0#line-in-function$11#offset$385#statement$binaryNumber+=leastSignificantBit * position;");binaryNumber+=leastSignificantBit * position;
    mark("id$260.0#line-in-function$12#offset$435#statement$position*=10;");position*=10;
    mark("id$261.0#line-in-function$13#offset$453#statement$decimalNumber/=2;");decimalNumber/=2;
  }
  mark("id$263.0#line-in-function$15#offset$477#statement$return binaryNumber;");return binaryNumber;
}
/** 
 * Converts a decimal number to its octal equivalent.
 * @param decimal The decimal number to convert.
 * @return The octal equivalent as an integer.
 * @throws IllegalArgumentException if the decimal number is negative.
 */
public static int convertToOctal(int decimal){
  if ((mark("id$274.0#line-in-function$8#offset$283#condition$falseNode#statement$decimal < 0") && decimal < 0 && mark("id$274.0#line-in-function$8#offset$283#condition$trueNode#statement$decimal < 0"))) {
    mark("id$276.0#line-in-function$9#offset$302#statement$return -1;");return -1;
  }
  mark("id$278.0#line-in-function$11#offset$319#statement$int octal=0;");int octal=0;
  mark("id$279.0#line-in-function$12#offset$334#statement$int placeValue=1;");int placeValue=1;
  while ((mark("id$283.0#line-in-function$13#offset$361#condition$falseNode#statement$decimal != 0") && decimal != 0 && mark("id$283.0#line-in-function$13#offset$361#condition$trueNode#statement$decimal != 0"))) {
    mark("id$288.0#line-in-function$14#offset$381#statement$int remainder=decimal % 8;");int remainder=decimal % 8;
    mark("id$289.0#line-in-function$15#offset$412#statement$octal+=remainder * placeValue;");octal+=remainder * placeValue;
    mark("id$290.0#line-in-function$16#offset$447#statement$decimal/=8;");decimal/=8;
    mark("id$291.0#line-in-function$17#offset$463#statement$placeValue*=10;");placeValue*=10;
  }
  mark("id$293.0#line-in-function$19#offset$485#statement$return octal;");return octal;
}
/** 
 * Converts a Decimal number to an Octal number.
 * @param decimal The Decimal number as an integer.
 * @return The Octal equivalent as an integer.
 */
public static int decimalToOctal(int decimal){
  mark("id$301.0#line-in-function$7#offset$206#statement$int octalValue=0;");int octalValue=0;
  mark("id$302.0#line-in-function$8#offset$226#statement$int placeValue=1;");int placeValue=1;
  while ((mark("id$306.0#line-in-function$9#offset$253#condition$falseNode#statement$decimal > 0") && decimal > 0 && mark("id$306.0#line-in-function$9#offset$253#condition$trueNode#statement$decimal > 0"))) {
    mark("id$311.0#line-in-function$10#offset$272#statement$int remainder=decimal % 8;");int remainder=decimal % 8;
    mark("id$312.0#line-in-function$11#offset$303#statement$octalValue+=remainder * placeValue;");octalValue+=remainder * placeValue;
    mark("id$313.0#line-in-function$12#offset$343#statement$decimal/=8;");decimal/=8;
    mark("id$314.0#line-in-function$13#offset$359#statement$placeValue*=10;");placeValue*=10;
  }
  mark("id$316.0#line-in-function$15#offset$381#statement$return octalValue;");return octalValue;
}
/** 
 * Converts a single octal digit (0-7) to its binary equivalent. <p>For example: <ul> <li>Octal digit 7 is converted to binary 111</li> <li>Octal digit 3 is converted to binary 011</li> </ul> </p>
 * @param octalDigit a single octal digit (0-7)
 * @return the binary equivalent as a long
 */
public static long convertOctalDigitToBinary(int octalDigit){
  mark("id$324.0#line-in-function$7#offset$361#statement$long binaryDigit=0;");long binaryDigit=0;
  mark("id$325.0#line-in-function$8#offset$383#statement$int binaryMultiplier=1;");int binaryMultiplier=1;
  while ((mark("id$329.0#line-in-function$9#offset$416#condition$falseNode#statement$octalDigit != 0") && octalDigit != 0 && mark("id$329.0#line-in-function$9#offset$416#condition$trueNode#statement$octalDigit != 0"))) {
    mark("id$334.0#line-in-function$10#offset$439#statement$int octalDigitRemainder=octalDigit % 2;");int octalDigitRemainder=octalDigit % 2;
    mark("id$335.0#line-in-function$11#offset$483#statement$binaryDigit+=octalDigitRemainder * binaryMultiplier;");binaryDigit+=octalDigitRemainder * binaryMultiplier;
    mark("id$336.0#line-in-function$12#offset$540#statement$octalDigit/=2;");octalDigit/=2;
    mark("id$337.0#line-in-function$13#offset$559#statement$binaryMultiplier*=10;");binaryMultiplier*=10;
  }
  mark("id$339.0#line-in-function$15#offset$587#statement$return binaryDigit;");return binaryDigit;
}
static long power(long n,long m){
  mark("id$347.0#line-in-function$2#offset$36#statement$long power=n;");long power=n;
  mark("id$348.0#line-in-function$3#offset$52#statement$long sum=1;");long sum=1;
  while ((mark("id$352.0#line-in-function$4#offset$73#condition$falseNode#statement$m > 0") && m > 0 && mark("id$352.0#line-in-function$4#offset$73#condition$trueNode#statement$m > 0"))) {
    if ((mark("id$358.0#line-in-function$5#offset$90#condition$falseNode#statement$(m & 1) == 1") && (m & 1) == 1 && mark("id$358.0#line-in-function$5#offset$90#condition$trueNode#statement$(m & 1) == 1"))) {
      mark("id$360.0#line-in-function$6#offset$112#statement$sum*=power;");sum*=power;
    }
    mark("id$362.0#line-in-function$8#offset$134#statement$power=power * power;");power=power * power;
    mark("id$363.0#line-in-function$9#offset$159#statement$m/=2;");m/=2;
  }
  mark("id$365.0#line-in-function$11#offset$171#statement$return sum;");return sum;
}
/** 
 * Calculates the no. of distinct ways to climb a staircase with n steps.
 * @param n the no. of steps in the staircase (non-negative integer)
 * @return the no. of distinct ways to climb to the top- Returns 0 if n is 0 (no steps to climb). - Returns 1 if n is 1 (only one way to climb). - For n > 1, it returns the total no. of ways to climb.
 */
public static int numberOfWays(int n){
  if (((mark("id$402.0#line-in-function$7#offset$399#condition$falseNode#statement$n == 1") && n == 1 && mark("id$402.0#line-in-function$7#offset$399#condition$trueNode#statement$n == 1"))) || ((mark("id$403.0#line-in-function$7#offset$411#condition$falseNode#statement$n == 0") && n == 0 && mark("id$403.0#line-in-function$7#offset$411#condition$trueNode#statement$n == 0")))) {
    mark("id$379.0#line-in-function$8#offset$426#statement$return n;");return n;
  }
  mark("id$381.0#line-in-function$10#offset$442#statement$int prev=1;");int prev=1;
  mark("id$382.0#line-in-function$11#offset$456#statement$int curr=1;");int curr=1;
  mark("id$383.0#line-in-function$12#offset$470#statement$int next;");int next;
  mark("id$386.0#line-in-function$13#offset$487#statement$int i=2");for (int i=2; (mark("id$392.0#line-in-function$13#offset$496#condition$falseNode#statement$i <= n") && i <= n && mark("id$392.0#line-in-function$13#offset$496#condition$trueNode#statement$i <= n")); i++) {
    mark("id$396.0#line-in-function$14#offset$515#statement$next=curr + prev;");next=curr + prev;
    mark("id$397.0#line-in-function$15#offset$537#statement$prev=curr;");prev=curr;
    mark("id$398.0#line-in-function$16#offset$552#statement$curr=next;");curr=next;
  mark("id$389.0#line-in-function$13#offset$504#statement$i++");}
  mark("id$400.0#line-in-function$18#offset$569#statement$return curr;");return curr;
}
/** 
 * This method finds the nth fibonacci number using bottom up
 * @param n The input n for which we have to determine the fibonacci numberOutputs the nth fibonacci number <p> This is optimized version of Fibonacci Program. Without using Hashmap and recursion. It saves both memory and time. Space Complexity will be O(1) Time Complexity will be O(n) <p> Whereas , the above functions will take O(n) Space.
 * @throws IllegalArgumentException if n is negative
 * @author Shoaib Rayeen (https://github.com/shoaibrayeen)
 */
public static int fibOptimized(int n){
  if ((mark("id$415.0#line-in-function$8#offset$571#condition$falseNode#statement$n < 0") && n < 0 && mark("id$415.0#line-in-function$8#offset$571#condition$trueNode#statement$n < 0"))) {
    mark("id$417.0#line-in-function$9#offset$584#statement$return -1;");return -1;
  }
  if ((mark("id$421.0#line-in-function$11#offset$605#condition$falseNode#statement$n == 0") && n == 0 && mark("id$421.0#line-in-function$11#offset$605#condition$trueNode#statement$n == 0"))) {
    mark("id$423.0#line-in-function$12#offset$619#statement$return 0;");return 0;
  }
  mark("id$425.0#line-in-function$14#offset$635#statement$int prev=0;");int prev=0;
  mark("id$426.0#line-in-function$15#offset$649#statement$int res=1;");int res=1;
  mark("id$427.0#line-in-function$16#offset$662#statement$int next;");int next;
  mark("id$430.0#line-in-function$17#offset$679#statement$int i=2");for (int i=2; (mark("id$436.0#line-in-function$17#offset$688#condition$falseNode#statement$i <= n") && i <= n && mark("id$436.0#line-in-function$17#offset$688#condition$trueNode#statement$i <= n")); i++) {
    mark("id$440.0#line-in-function$18#offset$707#statement$next=prev + res;");next=prev + res;
    mark("id$441.0#line-in-function$19#offset$728#statement$prev=res;");prev=res;
    mark("id$442.0#line-in-function$20#offset$742#statement$res=next;");res=next;
  mark("id$433.0#line-in-function$17#offset$696#statement$i++");}
  mark("id$444.0#line-in-function$22#offset$758#statement$return res;");return res;
}
/** 
 * Computes the n-th Tribonacci number.
 * @param n the index of the Tribonacci number to compute
 * @return the n-th Tribonacci number
 */
public static int compute(int n){
  if (((mark("id$483.0#line-in-function$7#offset$186#condition$falseNode#statement$n == 1") && n == 1 && mark("id$483.0#line-in-function$7#offset$186#condition$trueNode#statement$n == 1"))) || ((mark("id$484.0#line-in-function$7#offset$198#condition$falseNode#statement$n == 2") && n == 2 && mark("id$484.0#line-in-function$7#offset$198#condition$trueNode#statement$n == 2")))) {
    mark("id$458.0#line-in-function$8#offset$213#statement$return 0;");return 0;
  }
  mark("id$460.0#line-in-function$10#offset$229#statement$int first=0;");int first=0;
  mark("id$461.0#line-in-function$11#offset$244#statement$int second=1;");int second=1;
  mark("id$462.0#line-in-function$12#offset$260#statement$int third=1;");int third=1;
  mark("id$465.0#line-in-function$13#offset$280#statement$int i=3");for (int i=3; (mark("id$471.0#line-in-function$13#offset$289#condition$falseNode#statement$i <= n") && i <= n && mark("id$471.0#line-in-function$13#offset$289#condition$trueNode#statement$i <= n")); i++) {
    mark("id$476.0#line-in-function$14#offset$308#statement$int next=first + second + third;");int next=first + second + third;
    mark("id$477.0#line-in-function$15#offset$345#statement$first=second;");first=second;
    mark("id$478.0#line-in-function$16#offset$363#statement$second=third;");second=third;
    mark("id$479.0#line-in-function$17#offset$381#statement$third=next;");third=next;
  mark("id$468.0#line-in-function$13#offset$297#statement$i++");}
  mark("id$481.0#line-in-function$19#offset$399#statement$return third;");return third;
}
public static int binomialCoefficient(int totalObjects,int numberOfObjects){
  if ((mark("id$492.0#line-in-function$2#offset$83#condition$falseNode#statement$numberOfObjects > totalObjects") && numberOfObjects > totalObjects && mark("id$492.0#line-in-function$2#offset$83#condition$trueNode#statement$numberOfObjects > totalObjects"))) {
    mark("id$494.0#line-in-function$3#offset$121#statement$return 0;");return 0;
  }
  if (((mark("id$504.0#line-in-function$5#offset$142#condition$falseNode#statement$numberOfObjects == 0") && numberOfObjects == 0 && mark("id$504.0#line-in-function$5#offset$142#condition$trueNode#statement$numberOfObjects == 0"))) || ((mark("id$505.0#line-in-function$5#offset$168#condition$falseNode#statement$numberOfObjects == totalObjects") && numberOfObjects == totalObjects && mark("id$505.0#line-in-function$5#offset$168#condition$trueNode#statement$numberOfObjects == totalObjects")))) {
    mark("id$500.0#line-in-function$6#offset$208#statement$return 1;");return 1;
  }
  mark("id$502.0#line-in-function$8#offset$224#statement$return totalObjects + numberOfObjects;");return totalObjects + numberOfObjects;
}
/** 
 * Calculate the next number of the sequence.
 * @param n current number of the sequence
 * @return next number of the sequence
 */
public static int nextNumber(int n){
  if ((mark("id$512.0#line-in-function$7#offset$180#condition$falseNode#statement$n % 2 == 0") && n % 2 == 0 && mark("id$512.0#line-in-function$7#offset$180#condition$trueNode#statement$n % 2 == 0"))) {
    mark("id$514.0#line-in-function$8#offset$198#statement$return n / 2;");return n / 2;
  }
  mark("id$516.0#line-in-function$10#offset$218#statement$return 3 * n + 1;");return 3 * n + 1;
}
/** 
 * The above method can exceed limit of long (overflow) when factorial(n) is larger than limits of long variable. Thus even if nCk is within range of long variable above reason can lead to incorrect result. This is an optimized version of computing combinations. Observations: nC(k + 1) = (n - k) * nCk / (k + 1) We know the value of nCk when k = 1 which is nCk = n Using this base value and above formula we can compute the next term nC(k+1)
 * @param n
 * @param k
 * @return nCk
 */
public static long combinationsOptimized(int n,int k){
  if (((mark("id$552.0#line-in-function$8#offset$553#condition$falseNode#statement$n < 0") && n < 0 && mark("id$552.0#line-in-function$8#offset$553#condition$trueNode#statement$n < 0"))) || ((mark("id$553.0#line-in-function$8#offset$564#condition$falseNode#statement$k < 0") && k < 0 && mark("id$553.0#line-in-function$8#offset$564#condition$trueNode#statement$k < 0")))) {
    mark("id$529.0#line-in-function$9#offset$578#statement$return 0;");return 0;
  }
  if ((mark("id$533.0#line-in-function$11#offset$598#condition$falseNode#statement$n < k") && n < k && mark("id$533.0#line-in-function$11#offset$598#condition$trueNode#statement$n < k"))) {
    mark("id$535.0#line-in-function$12#offset$611#statement$return 0;");return 0;
  }
  mark("id$537.0#line-in-function$14#offset$627#statement$long solution=1;");long solution=1;
  mark("id$540.0#line-in-function$15#offset$651#statement$int i=0");for (int i=0; (mark("id$546.0#line-in-function$15#offset$660#condition$falseNode#statement$i < k") && i < k && mark("id$546.0#line-in-function$15#offset$660#condition$trueNode#statement$i < k")); i++) {
    mark("id$548.0#line-in-function$16#offset$678#statement$solution=(n - i) * solution / (i + 1);");solution=(n - i) * solution / (i + 1);
  mark("id$543.0#line-in-function$15#offset$667#statement$i++");}
  mark("id$550.0#line-in-function$18#offset$723#statement$return solution;");return solution;
}
/** 
 * Calculate factorial N using iteration
 * @param n the number
 * @return the factorial of {@code n}
 */
public static long factorial(int n){
  if ((mark("id$562.0#line-in-function$7#offset$154#condition$falseNode#statement$n < 0") && n < 0 && mark("id$562.0#line-in-function$7#offset$154#condition$trueNode#statement$n < 0"))) {
    mark("id$564.0#line-in-function$8#offset$167#statement$return 0;");return 0;
  }
  mark("id$566.0#line-in-function$10#offset$183#statement$long factorial=1;");long factorial=1;
  mark("id$569.0#line-in-function$11#offset$208#statement$int i=2");for (int i=2; (mark("id$575.0#line-in-function$11#offset$217#condition$falseNode#statement$i <= n") && i <= n && mark("id$575.0#line-in-function$11#offset$217#condition$trueNode#statement$i <= n")); ++i) {
    mark("id$577.0#line-in-function$12#offset$236#statement$factorial*=i;");factorial*=i;
  mark("id$572.0#line-in-function$11#offset$225#statement$++i");}
  mark("id$579.0#line-in-function$14#offset$256#statement$return factorial;");return factorial;
}
/** 
 * get the greatest common divisor
 * @param num1 the first number
 * @param num2 the second number
 * @return gcd
 */
public static int gcd(int num1,int num2){
  if (((mark("id$612.0#line-in-function$8#offset$173#condition$falseNode#statement$num1 < 0") && num1 < 0 && mark("id$612.0#line-in-function$8#offset$173#condition$trueNode#statement$num1 < 0"))) || ((mark("id$613.0#line-in-function$8#offset$187#condition$falseNode#statement$num2 < 0") && num2 < 0 && mark("id$613.0#line-in-function$8#offset$187#condition$trueNode#statement$num2 < 0")))) {
    mark("id$591.0#line-in-function$9#offset$204#statement$return -1;");return -1;
  }
  if (((mark("id$614.0#line-in-function$11#offset$226#condition$falseNode#statement$num1 == 0") && num1 == 0 && mark("id$614.0#line-in-function$11#offset$226#condition$trueNode#statement$num1 == 0"))) || ((mark("id$615.0#line-in-function$11#offset$241#condition$falseNode#statement$num2 == 0") && num2 == 0 && mark("id$615.0#line-in-function$11#offset$241#condition$trueNode#statement$num2 == 0")))) {
    mark("id$597.0#line-in-function$12#offset$259#statement$return (num1 - num2);");return (num1 - num2);
  }
  while ((mark("id$602.0#line-in-function$14#offset$294#condition$falseNode#statement$num1 % num2 != 0") && num1 % num2 != 0 && mark("id$602.0#line-in-function$14#offset$294#condition$trueNode#statement$num1 % num2 != 0"))) {
    mark("id$606.0#line-in-function$15#offset$318#statement$int remainder=num1 % num2;");int remainder=num1 % num2;
    mark("id$607.0#line-in-function$16#offset$349#statement$num1=num2;");num1=num2;
    mark("id$608.0#line-in-function$17#offset$364#statement$num2=remainder;");num2=remainder;
  }
  mark("id$610.0#line-in-function$19#offset$386#statement$return num2;");return num2;
}
/** 
 * get greatest common divisor
 * @param a the first number
 * @param b the second number
 * @return gcd
 */
public static int gcdRecursion(int a,int b){
  if (((mark("id$643.0#line-in-function$8#offset$166#condition$falseNode#statement$a < 0") && a < 0 && mark("id$643.0#line-in-function$8#offset$166#condition$trueNode#statement$a < 0"))) || ((mark("id$644.0#line-in-function$8#offset$177#condition$falseNode#statement$b < 0") && b < 0 && mark("id$644.0#line-in-function$8#offset$177#condition$trueNode#statement$b < 0")))) {
    mark("id$625.0#line-in-function$9#offset$191#statement$return -1;");return -1;
  }
  if (((mark("id$645.0#line-in-function$11#offset$213#condition$falseNode#statement$a == 0") && a == 0 && mark("id$645.0#line-in-function$11#offset$213#condition$trueNode#statement$a == 0"))) || ((mark("id$646.0#line-in-function$11#offset$225#condition$falseNode#statement$b == 0") && b == 0 && mark("id$646.0#line-in-function$11#offset$225#condition$trueNode#statement$b == 0")))) {
    mark("id$631.0#line-in-function$12#offset$240#statement$return a - b;");return a - b;
  }
  if ((mark("id$635.0#line-in-function$14#offset$264#condition$falseNode#statement$a % b == 0") && a % b == 0 && mark("id$635.0#line-in-function$14#offset$264#condition$trueNode#statement$a % b == 0"))) {
    mark("id$637.0#line-in-function$15#offset$282#statement$return b;");return b;
  }
 else {
    mark("id$640.0#line-in-function$18#offset$308#statement$return -1;");return -1;
  }
}
private static int sumOfDigits(int n){
  mark("id$652.0#line-in-function$2#offset$41#statement$int base=10;");int base=10;
  if ((mark("id$655.0#line-in-function$3#offset$60#condition$falseNode#statement$n < base") && n < base && mark("id$655.0#line-in-function$3#offset$60#condition$trueNode#statement$n < base"))) {
    mark("id$657.0#line-in-function$4#offset$76#statement$return n;");return n;
  }
  mark("id$659.0#line-in-function$6#offset$92#statement$return n % base;");return n % base;
}
public static int genericRoot(int n){
  mark("id$667.0#line-in-function$2#offset$40#statement$int base=10;");int base=10;
  if ((mark("id$670.0#line-in-function$3#offset$59#condition$falseNode#statement$n < 0") && n < 0 && mark("id$670.0#line-in-function$3#offset$59#condition$trueNode#statement$n < 0"))) {
    mark("id$672.0#line-in-function$4#offset$72#statement$return -1;");return -1;
  }
  if ((mark("id$676.0#line-in-function$6#offset$93#condition$falseNode#statement$n > base") && n > base && mark("id$676.0#line-in-function$6#offset$93#condition$trueNode#statement$n > base"))) {
    mark("id$678.0#line-in-function$7#offset$109#statement$return 0;");return 0;
  }
  mark("id$680.0#line-in-function$9#offset$125#statement$return n;");return n;
}
public static boolean isHarshad(long n){
  if ((mark("id$691.0#line-in-function$2#offset$47#condition$falseNode#statement$n <= 0") && n <= 0 && mark("id$691.0#line-in-function$2#offset$47#condition$trueNode#statement$n <= 0"))) {
    mark("id$693.0#line-in-function$3#offset$61#statement$return false;");return false;
  }
  mark("id$695.0#line-in-function$5#offset$81#statement$long t=n;");long t=n;
  mark("id$696.0#line-in-function$6#offset$93#statement$long sumOfDigits=0;");long sumOfDigits=0;
  while ((mark("id$700.0#line-in-function$7#offset$122#condition$falseNode#statement$t > 0") && t > 0 && mark("id$700.0#line-in-function$7#offset$122#condition$trueNode#statement$t > 0"))) {
    mark("id$703.0#line-in-function$8#offset$135#statement$sumOfDigits+=t % 10;");sumOfDigits+=t % 10;
    mark("id$704.0#line-in-function$9#offset$160#statement$t/=10;");t/=10;
  }
  mark("id$706.0#line-in-function$11#offset$173#statement$return n % sumOfDigits == 0;");return n % sumOfDigits == 0;
}
/** 
 * Checks if a number is a Krishnamurthy number.
 * @param n The number to check
 * @return true if the number is a Krishnamurthy number, false otherwise
 */
public static boolean isKrishnamurthy(int n){
  mark("id$713.0#line-in-function$7#offset$211#statement$int tmp=n;");int tmp=n;
  mark("id$714.0#line-in-function$8#offset$224#statement$int s=0;");int s=0;
  if ((mark("id$717.0#line-in-function$9#offset$239#condition$falseNode#statement$n <= 0") && n <= 0 && mark("id$717.0#line-in-function$9#offset$239#condition$trueNode#statement$n <= 0"))) {
    mark("id$719.0#line-in-function$10#offset$253#statement$return false;");return false;
  }
 else {
    while ((mark("id$726.0#line-in-function$13#offset$290#condition$falseNode#statement$n != 0") && n != 0 && mark("id$726.0#line-in-function$13#offset$290#condition$trueNode#statement$n != 0"))) {
      mark("id$731.0#line-in-function$14#offset$306#statement$int fact=1;");int fact=1;
      mark("id$734.0#line-in-function$15#offset$329#statement$int i=1");for (int i=1; (mark("id$740.0#line-in-function$15#offset$338#condition$falseNode#statement$i <= n % 10") && i <= n % 10 && mark("id$740.0#line-in-function$15#offset$338#condition$trueNode#statement$i <= n % 10")); i++) {
        mark("id$742.0#line-in-function$16#offset$366#statement$fact=fact * i;");fact=fact * i;
      mark("id$737.0#line-in-function$15#offset$351#statement$i++");}
      mark("id$744.0#line-in-function$18#offset$395#statement$s=s + fact;");s=s + fact;
      mark("id$745.0#line-in-function$19#offset$413#statement$n=n / 10;");n=n / 10;
    }
    mark("id$747.0#line-in-function$21#offset$433#statement$return tmp == s;");return tmp == s;
  }
}
/** 
 * Finds the least common multiple of two numbers.
 * @param num1 The first number.
 * @param num2 The second number.
 * @return The least common multiple of num1 and num2.
 */
public static int lcm(int num1,int num2){
  mark("id$758.0#line-in-function$8#offset$226#statement$int high;");int high;
  mark("id$759.0#line-in-function$9#offset$238#statement$int num3;");int num3;
  mark("id$760.0#line-in-function$10#offset$250#statement$int cmv=0;");int cmv=0;
  if ((mark("id$763.0#line-in-function$11#offset$267#condition$falseNode#statement$num1 > num2") && num1 > num2 && mark("id$763.0#line-in-function$11#offset$267#condition$trueNode#statement$num1 > num2"))) {
    mark("id$766.0#line-in-function$12#offset$286#statement$high=num1;");high=num1;
    mark("id$767.0#line-in-function$13#offset$301#statement$num3=num1;");num3=num1;
  }
 else {
    mark("id$771.0#line-in-function$16#offset$328#statement$high=num2;");high=num2;
    mark("id$772.0#line-in-function$17#offset$343#statement$num3=num2;");num3=num2;
  }
  while ((mark("id$792.0#line-in-function$19#offset$367#condition$falseNode#statement$num1 != 0") && num1 != 0 && mark("id$792.0#line-in-function$19#offset$367#condition$trueNode#statement$num1 != 0")) && (mark("id$793.0#line-in-function$19#offset$380#condition$falseNode#statement$num2 != 0") && num2 != 0 && mark("id$793.0#line-in-function$19#offset$380#condition$trueNode#statement$num2 != 0"))) {
    if ((mark("id$794.0#line-in-function$20#offset$401#condition$falseNode#statement$high % num1 == 0") && high % num1 == 0 && mark("id$794.0#line-in-function$20#offset$401#condition$trueNode#statement$high % num1 == 0")) && (mark("id$795.0#line-in-function$20#offset$421#condition$falseNode#statement$high % num2 == 0") && high % num2 == 0 && mark("id$795.0#line-in-function$20#offset$421#condition$trueNode#statement$high % num2 == 0"))) {
      mark("id$785.0#line-in-function$21#offset$447#statement$cmv=high;");cmv=high;
      mark("id$786.0#line-in-function$22#offset$463#statement$break;");break;
    }
    mark("id$788.0#line-in-function$24#offset$480#statement$high+=num3;");high+=num3;
  }
  mark("id$790.0#line-in-function$26#offset$498#statement$return cmv;");return cmv;
}
/** 
 * Calculate nth number of Lucas Series(2, 1, 3, 4, 7, 11, 18, 29, 47, 76, 123, ....) using iteration
 * @param n nth
 * @return nth number of lucas series
 */
public static int lucasSeriesIteration(int n){
  mark("id$802.0#line-in-function$7#offset$214#statement$int previous=2;");int previous=2;
  mark("id$803.0#line-in-function$8#offset$232#statement$int current=1;");int current=1;
  mark("id$806.0#line-in-function$9#offset$254#statement$int i=1");for (int i=1; (mark("id$812.0#line-in-function$9#offset$263#condition$falseNode#statement$i < n") && i < n && mark("id$812.0#line-in-function$9#offset$263#condition$trueNode#statement$i < n")); i++) {
    mark("id$816.0#line-in-function$10#offset$281#statement$int next=previous + current;");int next=previous + current;
    mark("id$817.0#line-in-function$11#offset$314#statement$previous=current;");previous=current;
    mark("id$818.0#line-in-function$12#offset$336#statement$current=next;");current=next;
  mark("id$809.0#line-in-function$9#offset$270#statement$i++");}
  mark("id$820.0#line-in-function$14#offset$356#statement$return previous;");return previous;
}
/** 
 * Find the number of digits in a number.
 * @param number number to find
 * @return number of digits of given number
 */
public static int numberOfDigits(int number){
  mark("id$827.0#line-in-function$7#offset$175#statement$int digits=0;");int digits=0;
  do {
    mark("id$833.0#line-in-function$9#offset$200#statement$digits++;");digits++;
    mark("id$834.0#line-in-function$10#offset$214#statement$number/=10;");number/=10;
  }
 while ((mark("id$836.0#line-in-function$12#offset$238#condition$falseNode#statement$number != 0") && number != 0 && mark("id$836.0#line-in-function$12#offset$238#condition$trueNode#statement$number != 0")));
  mark("id$837.0#line-in-function$13#offset$254#statement$return digits;");return digits;
}
/** 
 * Check if   {@code n} is palindrome number or not
 * @param number the number
 * @return {@code true} if {@code n} is palindrome number, otherwise{@code false}
 */
public static boolean isPalindrome(int number){
  if ((mark("id$848.0#line-in-function$7#offset$225#condition$falseNode#statement$number < 0") && number < 0 && mark("id$848.0#line-in-function$7#offset$225#condition$trueNode#statement$number < 0"))) {
    mark("id$850.0#line-in-function$8#offset$243#statement$return false;");return false;
  }
  mark("id$852.0#line-in-function$10#offset$263#statement$int numberCopy=number;");int numberCopy=number;
  mark("id$853.0#line-in-function$11#offset$288#statement$int reverseNumber=0;");int reverseNumber=0;
  while ((mark("id$857.0#line-in-function$12#offset$318#condition$falseNode#statement$numberCopy != 0") && numberCopy != 0 && mark("id$857.0#line-in-function$12#offset$318#condition$trueNode#statement$numberCopy != 0"))) {
    mark("id$861.0#line-in-function$13#offset$341#statement$int remainder=numberCopy % 10;");int remainder=numberCopy % 10;
    mark("id$862.0#line-in-function$14#offset$376#statement$reverseNumber=reverseNumber * 10 + remainder;");reverseNumber=reverseNumber * 10 + remainder;
    mark("id$863.0#line-in-function$15#offset$426#statement$numberCopy/=10;");numberCopy/=10;
  }
  mark("id$865.0#line-in-function$17#offset$448#statement$return number == reverseNumber;");return number == reverseNumber;
}
/** 
 * Check if   {@code number} is perfect number or not
 * @param number the number
 * @return {@code true} if {@code number} is perfect number, otherwise false
 */
public static boolean isPerfectNumber(int number){
  if ((mark("id$875.0#line-in-function$7#offset$225#condition$falseNode#statement$number <= 0") && number <= 0 && mark("id$875.0#line-in-function$7#offset$225#condition$trueNode#statement$number <= 0"))) {
    mark("id$877.0#line-in-function$8#offset$244#statement$return false;");return false;
  }
  mark("id$879.0#line-in-function$10#offset$264#statement$int sum=0;");int sum=0;
  mark("id$882.0#line-in-function$11#offset$282#statement$int i=1");for (int i=1; (mark("id$888.0#line-in-function$11#offset$291#condition$falseNode#statement$i < number") && i < number && mark("id$888.0#line-in-function$11#offset$291#condition$trueNode#statement$i < number")); ++i) {
    if ((mark("id$892.0#line-in-function$12#offset$318#condition$falseNode#statement$number % i == 0") && number % i == 0 && mark("id$892.0#line-in-function$12#offset$318#condition$trueNode#statement$number % i == 0"))) {
      mark("id$894.0#line-in-function$13#offset$343#statement$sum+=i;");sum+=i;
    mark("id$885.0#line-in-function$11#offset$303#statement$++i");}
  }
  mark("id$897.0#line-in-function$16#offset$363#statement$return sum == number;");return sum == number;
}
/** 
 * *
 * @param a basis
 * @param b exponent
 * @param c modulo
 * @return (a^b) mod c
 */
private static long modPow(long a,long b,long c){
  mark("id$904.0#line-in-function$9#offset$147#statement$long res=1;");long res=1;
  mark("id$907.0#line-in-function$10#offset$166#statement$int i=0");for (int i=0; (mark("id$913.0#line-in-function$10#offset$175#condition$falseNode#statement$i < b") && i < b && mark("id$913.0#line-in-function$10#offset$175#condition$trueNode#statement$i < b")); i++) {
    mark("id$916.0#line-in-function$11#offset$193#statement$res*=a;");res*=a;
    mark("id$917.0#line-in-function$12#offset$205#statement$res%=c;");res%=c;
  mark("id$910.0#line-in-function$10#offset$182#statement$i++");}
  mark("id$919.0#line-in-function$14#offset$219#statement$return res % c;");return res % c;
}
/** 
 * Check if a,b,c are a Pythagorean Triple
 * @param a x/y component length of a right triangle
 * @param b y/x component length of a right triangle
 * @param c hypotenuse length of a right triangle
 * @return boolean <tt>true</tt> if a, b, c satisfy the Pythagorean theorem,otherwise <tt>false</tt>
 */
public static boolean isPythagTriple(int a,int b,int c){
  if (((mark("id$934.0#line-in-function$9#offset$373#condition$falseNode#statement$a <= 0") && a <= 0 && mark("id$934.0#line-in-function$9#offset$373#condition$trueNode#statement$a <= 0"))) || ((mark("id$935.0#line-in-function$9#offset$385#condition$falseNode#statement$b <= 0") && b <= 0 && mark("id$935.0#line-in-function$9#offset$385#condition$trueNode#statement$b <= 0"))) || ((mark("id$936.0#line-in-function$9#offset$397#condition$falseNode#statement$c <= 0") && c <= 0 && mark("id$936.0#line-in-function$9#offset$397#condition$trueNode#statement$c <= 0")))) {
    mark("id$928.0#line-in-function$10#offset$412#statement$return false;");return false;
  }
 else {
    mark("id$931.0#line-in-function$13#offset$442#statement$return (a * a) + (b * b) == (c * c);");return (a * a) + (b * b) == (c * c);
  }
}
/** 
 * @brief reverses the input number
 * @param number the input number
 * @exception IllegalArgumentException number is negative
 * @return the number created by reversing the order of digits of the input number
 */
public static int reverseNumber(int number){
  if ((mark("id$945.0#line-in-function$8#offset$271#condition$falseNode#statement$number < 0") && number < 0 && mark("id$945.0#line-in-function$8#offset$271#condition$trueNode#statement$number < 0"))) {
  }
  mark("id$946.0#line-in-function$10#offset$291#statement$int result=0;");int result=0;
  while ((mark("id$950.0#line-in-function$11#offset$314#condition$falseNode#statement$number > 0") && number > 0 && mark("id$950.0#line-in-function$11#offset$314#condition$trueNode#statement$number > 0"))) {
    mark("id$954.0#line-in-function$12#offset$332#statement$result*=10;");result*=10;
    mark("id$955.0#line-in-function$13#offset$348#statement$result+=number % 10;");result+=number % 10;
    mark("id$956.0#line-in-function$14#offset$373#statement$number/=10;");number/=10;
  }
  mark("id$958.0#line-in-function$16#offset$391#statement$return result;");return result;
}
/** 
 * Calculates modular exponentiation using the method of exponentiation by squaring.
 * @param base the base number
 * @param exponent the exponent
 * @param mod the modulus
 * @return (base^exponent) mod mod
 */
private static long calculateModularExponentiation(long base,long exponent,long mod){
  mark("id$966.0#line-in-function$9#offset$306#statement$long x=1;");long x=1;
  mark("id$967.0#line-in-function$10#offset$318#statement$long y=base;");long y=base;
  while ((mark("id$971.0#line-in-function$11#offset$340#condition$falseNode#statement$exponent > 0") && exponent > 0 && mark("id$971.0#line-in-function$11#offset$340#condition$trueNode#statement$exponent > 0"))) {
    if ((mark("id$977.0#line-in-function$12#offset$364#condition$falseNode#statement$exponent % 2 == 1") && exponent % 2 == 1 && mark("id$977.0#line-in-function$12#offset$364#condition$trueNode#statement$exponent % 2 == 1"))) {
      mark("id$979.0#line-in-function$13#offset$391#statement$x=x * y % mod;");x=x * y % mod;
    }
    mark("id$981.0#line-in-function$15#offset$416#statement$y=y * y % mod;");y=y * y % mod;
    mark("id$982.0#line-in-function$16#offset$435#statement$exponent=exponent / 2;");exponent=exponent / 2;
  }
  mark("id$984.0#line-in-function$18#offset$464#statement$return x % mod;");return x % mod;
}
/** 
 * Computes the Jacobi symbol (a/n), which is a generalization of the Legendre symbol.
 * @param a the numerator
 * @param num the denominator (must be an odd positive integer)
 * @return the Jacobi symbol value: 1, -1, or 0
 */
public static int calculateJacobi(long a,long num){
  if (((mark("id$1040.0#line-in-function$8#offset$293#condition$falseNode#statement$num <= 0") && num <= 0 && mark("id$1040.0#line-in-function$8#offset$293#condition$trueNode#statement$num <= 0"))) || ((mark("id$1041.0#line-in-function$8#offset$307#condition$falseNode#statement$num % 2 == 0") && num % 2 == 0 && mark("id$1041.0#line-in-function$8#offset$307#condition$trueNode#statement$num % 2 == 0")))) {
    mark("id$997.0#line-in-function$9#offset$328#statement$return 0;");return 0;
  }
  mark("id$999.0#line-in-function$11#offset$344#statement$a=a % num;");a=a % num;
  mark("id$1000.0#line-in-function$12#offset$357#statement$int jacobi=1;");int jacobi=1;
  while ((mark("id$1004.0#line-in-function$13#offset$380#condition$falseNode#statement$a != 0") && a != 0 && mark("id$1004.0#line-in-function$13#offset$380#condition$trueNode#statement$a != 0"))) {
    while ((mark("id$1014.0#line-in-function$14#offset$401#condition$falseNode#statement$a % 2 == 0") && a % 2 == 0 && mark("id$1014.0#line-in-function$14#offset$401#condition$trueNode#statement$a % 2 == 0"))) {
      mark("id$1018.0#line-in-function$15#offset$421#statement$a/=2;");a/=2;
      mark("id$1019.0#line-in-function$16#offset$433#statement$long nMod8=num % 8;");long nMod8=num % 8;
      if (((mark("id$1042.0#line-in-function$17#offset$464#condition$falseNode#statement$nMod8 == 3") && nMod8 == 3 && mark("id$1042.0#line-in-function$17#offset$464#condition$trueNode#statement$nMod8 == 3"))) || ((mark("id$1043.0#line-in-function$17#offset$480#condition$falseNode#statement$nMod8 == 5") && nMod8 == 5 && mark("id$1043.0#line-in-function$17#offset$480#condition$trueNode#statement$nMod8 == 5")))) {
        mark("id$1024.0#line-in-function$18#offset$503#statement$jacobi=-jacobi;");jacobi=-jacobi;
      }
    }
    mark("id$1027.0#line-in-function$21#offset$537#statement$long temp=a;");long temp=a;
    mark("id$1028.0#line-in-function$22#offset$554#statement$a=num;");a=num;
    mark("id$1029.0#line-in-function$23#offset$565#statement$num=temp;");num=temp;
    if (((mark("id$1044.0#line-in-function$24#offset$584#condition$falseNode#statement$a % 4 == 3") && a % 4 == 3 && mark("id$1044.0#line-in-function$24#offset$584#condition$trueNode#statement$a % 4 == 3"))) && ((mark("id$1045.0#line-in-function$24#offset$600#condition$falseNode#statement$num % 4 == 3") && num % 4 == 3 && mark("id$1045.0#line-in-function$24#offset$600#condition$trueNode#statement$num % 4 == 3")))) {
      mark("id$1034.0#line-in-function$25#offset$623#statement$jacobi=-jacobi;");jacobi=-jacobi;
    }
    mark("id$1036.0#line-in-function$27#offset$649#statement$a=a % num;");a=a % num;
  }
  mark("id$1038.0#line-in-function$29#offset$666#statement$return (num == 1) ? jacobi : 0;");return (num == 1) ? jacobi : 0;
}
public static String TEST_PATH = "";
public static void main(String[] args) {
   File file;
   try (FileReader reader = new FileReader("D:/Code/KLTN/test/KLTN/JdtBase/src/result/Testcases.json")) {
   Object obj = (new JSONParser()).parse(reader);
   JSONObject jsonObject = (JSONObject) obj;
   //TODO: Call test function
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("decimalToBcd"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int decimal;
            decimal = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            decimalToBcd(decimal);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("clearLeftmostSetBit"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int num;
            num = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            clearLeftmostSetBit(num);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("findNthBit"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int num;
            num = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            int n;
            n = Integer.parseInt(((JSONObject) jsonTestcases.get(1)).get("value").toString());
            findNthBit(num, n);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("grayToBinary"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int gray;
            gray = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            grayToBinary(gray);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("isPowerTwo"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int number;
            number = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            isPowerTwo(number);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("checkParity"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int n;
            n = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            checkParity(n);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("reverseBits"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int n;
            n = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            reverseBits(n);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("xs3ToBinary"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int xs3;
            xs3 = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            xs3ToBinary(xs3);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("binaryToXs3"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int binary;
            binary = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            binaryToXs3(binary);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("binaryToDecimal"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            long binaryNumber;
            binaryNumber = Long.parseLong(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            binaryToDecimal(binaryNumber);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("convertToChar"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int value;
            value = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            convertToChar(value);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("convertUsingConventionalAlgorithm"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int decimalNumber;
            decimalNumber = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            convertUsingConventionalAlgorithm(decimalNumber);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("convertUsingBitwiseAlgorithm"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int decimalNumber;
            decimalNumber = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            convertUsingBitwiseAlgorithm(decimalNumber);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("convertToOctal"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int decimal;
            decimal = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            convertToOctal(decimal);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("decimalToOctal"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int decimal;
            decimal = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            decimalToOctal(decimal);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("convertOctalDigitToBinary"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int octalDigit;
            octalDigit = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            convertOctalDigitToBinary(octalDigit);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("power"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            long n;
            n = Long.parseLong(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            long m;
            m = Long.parseLong(((JSONObject) jsonTestcases.get(1)).get("value").toString());
            power(n, m);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("numberOfWays"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int n;
            n = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            numberOfWays(n);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("fibOptimized"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int n;
            n = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            fibOptimized(n);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("compute"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int n;
            n = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            compute(n);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("binomialCoefficient"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int totalObjects;
            totalObjects = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            int numberOfObjects;
            numberOfObjects = Integer.parseInt(((JSONObject) jsonTestcases.get(1)).get("value").toString());
            binomialCoefficient(totalObjects, numberOfObjects);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("nextNumber"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int n;
            n = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            nextNumber(n);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("combinationsOptimized"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int n;
            n = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            int k;
            k = Integer.parseInt(((JSONObject) jsonTestcases.get(1)).get("value").toString());
            combinationsOptimized(n, k);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("factorial"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int n;
            n = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            factorial(n);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("gcd"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int num1;
            num1 = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            int num2;
            num2 = Integer.parseInt(((JSONObject) jsonTestcases.get(1)).get("value").toString());
            gcd(num1, num2);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("gcdRecursion"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int a;
            a = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            int b;
            b = Integer.parseInt(((JSONObject) jsonTestcases.get(1)).get("value").toString());
            gcdRecursion(a, b);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("sumOfDigits"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int n;
            n = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            sumOfDigits(n);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("genericRoot"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int n;
            n = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            genericRoot(n);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("isHarshad"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            long n;
            n = Long.parseLong(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            isHarshad(n);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("isKrishnamurthy"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int n;
            n = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            isKrishnamurthy(n);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("lcm"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int num1;
            num1 = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            int num2;
            num2 = Integer.parseInt(((JSONObject) jsonTestcases.get(1)).get("value").toString());
            lcm(num1, num2);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("lucasSeriesIteration"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int n;
            n = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            lucasSeriesIteration(n);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("numberOfDigits"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int number;
            number = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            numberOfDigits(number);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("isPalindrome"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int number;
            number = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            isPalindrome(number);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("isPerfectNumber"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int number;
            number = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            isPerfectNumber(number);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("modPow"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            long a;
            a = Long.parseLong(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            long b;
            b = Long.parseLong(((JSONObject) jsonTestcases.get(1)).get("value").toString());
            long c;
            c = Long.parseLong(((JSONObject) jsonTestcases.get(2)).get("value").toString());
            modPow(a, b, c);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("isPythagTriple"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int a;
            a = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            int b;
            b = Integer.parseInt(((JSONObject) jsonTestcases.get(1)).get("value").toString());
            int c;
            c = Integer.parseInt(((JSONObject) jsonTestcases.get(2)).get("value").toString());
            isPythagTriple(a, b, c);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("reverseNumber"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            int number;
            number = Integer.parseInt(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            reverseNumber(number);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("calculateModularExponentiation"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            long base;
            base = Long.parseLong(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            long exponent;
            exponent = Long.parseLong(((JSONObject) jsonTestcases.get(1)).get("value").toString());
            long mod;
            mod = Long.parseLong(((JSONObject) jsonTestcases.get(2)).get("value").toString());
            calculateModularExponentiation(base, exponent, mod);
         }
      }
   }
   {
      JSONArray jsonMethod = ((JSONArray) jsonObject.get("calculateJacobi"));
      if(jsonMethod != null){
         for(Object testpath : jsonMethod){
            TEST_PATH = ((JSONObject) testpath).get("filepath").toString();
            file = new File(TEST_PATH);
            if (file.exists()) {
               file.delete();
            }
            JSONArray jsonTestcases = (JSONArray) ((JSONObject) testpath).get("testcase");
            long a;
            a = Long.parseLong(((JSONObject) jsonTestcases.get(0)).get("value").toString());
            long num;
            num = Long.parseLong(((JSONObject) jsonTestcases.get(1)).get("value").toString());
            calculateJacobi(a, num);
         }
      }
   }
   } catch (Exception e) {
      e.printStackTrace();
   }
}
}
