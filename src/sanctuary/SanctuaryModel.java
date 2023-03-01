package sanctuary;

import enumerate.Species;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;


/**
 * This class is used to instantiate the Sanctuary Object, move in a monkey, move out a monkey
 * report species list, find a particular species, product monkey list, product shopping list.
 */
public  class SanctuaryModel implements Sanctuary {
  
  private Isolation isolation;
  private Enclosures enclosures;

  public SanctuaryModel() {}
  
  /**
   * This is a constructor of SanctuaryModel which facilitates a client 
   * to instantiate Sanctuary.
   * @param cageNum      the number of cages in the isolation
   * @param enclosureNum the number of enclosures
   * @param enclosureSize the size of each enclosure
   */
  public SanctuaryModel(int cageNum, int enclosureNum, int enclosureSize) {
    if (cageNum <= 0 || enclosureNum <= 0 || enclosureSize <= 0) {
      throw new IllegalArgumentException(
          "The number and the size of enclosures and the cage number cannot be zero or negative");
    }
    this.isolation = new Isolation(cageNum);
    this.enclosures = new Enclosures(enclosureNum, enclosureSize);
  }
  
  @Override
  public void moveIntoIsolation(Monkey monkey) throws IllegalStateException {
    if (enclosures.lookUpMonkeyInEnclosures(monkey.hashCode())) {
      throw new IllegalStateException("The monkey has alreay been there in the enclosures");
    }
    isolation.moveIntoIsolation(monkey);
  }
  
  @Override
  public void moveIntoEnclosures(Monkey monkey) throws IllegalStateException {
    if (!isolation.lookUpMonkeyInIsolation(monkey.hashCode())) {
      throw new IllegalStateException("The monkey should be moved from Isolation into Enclosures, "
          + "but now the monkey does not exist in the Isolation");
    }
    isolation.moveOutFromIsolation(monkey);
    enclosures.moveIntoEnclosures(monkey);
  }
  
  @Override
  public void moveOutFromIsolation(Monkey monkey) {
    isolation.moveOutFromIsolation(monkey);
  }
  
  @Override
  public void moveOutFromEnclosures(Monkey monkey) {
    enclosures.moveOutFromEnclosures(monkey);
  }
  
  @Override
  public String reportSpecies() {
    HashMap<String, HashSet<String>> isoReport = isolation.reportSpeciesInIsolation();
    HashMap<String, HashSet<String>> encReport = enclosures.reportSpeciesInEnclosures();
    HashMap<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
    mergeTwoSpeciesMap(map, isoReport);
    mergeTwoSpeciesMap(map, encReport);
    ArrayList<String> result = new ArrayList<String>();
    for (HashMap.Entry<String, HashSet<String>> r : map.entrySet()) {
      StringBuilder sb = new StringBuilder();
      sb.append(r.getKey())
        .append(" : ")
          .append(r.getValue().toString());
      result.add(sb.toString());
    }
    Collections.sort(result);
    return result.toString();
  }
  
  /**
   * This method is to merge two map into the parameter "map".
   * @param map   the first map
   * @param report the second map
   */
  private void mergeTwoSpeciesMap(HashMap<String, HashSet<String>> map, 
      HashMap<String, HashSet<String>> report) {
    for (HashMap.Entry<String, HashSet<String>> r : report.entrySet()) {
      String key = r.getKey();
      HashSet<String> value = r.getValue();
      if (map.containsKey(key)) {
        HashSet<String> list = map.get(key);
        list.addAll(value);
        map.put(key, list);
      } else {
        map.computeIfAbsent(key, k -> new HashSet<String>()).addAll(value);
      }
    }
  }

  @Override
  public String lookUpSpecies(Species s) throws IllegalArgumentException {
    String s1 = isolation.lookUpSpecies(s);
    String s2 = enclosures.lookUpSpecies(s);
    if ("".equals(s1) && "".equals(s2)) {
      throw new IllegalArgumentException("There is no this species in the isolation");
    }
    String result = "";
    if (!"".equals(s1)) {
      result += s1;
    }
    if (!"".equals(s2)) {
      result += s2;
    }
    if (result.endsWith(",")) {
      result = result.substring(0, result.length() - 1);
    }
    return result;
  }

  @Override
  public String generateMonkeyList() {
    HashMap<String, String> isoList = isolation.generateMonkeyListInIsolation();
    HashMap<String, String> encList = enclosures.generateMonkeyListInEnclosures();
    isoList.putAll(encList);
    ArrayList<String> result = new ArrayList<String>();
    for (HashMap.Entry<String, String> m : isoList.entrySet()) {
      StringBuilder sb = new StringBuilder()
          .append(m.getKey())
          .append(" : ")
          .append(m.getValue());
      result.add(sb.toString());
    }
    Collections.sort(result);
    return result.toString();
  }

  @Override
  public String shoppingList() {
    HashMap<String, Integer> list1 = isolation.shoppingListInIsolation();
    HashMap<String, Integer> list2 = enclosures.shoppingListInEnclosures();
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    mergeTwoShoppingList(map, list1);
    mergeTwoShoppingList(map, list2);
    StringBuilder sb = new StringBuilder();
    for (HashMap.Entry<String, Integer> m : map.entrySet()) {
      sb.append(m.getKey())
        .append(" : ")
        .append(m.getValue())
          .append("\n");
    }
    return sb.toString();
  }

  /**
   * This method is to merge two map into the parameter "map".
   * @param map   the first map
   * @param report the second map
   */
  private void mergeTwoShoppingList(HashMap<String, Integer> map, 
      HashMap<String, Integer> shoppingList) {
    for (HashMap.Entry<String, Integer> r : shoppingList.entrySet()) {
      String key = r.getKey();
      int value = r.getValue();
      map.put(key, map.getOrDefault(key, 0) + value);
    }
  }
  
  @Override
  public void expand(int expandNum, int type) {
    if (type == 0) {
      isolation.expand(expandNum);
    } else {
      enclosures.expand(expandNum);
    }
  }

  @Override
  public String generateSignForEnclosure(String enclosureNumber) {
    return enclosures.generateSignForEnclosure(enclosureNumber);
  }
  
  /**
   * This method is used to generate a sign of each cage.
   * @param index No.index cage
   * @return a sign of a cage
   */
  protected String generateIdentity(int index, String type) {
    int len = 5;
    String sign = type + "-";
    for (int i = 0; i < len - (index + "").length(); i++) {
      sign += "0";
    }
    return sign + (index + "");
  }
  
  /**
   * This is used to instantiate each cage and enclosure.
   * @param start the start number
   * @param end the end number
   */
  protected HashMap<String, HashMap<Integer, Monkey>> instantiateCageAndEnclosure(
      int start, int end, String type) {
    HashMap<String, HashMap<Integer, Monkey>> map = new HashMap<String, HashMap<Integer, Monkey>>();
    for (int i = start; i <= end; i++) {
      HashMap<Integer, Monkey> monkey = new HashMap<Integer, Monkey>();
      map.put(generateIdentity(i, type), monkey);
    }
    return map;
  }
  
  /**
   * This method is used to check whether the monkey has existed in the isolation/enclosures or not.
   * @param monkey  the monkey waiting for checking
   * @param hashCode the hashCode of the monkey
   * @return if the monkey has existed in the isolation/enclosure 
   *         return the number of the cage/enclosure, otherwise return ""
   */
  protected String checkContainsMonkey(HashMap<String, 
      HashMap<Integer, Monkey>> map, int hashCode) {
    for (HashMap.Entry<String, HashMap<Integer, Monkey>> m : map.entrySet()) {
      HashMap<Integer, Monkey> monkeys = m.getValue();
      if (monkeys.containsKey(hashCode)) {
        return m.getKey();
      }
    }
    return "";
  }
  
  /**
   * This is to report species list with below format:
   * {"DRILL" : ["iso-00001", "iso-00002"]
   * "GUEREZA" : ["iso-00003", "iso-00004"]}
   * or
   * {"DRILL" : ["enc-00001", "enc-00002"]
   * "GUEREZA" : ["enc-00003", "enc-00004"]}.
   * @param map represents the enclosures or the isolation
   * @return the species list
   */
  protected HashMap<String, HashSet<String>> reportSpeciesInIsolationOrEnclosures(
      HashMap<String, HashMap<Integer, Monkey>> map) {
    HashMap<String, HashSet<String>> speciesLocation = new HashMap<String, HashSet<String>>();
    for (HashMap.Entry<String, HashMap<Integer, Monkey>> cageOrEnc : map.entrySet()) {
      String number = cageOrEnc.getKey();
      HashMap<Integer, Monkey> monkeys = cageOrEnc.getValue();
      for (HashMap.Entry<Integer, Monkey> monkey : monkeys.entrySet()) {
        Monkey m = monkey.getValue();
        speciesLocation.computeIfAbsent(m.getSpecies().toString(), 
              k -> new HashSet<String>()).add(number);
      }
    }
    return speciesLocation;
  }
  
  /**
   * This is to look up a particular species.
   * @param s the given species
   * @return the number of the cage/enclosure where the species is housed
   * @throws IllegalArgumentException if there is no this species in the isolation/enclosures
   */
  protected String lookUpSpeciesInIsolationOrEnclosures(
      HashMap<String, HashMap<Integer, Monkey>> map, Species s) throws IllegalArgumentException {
    StringBuilder result = new StringBuilder();
    for (HashMap.Entry<String, HashMap<Integer, Monkey>> cageOrEnc : map.entrySet()) {
      String number = cageOrEnc.getKey();
      HashMap<Integer, Monkey> monkeys = cageOrEnc.getValue();
      for (HashMap.Entry<Integer, Monkey> monkey : monkeys.entrySet()) {
        Monkey m = monkey.getValue();
        if (m.getSpecies().toString().equals(s.toString())) {
          result.append(number).append(",");
          break;
        }
      }
    }
    return result.toString();
  }
  
  /**
   * This is to generate the monkey list in the isolation/enclosures with the format:
   * {"Lili" : "iso-00001"}
   * {"Lucy" : "iso-00002"}
   * ....
   * @return the list of monkey in a map using the mentioned format
   */
  protected HashMap<String, String> generateMonkeyListInIsolationOrEnclosures(
      HashMap<String, HashMap<Integer, Monkey>> map) {
    HashMap<String, String> result = new HashMap<String, String>();
    for (HashMap.Entry<String, HashMap<Integer, Monkey>> cageOrEnc : map.entrySet()) {
      String number = cageOrEnc.getKey();
      HashMap<Integer, Monkey> monkeys = cageOrEnc.getValue();
      for (HashMap.Entry<Integer, Monkey> monkey : monkeys.entrySet()) {
        Monkey m = monkey.getValue();
        result.put(m.getName(), number);
      }
    }
    return result;
  }
  
  /**
   * This is to generate the shopping list of the isolation/enclosures with the format:
   * {"EGGS" : 500}
   * {"LEAVES" : 1000}.
   * @return the shopping list in the isolation or enclosures
   */
  protected HashMap<String, Integer> shoppingListInIsolationOrEnclosures(
      HashMap<String, HashMap<Integer, Monkey>> map) {
    HashMap<String, Integer> result = new HashMap<String, Integer>();
    for (HashMap.Entry<String, HashMap<Integer, Monkey>> cageOrEnc : map.entrySet()) {
      HashMap<Integer, Monkey> monkeys = cageOrEnc.getValue();
      for (HashMap.Entry<Integer, Monkey> monkey : monkeys.entrySet()) {
        Monkey m = monkey.getValue();
        String food = m.getFavoriteFood().toString();
        result.put(food, result.getOrDefault(food, 0) + m.getFeedingGram());
      }
    }
    return result;
  }
  
  /**
   * This is used to check whether the monkey exists in the isolation or enclosures.
   * @param map      the monkey list
   * @param hashCode the hashCode of the monkey
   * @return true if it exists, otherwise false
   */
  protected boolean lookUpMonkeyInIsolationOrEnclosures(
      HashMap<String, HashMap<Integer, Monkey>> map, int hashCode) {
    for (HashMap.Entry<String, HashMap<Integer, Monkey>> cageOrEnc : map.entrySet()) {
      HashMap<Integer, Monkey> monkeys = cageOrEnc.getValue();
      if (monkeys.containsKey(hashCode)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int getEmptyCageNum() {
    return this.isolation.getEmptyCageNum();
  }
  
  @Override
  public int getEmptyEnclosureNum() {
    return this.enclosures.getEmptyEnclosureNum();
  }
}
