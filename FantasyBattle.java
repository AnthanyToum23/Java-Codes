//program: Fantasy Battle
//author: Anthany Toum
//course: CIDS 235
//date: 2023/21/07
//assignment: #2
//description: My task is to implement a simple battle simulator in Java. 
//             This assignment aims to assess my understanding of 
//             inheritance, class hierarchies, and method overriding.
//*****************************************************************************
import java.util.Random;
import java.util.Scanner;
public class FantasyBattle {
  public static void main(String[] args) {
//*****************************************************************************
//  Note: I made the Player's health restart at the end of each battle because 
//        I thought it was unfair for the player to have such little health for 
//        the next boss battle. I also bumped up the potion because Dragon is 
//        super OP (Over Powered). 
//
//  function:              for (Monster monster : monsters)
//  purpose:     iterates each monster object in the monsters list
//  function:              Player player = new Player()
//  purpose:     Player is a class representing the player in the game    
//  function:           player.getHealth & monster.getHealth
//  purpose: Basically a health bar to know what the health is for the player  
//           and monster when battling.
//  function:                monster.getClass().getName()
//  purpose: Prints the class and name for the current monster when battling.

    Scanner scanner = new Scanner(System.in);

    Monster[] monsters = {new Orc(), new Demogorgon(), new Dragon()};

    for (Monster monster : monsters) {
      Player player = new Player();
      System.out.println("Player's HP: " +player.getHealth());
      System.out.println(monster.getClass().getName()+ "'s HP: " 
                         +monster.getHealth());
      boolean playerAlive = true;

      while(player.getHealth() > 0 && monster.getHealth() > 0 ) {
        System.out.println();
        System.out.println("--- Player's Turn ---");
        displayMenu();
        int choice = scanner.nextInt();

//*****************************************************************************
//
//  function:               switch(choice) and case         
//  purpose: Choices made when the user clicks numbers 1-3 and cases are the 
//           names of the characters actions.        
//  return:  break
        
        switch (choice) {
          case 1:
            player.swordSlash(monster);
            break;
          case 2:
            player.raiseShield();
            break;
          case 3:
            player.usePotion();
            break;
          default:
            System.out.println("Invalid choice. Try again.");
            continue;
        }
        if (monster.getHealth() <= 0) {
          System.out.println(monster.getClass().getName() 
                             + " has been defeated!");
          break;
        }

        System.out.println();
        System.out.println("--- " +monster.getClass().getName()+"'s Turn ---");
        if (player.shieldRaised()) {
          System.out.println(monster.getClass().getName() + " attacked, but" 
            + " Player used Shield to block it.");
        } else {
        monster.attack(player);
        }

        if (player.getHealth() <= 0) {
          System.out.println("Player has been defeated!");
          break;
        }
          
        System.out.println("\nPlayer's HP: " +player.getHealth());
        System.out.println(monster.getClass().getName() + "'s HP: " 
                           +monster.getHealth());
      }
      System.out.println();
      System.out.println("--- Battle Ends ---");
      System.out.println();
      if (!playerAlive) {
        break;
      }
    }
    scanner.close();
  }

  private static void displayMenu() {
    System.out.println("Choose an action:");
    System.out.println("1. Sword Slash");
    System.out.println("2. Raise Shield");
    System.out.println("3. Use Potion");
    System.out.println("Enter your choice: ");
  }
}
//*****************************************************************************
//
//  function:                    getRandomNumber        
//  purpose:  Randomly chooses the damage or health numbers between the given
//            numbers.
//  return:           health, random.nextInt(max - min + 1) +min

class Entity {
  private int health;

  public Entity(int health) {
    this.health = health;
  }
  
  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }
}

class Player extends Entity {
  private boolean shieldRaised;
  public Player() {
    super(100);
    shieldRaised = false;
  }

  public void swordSlash( Monster monster) {
    int damage = getRandomNumber( 20, 30);
    System.out.println();
    System.out.println("Player used Sword Slash and inflicted " 
                       +damage+ " damage.");
    monster.takeDamage(damage);
  }

  public boolean shieldRaised() {
    return shieldRaised;
  }

  public void raiseShield() {
    System.out.println();
    System.out.println("Player used the Shield. ");
    shieldRaised = true;
  }

  public void usePotion() {
    int recoveredHealth = getRandomNumber(80, 100);
    int currentHealth = getHealth();
    int newHealth = Math.min(currentHealth + recoveredHealth, 100);
    setHealth(newHealth); 
    System.out.println("Player used Potion and recovered " 
                       +recoveredHealth+ " health.");
  }

  public boolean takeDamage(int damage) {
    if (shieldRaised) {
      shieldRaised = false;
      return true;
    } else {
    int currentHealth = getHealth();
    int newHealth = Math.max(currentHealth - damage, 0);
    setHealth(newHealth);
    System.out.println("Player took " +damage+ " damage.");
    return false;
  }
  }

  private int getRandomNumber(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min + 1) +min;
  }
}

abstract class Monster extends Entity {
  public Monster(int health) {
    super(health);
  }

  public abstract void attack(Player player);

  public void takeDamage(int damage) {
    int currentHealth = getHealth();
    int newHealth = Math.max(currentHealth - damage, 0);
    setHealth(newHealth);
    System.out.println("Monster took " +damage+ " damage.");
  }

  protected int getRandomNumber(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min +1) +min;
  }
}
//*****************************************************************************
//
//  function:                     @Override        
//  purpose:  To ensure that this is an overridden version of the method and 
//            not a new method.

class Orc extends Monster {
  public Orc() {
    super(50);
  }

  @Override
  public void attack(Player player) {
    int choice = getRandomNumber(1, 2);
    int damage = 0;

    if (choice == 1) {
      damage = getRandomNumber( 5, 10);
      System.out.println("Orc used Sword Slash and inflicted " 
                         +damage+ " damage.");
    }else{
      damage = getRandomNumber(5,10); 
      System.out.println("Orc used Shoot Arrow and inflicted " 
                         +damage+ " damage.");
    }

    player.takeDamage(damage);
  }
}
class Demogorgon extends Monster {
  public Demogorgon() {
    super(100);
  }

  @Override
  public void attack(Player player) {
    int choice = getRandomNumber(1, 2);
    int damage = 0;

    if (choice == 1) {
      damage = getRandomNumber(10, 15);
      System.out.println("Demogoron used Slash and inflicted " 
                         +damage+ " damage.");
    }else{
      damage = getRandomNumber(20, 30); 
      System.out.println("Demogoron used Bite and inflicted " 
                         +damage+ " damage.");
    }

     player.takeDamage(damage);
  }
}
class Dragon extends Monster {
  public Dragon() {
    super(200);
  }

  @Override
  public void attack(Player player) {
    int choice = getRandomNumber(1, 2);
    int damage = 0;

    if (choice == 1) {
      damage = getRandomNumber(20, 50);
      System.out.println("Dragon used Breathe Fire and inflicted " 
                         +damage+ " damage.");
    }else{
      damage = getRandomNumber(20, 75); 
      System.out.println("Dragon used Bite and inflicted " 
                         +damage+ " damage.");
    }

     player.takeDamage(damage);
  }
}
//*****************************************************************************
//
//  function:            
//  purpose:            
//  return:    

