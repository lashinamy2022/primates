package enumerate;

/**
 * This is an enumeration of monkey's favorite food.
 *
 */
public enum MonkeySize {
  
  SMALL("SMALL"), 
  MEDIUM("MEDIU "),
  LARGE("LARGE");

  private final String disp;

  private MonkeySize(String disp) {
    this.disp = disp;
  }

  @Override
  public String toString() {
    return disp;
  }


}
