package enumerate;

import sanctuary.Monkey;

/**
 * This is an enumeration of {@link Monkey}'s species.
 *
 */
public enum Species {
  DRILL("DRILL"), 
  GUEREZA("GUEREZA"),
  HOWLER("HOWLER"),
  MANGABEY("MANGABEY"),
  SAKI("SAKI"),
  SPIDER("SPIDER"),
  SQUIRREL("SQUIRREL"),
  TAMARIN("TAMARIN");

  private final String disp;

  private Species(String disp) {
    this.disp = disp;
  }

  @Override
  public String toString() {
    return disp;
  }

}
