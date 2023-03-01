package drivers;

import enumerate.Foods;
import enumerate.Species;
import sanctuary.Monkey;

/**
 * This is to test the functions of {@link Monkey}.
 *
 */
public class MonkeyDriver {
  
  /**
   * This method is to test instantiate monkey with invalid parameter whether
   * it can throw {@link IllegalArgumentException}.
   */
  public static void testMonkeyWithInvalidParameter() {
    try {
      Monkey.getBuilder()
          .name("Lili")
          .species(Species.DRILL)
          .sex("female")
          .size(-20)
          .weight(10)
          .age(2)
          .favoriteFood(Foods.EGGS)
          .build();
      System.out.println("test Monkey with invalid size:error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test Monkey with invalid size:good");
    }
    
    try {
      Monkey.getBuilder()
          .name("Lili")
          .species(Species.DRILL)
          .sex("female")
          .size(20)
          .weight(-10)
          .age(2)
          .favoriteFood(Foods.EGGS)
          .build();
      System.out.println("test Monkey with invalid weight:error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test Monkey with invalid weight:good");
    }
    
    try {
      Monkey.getBuilder()
          .name("Lili")
          .species(Species.DRILL)
          .sex("female")
          .size(20)
          .weight(10)
          .age(0)
          .favoriteFood(Foods.EGGS)
          .build();
      System.out.println("test Monkey with invalid age:error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test Monkey with invalid age:good");
    }
  }
  
  /**
   * This is to test whether the class Monkey can throw {@link IllegalArgumentException} corrently 
   * with null values.
   */
  public static void testMonkeyWithNull() {
    try {
      Monkey.getBuilder()
          .species(Species.DRILL)
          .sex("female")
          .size(-20)
          .weight(10)
          .age(2)
          .favoriteFood(Foods.EGGS)
          .build();
      System.out.println("test Monkey's name with null:error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test Monkey's name with null:good");
    }
    
    try {
      Monkey.getBuilder()
          .name("Lili")
          .sex("female")
          .size(20)
          .weight(10)
          .age(0)
          .favoriteFood(Foods.EGGS)
          .build();
      System.out.println("test Monkey's species with null:error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test Monkey's species with null:good");
    }
    
    try {
      Monkey.getBuilder()
          .name("Lili")
          .species(Species.DRILL)
          .size(20)
          .weight(10)
          .age(0)
          .favoriteFood(Foods.EGGS)
          .build();
      System.out.println("test Monkey's sex with null:error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test Monkey's sex with null:good");
    }
    
    try {
      Monkey.getBuilder()
          .name("Lili")
          .species(Species.DRILL)
          .sex("female")
          .weight(10)
          .age(0)
          .favoriteFood(Foods.EGGS)
          .build();
      System.out.println("test Monkey's size  with null:error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test Monkey's size with null:good");
    }
    
    try {
      Monkey.getBuilder()
          .name("Lili")
          .species(Species.DRILL)
          .sex("female")
          .size(-20)
          .age(2)
          .favoriteFood(Foods.EGGS)
          .build();
      System.out.println("test Monkey's weight  with null:error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test Monkey's weight  with null:good");
    }
    
    try {
      Monkey.getBuilder()
          .name("Lili")
          .species(Species.DRILL)
          .sex("female")
          .size(-20)
          .weight(10)
          .favoriteFood(Foods.EGGS)
          .build();
      System.out.println("test Monkey's age  with null:error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test Monkey's age  with null:good");
    }
    
    try {
      Monkey.getBuilder()
          .name("Lili")
          .species(Species.DRILL)
          .sex("female")
          .size(-20)
          .weight(10)
          .age(2)
          .build();
      System.out.println("test Monkey's favorite food  with null:error");
    } catch (IllegalArgumentException ex) {
      System.out.println("test Monkey's favorite food  with null:good");
    }
  }
  
  /**
   * This is to test whether getters can return the correct value.
   */
  public static void testMonkeyGetValues() {
    Monkey monkey = Monkey.getBuilder()
                          .name("Lili")
                          .species(Species.DRILL)
                          .sex("female")
                          .size(20)
                          .weight(10)
                          .age(2)
                          .favoriteFood(Foods.EGGS)
                          .build();
    System.out.println("test getName:" + (monkey.getName().equals("Lili") ? "good" : "error"));
    System.out.println("test getSpecies:" + (
        monkey.getSpecies().toString().equals(Species.DRILL.toString()) ? "good" : "error"));
    System.out.println("test getSex:" + (monkey.getSex().equals("female") ? "good" : "error"));
    System.out.println("test getSize:" + (monkey.getSize() == 20 ? "good" : "error"));
    System.out.println("test getWeight:" + (monkey.getWeight() == 10 ? "good" : "error"));
    System.out.println("test getAge:" +  (monkey.getAge() == 2 ? "good" : "error"));
    System.out.println("test getFavoriteFood:" + (
        monkey.getFavoriteFood().toString().equals(Foods.EGGS.toString()) ? "good" : "error"));
  }
  
  /**
   * Driver program for Monkey to show how it works.
   * @param args not used
   */
  public static void main(String[] args) {
    testMonkeyWithInvalidParameter();
    testMonkeyWithNull();
    testMonkeyGetValues();
  }
}
