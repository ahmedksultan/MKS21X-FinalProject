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
