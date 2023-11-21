//program: Pokemon Battle Simulator
//author: Anthany Toum
//course: CIDS 162 
//date: 2023/03/02
//assignment #1
//description: this is a Pokemon Battle Simulation program that consists of using arrays 
//             and multiple methods. We use methods to call the attack or choices of our
//             actions. Other methods will allow the program to randomly generate and 
//             attack as the opponent. Other methods such as calculationDamage will determine
//             how strong the attack will be for each player. 
//*************************************************************************************************
import java.util.Scanner;
public class PokemonBattle {
  public static void main(String[] args) {

    //make arrays for the player and opponent
    String[] player_pokemon = {"Charizard", "Dragon Claw", "Flare Blitz", "Slash", "Inferno"};
    int[] player_stats = {100, 100, 90, 20, 70};
    String[] opponent_pokemon = {"Mewtwo", "Confusion", "Psycho Cut", "Aura Sphere", "Low Kick"};
    int[] opponent_stats = {110, 90, 80, 70, 60};
  
  //***********************************************************************************************
  //call methods
    printWelcomeMessage(player_pokemon, player_stats, opponent_pokemon, opponent_stats);
    printAvailableMoves(player_pokemon, player_stats);

  //***********************************************************************************************
  //booleans help to continue or stop the while loop
    boolean playing = true;
    boolean playerFaints = false;
    boolean opponentFaints = false;
  //*********************************************************************************************** 
  //getUserMoveChoice() calls the method to declare the player's move or choice.
  //calculateDamage calls method to determine how much damage the pokemon will attack.
  //printMoveResult calls method to tell the name and attack move of your pokemon and the opponents 
  //name and how much damage it took.
  //checkIfFainted will determine the outcome of who won the battle.
    while(playing) {
    
      int move = getUserMoveChoice();
      int damage = calculateDamage(player_stats[move]);
      printMoveResult(player_pokemon, player_stats, opponent_pokemon, opponent_stats, move, damage);
      opponent_stats[0] = opponent_stats[0] - damage;

      opponentFaints = checkIfFainted(opponent_stats[0]);
      if(opponentFaints == false) {
        System.out.println(opponent_pokemon[0] + " has " +opponent_stats[0]+ " HP remaining!");
  //***********************************************************************************************
  //  function:            opponentFaints           
  //  purpose:             Stops the while loop and tells who fainted           
  //  parameters:          playing = false
      }
      if(opponentFaints) {
        System.out.println(opponent_pokemon[0] + " fainted!");
        playing = false;
        break;
  //***********************************************************************************************
  //chooseOpponentMove() calls the method to declare the opponent's move or choice.
  //calculateDamage calls method to determine how much damage the pokemon will attack. 
  //printMoveResult calls method to tell the name and attack move of the opponent's pokemon and the
  //name of your pokemon and how much damage your pokemon took.
  //checkIfFainted will determine the outcome of who won the battle.
      }
      int opponentMove = chooseOpponentMove();
      int opponentDamage = calculateDamage(opponent_stats[opponentMove]);
      printMoveResult(opponent_pokemon, opponent_stats, player_pokemon, player_stats, opponentMove, 
                      opponentDamage);
      player_stats[0] = player_stats[0] - opponentDamage;

      playerFaints = checkIfFainted(player_stats[0]);
      if(playerFaints == false) {
        System.out.println(player_pokemon[0]+ " has " +player_stats[0]+ " HP remaining!");

      }
        if(playerFaints) {
          System.out.println(player_pokemon[0] + " fainted!");
          playing = false;
        
      }
    }
  //***********************************************************************************************
  // determine the outcome of the winner
    if (opponentFaints) {
      System.out.println(player_pokemon[0] + " Wins!");
    }else {
      System.out.println(opponent_pokemon[0]+ " Wins!");
    }

  }
  //***********************************************************************************************
  //                                        Methods
  //  function:            String, int
  //  purpose:             prints out a welcome message for the player and tells the hp of both 
  //                       pokemon. 
  //  parameters:          String[] player_pokemon, int[] player_stats, String[] opponent_pokemon, 
  //                       int[] opponent_stats       
  public static void printWelcomeMessage(String[] player_pokemon, int[] player_stats, 
                                         String[] opponent_pokemon, int[] opponent_stats) {
    System.out.println();
    System.out.println("Welcome to the Pokemon Battle Simulation! ");
    System.out.println();
    System.out.println("Your Pokemon is " +player_pokemon[0]+ " with " +player_stats[0]+ " hp!");
    System.out.println("Your opponent's Pokemon is " +opponent_pokemon[0]+ " with " 
                       +opponent_stats[0]+ " hp!");

  }
  //***********************************************************************************************
  //  function:            String, int  
  //  purpose:             to print out the available moves of your pokemon
  //  parameters:          String[] player_pokemon, int[] player_stats  
  public static void printAvailableMoves(String[] player_pokemon, int[] player_stats) {
    System.out.println();
    System.out.println("Moves Available: ");
    for (int i=1; i < player_pokemon.length; i++) {
      System.out.println((i + 0) + ". " +player_pokemon[i] + " (" +player_stats[i]+ " power)");
    }
     
  }
  //***********************************************************************************************
  //  function:            int, while        
  //  purpose:             the user will choose moves (1-4). If choose anything higher than 4 then 
  //                       move will be invalid and the program will ask you again to choose a 
  //                       move (1-4).
  //  parameters:          move < 0 and move >=5       
  //  return:              move     
  public static int getUserMoveChoice() {
    Scanner input = new Scanner(System.in);
     System.out.println();
     System.out.print("Choose a move (1-4): ");
     int move = input.nextInt();
     while (move < 0 || move >=5) { 
       System.out.print("Invalid move choice. Choose a move (1-4): ");
       move = input.nextInt();
     }
     return move;
     
  }
  //***********************************************************************************************
  //  function:            damage
  //  purpose:             calculates the damage of the attack 
  //  parameters:          int movePower
  //  return:              damage
   public static int calculateDamage(int movePower) {
     int damage = (int) (Math.random() * movePower);
     return damage;
   
  }
  //***********************************************************************************************
  //  function:            String, int  
  //  purpose:             print the name and attack move of your pokemon and the opponents 
  //                       name and how much damage it took.
  //  parameters:          tring[] pokemon1, int[] pokemon1_stats, String[] pokemon2, 
  //                       int[] pokemon2_stats, int opponentMove, int opponentDamage
  public static void printMoveResult(String[] pokemon1, int[] pokemon1_stats, String[] pokemon2, 
                                     int[] pokemon2_stats, int opponentMove, int opponentDamage) {
    System.out.println();
    System.out.println(pokemon1[0]+ " used " +pokemon1[opponentMove]+ "!");
    System.out.println(pokemon2[0]+ " took " +opponentDamage+ " damage!");
    
  }
  //***********************************************************************************************
  //  function:            int
  //  purpose:             to calculate the damage of the opponent's attack
  //  parameters:          int chooseOpponentMove
  //  return:              ((int) (Math.random() * 4)) + 1;
  public static int chooseOpponentMove() {
    return ((int) (Math.random() * 4)) + 1;
    
  }
  //***********************************************************************************************
  //  function:            boolean
  //  purpose:             Checks if the HP is greater than 0 if the user or opponent is capable of 
  //                       fainting.
  //  parameters:          if (HP>0)        
  //  return:              faints
  public static boolean checkIfFainted(int HP) {
    boolean faints;
    if (HP>0) {
      faints = false;
    }else {
      faints = true;
      
    }
    return faints;
  }
  
}
 //***********************************************************************************************
  //
  //  function:            
  //  purpose:            
  //  parameters:         
  //  return:  

