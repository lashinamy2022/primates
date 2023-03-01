package sanctuary;

import enumerate.Foods;
import enumerate.MonkeySize;
import enumerate.Species;
import java.util.HashMap;
import java.util.Objects;

/**
 * A class that represents a monkey in a sanctuary.
 */
public final class Monkey {
  private final String name;
  private final Species species;
  private final String sex;
  private final double size; // the unit is centimeter
  private final double weight;
  private final int age;
  private final Foods favoriteFood;
  private final HashMap<String, Integer> feedingGram;
  private final HashMap<String, Integer> livingArea;
  
  /*
   * A private constructor that can only be called from the MonkeyBuider. This
   * works because the Builder is an inner class and has access to private
   * members.
   */
  private Monkey(String name, Species species, String sex,
      double size, double weight, int age, Foods favoriteFood) {
    super();
    this.name = name;
    this.species = species;
    this.sex = sex;
    this.size = size;
    this.weight = weight;
    this.age = age;
    this.favoriteFood = favoriteFood;

    HashMap<String, Integer>  fg =  new HashMap<String, Integer>();
    fg.put(MonkeySize.SMALL.toString(), 100);
    fg.put(MonkeySize.MEDIUM.toString(), 300);
    fg.put(MonkeySize.LARGE.toString(), 500);
    this.feedingGram = fg;
    
    HashMap<String, Integer>  la =  new HashMap<String, Integer>();
    la.put(MonkeySize.SMALL.toString(), 2);
    la.put(MonkeySize.MEDIUM.toString(), 5);
    la.put(MonkeySize.LARGE.toString(), 10);
    this.livingArea = la;
  }
  
  /**
   * Mandating that the client of the Monkey class uses the builder requires
   * that we have a way to get the builder.
   * 
   * @return a builder to use to create a Monkey.
   */
  public static MonkeyBuilder getBuilder() {
    return new MonkeyBuilder();
  }
  
  
  public String getName() {
    return name;
  }


  public Species getSpecies() {
    return species;
  }


  public String getSex() {
    return sex;
  }


  public double getSize() {
    return size;
  }


  public double getWeight() {
    return weight;
  }


  public int getAge() {
    return age;
  }


  public Foods getFavoriteFood() {
    return favoriteFood;
  }
  
  /**
   * check the monkey's size
   * Small monkeys (<10 cm), medium monkeys (10 to 20 cm), and large monkeys (>20 cm).
   * @return the type of size
   */
  private String checkSize() {
    if (size < 10) {
      return MonkeySize.SMALL.toString();
    } else if (size >= 10 && size <= 20) {
      return MonkeySize.MEDIUM.toString();
    } else {
      return MonkeySize.LARGE.toString();
    }
  }
  
  /**
   * This is used to get the feeding gram of the favorite food per day for a monkey.
   * @return the feeding gram
   */
  public int getFeedingGram() {
    return feedingGram.get(checkSize());
  }
  
  /**
   * This is used to get needed living area of the monkey.
   * @return the size of the living area
   */
  public int getLivingArea() {
    return livingArea.get(checkSize());
  }
  
  /**
   * This is to get a sign for each monkey, including their name,sex and favorite food.
   * @return the sign
   */
  public String getSign() {
    StringBuilder sb =  new StringBuilder();
    sb.append(name + " - ");
    sb.append(sex + " - ");
    sb.append(favoriteFood.toString());
    return sb.toString(); 
  }
  
 

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[Monkey: ");
    sb.append(name).append("\n");
    sb.append(species).append("\n");
    sb.append(sex).append("\n");
    sb.append(size).append("\n");
    sb.append(weight).append(", ");
    sb.append(age).append(" ");
    sb.append(favoriteFood).append("\n");
    return sb.toString();
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(age, favoriteFood, name, sex, size, species, weight);
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
    MonkeyBuilder other = (MonkeyBuilder) obj;
    return age == other.age && favoriteFood == other.favoriteFood
        && Objects.equals(name, other.name) && Objects.equals(sex, other.sex)
        && Double.doubleToLongBits(size) == Double.doubleToLongBits(other.size)
        && species == other.species
        && Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
  }
  
  /**
   * A builder class for building new Monkeys.
   */
  public static class MonkeyBuilder {
    // maintains fields, one for each field in the actual complex class
    private  String name;
    private  Species species;
    private  String sex;
    private  Double size;
    private  Double weight;
    private  Integer age;
    private  Foods favoriteFood;
    
    /*
     * Making this constructor private forces the client of this class to use the
     * static getBuilder method.
     */
    private MonkeyBuilder() {
      // assign default values to all the fields as above
      name = null;
      species = null;
      sex = null;
      size = null;
      weight = null;
      age = null;
      favoriteFood = null;
    }

    /**
     * Use this for setting the Monkey's name.
     * 
     * @param name the monkey's name.
     * @return this builder so that other methods can be called
     */
    public MonkeyBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    /**
     * Use this for setting the Monkey's species.
     * 
     * @param species the monkey's species.
     * @return this builder so that other methods can be called
     */
    public MonkeyBuilder species(Species species) {
      this.species = species;
      return this;
    }
    
    /**
     * Use this for setting the Monkey's sex.
     * 
     * @param sex the monkey's sex.
     * @return this builder so that other methods can be called
     */
    public MonkeyBuilder sex(String sex) {
      this.sex = sex;
      return this;
    }
    
    /**
     * Use this for setting the Monkey's size.
     * 
     * @param size the monkey's size.
     * @return this builder so that other methods can be called
     */
    public MonkeyBuilder size(double size) throws IllegalArgumentException {
      if (size <= 0) {
        throw new IllegalArgumentException("the size should be greater than 0");
      }
      this.size = size;
      return this;
    }
    
    /**
     * Use this for setting the Monkey's weight.
     * 
     * @param weight the monkey's weight.
     * @return this builder so that other methods can be called
     */
    public MonkeyBuilder weight(double weight) {
      if (weight <= 0) {
        throw new IllegalArgumentException("the weight should be greater than 0");
      }
      this.weight = weight;
      return this;
    }
    
    /**
     * Use this for setting the Monkey's  age.
     * 
     * @param age the monkey's age.
     * @return this builder so that other methods can be called
     */
    public MonkeyBuilder age(int age) {
      if (age <= 0) {
        throw new IllegalArgumentException("the age should be greater than 0");
      }
      this.age = age;
      return this;
    }
    
    /**
     * Use this for setting the Monkey's favorite food.
     * 
     * @param favoriteFood the monkey's favorite food.
     * @return this builder so that other methods can be called
     */
    public MonkeyBuilder favoriteFood(Foods favoriteFood) {
      this.favoriteFood = favoriteFood;
      return this;
    }

    /**
     * Build method called once all of the necessary data has been provided.
     * 
     * @return the Monkey
     */
    public Monkey build() throws IllegalArgumentException {
      // use the currently set values to create the Monkey object
      if (name == null || species == null || sex == null 
          || size == null || weight == null || age == null || favoriteFood == null) {
        throw new IllegalArgumentException("null is not allowed in every attribute");
      }
      return new Monkey(name, species, sex, size, weight, age, favoriteFood);
    }
  }
}