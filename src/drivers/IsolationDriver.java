package drivers;

import enumerate.Species;
import sanctuary.Isolation;
import sanctuary.Monkey;

/**
 * This is to test the functions of {@link Isolation}.
 *
 */
public class IsolationDriver extends BaseDriver {

  /**
   * This is to test whether the constructor can throw {@link IllegalArgumentException} correctly 
   * with invalid parameters.
   */
  public static void testConstructor() {
    try {
      new Isolation(0);
      System.out.println("test constructor with 0 : error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test constructor with 0 : good");
    }
    try {
      new Isolation(-1);
      System.out.println("test constructor with -1 : error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test constructor with -1 : good");
    }
  }
  
  /**
   * This is to test moveIntoIsolation when there is no empty cage.
   */
  public static void testMoveInWhenNoEmptyCage() {
    try {
      Isolation iso = new Isolation(2);
      iso.moveIntoIsolation(createMonkey());
      iso.moveIntoIsolation(createMonkey());
      iso.moveIntoIsolation(createMonkey());
      System.out.println("test moveIntoIsolation when there is no empty cage : error");
    } catch (IllegalStateException ex) {
      System.out.println("test moveIntoIsolation when there is no empty cage : good");
    }
  }
  
  /**
   * This is to test moveIntoIsolation when the monkey exists.
   */
  public static void testMoveInWhenMonkeyExists() {
    try {
      Isolation iso = new Isolation(2);
      iso.moveIntoIsolation(createMonkey(1, 1, 1, 10, 20, 2, 1));
      iso.moveIntoIsolation(createMonkey(1, 1, 1, 10, 20, 2, 1));
      System.out.println("test moveIntoIsolation when the monkey exists : error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test moveIntoIsolation when the monkey exists : good");
    }
  }
  
  /**
   * This is to test the emptyCageNum of moveIntoIsolation.
   */
  public static void testMoveInEmptyCageNum() {
    Isolation iso = new Isolation(2);
    iso.moveIntoIsolation(createMonkey());
    int leftCages = iso.getEmptyCageNum();
    System.out.println("test the emptyCageNum of "
        + "moveIntoIsolation : " + (leftCages == 1 ? "good" : "error"));
  }
  
  /**
   * This is to test moveOutFromIsolation when the monkey does not exist.
   */
  public static void testMoveOutWithoutExisting() {
    try {
      Isolation iso = new Isolation(2);
      iso.moveIntoIsolation(createMonkey(1, 1, 1, 10, 20, 2, 1));
      iso.moveOutFromIsolation(createMonkey());
      System.out.println("test moveOutFromIsolation when the monkey does not exist : error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test moveOutFromIsolation when the monkey does not exist : good");
    }
  }
  
  /**
   * This is to test the emptyCageNum of moveOutFromIsolation.
   */
  public static void testMoveOutEmptyCageNum() {
    Isolation iso = new Isolation(2);
    iso.moveIntoIsolation(createMonkey());
    iso.moveIntoIsolation(createMonkey(1, 1, 1, 10, 20, 2, 1));
    iso.moveOutFromIsolation(createMonkey(1, 1, 1, 10, 20, 2, 1));
    int leftCages = iso.getEmptyCageNum();
    System.out.println("test the emptyCageNum of "
        + "moveOutFromIsolation : " + (leftCages == 1 ? "good" : "error"));
  }
  
  /**
   * This is to test reportSpecies
   *  when there is no monkey/ after moveIntoIsolation/ after moveOutFromIsolation.
   */
  public static void testReportSpecies() {
    Isolation iso = new Isolation(3);
    // nameIndex,  speciesIndex,  sexIndex, 
    // size,  weight,  age,  foodIndex
    String report = iso.reportSpeciesInIsolation().toString();
    System.out.println("test report Species when there is no monkey : " + report.toString());
    iso.moveIntoIsolation(createMonkey(1, 0, 1, 10, 20, 2, 1));
    iso.moveIntoIsolation(createMonkey(2, 1, 1, 10, 20, 2, 1));
    iso.moveIntoIsolation(createMonkey(3, 1, 1, 10, 20, 2, 1));
    report = iso.reportSpeciesInIsolation().toString();
    System.out.println("test report Species after moveIntoIsolation : " + report.toString());
    iso.moveOutFromIsolation(createMonkey(3, 1, 1, 10, 20, 2, 1));
    report = iso.reportSpeciesInIsolation().toString();
    System.out.println("test report Species after moveOutFromIsolation : " + report.toString());
  }
  
  /**
   * This is to testLookUpSpeceis 
   * when this is no monkey/ after moveIntoIsolation/ after moveOutFromIsolation.
   */
  public static void testLookUpSpecies() {
    Isolation iso = new Isolation(3);
    // nameIndex,  speciesIndex,  sexIndex, 
    // size,  weight,  age,  foodIndex
    String report = iso.lookUpSpecies(Species.DRILL);
    System.out.println("test lookUpSpecies when there is no monkey : " + report.toString());
    iso.moveIntoIsolation(createMonkey(1, 0, 1, 10, 20, 2, 1));
    iso.moveIntoIsolation(createMonkey(2, 1, 1, 10, 20, 2, 1));
    iso.moveIntoIsolation(createMonkey(3, 1, 1, 10, 20, 2, 1));
    report = iso.lookUpSpecies(Species.DRILL);
    System.out.println("test lookUpSpecies after moveIntoIsolation : " + report.toString());
    iso.moveOutFromIsolation(createMonkey(1, 0, 1, 10, 20, 2, 1));
    report = iso.lookUpSpecies(Species.DRILL);
    System.out.println("test lookUpSpecies after moveOutFromIsolation : " + report.toString());
  }
  
  /**
   * This is to test generateMonkeyListInIsolation 
   * when this is no monkey/ after moveIntoIsolation/ after moveOutFromIsolation.
   */
  public static void testGenerateMonkeyList() {
    Isolation iso = new Isolation(3);
    // nameIndex,  speciesIndex,  sexIndex, 
    // size,  weight,  age,  foodIndex
    String report = iso.generateMonkeyListInIsolation().toString();
    System.out.println("test generateMonkeyList when there is no monkey : " + report.toString());
    iso.moveIntoIsolation(createMonkey(1, 0, 1, 10, 20, 2, 1));
    iso.moveIntoIsolation(createMonkey(2, 1, 1, 10, 20, 2, 1));
    iso.moveIntoIsolation(createMonkey(3, 1, 1, 10, 20, 2, 1));
    report = iso.generateMonkeyListInIsolation().toString();
    System.out.println("test generateMonkeyList after moveIntoIsolation : " + report.toString());
    iso.moveOutFromIsolation(createMonkey(1, 0, 1, 10, 20, 2, 1));
    report = iso.generateMonkeyListInIsolation().toString();
    System.out.println("test generateMonkeyList after moveOutFromIsolation : " + report.toString());
  }
  
  /**
   * This is to test shoppingListInIsolation
   *  when this is no monkey/ after moveIntoIsolation/ after moveOutFromIsolation.
   */
  public static void testShoppingList() {
    Isolation iso = new Isolation(4);
    // nameIndex,  speciesIndex,  sexIndex, 
    // size,  weight,  age,  foodIndex
    String report = iso.shoppingListInIsolation().toString();
    System.out.println("test shoppingList when there is no monkey : " + report.toString());
    Monkey m1 = createMonkey(1, 0, 1, 10, 20, 2, 1);
    Monkey m2 = createMonkey(2, 1, 1, 10, 20, 2, 1);
    Monkey m3 = createMonkey(3, 1, 1, 10, 20, 2, 1);
    iso.moveIntoIsolation(m1);
    iso.moveIntoIsolation(m2);
    iso.moveIntoIsolation(m3);
    int expected1 = m1.getFeedingGram();
    expected1 += m2.getFeedingGram();
    expected1 += m3.getFeedingGram();
    report = iso.shoppingListInIsolation().toString();
    System.out.println("the expected value : " + expected1);
    System.out.println("test shoppingList after moveIntoIsolation : " + report.toString());
    
    Monkey m4 = createMonkey(3, 0, 1, 10, 20, 2, 0);
    iso.moveIntoIsolation(m4);
    int expected2 = m4.getFeedingGram();
    System.out.println("the expected value : " + expected1 + "," + expected2);
    report = iso.shoppingListInIsolation().toString();
    System.out.println("test shoppingList after moveIntoIsolation : " + report.toString());
    
    iso.moveOutFromIsolation(createMonkey(1, 0, 1, 10, 20, 2, 1));
    report = iso.shoppingListInIsolation().toString();
    System.out.println("test shoppingList after moveOutFromIsolation : " + report.toString());
  }
  
  /**
   * This is to test expand method.
   */
  public static void testExpand() {
    Isolation iso = new Isolation(4);
    int num = iso.getEmptyCageNum();
    System.out.println(num);
    iso.expand(3);
    num = iso.getEmptyCageNum();
    System.out.println(num);
    System.out.println("test expand : " + (num == 7 ? "good" : "error"));
  }
  
  /**
   * Driver program for Isolation to show how it works.
   * @param args not used
   */
  public static void main(String[] args) {
    testConstructor();
    testMoveInWhenNoEmptyCage();
    testMoveInWhenMonkeyExists();
    testMoveInEmptyCageNum();
    testMoveOutWithoutExisting();
    testMoveOutEmptyCageNum();
    testReportSpecies();
    testLookUpSpecies();
    testGenerateMonkeyList();
    testShoppingList();
    testExpand();
  }

}
