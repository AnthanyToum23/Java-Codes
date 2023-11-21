//assignment: #2
//description: The goal is to build a Pokédex. A Pokédex is an electronic 
//             Pokémon index that allows you to search a number of 
//             Pokemons(1-802). In the game, a Pokedex helps a player keep
//             track of which Pokemon they encounter and catch throughout 
//             their adventure. The Pokédex catalogs useful information 
//             about various Pokémon species such as height, weight, type 
//             and a description of the Pokémon in question. 
//*****************************************************************************
import java.io.IOException; // handles errors io looks deeper into the code
import java.nio.file.Files; // nio handles IO operations that require high 
//                             performance import files to class
import java.nio.file.Path; // creates a new path by a string
import java.util.List; // represents a collection of elements
import java.util.Scanner;
class Pokedex {
  public static void main(String[] args) {
//*****************************************************************************
//  function:              String[][] pokemonList           
//  purpose: This initializes an empty 2D array named pokemonList that 
//           will be used to store the data read from the file.          
    Scanner scanner = new Scanner(System.in);
    String[][] pokemonList = {};

//*****************************************************************************
//  function:        Files.readAllLines(Path.of("pokemon.csv"))       
//  purpose:  Reads all lines from the file and returns them as a list of 
//            strings. The purpose of this Java code is to read data from a 
//            file named pokemon.csv and store it in a 2D array named 
//            pokemonList.
    try {
      List<String> lines = Files.readAllLines(Path.of("pokemon.csv"));
      pokemonList = new String[lines.size()][]; //name of array and returns  
//                                                the elements of the list
//*****************************************************************************
//  function:                      for loop         
//  purpose: This loop iterates through each line in the list of strings, 
//           splits it into an array of strings using the comma as a delimiter, 
//           and stores it in the pokemonList array.
      for (int i = 0; i < lines.size(); i++) {
        pokemonList[i] = lines.get(i).split(",");
        
      }
//*****************************************************************************
//  function:     try{} catch(IOException e), System.err, e.getMessage()           
//  purpose: try{} catch (IOException e){} is a block of code that reads the 
//           contents of the pokemon.csv file into the pokemonList array. A 
//           message will appear when IOException the code fails to read the 
//           file. System.err is used to print out error messages and has an 
//           exception to print other errors that occur during the program
//           execution. getMessage() is a method that returns a string, 
//           "pokemon.csv", with a message about an exception error. 
//  parameter:                        boolean
//  return:                            true
    }catch (IOException e) {
      System.err.println("Failed to read pokemon.csv file: " 
                         +e.getMessage());
      System.exit(1);
      
    }
    System.out.println();
    System.out.print("Welcome to the Pokedex! ");
    boolean searching = true;
//*****************************************************************************
//  function:              For loop, boolean, searchType           
//  purpose: iterates each row in the pokemonList. Boolean initializes a 
//           variable called foundPokemon to false. searchType Stores the name 
//           or the number of Pokemon the user wants to search for.  
//  parameter:               searching, foundPokemon
//  return:                        true, break
    while (searching) {
      System.out.println("Would you like to search by name or by"+
                         " number?");
      String searchType = scanner.nextLine();
      if (searchType.equalsIgnoreCase("name")) {
        System.out.println("Please enter the name of the Pokemon:");
        String searchName = scanner.nextLine();
        boolean foundPokemon = false;
        for (String[] pokemon : pokemonList) {
          if (pokemon[1].equalsIgnoreCase(searchName)) {
            System.out.println(pokemon[1]+", Pokedex Number "+pokemon[0]+
                              ", "+pokemon[2]+", type1: "+pokemon[3]+
                              ", type2: "+pokemon[4]);
            foundPokemon = true;
            break;
          }
        }
//*****************************************************************************
//  function:                     "!", else if             
//  purpose:  "!" is the not or false operator. This handles the case where the 
//            user wants to search for a Pokemon by their Pokedex number.   
//  parameter:                    foundPokemon
//  return:                       false, break
        if (!foundPokemon) {
          System.out.println("Sorry, there is no known Pokemon by "+
                             "that name. Please try again.");
        }
      }else if (searchType.equalsIgnoreCase("number")) {
        System.out.println("Please enter the number of the Pokemon: ");
        String searchNumber = scanner.nextLine();
        boolean foundPokemon = false;
        for (String[] pokemon : pokemonList) {
          if (pokemon[0].equals(searchNumber)) {
            System.out.println(pokemon[1]+", Pokedex Number "+pokemon[0]+
                              ", "+pokemon[2]+", type1: "+pokemon[3]+
                              ", type2: "+pokemon[4]);
            foundPokemon = true;
            break;
          }
        }
//*****************************************************************************
//  function:                  if-else statement       
//  purpose: if checks to see if the name of the pokemon was in the search, if 
//           not then an error message will pop up. Else checks if the user 
//           types an invalid search an error message will pop up. 
//  parameters:                    searching     
//  return:                          false 
        if (!foundPokemon) {
          System.out.println("Sorry, there is no known Pokemon by "+
                             "that name. Please try again.");
      }
      }else{
        System.out.println("Invalid search type. Please try again.");
        
      }
      System.out.println("Would you like to search for another"+
                        " Pokemon (y/n)?");
      String continueSearch = scanner.nextLine();
      if (continueSearch.equalsIgnoreCase("n")) {
        searching = false;
        
      }
    }
    System.out.println("Thank you for using your Pokedex!");

  }
}
//*****************************************************************************
//
//  function:            
//  purpose:            
//  parameters:         
//  return:      
