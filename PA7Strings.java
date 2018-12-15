/*
 * Name: Yingfeng Dai
 * Login: cs11face
 * Date: November 13, 2017
 * File: PA7Strings.java
 * Sources of Help: https://cseweb.ucsd.edu/~ricko/CSE11/PA7.pdf 
 *                  
 * This file can create a class PA7Strings, which provided useful String
 * constants.
 */

public class PA7Strings {

  /* Strings for PrintDigits2English */
  public static final String[] STR_ARRAY = {"Zero", "One", "Two", "Three",
                                             "Four", "Five", "Six", "Seven",
                                             "Eight", "Nine"};
  public static final String NUM_SEP = " ";
  public static final String USAGE_MSG =
        "Usage: java PrintDigits2English num1 [num2 ...]\n"+
        "       num1, num2, ...: must be valid integer\n";
  public static final String NEG_NUM_STR = "Negative ";
  public static final String INVALID_ERR_STR =
        "Error: %s must be a valid integer\n";


  /* Strings for slots. */

  public static final String USAGE = "Usage: java Slots [delay]";
  public static final String EC_USAGE = "Usage: java EC_Slots [delay]";

  public static final String ERR_INVALID_ARGS =
    "Error: Invalid Number of arguments.";

  public static final String ERR_DELAY_RANGE =
    "Error: Delay must be in the range [%d, %d]\n";

  public static final String ERR_INVALID_DELAY = "Error: Invalid Delay %s\n";

  public static final String SLOTS_TITLE = "%s Viva Lost Wages!";

  public static final String WIN_LABEL = "Wins: %d";

  public static final String LOSS_LABEL = "Losses: %d";

  public static final String BUTTON_TEXT = "Click to Spin!";

  public static final String[] IMG_NAMES = {
    "sungod.jpg", "sungod-bear.jpg",
    "bear.jpg", "bear-triton.jpg",
    "triton.jpg", "triton-library.jpg",
    "library.jpg", "library-sungod.jpg"
  };

}
