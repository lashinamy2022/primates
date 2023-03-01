package enumerate;

/**
 * This is an enumeration of monkey's favorite food.
 *
 */
public enum Foods {
  
  EGGS("EGGS"), 
  FRUITS("FRUITS"),
  INSECTS("INSECTS"),
  LEAVES("LEAVES"),
  NUTS("NUTS"),
  SEEDS("SEEDS"),
  TREE_SAP("TREE_SAP");

  private final String disp;

  private Foods(String disp) {
    this.disp = disp;
  }

  @Override
  public String toString() {
    return disp;
  }


}
