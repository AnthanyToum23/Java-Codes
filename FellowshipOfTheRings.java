//program: The Fellowship of the Rings
//author: Anthany Toum
//course: CIDS 235
//date: 2023/18/06
//assignment: #1
//description: Practice creating classes and objects. You will create a 
//             Character class and an instance for each member of The 
//             Fellowship and implement methods to interact with those objects.
//*****************************************************************************
class Main {
  public static void main(String[] args) {
//*****************************************************************************
//  function:                        Character         
//  purpose: To initialize each character with specific values such as their 
//           name, race, and age.

    Character character1 = new Character("Frodo", "Hobbit", 50);
    Character character2 = new Character("Samwise", "Hobbit", 38);
    Character character3 = new Character("Merry", "Hobbit", 36);
    Character character4 = new Character("Pippin", "Hobbit", 28);
    Character character5 = new Character("Aragorn", "Man", 87);
    Character character6 = new Character("Boromir", "Man", 40);
    Character character7 = new Character("Legolas", "Elf", 2000);
    Character character8 = new Character("Gimli", "Dwarf", 139);
    Character character9 = new Character("Gandalf", "Wizard", 2000);

//*****************************************************************************
//  function:                  Character.getNumNumbers()         
//  purpose:       Prints the total number of fellowship members. 

    System.out.println("Number of Fellowship Members: " +
                      Character.getNumMembers());
    System.out.println("\nCharacter Details: ");
//*****************************************************************************
//  function:                         details()           
//  purpose: A method to call each character object which prints the name,  
//           race, and age.

    character1.details();
    character2.details();
    character3.details();
    character4.details();
    character5.details();
    character6.details();
    character7.details();
    character8.details();
    character9.details();

  }
}
//*****************************************************************************
//  function:                     String and int           
//  purpose: Represents the name, race, and age of a character (String, String,
//           and int). Static int numMembers counts the number of characters 
//           created.
//  parameters:                   name, race, age

class Character{
  String name;
  String race;
  int age;
  static int numMembers = 0;

//*****************************************************************************
//  function:                         this.name          
//  purpose: Refers specifically to the name attribute of the object, same goes 
//           for age and race. 
  
  Character (String name, String race, int age){
  this.name = name;
  this.race = race;
  this.age = age;
  numMembers++;
}
  
  void details(){
    System.out.println("Name: " +name);
    System.out.println("Race: " +race);
    System.out.println("Age: " +age);
    System.out.println();
  }
//*****************************************************************************
//  function:                  getNumMembers, numMembers           
//  purpose: getNumMembers is a static method that returns the total of number 
//           of character members in the Fellowship. 
//  return:                          numMembers
  
  static int getNumMembers(){
    return numMembers;
  }

}
//*****************************************************************************
//
//  function:            
//  purpose:            
//  parameters:         
//  return:    