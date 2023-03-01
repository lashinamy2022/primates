package drivers;


import enumerate.Species;
import sanctuary.Enclosures;
import sanctuary.Monkey;

/**
 * This class is to test the functions of {@link Enclosures}.
 */
public class EnclosureDriver extends BaseDriver {
  
  
  /**
   * This is to test whether the constructor can throw {@link IllegalArgumentException} correctly 
   * with invalid parameters.
   */
  public static void testConstructor() {
    try {
      new Enclosures(0, 0);
      System.out.println("test constructor with 0 : error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test constructor with 0 : good");
    }
    try {
      new Enclosures(-1, -1);
      System.out.println("test constructor with -1 : error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test constructor with -1 : good");
    }
  }
  
  /**
   * This is to test moveIntoEnclosures when there is no empty enclosure with same species.
   */
  public static void testMoveInWhenNoEmptyEnclosureWithSameSpecies() {
    Enclosures enc = new Enclosures(2, 15);
    int area = 0;
    int num = 0;
    try {
      for (int i = 0; i < 7; i++) {
        // nameIndex,  speciesIndex,  sexIndex, 
        // size,  weight,  age,  foodIndex
        Monkey m = createMonkey(i, 0, 1, 10, 20, 2, 1);
        enc.moveIntoEnclosures(m);
        area += m.getLivingArea();
        num++;
      }
      System.out.println(num + " monkeys occupied " + area + " square meter");
      System.out.println("test moveIntoEnclosures "
          + "when there is no empty enclosure with same species : error");
    } catch (IllegalStateException ex) {
      System.out.println(enc.generateSignForEnclosure("enc-00001"));
      System.out.println(enc.generateSignForEnclosure("enc-00002"));
      System.out.println(num + " monkeys occupied " + area + " square meter");
      System.out.println("test moveIntoEnclosures "
          + "when there is no empty enclosure with same species : good");
    }
  }
  
  /**
   * This is to test moveIntoEnclosures when there is no empty enclosure with different species.
   */
  public static void testMoveInWhenNoEmptyEnclosureWithDiffSpecies() {
    Enclosures enc = new Enclosures(2, 15);
    int area = 0;
    int num = 0;
    try {
      for (int i = 0; i < 3; i++) {
        // nameIndex,  speciesIndex,  sexIndex, 
        // size,  weight,  age,  foodIndex
        Monkey m = createMonkey(i, i, 1, 10, 20, 2, 1);
        enc.moveIntoEnclosures(m);
        area += m.getLivingArea();
        num++;
      }
      System.out.println(enc.reportSpeciesInEnclosures());
      System.out.println(num + " monkeys occupied " + area + " square meter");
      System.out.println("test moveIntoEnclosures when "
          + "there is no empty enclosure with different species : error");
    } catch (IllegalStateException ex) {
      System.out.println(enc.generateSignForEnclosure("enc-00001"));
      System.out.println(enc.generateSignForEnclosure("enc-00002"));
      System.out.println(enc.reportSpeciesInEnclosures());
      System.out.println(num + " monkeys occupied " + area + " square meter");
      System.out.println("test moveIntoEnclosures when "
          + "there is no empty enclosure with different species: good");
    }
  }
  
  
  /**
   * This is to test moveIntoEnclosures when the monkey exists.
   */
  public static void testMoveInWhenMonkeyExists() {
    try {
      Enclosures enc = new Enclosures(2, 20);
      enc.moveIntoEnclosures(createMonkey(1, 1, 1, 0, 20, 2, 1));
      enc.moveIntoEnclosures(createMonkey(1, 1, 1, 0, 20, 2, 1));
      System.out.println("test moveIntoEnclosures when the monkey exists : error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test moveIntoEnclosures when the monkey exists : good");
    }
  }
  
  /**
   * This is to test the emptyCageNum of moveIntoEnclosures.
   */
  public static void testMoveInEmptyEnclosureNum() {
    Enclosures enc = new Enclosures(2, 20);
    enc.moveIntoEnclosures(createMonkey());
    int leftEnclosures = enc.getEmptyEnclosureNum();
    System.out.println("test the emptyCageNum of moveIntoEnclosures : " 
         + (leftEnclosures == 1 ? "good" : "error"));
  }
  
  /**
   * This is to test moveOutFromEnclosures when the monkey does not exist.
   */
  public static void testMoveOutNotExist() {
    try {
      Enclosures enc = new Enclosures(2, 20);
      enc.moveIntoEnclosures(createMonkey(1, 1, 1, 0, 20, 2, 1));
      enc.moveOutFromEnclosures(createMonkey());
      System.out.println("test moveOutFromEnclosures when the monkey does not exist : error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test moveOutFromEnclosures when the monkey does not exist : good");
    }
  }
  
  /**
   * This is to test the emptyEnclosureNum of moveOutFromEnclosures.
   */
  public static void testMoveOutEmptyEnclosureNum() {
    Enclosures enc = new Enclosures(2, 20);
    enc.moveIntoEnclosures(createMonkey(1, 0, 1, 10, 20, 2, 1));
    enc.moveIntoEnclosures(createMonkey(1, 1, 1, 10, 20, 2, 1));
    enc.moveOutFromEnclosures(createMonkey(1, 1, 1, 10, 20, 2, 1));
    int leftEnclosure = enc.getEmptyEnclosureNum();
    System.out.println("test the emptyCageNum of moveOutFromEnclosures : " 
         + (leftEnclosure == 1 ? "good" : "error"));
  }
  
  /**
   * This is to test reportSpecies 
   * when there is no monkey/ after moveIntoEnclosures/ after moveOutFromEnclosures.
   */
  public static void testReportSpecies() {
    Enclosures enc = new Enclosures(3, 20);
    // nameIndex,  speciesIndex,  sexIndex, 
    // size,  weight,  age,  foodIndex
    String report = enc.reportSpeciesInEnclosures().toString();
    System.out.println("test report Species when there is no monkey : " + report.toString());
    enc.moveIntoEnclosures(createMonkey(1, 0, 1, 10, 20, 2, 1));
    enc.moveIntoEnclosures(createMonkey(2, 1, 1, 10, 20, 2, 1));
    enc.moveIntoEnclosures(createMonkey(3, 1, 1, 10, 20, 2, 1));
    enc.moveIntoEnclosures(createMonkey(4, 1, 1, 10, 20, 2, 1));
    enc.moveIntoEnclosures(createMonkey(5, 1, 1, 10, 20, 2, 1));
    enc.moveIntoEnclosures(createMonkey(0, 1, 1, 10, 20, 2, 1));

    report = enc.reportSpeciesInEnclosures().toString();
    System.out.println("test report Species after moveIntoEnclosures : " + report.toString());
    enc.moveOutFromEnclosures(createMonkey(1, 0, 1, 10, 20, 2, 1));
    report = enc.reportSpeciesInEnclosures().toString();
    System.out.println("test report Species after moveOutFromEnclosures : " + report.toString());
  }
  
  /**
   * This is to testLookUpSpeceis 
   * when this is no monkey/ after moveIntoEnclosures/ after moveOutFromEnclosures.
   */
  public static void testLookUpSpecies() {
    Enclosures enc = new Enclosures(3, 20);
    // nameIndex,  speciesIndex,  sexIndex, 
    // size,  weight,  age,  foodIndex
    String report = enc.lookUpSpecies(Species.DRILL);
    System.out.println("test lookUpSpecies when there is no monkey : " + report.toString());
    enc.moveIntoEnclosures(createMonkey(1, 0, 1, 10, 20, 2, 1));
    enc.moveIntoEnclosures(createMonkey(2, 1, 1, 10, 20, 2, 1));
    enc.moveIntoEnclosures(createMonkey(3, 1, 1, 10, 20, 2, 1));
    report = enc.lookUpSpecies(Species.DRILL);
    System.out.println("test lookUpSpecies after moveIntoEnclosures : " + report.toString());
    enc.moveOutFromEnclosures(createMonkey(1, 0, 1, 10, 20, 2, 1));
    report = enc.lookUpSpecies(Species.DRILL);
    System.out.println("test lookUpSpecies after moveOutFromEnclosures : " + report.toString());
  }
  
  /**
   * This is to test generateMonkeyListInIsolation 
   * when this is no monkey/ after moveIntoEnclosures/ after moveOutFromEnclosures.
   */
  public static void testGenerateMonkeyList() {
    Enclosures enc = new Enclosures(3, 20);
    // nameIndex,  speciesIndex,  sexIndex, 
    // size,  weight,  age,  foodIndex
    String report = enc.generateMonkeyListInEnclosures().toString();
    System.out.println("test generateMonkeyList when there is no monkey : " + report.toString());
    enc.moveIntoEnclosures(createMonkey(1, 0, 1, 10, 20, 2, 1));
    enc.moveIntoEnclosures(createMonkey(2, 1, 1, 10, 20, 2, 1));
    enc.moveIntoEnclosures(createMonkey(3, 1, 1, 10, 20, 2, 1));
    report = enc.generateMonkeyListInEnclosures().toString();
    System.out.println("test generateMonkeyList after moveIntoEnclosures : " + report.toString());
    enc.moveOutFromEnclosures(createMonkey(1, 0, 1, 10, 20, 2, 1));
    report = enc.generateMonkeyListInEnclosures().toString();
    enc.moveOutFromEnclosures(createMonkey(2, 1, 1, 10, 20, 2, 1));
    report = enc.generateMonkeyListInEnclosures().toString();
    System.out.println("test generateMonkeyList after "
        + "moveOutFromEnclosures : " + report.toString());
  }
  
  /**
   * This is to test shoppingListInIsolation 
   * when this is no monkey/ after moveIntoEnclosures/ after moveOutFromEnclosures.
   */
  public static void testShoppingList() {
    Enclosures enc = new Enclosures(4, 20);
    // nameIndex,  speciesIndex,  sexIndex, 
    // size,  weight,  age,  foodIndex
    String report = enc.shoppingListInEnclosures().toString();
    System.out.println("test shoppingList when there is no monkey : " + report.toString());
    Monkey m1 = createMonkey(1, 0, 1, 10, 20, 2, 1);
    Monkey m2 = createMonkey(2, 1, 1, 10, 20, 2, 1);
    Monkey m3 = createMonkey(3, 1, 1, 10, 20, 2, 1);
    enc.moveIntoEnclosures(m1);
    enc.moveIntoEnclosures(m2);
    enc.moveIntoEnclosures(m3);
    int expected1 = m1.getFeedingGram();
    expected1 += m2.getFeedingGram();
    expected1 += m3.getFeedingGram();
    report = enc.shoppingListInEnclosures().toString();
    System.out.println("the expected value : " + expected1);
    System.out.println("test shoppingList after moveIntoEnclosures : " + report.toString());
    
    Monkey m4 = createMonkey(3, 0, 1, 10, 20, 2, 0);
    enc.moveIntoEnclosures(m4);
    int expected2 = m4.getFeedingGram();
    System.out.println("the expected value : " + expected1 + "," + expected2);
    report = enc.shoppingListInEnclosures().toString();
    System.out.println("test shoppingList after moveIntoEnclosures : " + report.toString());
    
    enc.moveOutFromEnclosures(createMonkey(1, 0, 1, 10, 20, 2, 1));
    report = enc.shoppingListInEnclosures().toString();
    System.out.println("test shoppingList after moveOutFromEnclosures : " + report.toString());
  }
  
  /**
   * This is to test expand method.
   */
  public static void testExpand() {
    Enclosures enc = new Enclosures(4, 20);
    int num = enc.getEmptyEnclosureNum();
    enc.expand(3);
    num = enc.getEmptyEnclosureNum();
    System.out.println("expected value :" + num);
    System.out.println("test expand : " + (num == 7 ? "good" : "error"));
  }
  
  /**
   * This is to test generateSignForEnclosure 
   * when this is no monkey/ after moveIntoEnclosures/ after moveOutFromEnclosures.
   */
  public static void testGenerateSignForEnclosure() {
    Enclosures enc = new Enclosures(1, 20);
    // nameIndex,  speciesIndex,  sexIndex, 
    // size,  weight,  age,  foodIndex
    String report = enc.generateSignForEnclosure("enc-00001");
    System.out.println("test generateMonkeyList when there is no monkey : " + report.toString());
    enc.moveIntoEnclosures(createMonkey(1, 1, 1, 10, 20, 2, 0));
    enc.moveIntoEnclosures(createMonkey(2, 1, 1, 10, 20, 2, 1));
    enc.moveIntoEnclosures(createMonkey(3, 1, 1, 10, 20, 2, 2));
    report = enc.generateSignForEnclosure("enc-00001");
    System.out.println("test generateSignForEnclosure "
        + "after moveIntoEnclosures : " + report.toString());
    enc.moveOutFromEnclosures(createMonkey(1, 1, 1, 10, 20, 2, 0));
    report = enc.generateSignForEnclosure("enc-00001");
    System.out.println(
        "test generateSignForEnclosure after moveOutFromEnclosures : " + report.toString());
    enc.moveOutFromEnclosures(createMonkey(2, 1, 1, 10, 20, 2, 1));
    report = enc.generateSignForEnclosure("enc-00001");
    System.out.println(
        "test generateSignForEnclosure after moveOutFromEnclosures : " + report.toString());
  }
  
  /**
   * Driver program for Enclosure to show how it works.
   * @param args not used
   */
  public static void main(String[] args) {
    testConstructor();
    testMoveInWhenNoEmptyEnclosureWithSameSpecies();
    testMoveInWhenNoEmptyEnclosureWithDiffSpecies();
    testMoveInWhenMonkeyExists();
    testMoveInEmptyEnclosureNum();
    testMoveOutNotExist();
    testMoveOutEmptyEnclosureNum();
    testReportSpecies();
    testLookUpSpecies();
    testGenerateMonkeyList();
    testShoppingList();
    testExpand();
    testGenerateSignForEnclosure();
  }
}
