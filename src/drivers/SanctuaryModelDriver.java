package drivers;


import enumerate.Species;
import sanctuary.Monkey;
import sanctuary.Sanctuary;
import sanctuary.SanctuaryModel;

/**
 * This is to test the functions of {@link SanctuaryModel}.
 */
public class SanctuaryModelDriver extends BaseDriver {
  
  /**
   * Driver program for SanctuaryModel to show how it works.
   * @param args not used
   */
  public static void main(String[] args) {
    testConstructor();
    testMoveInWhenNoEmptyCageInIsolation();
    testMoveInEnclosuresFirst();
    testMoveInWhenNoEmptyEnclosureWithSameSpecies();
    testMoveInWhenNoEmptyEnclosureWithDiffSpecies();
    testMoveInWhenMonkeyExistsInIsolation();
    testMoveInWhenMonkeyExistsInEnclosures();
    testEmptyCageNum();
    testEmptyEnclosureNum();
    testMoveOutNotExistInIsolation();
    testMoveOutNotExistInEnclosures();
    testMoveOutEmptyCageNum();
    testMoveOutEmptyEnclosureNum();
    testReportSpecies();
    testLookUpSpecies();
    testGenerateMonkeyList();
    testShoppingList();
    testExpand();
    testGenerateSignForEnclosure();
  }

  /**
   * This is to test whether the constructor can throw {@link IllegalArgumentException} correctly 
   * with invalid parameters.
   */
  public static void testConstructor() {
    try {
      new SanctuaryModel(0, 0, 0);
      System.out.println("test constructor with 0 : error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test constructor with 0 : good");
    }
    try {
      new SanctuaryModel(-1, -1, -100);
      System.out.println("test constructor with -1 : error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test constructor with -1 : good");
    }
  }
  
  /**
   * This is to test move into isolation when there is no empty cages.
   */
  public static void testMoveInWhenNoEmptyCageInIsolation() {
    try {
      Sanctuary san = new SanctuaryModel(1, 2, 10);
      san.moveIntoIsolation(createMonkey());
      san.moveIntoIsolation(createMonkey());
      System.out.println("test moveIn when there is no empty cage : error");
    } catch (IllegalStateException ex) {
      System.out.println("test moveIn when there is no empty cage : good");
    }
  }
  
  /**
   * This is to test move into enclosures directly without moving into Isolation first.
   */
  public static void testMoveInEnclosuresFirst() {
    try {
      Sanctuary san = new SanctuaryModel(1, 2, 10);
      san.moveIntoEnclosures(createMonkey());
      System.out.println("test moveInEnclosures directly without"
          + " moving into Isolation first : error");
    } catch (IllegalStateException ex) {
      System.out.println("test moveInEnclosures directly without"
          + " moving into Isolation first : good");
    }
  }
  
  /**
   * This is to test moveIn when there is no empty enclosure with same species.
   */
  public static void testMoveInWhenNoEmptyEnclosureWithSameSpecies() {
    Sanctuary san = new SanctuaryModel(10, 2, 10);
    
    int area = 0;
    int num = 0;
    try {
      for (int i = 0; i < 7; i++) {
        // nameIndex,  speciesIndex,  sexIndex, 
        // size,  weight,  age,  foodIndex
        Monkey m = createMonkey(i, 0, 1, 10, 20, 2, 1);
        san.moveIntoIsolation(m);
        san.moveIntoEnclosures(m);
        area += m.getLivingArea();
        num++;
      }
      System.out.println(num + " monkeys occupied " + area + " square meter");
      System.out.println("test moveIn when there is no empty enclosure with same species : error");
    } catch (IllegalStateException ex) {
      System.out.println(san.generateSignForEnclosure("enc-00001"));
      System.out.println(san.generateSignForEnclosure("enc-00002"));
      System.out.println(num + " monkeys occupied " + area + " square meter");
      System.out.println("test moveIn when there is no empty enclosure with same species : good");
    }
  }
  
  /**
   * This is to test moveIn when there is no empty enclosure with different species.
   */
  public static void testMoveInWhenNoEmptyEnclosureWithDiffSpecies() {
    Sanctuary san = new SanctuaryModel(10, 2, 10);
    int area = 0;
    int num = 0;
    try {
      for (int i = 0; i < 3; i++) {
        // nameIndex,  speciesIndex,  sexIndex, 
        // size,  weight,  age,  foodIndex
        Monkey m = createMonkey(i, i, 1, 10, 20, 2, 1);
        san.moveIntoIsolation(m);
        san.moveIntoEnclosures(m);
        area += m.getLivingArea();
        num++;
      }
      System.out.println(san.reportSpecies());
      System.out.println(num + " monkeys occupied " + area + " square meter");
      System.out.println("test moveIn when there is no empty "
          + "enclosure with different species : error");
    } catch (IllegalStateException ex) {
      System.out.println(san.generateSignForEnclosure("enc-00001"));
      System.out.println(san.generateSignForEnclosure("enc-00002"));
      System.out.println(san.reportSpecies());
      System.out.println(num + " monkeys occupied " + area + " square meter");
      System.out.println("test moveIn when there is no empty"
          + " enclosure with different species: good");
    }
  }
  
  
  /**
   * This is to test moveIn when the monkey exists in isolation.
   */
  public static void testMoveInWhenMonkeyExistsInIsolation() {
    try {
      Sanctuary san = new SanctuaryModel(10, 2, 10);
      san.moveIntoIsolation(createMonkey(1, 1, 1, 10, 20, 2, 1));
      san.moveIntoIsolation(createMonkey(1, 1, 1, 10, 20, 2, 1));
      System.out.println("test moveIn when the monkey exists in the isolation: error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test moveIn when the monkey exists in the isolation : good");
    }
  }
  
  /**
   * This is to test moveIn when the monkey exists in the enclosures.
   */
  public static void testMoveInWhenMonkeyExistsInEnclosures() {
    try {
      Sanctuary san = new SanctuaryModel(10, 2, 10);
      san.moveIntoIsolation(createMonkey(1, 1, 1, 10, 20, 2, 1));
      san.moveIntoEnclosures(createMonkey(1, 1, 1, 10, 20, 2, 1));
      san.moveIntoIsolation(createMonkey(1, 1, 1, 10, 20, 2, 1));
      san.moveIntoEnclosures(createMonkey(1, 1, 1, 10, 20, 2, 1));
      System.out.println("test moveIn when the monkey exists in the isolation: error");
    } catch (IllegalStateException ex) {
      System.out.println("test moveIn when the monkey exists in the isolation : good");
    }
  }
  
  /**
   * This is to test the emptyCageNum after move in.
   */
  public static void testEmptyCageNum() {
    Sanctuary san = new SanctuaryModel(10, 3, 10);
    Monkey m1 = createMonkey();
    Monkey m2 = createMonkey();
    Monkey m3 = createMonkey();

    san.moveIntoIsolation(m1);
    san.moveIntoIsolation(m2);
    san.moveIntoIsolation(m3);

    int left = san.getEmptyCageNum();
    System.out.println("test the emptyCageNum of moveIn : " + (left == 7 ? "good" : "error"));
  }
  
  /**
   * This is to test the emptyEnclosureNum after move in.
   */
  public static void testEmptyEnclosureNum() {
    Sanctuary san = new SanctuaryModel(10, 3, 10);
    Monkey m1 = createMonkey();
    Monkey m2 = createMonkey();
    Monkey m3 = createMonkey();

    san.moveIntoIsolation(m1);
    san.moveIntoIsolation(m2);
    san.moveIntoIsolation(m3);

    san.moveIntoEnclosures(m1);
    san.moveIntoEnclosures(m2);
    san.moveIntoEnclosures(m3);

    int leftEnclosures = san.getEmptyEnclosureNum();
    System.out.println("test the emptyEnclosureNum of moveIn : "
        + (leftEnclosures == 0 ? "good" : "error"));
  }
  
  /**
   * This is to test moveOut when the monkey does not exist.
   */
  public static void testMoveOutNotExistInIsolation() {
    try {
      Sanctuary san = new SanctuaryModel(10, 3, 10);
      san.moveOutFromIsolation(createMonkey());
      System.out.println("test moveOut when the monkey does not exist in the isolation : error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test moveOut when the monkey does not exist in the isolation : good");
    }
  }
  
  /**
   * This is to test moveOut when the monkey does not exist.
   */
  public static void testMoveOutNotExistInEnclosures() {
    try {
      Sanctuary san = new SanctuaryModel(10, 3, 10);
      san.moveOutFromEnclosures(createMonkey());
      System.out.println("test moveOut when the monkey does not exist in the enclosures : error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test moveOut when the monkey does not exist in the enclosures : good");
    }
  }
  
  /**
   * This is to test the emptyEnclosureNum of moveOut.
   */
  public static void testMoveOutEmptyCageNum() {
    Sanctuary san = new SanctuaryModel(10, 3, 10);
    san.moveIntoIsolation(createMonkey(1, 0, 1, 10, 20, 2, 1));
    san.moveIntoIsolation(createMonkey(1, 1, 1, 10, 20, 2, 1));
    san.moveOutFromIsolation(createMonkey(1, 1, 1, 10, 20, 2, 1));
    int leftEnclosure = san.getEmptyCageNum();
    System.out.println("test the emptyCageNum of moveOut : " + (
        leftEnclosure == 9 ? "good" : "error"));
  }
  
  /**
   * This is to test the emptyEnclosureNum of moveOut.
   */
  public static void testMoveOutEmptyEnclosureNum() {
    Sanctuary san = new SanctuaryModel(10, 3, 10);
    san.moveIntoIsolation(createMonkey(1, 0, 1, 10, 20, 2, 1));
    san.moveIntoIsolation(createMonkey(1, 1, 1, 10, 20, 2, 1));
    san.moveIntoEnclosures(createMonkey(1, 0, 1, 10, 20, 2, 1));
    san.moveIntoEnclosures(createMonkey(1, 1, 1, 10, 20, 2, 1));
    san.moveOutFromEnclosures(createMonkey(1, 1, 1, 10, 20, 2, 1));
    int leftEnclosure = san.getEmptyEnclosureNum();
    System.out.println("test the emptyEnclosureNum of moveOut : " + (
        leftEnclosure == 2 ? "good" : "error"));
  }
  
  
  
  /**
   * This is to test reportSpecies when there is no monkey/ after moveIn/ after moveOut.
   */
  public static void testReportSpecies() {
    Sanctuary san = new SanctuaryModel(10, 3, 10);
    // nameIndex,  speciesIndex,  sexIndex, 
    // size,  weight,  age,  foodIndex
    String report = san.reportSpecies();
    System.out.println("test report Species when there is no monkey : " + report);
    san.moveIntoIsolation(createMonkey(1, 0, 1, 10, 20, 2, 1));
    san.moveIntoIsolation(createMonkey(2, 1, 1, 10, 20, 2, 1));
    san.moveIntoIsolation(createMonkey(3, 1, 1, 10, 20, 2, 1));
    san.moveIntoIsolation(createMonkey(4, 1, 1, 10, 20, 2, 1));
    san.moveIntoIsolation(createMonkey(5, 1, 1, 10, 20, 2, 1));
    san.moveIntoIsolation(createMonkey(0, 1, 1, 10, 20, 2, 1));
    report = san.reportSpecies();
    System.out.println("test report Species : " + report);

    san.moveIntoEnclosures(createMonkey(0, 1, 1, 10, 20, 2, 1));
    report = san.reportSpecies();
    System.out.println("test report Species : " + report);

    san.moveOutFromIsolation(createMonkey(5, 1, 1, 10, 20, 2, 1));
    report = san.reportSpecies();
    System.out.println("test report Species : " + report);
    
    san.moveOutFromEnclosures(createMonkey(0, 1, 1, 10, 20, 2, 1));
    report = san.reportSpecies();
    System.out.println("test report Species : " + report);
    
  }
  
  /**
   * This is to testLookUpSpeceis when this is no monkey/ after moveIn/ after moveOut.
   */
  public static void testLookUpSpecies() {
    Sanctuary san = new SanctuaryModel(10, 3, 10);
    String report = "";
    // nameIndex,  speciesIndex,  sexIndex, 
    // size,  weight,  age,  foodIndex
    try {
      report = san.lookUpSpecies(Species.DRILL);
      System.out.println("test lookUpSpecies when there is no monkey : error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test lookUpSpecies when there is no monkey : good");
    }
    san.moveIntoIsolation(createMonkey(1, 0, 1, 10, 20, 2, 1));
    san.moveIntoIsolation(createMonkey(3, 0, 1, 10, 20, 2, 1));
    san.moveIntoIsolation(createMonkey(2, 1, 1, 10, 20, 2, 1));
    san.moveIntoEnclosures(createMonkey(1, 0, 1, 10, 20, 2, 1));
    report = san.lookUpSpecies(Species.DRILL);
    System.out.println("test lookUpSpecies after moveIn : " + report);
  }
  
  /**
   * This is to test generateMonkeyList when this is no monkey/ after moveIn/ after moveOut.
   */
  public static void testGenerateMonkeyList() {
    Sanctuary san = new SanctuaryModel(10, 3, 10);
    // nameIndex,  speciesIndex,  sexIndex, 
    // size,  weight,  age,  foodIndex
    String report = san.generateMonkeyList();
    System.out.println("test generateMonkeyList when there is no monkey : " + report);
    san.moveIntoIsolation(createMonkey(1, 0, 1, 10, 20, 2, 1));
    san.moveIntoIsolation(createMonkey(2, 1, 1, 10, 20, 2, 1));
    san.moveIntoIsolation(createMonkey(3, 1, 1, 10, 20, 2, 1));
    report = san.generateMonkeyList();
    System.out.println("test generateMonkeyList after move into isolation :\n" + report);
    san.moveOutFromIsolation(createMonkey(1, 0, 1, 10, 20, 2, 1));
    report = san.generateMonkeyList();
    System.out.println("test generateMonkeyList after move out from isolation :\n" + report);
    san.moveIntoIsolation(createMonkey(1, 0, 1, 10, 20, 2, 1));
    san.moveIntoEnclosures(createMonkey(1, 0, 1, 10, 20, 2, 1));
    report = san.generateMonkeyList();
    System.out.println("test generateMonkeyList after move into  Enclosures :\n" + report);
    san.moveOutFromEnclosures(createMonkey(1, 0, 1, 10, 20, 2, 1));
    report = san.generateMonkeyList();
    System.out.println("test generateMonkeyList after move out from Enclosures :\n" + report);
  }
  
  /**
   * This is to test shoppingList when this is no monkey/ after moveIn/ after moveOut.
   */
  public static void testShoppingList() {
    Sanctuary san = new SanctuaryModel(10, 3, 10);
    // nameIndex,  speciesIndex,  sexIndex, 
    // size,  weight,  age,  foodIndex
    String report = san.shoppingList();
    System.out.println("test shoppingList when there is no monkey : " + report);
    Monkey m1 = createMonkey(1, 0, 1, 10, 20, 2, 1);
    Monkey m2 = createMonkey(2, 1, 1, 10, 20, 2, 1);
    Monkey m3 = createMonkey(3, 1, 1, 10, 20, 2, 1);

    san.moveIntoIsolation(m1);
    san.moveIntoIsolation(m2);
    san.moveIntoIsolation(m3);
    int expected1 = m1.getFeedingGram();
    expected1 += m2.getFeedingGram();
    expected1 += m3.getFeedingGram();
    report = san.shoppingList();
    System.out.println("the expected value : " + expected1);
    System.out.println("test shoppingList after move into isolation :\n" + report);
    
    san.moveIntoEnclosures(m3);
    report = san.shoppingList();
    System.out.println("test shoppingList after move into isolation :\n" + report);

    san.moveOutFromIsolation(m1);
    report = san.shoppingList();
    System.out.println("test shoppingList after move into isolation :\n" + report);
    
    san.moveOutFromEnclosures(m3);
    report = san.shoppingList();
    System.out.println("test shoppingList after move into isolation :\n" + report);
    
    Monkey m4 = createMonkey(4, 1, 1, 10, 20, 2, 0);
    Monkey m5 = createMonkey(5, 1, 1, 10, 20, 2, 2);
    san.moveIntoIsolation(m4);
    san.moveIntoIsolation(m5);
    report = san.shoppingList();
    System.out.println("test shoppingList after move into isolation :\n" + report);
  }
  
  /**
   * This is to test expand method.
   */
  public static void testExpand() {
    Sanctuary san = new SanctuaryModel(10, 3, 10);
    System.out.println("empty cage number : " + san.getEmptyCageNum());
    san.expand(3, 0);
    System.out.println("empty cage number after expanding: " + san.getEmptyCageNum());
    
    System.out.println("empty enclosure number : " + san.getEmptyEnclosureNum());
    san.expand(3, 1);
    System.out.println("empty enclosure number after expanding: " + san.getEmptyEnclosureNum());

  }
  
  /**
   * This is to test generateSignForEnclosure when this is no monkey/ after moveIn/ after moveOut.
   */
  public static void testGenerateSignForEnclosure() {
    Sanctuary san = new SanctuaryModel(3, 3, 10);
    // nameIndex,  speciesIndex,  sexIndex, 
    // size,  weight,  age,  foodIndex
    String report = san.generateSignForEnclosure("enc-00001");
    System.out.println("test generateSignForEnclosure when there is no monkey : " + report);
    san.moveIntoIsolation(createMonkey(1, 1, 1, 10, 20, 2, 0));
    san.moveIntoIsolation(createMonkey(2, 1, 1, 10, 20, 2, 1));
    san.moveIntoIsolation(createMonkey(3, 1, 1, 10, 20, 2, 2));
    try {
      report = san.generateSignForEnclosure("iso-00001");
      System.out.println("test generateSignForEnclosure when there is no such enclosure:error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test generateSignForEnclosure when there is no such enclosure:good");
    }
    report = san.generateSignForEnclosure("enc-00001");
    System.out.println("test generateSignForEnclosure after move into isolation : " + report);
    
    san.moveIntoEnclosures(createMonkey(1, 1, 1, 10, 20, 2, 0));
    report = san.generateSignForEnclosure("enc-00001");
    System.out.println("test generateSignForEnclosure after move into enclosoures :\n" + report);
    report = san.generateSignForEnclosure("enc-00002");
    System.out.println("test generateSignForEnclosure after move into enclosoures :\n" + report);
    report = san.generateSignForEnclosure("enc-00003");
    System.out.println("test generateSignForEnclosure after move into enclosoures :\n" + report);
    
    san.moveIntoEnclosures(createMonkey(2, 1, 1, 10, 20, 2, 1));
    report = san.generateSignForEnclosure("enc-00001");
    System.out.println("test generateSignForEnclosure after move into enclosoures :\n" + report);
    report = san.generateSignForEnclosure("enc-00002");
    System.out.println("test generateSignForEnclosure after move into enclosoures :\n" + report);
    report = san.generateSignForEnclosure("enc-00003");
    System.out.println("test generateSignForEnclosure after move into enclosoures :\n" + report);
    
    san.moveOutFromEnclosures(createMonkey(1, 1, 1, 10, 20, 2, 0));
    report = san.generateSignForEnclosure("enc-00003");
    System.out.println("test generateSignForEnclosure after move out "
        + "from enclosoures :\n" + report);
  }
  
  

}
