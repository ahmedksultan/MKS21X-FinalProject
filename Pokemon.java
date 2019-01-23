import java.util.*; //scanner, ArrayList, Map, HashMap
import java.io.*; //file, filenotfoundexception

public class Pokemon {
  public static void main(String[] args) {
    Pokemon chara = new Pokemon("Squirtle");
    Pokemon hey = new Pokemon("Charmander");
    System.out.println(chara.getAttacks());

    System.out.println(hey.getAttacks());

    chara.attack(hey);
    hey.attack(chara);
  }


  private String name, type1, type2;
  private int attack, speed, defense, ID, typeID1, typeID2;
  private double hp;
  private ArrayList<String> attacks, possibleAttacks;
  private ArrayList<String> typeWeakness, typeResistance;
  private boolean evolve = true;
  private String enemyattack;

  //Have to convert this way because CSV file gives IDs for types, not names, so
  //we can match them up.
  private String[] types =
  {"Normal", "Fighting", "Flying", "Poison", "Ground",
   "Rock", "Bug", "Ghost", "Steel", "Fire", "Water",
   "Grass", "Electric", "Psychic", "Ice", "Dragon", "Dark", "Fairy"};

   private void create(String name1){
     name = name1;

     String[] data = organizeData(name1);

     ID = Integer.parseInt(data[0]);
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

  public Pokemon(String name1, ArrayList<String> selectedAttacks){
    create(name1);
    possibleAttacks(name1);
    setAttacks(name1, selectedAttacks);
    haveEvolve(name1);

    if (evolve){
      evolvedMoves(name1);
    }
  }

  public Pokemon(String name1){
    create(name1);
    possibleAttacks(name1);
    setAttacks(name1);
    haveEvolve(name1);

    if (evolve){
      evolvedMoves(name1);
    }

  }

  public String toString(){
    return name;
  }

  public boolean evolves(){
    return evolve;
  }

  private void haveEvolve(String named){
    try{
      File f = new File("movesets.csv");
      Scanner in = new Scanner(f);

      while (in.hasNext()){
        String line = in.nextLine();
        String[] stats = line.split(",");
        if (stats[0].equals(named)){
          evolve = false;
          break;
        }
      }
    }
    catch(FileNotFoundException e){
      System.out.println("In haveEvolve");
      throw new Error();
    }
  }

  private static int evolutionID(int index){
    try{
      File f = new File("evolutions.csv");
      Scanner in = new Scanner(f);

      while (in.hasNext()){
        String line = in.nextLine();
        String[] stats = line.split(",");

        if (String.valueOf(index).equals(stats[0])){
          return Integer.parseInt(stats[1]);
        }
      }
    }
    catch(FileNotFoundException e){
      System.out.println("ERROR evolutionID");
      throw new Error();
    }
    throw new Error();
  }

  private static String idToName(int index){
    if (index < 0 || index > 151){
      throw new Error();
    }
    try{
      File f = new File("Pokemon.csv");
      Scanner in = new Scanner(f);

      while (in.hasNext()){
        String line = in.nextLine();
        String[] stats = line.split(",");

        if (String.valueOf(index).equals(stats[0])){
          return stats[1];
        }
      }
    }
    catch(FileNotFoundException e){
      System.out.println("Error in idToName");
      throw new Error();
    }
    throw new Error();
  }

  public static String nameToID(String names){
    try{
      File f = new File("Pokemon.csv");
      Scanner in = new Scanner(f);

      while (in.hasNext()){
        String line = in.nextLine();
        String[] stats = line.split(",");

        if (names.equals(stats[1])){
          return stats[0];
        }
      }
    }
    catch(FileNotFoundException e){
      System.out.println("Error in idToName");
      throw new Error();
    }
    throw new Error();
  }

  public String evolve(int index){
    return idToName(evolutionID(index));
  }

  public ArrayList<String> possibleAttacks(String name1){
    possibleAttacks = new ArrayList<String>(50);
    try{
      File f = new File("movesets.csv");
      Scanner in = new Scanner(f);

      while (in.hasNext()){
        String line = in.nextLine();
        String[] stats = line.split(",");

        if (stats[0].equals(name1)){
          Move temp = new Move(name1);

          possibleAttacks.add(stats[1]);
          possibleAttacks.add(stats[2]);
        }
      }
    }
    catch(FileNotFoundException e){
      System.out.println("Error in possibleAttacks");
      throw new Error();
    }

    return possibleAttacks;
}

    public void evolvedMoves(String name){
      String newName = evolve(Integer.parseInt(nameToID(name)));
      System.out.println(newName);

      if (possibleAttacks(newName).isEmpty()){
        while (possibleAttacks(newName).isEmpty()) {
          String newerName = evolve(Integer.parseInt(nameToID(newName)));
          possibleAttacks(newerName);
          setAttacks(newerName);
          break;
        }
      }
      else{
        setAttacks(newName);
      }
    }

  public void setAttacks(String name1){
    ArrayList<String> temp = new ArrayList<String>();
    possibleAttacks(name1);
    attacks = new ArrayList<String>(4);
    for (int x = 0; x < possibleAttacks.size() && attacks.size() < 4; x++){
      attacks.add(possibleAttacks.get(x));
    }
    for (String element: attacks){
      if (!temp.contains(element)){
        temp.add(element);
      }
    }
  }

  public static String arrayToString(ArrayList<Move> input){
    String output = "";
    for (int x = 0; x < input.size(); x++){
      output += input.get(x);
      if (x+1 != input.size()) output += ", ";
    }
    return output;
  }

  public void setAttacks(String name1, ArrayList<String> selectedAttacks){
    possibleAttacks(name1);
    attacks = new ArrayList<String>();
    for (int x = 0; x < selectedAttacks.size(); x++){
      if (possibleAttacks.contains(selectedAttacks.get(x))){
        attacks.add(selectedAttacks.get(x));
      }
      else{
        System.out.println("Error in setAttacks");
        throw new Error();
      }
    }
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

  public int getID(){
    return ID;
  }

  public String getType1(){
    return type1;
  }

  public String getEnemyAttack() {
    return enemyattack;
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

  public boolean getEvolve(){
    return evolve;
  }

  public int getDefense(){
    return defense;
  }

  public ArrayList<String> getAttacks(){
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

  public ArrayList<String> getPossibleAttacks(){
    return possibleAttacks;
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
    if (num == 1) return .5;
    if (num == 2) return .25;
  }
  return 1;
}

  public double attack(Pokemon enemy){
    Random rand = new Random();
    int x = rand.nextInt((3-0)+1);

    if (enemy.isDead()){
      throw new Error();
    }

    if (hp <= 0){
      throw new NumberFormatException();
    }

    System.out.println(attacks);
    enemyattack = attacks.get(x);

    return attack(enemy, attacks.get(x));
  }

  public double getMod(String move1, Pokemon enemy){
    return modifier(new Move(move1), enemy);
  }

  public double attack(Pokemon enemy, String move1){

      Move move = new Move(move1);
      double mod = modifier(move, enemy);

      if (!attacks.contains(move1)){
        throw new NumberFormatException();
      }

      if (enemy.isDead()){
        throw new Error();
      }

      if (hp <= 0){
        throw new NumberFormatException();
      }


      // Formula found online - it's the actual formula used to calculate damage

      double dmg = ((2.0/5 * move.getPower()) *
             (attack / enemy.getDefense()+2)
             / 50 * mod);

      System.out.println(move.getPower() + " power");
      System.out.println(enemy.getDefense() + " defense");
      System.out.println(mod + " mod");

      if (enemy.getHP() - dmg > 0) enemy.setHP(enemy.getHP() - dmg);
      else enemy.setHP(0);

      System.out.println("This is the damage: " + dmg);
      return dmg;
  }
}
