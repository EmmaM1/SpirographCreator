/**
 * Hypocycloid class models the hypocycloid
 */
public class Hypocycloid {

  private final int fixedRadius; // radius of fixed circle
  private final int movingRadius; // radius of moving circle
  private final int offset; // offset of pen point in moving circle
  private int x; // current x coordinate
  private int y; // current y coordinate
  private double t = 0.0; // for parametric equation

  /**
   * Constructor for Hypocycloid sets instance variables to user provided values
   * @param fixedRadius Radius of fixed circle
   * @param movingRadius Radius of moving circle
   * @param offset Offset of pen point in moving circle
   */
  public Hypocycloid(int fixedRadius, int movingRadius, int offset) {
    this.fixedRadius = fixedRadius;
    this.movingRadius = movingRadius;
    this.offset = offset;
    calculateCoordinates();
  }

  /**
   * @return int Returns current x coordinate
   */
  public int getX() {
    return x;
  }

  /**
   * @return int Returns current y coordinate
   */
  public int getY() {
    return y;
  }

  /**
   * Method to calculate current coordinates using parametric equation and user provided parameters
   */
  public void calculateCoordinates() {
    double radiiDifference = fixedRadius - movingRadius;
    x = (int) Math.round((radiiDifference) * Math.cos(t) + offset * Math.cos((radiiDifference / movingRadius) * t));
    y = (int) Math.round((fixedRadius - movingRadius) * Math.sin(t) - offset * Math.sin((radiiDifference / movingRadius) * t));
  }

  /**
   * Increases t to get next value from parametric equation
   */
  public void update() {
    if (t < 1000) {
      t += 0.01;
    } else {
      t = 0.0;
    }
    calculateCoordinates();
  }

}
