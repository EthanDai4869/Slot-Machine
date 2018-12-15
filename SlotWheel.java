/*
 * Name: Yingfeng Dai
 * Login: cs11face
 * Date: November 14, 2017
 * File: SlotWheel.java
 * Sources of Help: https://cseweb.ucsd.edu/~ricko/CSE11/PA7.pdf
 *                  week 6 discussion worksheet
 *                  docs.oracle.com/javase/7/docs/api/java/awt/Toolkit.html
 *
 * This file can create a class SlotWheel, from which animated wheel can be
 * created.
 */

import objectdraw.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

/**
 * This class defines what the SlotWheel is (a framed rectangular and a image)
 * and what a SlotWheel will do (spinning).
 */

public class SlotWheel extends ActiveObject 
                       implements ActionListener {

  // The number of images can be shown.
  private static final int VISIBLE_IMAGE = 4;

  private static final int EVEN_INDEX_MAKER = 2;
  private static final int TOTAL_IMAGES = 8;

  private Random random;

  private Image[] image;
  private VisibleImage visibleImage;

  // The index for the visible image.
  private int visibleIndex;

  // The ticks for each wheel.
  private int wheelTicks;

  private int remainingTicks;

  // The delay time for each wheel.
  private int wheelDelay;

  // Whether the last wheel is stopped or not.
  private boolean isStop = true;

  /**
   * This constructor can create Slotwheel that can simulates the slot
   * machines seen in casinos.
   *
   * @param myImage represents the images that will be used.
   * @param ticks represents the ticks of each wheel.
   * @param delay represents the delay time of each wheel.
   * @param xOffset represents the x coordinate of upper left corner.
   * @param xOffset represents the y coordinate of upper left corner.
   * @param canvas represents the canvas that wheels will be created on.
   */

  public SlotWheel(Image[] myImage, int ticks, int delay, double xOffset,
  double yOffset, DrawingCanvas canvas) {
    this.random = new Random();
    this.visibleIndex = random.nextInt(VISIBLE_IMAGE) * EVEN_INDEX_MAKER;
    this.wheelTicks = ticks;
    this.wheelDelay = delay;
    this.image = myImage;
    this.visibleImage = new VisibleImage(image[visibleIndex], xOffset, yOffset, 
    canvas);
    new FramedRect(xOffset, yOffset, Slots.IMAGE_WIDTH, Slots.IMAGE_HEIGHT,
    canvas);
    this.start();
  }

  /**
   * This is the forever loop, which simulates the running progress of
   * slot machines seen in casinos.
   */

  public void run() {
    while(true) {
      if(remainingTicks > 0) {
        // Make sure the index go back to 0 when past the end of the array.
        this.visibleIndex = (visibleIndex + 1) % TOTAL_IMAGES;
        this.visibleImage.setImage(image[visibleIndex]);
        this.remainingTicks--;
      } else {
        this.isStop = true;
      }
      pause(wheelDelay);
    }
  }

  /**
   * On a button clicked, reset the number of ticks and pick another image to
   * show, and set the isStop to false.
   *
   * @param evt represents an ActionEvent object.
   */
  @Override
  public void actionPerformed(ActionEvent evt) {
    this.remainingTicks = wheelTicks;
    this.visibleIndex = random.nextInt(VISIBLE_IMAGE) * EVEN_INDEX_MAKER;
    this.visibleImage.setImage(image[visibleIndex]);
    this.isStop = false;
  }

  /**
   * Get whether the last wheel is stopped.
   *
   * @return represents the state of the last wheel, is stop or not.
   */

  public boolean getStop() {
    return isStop;
  }

  /**
   * Return the index of the image of each wheel.
   *
   * @return represents the index of the image of each wheel.
   */

  public int getIndex() {
    return visibleIndex;
  }

} // end class.
