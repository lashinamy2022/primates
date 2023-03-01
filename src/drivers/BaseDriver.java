package drivers;

import enumerate.Foods;
import enumerate.Species;
import java.util.Random;
import sanctuary.Monkey;

/**
 * 
 *This is class is mainly used to create Monkey Object to facilitate other driver classes.
 */
public class BaseDriver {
  private static final  String[] names = {"Lili", "Lucy", "Tony", "Tom", "Cici", "Tomy", "Peter"};
  private static final  String[] genders = {"female", "male"};
  private static final  Species[] species = {Species.DRILL, Species.GUEREZA, 
      Species.HOWLER, Species.MANGABEY, Species.SAKI, Species.SPIDER,
      Species.SQUIRREL, Species.TAMARIN};
  private static final  Foods[] foods = {Foods.EGGS, Foods.FRUITS, Foods.INSECTS, Foods.LEAVES, 
      Foods.NUTS, Foods.SEEDS, Foods.TREE_SAP};
                
  /**
   * This is to instantiate Monkey with random values.
   * @return Monkey Object
   */
  protected static Monkey createMonkey() {
    Monkey monkey = Monkey.getBuilder()
        .name(names[new Random().nextInt(7)])
        .species(species[new Random().nextInt(8)])
        .sex(genders[new Random().nextInt(2)])
        .size(new Random().nextInt(30) + 1)
        .weight(new Random().nextInt(50) + 1)
        .age(new Random().nextInt(15) + 1)
        .favoriteFood(foods[new Random().nextInt(7)])
        .build();
    return monkey;
  }
  
  /**
   * This is to instantiate Monkey with fixed values.
   * @param nameIndex    the index of names array
   * @param speciesIndex the index of species array
   * @param sexIndex     the index of sex array
   * @param size         the size of the monkey
   * @param weight       the weight of the monkey
   * @param age          the age of the monkey
   * @param foodIndex    the index of food array
   * @return  Monkey Object
   */
  protected static Monkey createMonkey(int nameIndex, int speciesIndex, int sexIndex, 
      int size, int weight, int age, int foodIndex) {
    Monkey monkey = Monkey.getBuilder()
        .name(names[nameIndex])
        .species(species[speciesIndex])
        .sex(genders[sexIndex])
        .size(size)
        .weight(weight)
        .age(age)
        .favoriteFood(foods[foodIndex])
        .build();
    return monkey;
  }
}
