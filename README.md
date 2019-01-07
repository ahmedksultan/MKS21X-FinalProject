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

**Jan 6, 2019** - Began creating map data for the RPG to take place in.

**Jan 7, 2019** - Continued to work on map-making. Created a "makeBorders" method to simplify creating map borders.
