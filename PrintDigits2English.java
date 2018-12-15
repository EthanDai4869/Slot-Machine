/*
 * Name: Yingfeng Dai
 * Login: cs11face
 * Date: November 15, 2017
 * File: PrintDigits2English.java
 * Sources of Help: https://cseweb.ucsd.edu/~ricko/CSE11/PA7.pdf 
 *                  week 6 discussion worksheet
 *                  
 * This program takes numbers entered by the user and printing them
 * out in English one digit at a time.
 */

/**
 * This class is going to use recursion to print the number that entered by
 * the user and printing them out in English one digit at a time.
 */

public class PrintDigits2English {

  private static final int SINGLE_DIGIT_CHECKER = 10;

  /**
   * This is the main method, and it will take the arguments entered by user
   * and throw the exception if there is any.
   *
   * @param args represents the arguments of each command line when the 
   *             program is run from the command line.
   */

  public static void main(String[] args) {

    int remainingArgs = args.length;
    int argsIndex = 0;

    // The number entered by the user.
    int num;

    // Make sure the user enter any number.
    if(args.length == 0) {
      System.out.println(PA7Strings.USAGE_MSG);
      System.exit(1);
    }

    // Print every number entered by the user one at a time.
    while(remainingArgs > 0) {

      // Make sure the user enter the valid integer.
      try {
        num = Integer.parseInt(args[argsIndex]);
        if(num < 0) {
          System.out.print(PA7Strings.NEG_NUM_STR);
        }
        printDigits2Num(num);
        System.out.println(".");
      } catch(NumberFormatException e) {
        System.out.printf(PA7Strings.INVALID_ERR_STR, args[argsIndex]);
      }

      argsIndex++;
      remainingArgs--;

    }

    System.out.println();

  } // end main.

  /**
   * This is the recursive method used to print out each digit of a number in 
   * English one at a time.
   *
   * @param num represents the number that will be printed out in English.
   */

  private static void printDigits2Num(int num) {

    // Each digit of a number that given by the user.
    int digit;

    if(num < SINGLE_DIGIT_CHECKER && num > -SINGLE_DIGIT_CHECKER ) {
      // Base case.
      digit = num;
      if(digit < 0) {
        digit = -digit;
      }      
      System.out.print(PA7Strings.STR_ARRAY[digit]);
    } else {
      // Recursive case.
      digit = num % SINGLE_DIGIT_CHECKER;
      num = num / SINGLE_DIGIT_CHECKER;
      printDigits2Num(num);
      if(digit < 0) {
        digit = -digit;
      }      
      System.out.print(PA7Strings.NUM_SEP + PA7Strings.STR_ARRAY[digit]);
    }

  }

} // end class.
