# pkmn-java
Created by Ahmed Sultan and Ali Taoube, for Mr. K's Fall Semester APCS class.

Jan 4, 2019-
Began working on the Pokemon constructor. Initially, the constructor used a dummy data file. This data file
did not exist, as we worked on the constructor before reading in the file. Next, we added accessor methods for all possible values - Defense, Attack, Health, etc.

Fixed an error caused by attempting to store a String value in String[] in the data value. Also fixed converting values to ints for some statistics.

Began working on a dealDamage function, using the official formula used online. Also added a modifier help method that calculates 1/4x, 1/2x, 1x, or 2x power based on the move used.

Also added a moves.csv to be used in calculating damage.

Added a takeDamage function to work in tandem with the dealDamage function.

Added another helper function to check if a Pokemon has fainted (hp < 0)
