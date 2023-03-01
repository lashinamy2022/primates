package sanctuary;

import enumerate.Species;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;


/**
 * 
 * This class represents an Isolation in the sanctuary.
 *
 */
public final class Isolation extends SanctuaryModel {
  
  private int cageNum;
  //the number of the rest cages
  private int emptyCageNum;
  //The map saves all the monkeys in the isolation. 
  //the first key represents the cage number(every cage has a number to identify it)
  //the internal HashMap<Integer, Monkey> is the information of the monkey living in the cage
  //the Integer represents the hashCode of the monkey which is also the identity of the monkey
  //the Money Object represents the entity of the monkey
  //for example:
  // "iso-00001" : {1451518571, Monkey}
  // "iso-00002" : {232839049, Monkey}
  private HashMap<String, HashMap<Integer, Monkey>> cages;
  
  /**
   * This is the constructor of the Isolation which is used to 
   * instantiate a new Isolation object for a client.
   * @param cageNum the number of cages in the isolation
   * @throws IllegalArgumentException when the cageNum <= 0
   */
  public Isolation(int cageNum)throws IllegalArgumentException {
    super();
    if (cageNum <= 0) {
      throw new IllegalArgumentException(
          "Isolation:The number of the cages cannot be zero or negative");
    }
    this.cageNum = cageNum;
    this.cages = new HashMap<String, HashMap<Integer, Monkey>>();
    this.cages.putAll(instantiateCageAndEnclosure(1, cageNum, "iso"));
    this.emptyCageNum = cageNum;
  }
  
  @Override
  public void moveIntoIsolation(Monkey monkey) 
      throws IllegalArgumentException, IllegalStateException {
    int hashCode = monkey.hashCode();
    if (emptyCageNum == 0) {
      throw new IllegalStateException("Isolation:There is no rest cage for this monkey");
    }
    if (!checkContainsMonkey(cages, hashCode).equals("")) {
      throw new IllegalArgumentException("Isolation:This monkey has existed in the isolation");
    }
    // put the monkey into an empty cage
    HashMap<Integer, Monkey> m = new HashMap<Integer, Monkey>();
    m.put(hashCode, monkey);
    for (HashMap.Entry<String, HashMap<Integer, Monkey>> cage : cages.entrySet()) {
      if (cage.getValue().isEmpty()) {
        cage.setValue(m);
        break;
      }
    }
    decreaseCageNum();
  }
  
  @Override
  public void moveOutFromIsolation(Monkey monkey) throws IllegalArgumentException {
    int hashCode = monkey.hashCode();
    String cageNumber = checkContainsMonkey(cages, hashCode);
    
    if (!"".equals(cageNumber) && cages.containsKey(cageNumber)) {
      cages.put(cageNumber, new HashMap<Integer, Monkey>());
      increaseCageNum();
    } else {
      throw new IllegalArgumentException("Isolation:Can't find this monkey in the isolation");
    }
  }
  
  /**
   * This method is used to decrease the number of cages.
   * @throws IllegalStateException if the number of cages is zero or negative
   */
  private void decreaseCageNum() throws IllegalStateException {
    if (emptyCageNum <= 0) {
      throw new IllegalStateException(
          "Isolation:The number of cages in the isolation cannot be negative");
    }
    this.emptyCageNum--;
  }
  
  /**
   * This method is to increase the number of cages.
   */
  private void increaseCageNum() {
    if (emptyCageNum >= cageNum) {
      throw new IllegalStateException(
          "Isolation:The emptyCageNum cannot be greater than cageNum");
    }
    this.emptyCageNum++;
  }

  /**
   * This method is used to report species in isolation.
   * @return the species list
   */
  public HashMap<String, HashSet<String>> reportSpeciesInIsolation() {
    return reportSpeciesInIsolationOrEnclosures(cages);
  }
  
  
  @Override
  public String lookUpSpecies(Species s) {
    return lookUpSpeciesInIsolationOrEnclosures(cages, s);
  }
  
  /**
   * This is to generate the monkey list in the isolation with the format:
   * {"Lili" : "iso-00001"}
   * {"Lucy" : "iso-00002"}
   * ....
   * @return the list of monkey in a map using the mentioned format
   */
  public HashMap<String, String> generateMonkeyListInIsolation() {
    return generateMonkeyListInIsolationOrEnclosures(cages);
  }
  
  /**
   * 
   * This is to generate the shopping list of the isolation with the format:
   * {"EGGS" : 500}
   * {"LEAVES" : 1000}.
   * @return the shopping list in the isolation
   */
  public HashMap<String, Integer> shoppingListInIsolation() {
    return shoppingListInIsolationOrEnclosures(cages);
  }
  
  /**
   * This is used to expand the isolation with more cages.
   * @param expandNum the number of expanded cages
   */
  public void expand(int expandNum) {
    this.cages.putAll(instantiateCageAndEnclosure(cageNum + 1, cageNum + expandNum, "iso"));
    this.cageNum += expandNum;
    this.emptyCageNum += expandNum;
  }
  
  /**
   * This is used to check whether the monkey exists in the isolation.
   * @param map      the monkey list
   * @param hashCode the hashCode of the monkey
   * @return true if it exists, otherwise false
   */
  protected boolean lookUpMonkeyInIsolation(int hashCode) {
    return lookUpMonkeyInIsolationOrEnclosures(cages, hashCode);
  }
 
  @Override
  public int getEmptyCageNum() {
    return emptyCageNum;
  }

  @Override
  public int hashCode() {
    return Objects.hash(cageNum, cages, emptyCageNum);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return false;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Isolation other = (Isolation) obj;
    return cageNum == other.cageNum && Objects.equals(cages, other.cages)
        && emptyCageNum == other.emptyCageNum;
  }
  
  
}
