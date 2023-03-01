package sanctuary;

import enumerate.Species;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;



/**
 * This class represents the enclosures in the sanctuary 
 * where monkeys are housed after receiving medical attention.
 */
public final class Enclosures extends SanctuaryModel {
  
  private int enclosureNum;
  //the number of the rest enclosures
  private int emptyEncNum;
  //the size of an individual enclosure
  private final int size;
  
  //The map saves all the monkeys in the enclosures. 
  //the first key represents the enclosure number(every enclosure has a number to identify it)
  //the internal HashMap<Integer, Monkey> is the information of the monkey living in the enclosure
  //the Integer represents the hashCode of the monkey which is also the identity of the monkey
  //the Money Object represents the entity of the monkey
  //for example:
  // "enc-00001" : {1451518571, Monkey},{1111111, Monkey},{2222222, Monkey}
  // "enc-00002" : {232839049, Monkey}
  private HashMap<String, HashMap<Integer, Monkey>> enclosures;
  
  /**
   * This is the constructor of the class which facilitates a client to instantiate Enclosures.
   * @param enclosureNum how many enclosures there are in the sanctuary
   * @param size  the size of each enclosure
   */
  public Enclosures(int enclosureNum, int size) {
    super();
    if (enclosureNum <= 0 || size <= 0) {
      throw new IllegalArgumentException(
          "The number and the size of each enclosure cannot be zero or negative");
    }
    this.enclosureNum = enclosureNum;
    this.size = size;
    this.enclosures = new HashMap<String, HashMap<Integer, Monkey>>();
    this.enclosures.putAll(instantiateCageAndEnclosure(1, enclosureNum, "enc"));
    this.emptyEncNum = enclosureNum;
  }
  
  @Override
  public void moveIntoEnclosures(Monkey o) throws IllegalStateException, IllegalArgumentException {
    int hashCode = o.hashCode();
    if (!checkContainsMonkey(enclosures, hashCode).equals("")) {
      throw new IllegalArgumentException("Enclosures:This monkey has existed in the enclosures");
    }
    
    // put the monkey into an proper enclosure
    String encNumber = findProperEnclosure(o);
    enclosures.get(encNumber).put(hashCode, o);
  }
  
  @Override
  public void moveOutFromEnclosures(Monkey monkey) throws IllegalArgumentException {
    int hashCode = monkey.hashCode();
    String encNumber = checkContainsMonkey(enclosures, hashCode);
    if (!"".equals(encNumber) && enclosures.containsKey(encNumber)) {
      HashMap<Integer, Monkey> troop = enclosures.get(encNumber);
      troop.remove(hashCode);
      if (troop.isEmpty()) {
        increaseEnclosureNum();
      }
    } else {
      throw new IllegalArgumentException("Isolation:Can't find this monkey in the enclosures");
    }
  }

  /**
   * This method is used to find a proper enclosure for a monkey.
   * If can find a proper exiting enclosure for this monkey,
   * then it can move into the enclosure.
   * If can't find a proper one but there is still an empty enclosure left,
   * then it can move into the empty enclosure.
   * 
   * @param monkey  a monkey waiting for being accepted by the sanctuary
   * @return the number of a proper enclosure
   */
  private String findProperEnclosure(Monkey o) {
    String proper = "";
    String emptyNumber = "";
    for (HashMap.Entry<String, HashMap<Integer, Monkey>> enclosure : enclosures.entrySet()) {
      boolean flag = false;
      String number = enclosure.getKey();
      HashMap<Integer, Monkey> troop = enclosure.getValue();
      if (troop.isEmpty()) {
        emptyNumber = number;
      }
      for (HashMap.Entry<Integer, Monkey>  monkey : troop.entrySet()) {
        Monkey m = monkey.getValue();
        //check if there is an enclosure living in monkeys with as same species as the new monkey
        //then check whether there is extra room for this new monkey
        if (m.getSpecies().toString().equals(o.getSpecies().toString()) 
            && hasRoom(o, troop)) {
          proper = number;
          flag = true;
          break;
        }
      }
      if (flag) {
        break;
      }
    }
    
    if ("".equals(proper)) {
      if (emptyEncNum <= 0) {
        throw new IllegalStateException("There is no room for this new monkey,"
            + "please contact other sanctuary to accept it");
      }
      proper = emptyNumber;
      decreaseEnclosureNum();
    }
    return proper;
  }
  
  /**
  * Check whether the enclosure has room for the new monkey or not.
  * @param monkey the new monkey
  * @param troop  existed monkeys in the enclosure
  * @return true if there is room for the new monkey, otherwise false 
  */
  private boolean hasRoom(Monkey monkey, HashMap<Integer, Monkey> troop) {
    int need = monkey.getLivingArea();
    int used = 0;
    for (HashMap.Entry<Integer, Monkey>  m : troop.entrySet()) {
      used += m.getValue().getLivingArea();
    }
    int left = this.size - used;
    if (need <= left) {
      return true;
    } 
    return false;
  }
  
  /**
   * This method is to decrease the number of the empty enclosures.
   * 
   */
  private void decreaseEnclosureNum() {
    if (emptyEncNum <= 0) {
      throw new IllegalStateException("The number of the enclosures cannot be negative");
    }
    emptyEncNum--;
  }

  /**
   * This method is to increase the number of the empty enclosures.
   */
  private void increaseEnclosureNum() {
    if (emptyEncNum >= enclosureNum) {
      throw new IllegalStateException("The number of the enclosures "
          + "cannot be greater than enclosureNum");
    }
    emptyEncNum++;
  }
  
  /**
   * This method is to report species in enclosures.
   * @return the species list
   */
  public HashMap<String, HashSet<String>> reportSpeciesInEnclosures() {
    return reportSpeciesInIsolationOrEnclosures(enclosures);
  }
  

  @Override
  public String lookUpSpecies(Species s) {
    return lookUpSpeciesInIsolationOrEnclosures(enclosures, s);
  }

  /**
   * This is to generate the monkey list in the enclosures with the format:
   * {"Lili" : "iso-00001"}
   * {"Lucy" : "iso-00002"}
   * ....
   * @return the list of monkey in a map using the mentioned format
   */
  public HashMap<String, String> generateMonkeyListInEnclosures() {
    return generateMonkeyListInIsolationOrEnclosures(enclosures);
  }
  
  /**
   * This is to generate the shopping list of the enclosures with the format:
   * {"EGGS" : 500}
   * {"LEAVES" : 1000}.
   * @return the shopping ArrayLIst in the enclosures
   */
  public HashMap<String, Integer> shoppingListInEnclosures() {
    return shoppingListInIsolationOrEnclosures(enclosures);
  }
  
  /**
   * This is used to expand the isolation with more cages.
   * @param expandNum the number of expanded cages
   */
  public void expand(int expandNum) {
    this.enclosures.putAll(
        instantiateCageAndEnclosure(enclosureNum + 1, enclosureNum + expandNum, "enc"));
    this.enclosureNum += expandNum;
    this.emptyEncNum += expandNum;
  }
  
  @Override
  public String generateSignForEnclosure(String enclosureNumber) throws IllegalArgumentException {
    if (!enclosures.containsKey(enclosureNumber)) {
      throw new IllegalArgumentException("There is no such enclosure");
    }
    StringBuilder result = new StringBuilder();
    HashMap<Integer, Monkey> monkeys = enclosures.get(enclosureNumber);
    for (HashMap.Entry<Integer, Monkey> monkey : monkeys.entrySet()) {
      Monkey m = monkey.getValue();
      result.append(m.getSign()).append("\n");
    }
    return result.toString();
  } 
  
  /**
   * This is used to check whether the monkey exists in the enclosures.
   * @param map      the monkey list
   * @param hashCode the hashCode of the monkey
   * @return true if it exists, otherwise false
   */
  protected boolean lookUpMonkeyInEnclosures(int hashCode) {
    return lookUpMonkeyInIsolationOrEnclosures(enclosures, hashCode);
  }
  
  public int getEmptyEnclosureNum() {
    return this.emptyEncNum;
  }

  @Override
  public int hashCode() {
    return Objects.hash(emptyEncNum, enclosureNum, enclosures, size);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Enclosures other = (Enclosures) obj;
    return emptyEncNum == other.emptyEncNum && enclosureNum == other.enclosureNum
        && Objects.equals(enclosures, other.enclosures) && size == other.size;
  }
}

