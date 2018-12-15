/*
 * Name: Yingfeng Dai
 * Login: cs11face
 * Date: November 14, 2017
 * File: Winner.java
 * Sources of Help: https://cseweb.ucsd.edu/~ricko/CSE11/PA7.pdf
 *                  week 6 discussion worksheet
 *                  docs.oracle.com/javase/7/docs/api/java/awt/Toolkit.html
 *
 * This file can create a class Winner, which will handle the win logic.
 */

import objectdraw.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class will handle the win logic and implement ActionListener.
 */

public class Winner extends ActiveObject 
                    implements ActionListener {

  private static final int HALF_SIZE_DIVIDER = 2;

  private SlotWheel wheel1;
  private SlotWheel wheel2;
  private SlotWheel wheel3;
  private JLabel winLabel;
  private JLabel lossLabel;
  private JButton spinButton;

  // The delay time.
  private int delay;

  private VisibleImage winImage;

  // Counts for wins and losses.
  private int wins;
  private int losses;

  // Whether the slot machines is spinning or not.
  private boolean isSpinning;

  /**
   * This constructor can create Winner that can handle the win logic.
   *
   * @param w1 represents the first slot wheel.
   * @param w2 represents the second slot wheel.
   * @param w3 represents the third slot wheel.
   * @param win represents the win label.
   * @param loss represents the loss label.
   * @param spin represents the spin button.
   * @param image represents the win image.
   * @param delayTime represents the delay time.
   * @param canvas represents the canvas that win image will be created on.
   */

  public Winner(SlotWheel w1, SlotWheel w2, SlotWheel w3, JLabel win,
  JLabel loss, JButton spin, Image image, int delayTime, 
  DrawingCanvas canvas) {
    this.wheel1 = w1;
    this.wheel2 = w2;
    this.wheel3 = w3;
    this.winLabel = win;
    this.lossLabel = loss;
    this.spinButton = spin;
    this.delay = delayTime;
    this.winImage = new VisibleImage(image, 0, 0, canvas);
    this.winImage.moveTo(canvas.getWidth() / HALF_SIZE_DIVIDER - 
    winImage.getWidth() / HALF_SIZE_DIVIDER, canvas.getHeight() / 
    HALF_SIZE_DIVIDER - winImage.getHeight() / HALF_SIZE_DIVIDER);
    this.winImage.hide();
    this.start();
  }

  /**
   * This is the forever loop, which determines when to show win image and
   * updates the number of wins and losses.
   */

  public void run() {
    while(true) {
      if(isSpinning) {
        if(wheel3.getStop()) {
          this.isSpinning = false;
          this.spinButton.setEnabled(true);
          if(wheel1.getIndex() == wheel2.getIndex() && wheel1.getIndex() == 
          wheel3.getIndex()) {
            this.winImage.show();
            this.wins++;
            this.winLabel.setText(String.format(PA7Strings.WIN_LABEL, wins));
          } else {
            this.losses++;
            this.lossLabel.setText(String.format(PA7Strings.LOSS_LABEL, 
            losses));
          }
        }
      }
      pause(delay);
    }
  }

  /**
   * On a button clicked, disable the button, set isSpinning to be true, and
   * hide win image.
   *
   * @param evt represents an ActionEvent object.
   */
  @Override
  public void actionPerformed(ActionEvent evt) {
    this.spinButton.setEnabled(false);
    this.isSpinning = true;
    this.winImage.hide();
  }

} // end class.
