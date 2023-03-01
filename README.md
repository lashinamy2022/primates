1.How to run and use my program?

 First, using below code to instantiate a Sanctuary with 10 cages in the isolation, 
        15 enclosures with 30 square meter each. Each parameter should be greater than 0.
        
    Sanctuary sanctuary = new SanctuaryModel(10, 15, 30);
    
 And using below code to instantiate a Monkey class. Each number type parameters should be greater than 0
 like size, weight, age.
    
    Monkey monkey = Monkey.getBuilder()
				          .name("Lucy")
				          .species(Species.DRILL)
				          .sex("female")
				          .size(20)
				          .weight(10)
				          .age(0)
				          .favoriteFood(Foods.EGGS)
				          .build();
    
 Second, using below code to move a monkey into the Sanctuary. You should
         move a monkey into the Isolation first and then Enclosures, otherwise
         the program will throw exceptions.
 
    sanctuary.moveIntoIsolation(monkey);
    sanctuary.moveIntoEnclosures(monkey);
    
 
 Third, using below code to move a monkey out from the Isolation or Enclosure. You should move monkeys
 out from the Sanctuary when they have already existed in there.
 
    sanctuary.moveOutFromIsolation(monkey);
    sanctuary.moveOutFromEnclosures(monkey);
    
 Fourth, using below code to report species list.
 
    sanctuary.reportSpecies();
    
 Fifth, using below code to look up a particular species. You should look up an existing species.
 
    sanctuary.lookUpSpecies(Species.DRILL);
    
 Sixth, using below code to generate a monkey list living in the sanctuary.
   
    sanctuary.generateMonkeyList();
 
 Seventh, using below code to produce a shopping list.
 
    sanctuary.shoppingList();
    
 eighth, using below code to generate a sign for a given enclosure. You can use generateMonkeyList() 
         to get enclosureNumber.
   
    sanctuary.generateSignForEnclosure(String enclosureNumber);

2. example run
    //instantiate the sanctuary with 3 cages in the isolation, 
        3 enclosures with 10 square meter each.
    Sanctuary san = new SanctuaryModel(3, 3, 10);
    //instantiate monkeys
    Monkey m1 = Monkey.getBuilder()
				          .name("Lucy")
				          .species(Species.DRILL)
				          .sex("female")
				          .size(20)
				          .weight(10)
				          .age(0)
				          .favoriteFood(Foods.EGGS)
				          .build();
	Monkey m2 = Monkey.getBuilder()
				          .name("Lucy")
				          .species(Species.DRILL)
				          .sex("female")
				          .size(20)
				          .weight(10)
				          .age(0)
				          .favoriteFood(Foods.EGGS)
				          .build();		
	//move m1 into isolation			          	 
    san.moveIntoIsolation(m1);
    san.moveIntoIsolation(m2);
    //move m1 into an proper enclosure
    san.moveIntoEnclosures(m1);
    //move m2 out from the isolation
    san.moveOutFromIsolation(m2);
    //move m3 out from the enclosure
    san.moveOutFromEnclosures(m1);
    san.moveIntoIsolation(m2);
    //report the species that are currently being housed in alphabetical order
    String report = san.reportSpecies();
    System.out.println("reportSpecies:" + report);
    //look up where a particular species is currently housed
    String species = san.lookUpSpecies(Species.DRILL);
    System.out.println("look up species:" + species);
    //produce an alphabetical list (by name) of all of the monkeys housed in the Sanctuary
    String monkeyList = san.generateMonkeyList();
    System.out.println("generateMonkeyList:" + monkeyList);
    //produce a shopping list of the favorite foods of the inhabitants of the Sanctuary
    String shoppingList = san.shoppingList();
    System.out.println("shoppingList:" + shoppingList);
    //produce a sign for a given enclosure that lists each individual monkey that is currently housed there
    String enclosureSign = san.generateSignForEnclosure("enc-00001");
    System.out.println("enclosure sign:" + enclosureSign);
    //expand three cages in the isolation
    san.expand(3, 0);
    //expand five enclosures
    san.expand(5, 1);
  
 3. What changes did you made to your design after the preliminary design submission?
 
      I changed the data structure of many fields, such as 
      HashMap<String, HashMap<Integer, Monkey>> cages(in the Isolation), I changed it from List<Monkey>
      HashMap<String, HashMap<Integer, Monkey>> enclosures(in the Enclosures), I changed it from List<Monkey>
      
      And I gave up using another class Enclosure to represents each enclosure. I turned to used a field
      with the data structure mentioned before to represent it.
      
 4. Some assumptions that you made during the implementation of the program.
  
    I assumed that every money already has detail information when they come into the Sanctuary 
    and what the program does is to record these information.
        
 5. how to run JAR
    
    java -cp project1.jar drivers.SanctuaryModelDriver
