/*
 * Name: Yingfeng Dai
 * Login: cs11face
 * Date: November 14, 2017
 * File: Slots.java
 * Sources of Help: https://cseweb.ucsd.edu/~ricko/CSE11/PA7.pdf
 *                  week 6 discussion worksheet
 *                  docs.oracle.com/javase/tutorial/uiswing/layout/box.html
 *
 * This program simulates the classic slot machines seen in casinos.
 */

import objectdraw.*;
import Acme.*;
import javax.swing.*;
import java.awt.*;

/**
 * This class is the main GUI controller class, which parses arguments, lays 
 * out GUI components, and deal with event handling.
 */

public class Slots extends WindowController {

  private static final int NUM_OF_IMAGES = 8;
  public static final double IMAGE_WIDTH = 110;
  public static final double IMAGE_HEIGHT = 145;
  private static final int FRAME_WIDTH = 500;
  private static final int FRAME_HEIGHT = 280;
  private static final double WHEELS_Y_OFFSET = 5;
  private static final double SPACE_BETWEEN_WHEELS = 5;
  private static final int WHEEL_1_TICKS = 22;
  private static final int WHEEL_2_TICKS = WHEEL_1_TICKS + 6;
  private static final int WHEEL_3_TICKS = WHEEL_2_TICKS + 6;
  private static final int MIN_DELAY = 10;
  private static final int MAX_DELAY = 2000;
  private static final int DEFAULT_DELAY = 75;
  private static final int WHEEL_1_DELAY = 0;
  private static final int WHEEL_2_DELAY = WHEEL_1_DELAY + 25;
  private static final int WHEEL_3_DELAY = WHEEL_2_DELAY + 25;

  private static final int HALF_SIZE_DIVIDER = 2;
  private static final int MAX_CMD_LINE_ARGS = 1;
  private static final int DELAY_INDEX = 0;
  private static final int LABEL_ROW = 2;
  private static final int LABEL_COLUMN = 1;
  private static final int COUNT_ROW = 1;
  private static final int COUNT_COLUMN = 4;

  // Font size for title.
  private static final int HEADER_FONT_SIZE = 24;

  // Font size for wins and losses lable.
  private static final int COUNT_FONT_SIZE = 16;

  private SlotWheel wheel1;
  private SlotWheel wheel2;
  private SlotWheel wheel3;

  private Winner youWin;

  private int wheelDelay;

  /**
   * This constructor default the delay time by using the value given by the
   * user.
   */

  public Slots(int delayTime) {
    this.wheelDelay = delayTime;
  }

  /**
   * This is the main method, and it parses the arguments given by the user and
   * runs this as an application with Acme.MainFrame.
   *
   * @param args represents the arguments of each command line when the program
   *             is running from the command line.
   */

  public static void main(String[] args) {

    int delay = DEFAULT_DELAY;

    // Check whether the delay value is valid and in range if given by user.
    if(args.length == MAX_CMD_LINE_ARGS) {
      try {
          delay = Integer.parseInt(args[DELAY_INDEX]);
        if(delay < MIN_DELAY || delay > MAX_DELAY) {
          System.out.printf(PA7Strings.ERR_DELAY_RANGE, MIN_DELAY, MAX_DELAY);
          System.out.println(PA7Strings.USAGE);
          System.exit(1);
        } 
      } catch(NumberFormatException e) {
        System.out.printf(PA7Strings.ERR_INVALID_DELAY, args[DELAY_INDEX]);
        System.out.println(PA7Strings.USAGE);
        System.exit(1);
      }
    }

    // Make sure the user not give too many arguments.
    if(args.length > MAX_CMD_LINE_ARGS) {
      System.out.println(PA7Strings.ERR_INVALID_ARGS);
      System.out.println(PA7Strings.USAGE);
      System.exit(1);
    }

    new Acme.MainFrame(new Slots(delay), args, FRAME_WIDTH, FRAME_HEIGHT);

  } // end main.

  /**
   * It lays out the GUI components in the canvas at beginning.
   */

  public void begin() {

    JPanel labelPanel = new JPanel();
    labelPanel.setLayout(new GridLayout(LABEL_ROW, LABEL_COLUMN));

    // Panel for title.
    JPanel headerPanel = new JPanel();
    JLabel header = new JLabel(String.format(PA7Strings.SLOTS_TITLE, 
                        "Yingfeng's"));
    header.setFont(new Font(Font.SANS_SERIF, Font.BOLD, HEADER_FONT_SIZE));
    headerPanel.add(header, SwingConstants.CENTER);

    // Panel for wins and losses.
    JPanel countPanel = new JPanel();
    countPanel.setLayout(new GridLayout(COUNT_ROW, COUNT_COLUMN));
    JLabel winLabel = new JLabel(String.format(PA7Strings.WIN_LABEL, 0), 
                          SwingConstants.CENTER);
    winLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, COUNT_FONT_SIZE));
    JLabel lossLabel = new JLabel(String.format(PA7Strings.LOSS_LABEL, 0), 
                           SwingConstants.CENTER);
    lossLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, COUNT_FONT_SIZE));

    countPanel.add(Box.createHorizontalGlue());
    countPanel.add(winLabel);
    countPanel.add(lossLabel);
    countPanel.add(Box.createHorizontalGlue());

    labelPanel.add(headerPanel);
    labelPanel.add(countPanel);
    this.add(labelPanel, BorderLayout.NORTH);

    JPanel buttonPanel = new JPanel();
    JButton spinButton = new JButton(PA7Strings.BUTTON_TEXT);
    buttonPanel.add(spinButton, SwingConstants.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.validate();

    Image[] image = new Image[NUM_OF_IMAGES];

    for(int i=0; i < NUM_OF_IMAGES; i++) {
      image[i] = getImage(PA7Strings.IMG_NAMES[i]);
    }

    // Creates three wheels in the canvas.
    this.wheel1 = new SlotWheel(image, WHEEL_1_TICKS, wheelDelay + 
    WHEEL_1_DELAY, canvas.getWidth() / HALF_SIZE_DIVIDER - IMAGE_WIDTH / 
    HALF_SIZE_DIVIDER - SPACE_BETWEEN_WHEELS - IMAGE_WIDTH, WHEELS_Y_OFFSET, 
    canvas);
    this.wheel2 = new SlotWheel(image, WHEEL_2_TICKS, wheelDelay + 
    WHEEL_2_DELAY, canvas.getWidth() / HALF_SIZE_DIVIDER - IMAGE_WIDTH / 
    HALF_SIZE_DIVIDER, WHEELS_Y_OFFSET, canvas);
    this.wheel3 = new SlotWheel(image, WHEEL_3_TICKS, wheelDelay + 
    WHEEL_3_DELAY, canvas.getWidth() / HALF_SIZE_DIVIDER + IMAGE_WIDTH / 
    HALF_SIZE_DIVIDER + SPACE_BETWEEN_WHEELS, WHEELS_Y_OFFSET, canvas);

    this.youWin = new Winner(wheel1, wheel2, wheel3, winLabel, lossLabel, 
    spinButton, getImage("winner.jpg"), wheelDelay, canvas);

    spinButton.addActionListener(wheel1);
    spinButton.addActionListener(wheel2);
    spinButton.addActionListener(wheel3);
    spinButton.addActionListener(youWin);

  }

} // end class.
