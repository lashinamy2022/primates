package sanctuary;


import enumerate.Species;

/**
 * 
 * The Sanctuary consists of two different types of housing:
 *  .Isolation is used to keep monkeys when they first arrive at the sanctuary
 *   and any time they are receiving medical attention. Isolation consists of a series of
 *   cages each of which can house a single animal.
 *  .Enclosures are much larger and can host a single troop of monkeys. 
 *   Each troop consists of a single species that is found 
 *   in the New World.
 */
public interface Sanctuary {
  
  
  /**
   * Move a monkey into the Isolation.
   * @param monkey the monkey waiting for moving in
   */
  void moveIntoIsolation(Monkey monkey);
  
  /**
   * Move a monkey into the Enclosures.
   * @param monkey the monkey waiting for moving in
   */
  void moveIntoEnclosures(Monkey monkey);
  
  /**
   * Move a monkey out from the Isolation.
   * @param monkey the monkey waiting for moving out
   */
  void moveOutFromIsolation(Monkey monkey);
  
  /**
   * Move a monkey out from the Enclosures.
   * @param monkey the monkey waiting for moving out
   */
  void moveOutFromEnclosures(Monkey monkey);
  
  /**
   * The Sanctuary would like the flexibility of expanding should the needs and funds allow.
   * @param expandNum the number of expanded cages or enclosures
   * @param type : 0 represents isolation, 1 represents enclosures
   */
  void expand(int expandNum, int type);
  
  /**
   * Report the species that are currently being housed in alphabetical order. The list should
   * include where in the sanctuary each species is (both in enclosures and in isolation).
   * @return where each species is housed with the below format:
   *          "DRILL" : ["00001", "00002"]
   *          "GUEREZA" : ["00003", "00004"]
   *          ...
   *          "00001" represents cage number
   */
  String reportSpecies();
  
  /**
   * Look up where a particular species is currently housed. If none of a particular species
   * is currently being housed, it should report this fact.
   * @param s the given species
   * @return where is the species housed with the format:
   *     iso-00001,iso-00002,enc-00001,enc-00002
   *     ( "iso" represents isolation, and the number represents the number of a cage;
   *     "enc" represents enclosures, and the number represents the number of a enclosure)
   */
  String lookUpSpecies(Species s);
  
  /**
   * Produce a sign for a given enclosure that lists each individual monkey that is currently
   * housed there. For each individual monkey, the sign should include their name, sex, and
   * favorite food.
   * @param enclousureNumber the given enclosure
   * @return the monkey list in this enclosure
   */
  String generateSignForEnclosure(String enclousureNumber);
  
  /**
  * Produce an alphabetical list (by name) of all of the monkeys housed in the Sanctuary. 
  * This information should include where each monkey can be found.
  * @return the monkey list in the sanctuary
  */
  String generateMonkeyList();
  
  /**
   * Produce a shopping list of the favorite foods of the inhabitants of the Sanctuary. 
   * For each food listed, the quantity needed should also be listed. Large monkeys should 
   * receive 500 grams of their favorite food while medium and small monkeys require 300 
   * grams and 100 grams, respectively.
   * @return the shopping list string
   */
  String shoppingList();
  
  /**
   * Get the number of empty cages in the isolation.
   * @return the number
   */
  int getEmptyCageNum();
  
  /**
   * Get the number of empty enclosures.
   * @return the number
   */
  int getEmptyEnclosureNum();
}
