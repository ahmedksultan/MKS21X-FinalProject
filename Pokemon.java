import java.util.*; //scanner, ArrayList, Map
import java.io.*; //file, filenotfoundexception

public class Pokemon {

  public static void main(String[] args) {
    Pokemon bulb = new Pokemon("Bulbasaur");
    Pokemon ivy = new Pokemon("Ivysaur");
    Pokemon weeze = new Pokemon("Weezing");

    System.out.println("Testing Bulbasaur properties");
    System.out.println(bulb.getHP());
    System.out.println(bulb.getAttack());
    System.out.println(bulb.getDefense());
    System.out.println(bulb.getSpeed());
    System.out.println(bulb.getType1());
    System.out.println(bulb.getTypeID1());
    System.out.println(bulb.getType2());
    System.out.println(bulb.getTypeID2());
    System.out.println(ivy.getTypeWeakness());
    System.out.println(ivy.getTypeResistance());

    bulb.attack(weeze, "earthquake");
    System.out.println();
    System.out.println(ivy.getHP());
    bulb.attack(ivy, "absorb");
    System.out.println(ivy.getHP());
    bulb.attack(ivy, "fly");

    bulb.attack(ivy, "flamethrower");
    System.out.println(ivy.getHP());
  }

  private String name, type1, type2;
  private int attack, speed, defense, typeID1, typeID2;
  private double hp;
  private ArrayList<Move> attacks;
  private ArrayList<String> typeWeakness, typeResistance;
  //Have to convert this way because CSV file gives IDs for types, not names, so
  //we can match them up.
  private String[] types =
  {"Normal", "Fighting", "Flying", "Poison", "Ground",
   "Rock", "Bug", "Ghost", "Steel", "Fire", "Water",
   "Grass", "Electric", "Psychic", "Ice", "Dragon", "Dark", "Fairy"};

  public Pokemon(String name1){
    name = name1;

    String[] data = organizeData(name1);

    type1 = data[2];
    type2 = data[3];

    for (int x = 0; x < types.length; x++){
      if (types[x].equals(type1)) typeID1 = x+1;
      if (types[x].equals(type2)) typeID2 = x+1;
    }

    setWeakandRes();

    hp = Integer.parseInt(data[5]);
    attack = Integer.parseInt(data[6]);
    defense = Integer.parseInt(data[7]);
    speed = Integer.parseInt(data[10]);
  }

  public String toString(){
    return name;
  }


  // organizeData reads through the moves.csv file and returns a String with all
  // the data from the corresponding move.
  private String[] organizeData(String name1){
    try{
      File f = new File("Pokemon.csv");
      Scanner in = new Scanner(f);

      while (in.hasNext()){
        String line = in.nextLine();
        String[] stats = line.split(",");

        if (stats[1].equals(name)) {
          return stats;
        }
      }
    }
    catch (FileNotFoundException e){
      System.out.println("Error");
    }
    throw new Error();
  }

  public boolean isDead(){
    return hp <= 0;
  }

  //Accessor Methods///////////////////
  //This essentially returns every property that a Pokemon has.
  public String getName(){
    return name;
  }

  public String getType1(){
    return type1;
  }

  public String getType2(){
    return type2;
  }

  public double getHP(){
    return hp;
  }

  public int getAttack(){
    return attack;
  }

  public int getDefense(){
    return defense;
  }

  public ArrayList<Move> getAttacks(){
    return attacks;
  }

  public ArrayList<String> getTypeWeakness(){
    return typeWeakness;
  }

  public ArrayList<String> getTypeResistance(){
    return typeResistance;
  }

  public int getSpeed(){
    return speed;
  }

  public int getTypeID1(){
    return typeID1;
  }

  public int getTypeID2(){
    return typeID2;
  }

  /////////////////////////////////

  // Mutator  and Helper Methods

  private void setHP(double num){
    hp = num;
  }

  /////////////////////////////

  // Goes through type_efficacy.csv and checks to align the weaknesses and
  // resistances - easier to do this in one function.

  private void setWeakandRes(){
    typeWeakness = new ArrayList<String>(10);
    typeResistance = new ArrayList<String>(10);

    try{
      File f = new File("type_efficacy");
      Scanner in = new Scanner(f);
      String line = in.nextLine(); // To skip the first row that just has labels

      while (in.hasNext()){
        line = in.nextLine();
        String[] stats = line.split(",");


        if (typeID1 == Integer.parseInt(stats[1])){
          if (Integer.parseInt(stats[2]) == 200){
            typeWeakness.add(stats[0]);
          }
          if (Integer.parseInt(stats[2]) == 50){
            typeResistance.add(stats[0]);
          }
        }

        if (typeID2 == Integer.parseInt(stats[1])){
          if (Integer.parseInt(stats[2]) == 200){
            typeWeakness.add(stats[0]);
          }
          if (Integer.parseInt(stats[2]) == 50){
            typeResistance.add(stats[0]);
          }
        }
      }
    }
      catch(FileNotFoundException e){
        System.out.println("error");
      }
      removeRepeats();
    }


    // There can be a type in the typeWeakness array, and the same type in the
    // typeResistance array due to the fact there some Pokemon have more than
    // one type. This removes the type from both arrays if they exist.
    private void removeRepeats(){
      int count = 0;
      for (int x = 0; x < typeResistance.size(); x++){
        if (typeWeakness.contains(typeResistance.get(x))){
          typeWeakness.remove(typeResistance.get(x));
          typeResistance.remove(x);
          typeResistance.remove(typeResistance.get(x));
        }
      }
    }


    // This calculates the proper modifier, based on if the type exists in the
    // typeWeakness or typeResistance array, and returns .25, .5, 1, 2, or 4
    // based on which array it exists in, and how many times.

private double modifier(Move move, Pokemon enemy){
  int typeID = move.getTypeID();

  if (enemy.getTypeWeakness().contains(String.valueOf(typeID))) {
    int num = Collections.frequency(enemy.getTypeWeakness(), String.valueOf(typeID));
    if (num == 1) return 2;
    if (num == 2) return 4;
  }
  if (enemy.getTypeResistance().contains(String.valueOf(typeID))) {
    int num = Collections.frequency(enemy.getTypeResistance(), String.valueOf(typeID));
    System.out.println(num + "here");
    if (num == 1) return .5;
    if (num == 2) return .25;
  }
  return 1;
}

  public void attack(Pokemon enemy, String move1){
    Move move = new Move(move1);
    double mod = modifier(move, enemy);

    // Formula found online - it's the actual formula used to calculate damage
    double dmg = ((42 * move.getPower()) *
           (attack / enemy.getDefense()+2)
           / 50 * mod);

    System.out.println("attack was " + mod + "x effective. " + enemy.getName() + " took " + dmg + " damage!");

    if (enemy.getHP() - dmg > 0) enemy.setHP(enemy.getHP() - dmg);
    else enemy.setHP(0);
  }
}
