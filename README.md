# pkmn-java
Created by Ahmed Sultan and Ali Taoube, for Mr. K's Fall Semester APCS class.

## devlog

**Jan 4, 2019** -
Began working on the Pokemon constructor. Initially, the constructor used a dummy data file. This data file
did not exist, as we worked on the constructor before reading in the file.

Added accessor methods for all possible values - Defense, Attack, Health, etc.

Fixed an error caused by attempting to store a String value in String[] in the data value. Also fixed converting values to ints for some statistics.

Began working on a dealDamage function, using the official formula used online. Also added a modifier help method that calculates 1/4x, 1/2x, 1x, or 2x power based on the move used.

Also added a moves.csv to be used in calculating damage.

Added a takeDamage function to work in tandem with the dealDamage function.

Added another helper function to check if a Pokemon has fainted (hp < 0)

**Jan 5, 2019** -
Added move class in order to more efficiently manage Pokemon attacks. Began by working on the move constructor. Made it so that it effectively assigns values of move properties (power and type) by reading in the text file.

Added corresponding accessor methods for aforementioned properties.

Edited the modifier calculator so that it would calculate damage based on the move, rather than the opponent.

Added a type_efficacy .csv file so that the damageCalculator could be calculated based on the effectiveness of the move type against the type of the opponent. Also got rid of the takeDamage function and simply encompassed it within the previously named dealDamage function, and renamed it attack.

**Jan 6, 2019** -
Edited the function that calculates typeWeaknesses by parsing through the file and checking for the corresponding type to add to the ArrayList.

Fixed a bug that caused an error to be thrown by a helper function for the calculate typeWeaknesses and Resistances function. Fixed it by ignoring the first line, which were strings and therefore you could not parseInt them.

Edited the attack function so that it uses an String name rather than a Move.

Properly assigned typeIDs by editing the function that assigns them, and added accessor methods for them.

Fixed an error in the modifier function that caused it to use the wrong list to calculate the modifier.

Began creating map data for the RPG to take place in.

**Jan 7, 2019** - Continued to work on map-making. Created a "makeBorders" method to simplify creating map borders.

Fixed an error that caused a 2x effective move to return 1x effective.

Began working on the Player abstract class that will be used for the main character themselves as well as the NPCs.

Added a file full of Pokemon sprites to be used for the battleSystem. Also trimmed the Pokemon.csv file to only have Pokemon that we have the sprites for.

**Jan 8, 2019** - Again, continued to work on map-making. Started working more with lanterna.

Begun creating processes to put the maps in Java.

Converted the sprites file into a textfile rather than a java file, and removed the previous one.

**Jan 9, 2019** - practiced lanterna to get a better understanding of how it works.

Continued working on abstract Player class by finishing the Constructor. Then, extended Player class through Trainer.

**Jan 10, 2019** - restarted maps, as we were not getting anywhere with the current version.

Created an Enemy class that also extends Player, just like the Trainer class. Implemented the constructor for Enemy.

Created a method we would stick to when creating maps.

Began working on the Battle class. Implemented a simple constructor that sets both Players. Also, two functions that update the turn.

Added a get function to retrieve the pokemon corresponding to the input index.

**Jan 11, 2019** - continued working on maps.

Completely reworked the Turn System. Got rid of all appropriate accessor methods. Implemented a catchable abstract method to get whether or not the Pokemon can be caught, and then implemented it in the subclasses.

Created a Sprites class to read through the previously implemented Sprites.txt file. Added the constructor for this class.

**Jan 12, 2019** - continued working on maps. Restarted work again.

Added a method to check whether all Pokemon in the Trainer's party are dead.

Now that all necessary functions are added, began working on a driver to make sure the Driver class works properly.

Completely reworked the Player class. Instead of having a lot of actual functions, we replaced them all with abstract methods, and then implemented them in subclasses.

Continued updating the driver for the Battle class.

**Jan 13, 2019** - continued working on maps, and the RPG element of the game. Restarted work again at the end of the night (third time thus far).

Worked on bringing all the map data into Lanterna, and transferring the 2d array data into visual graphics.

**Jan 14, 2019** - continued working on maps, and implemented trainer and random battles into the game.

"Working" demo completed.

Modified the modifier function using Collections.frequency in order to cut down on the length of the code.

Added a constructor in the Move class that allows you to choos a move based on the index of a move rather than the name.

Fixed error that caused the game to never end when you only had a single Pokemon left. Added a setMoves function that automatically assigns four moves when none are input - this is to auto generate Pokemon.

Reworked Pokemon constructor. Created a function called create that assigns all the values, like attack, defense, etc. Then, we made two constructors. One of which took an ArrayList of Moves as an input. In this case, it used the setAttacks function that manually assigned moves, while the other constructor auto-assigned moves.

Made it so that attempting to use a non-existent move will now throw and catch the error.

Ran into an error that caused any Pokemon that had an evolutionary state to not have any moves. Discovered this was a result of the movesets.csv file not having these Pokemon. To fix this, we added a csv file that has all the evolutions of any Pokemon. Then, we made a function that finds the evolutionary ID of any Pokemon ID by using this new csv file. Then, introduced an idToName function that converts this ID to a name. Also implemented an nameToID function that does the opposite. Afterwards, we can run the setAttacks on the evolutionary state.

**Jan 15, 2019** -

Edited the Pokemon constructor to now assign the ID of the Pokemon.

Fixed the idToName function.

Overloaded the attack function to allow for a function that chooses a Move based on a percent chance and the type efficacies of each move.

**Jan 16, 2019** -

Fixed an error that was causing all moves to do damage - the issue was the move names in the movesets.csv file and the moves.csv file were not matching up in case. To fix this, we used .toLowerCase(). Also, removed all hyphens in the movesets.csv file to allow for more uniformity.

The overloaded attack method does not work as expected, decided to just choose a random move in the Driver when actually running the battle.

Began to work on the Sprites class - implemented two functions. One to get the length of the necessary array, and one to get the width.

Realized that previously implemented functions in the Sprites class were unnecessary. Instead, replaced it with a single getArray function that gets the necessary array.

Fixed an infinite loop in the getArray function.

Introduced another constructor that takes in a Pokemon name rather than an ID, using the nameToID function.

**Jan 17, 2019** -

Began to work on formatting the array that was returned by the getArray function by removing all \n and "" that were not necessary for printing.

Removed unnecessary comments throughout all files.

Added a function for Pokemon that randomly chooses a move for a Pokemon.

Changed the move function in the Battle class to no longer require two moves, as only one is needed - the Player's, as the NPCs is auto-chosen.


**Jan 18, 2019** -

Added a function that gets the modifier for a move and enemy.

Removed unnecessary comments in the Pokemon class.

Attempted to introduce a function that flips the array given by getArray in the Sprites class.

**Jan 20, 2019** -

Abandoned flipArray function in the Sprites class.
