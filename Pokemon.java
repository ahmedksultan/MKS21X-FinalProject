import java.util.*; //scanner, ArrayList, Map
import java.io.*; //file, filenotfoundexception

public class Pokemon{

  public static void main(String[] args) {
    Pokemon bulb = new Pokemon("Bulbasaur");
    Pokemon ivy = new Pokemon("Ivysaur");
    Pokemon zekrom = new Pokemon("zekrom");


    System.out.println("Testing Bulbasaur properties");
    System.out.println(bulb.getHP());
    System.out.println(bulb.getAttack());
    System.out.println(bulb.getDefense());
    System.out.println(bulb.getSpeed());

    Move razor = new Move("razor-leaf");

    bulb.attack(ivy, razor);
    System.out.println(bulb.getHP());
    bulb.attack(ivy, razor);
    System.out.println(bulb.getHP());

    bulb.attack(zekrom, razor);
    System.out.println(zekrom.getHP());
    System.out.println();

    bulb.attack(zekrom, razor);
    System.out.println(zekrom.getHP());
    System.out.println();
  }

  private String name, type1, type2;
  private int attack, speed, defense, typeID1, typeID2;
  private double hp;
  private ArrayList<Move> attacks;
  private ArrayList<String> typeWeakness, typeResistance;
  private String[] types =
  {"normal", "fighting", "flying", "poison", "ground",
   "rock", "bug", "ghost", "steel", "fire", "water",    //Have to convert this way
   "grass", "electric", "psychic", "ice", "dragon", "dark", "fairy"};

  public Pokemon(String name1){
    name = name1;

    setWeakandRes();
    String[] data = organizeData(name1);

    type1 = data[2];
    type2 = data[3];

    for (int x = 0; x < types.length; x++){
      if (types[x] == type1) typeID1 = x+1;
      if (types[x] == type2) typeID2 = x+1;
    }

    hp = Integer.parseInt(data[5]);
    attack = Integer.parseInt(data[6]);
    defense = Integer.parseInt(data[7]);
    speed = Integer.parseInt(data[10]);
  }

  private String[] organizeData(String name1){
    try{

      File f = new File("Pokemon.csv");
      Scanner in = new Scanner(f);

      while (in.hasNext()){
        String line = in.nextLine();
        String[] stats = line.split(",");

        // for (int x = 0; x < stats.length; x++){
          // System.out.println(stats[1]);
        // }

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
  /////////////////////////////////

  // Mutator Methods

  private void setHP(double num){
    hp -= num;
  }

  private void checkFile(int ID){

  }

  private void setWeakandRes(){
    typeWeakness = new ArrayList<String>(10);
    typeResistance = new ArrayList<String>(10);
    try{
      File f = new File("type_efficacy.csv.txt");
      Scanner in = new Scanner(f);

      while (in.hasNext()){
        String line = in.nextLine();
        String[] stats = line.split(",");

        if (typeID1 == Integer.parseInt(stats[1])) {
          if (Integer.parseInt(stats[2]) == 200){
            typeWeakness.add(types[Integer.parseInt(stats[1]) - 1]);
          }
          else if (Integer.parseInt(stats[2]) == 50) {
            typeResistance.add(types[Integer.parseInt(stats[1]) - 1]);
          }                                                                 // CAN BE CUT DOWN SIGNIFICANTLY BY USING HELPER FXN

          if (typeID2 == Integer.parseInt(stats[1])) {
            if (Integer.parseInt(stats[2]) == 200){
              typeWeakness.add(types[Integer.parseInt(stats[1]) - 1]);
            }
            else if (Integer.parseInt(stats[2]) == 50) {
              typeResistance.add(types[Integer.parseInt(stats[1]) - 1]);
            }
          }
        }
      }
    }
      catch(FileNotFoundException e){
        System.out.println("error");
      }

      for (int x = 0; x < typeWeakness.size() && x < typeResistance.size(); x++){
        if (typeResistance.contains(typeWeakness.get(x))){
          typeResistance.remove(typeWeakness.get(x));
          typeWeakness.remove(x);
        }
      }

    }

  ///////////////////////////////

  private double modifier(Move move, Pokemon enemy){
    double x = 0;
    for (int i = 0; i < enemy.getTypeWeakness().size(); i++){
      if (enemy.getTypeWeakness().get(i) == move.getType()){
        x++;
      }
    }
    for (int i = 0; i < enemy.getTypeResistance().size(); i++){
      if (enemy.getTypeResistance().get(i) == move.getType()){
        x--;
      }
    }

    if (x == -2) x = .25;
    if (x == -1) x = .5;
    if (x == 0) x = 1;
    if (x == 1) x = 2;
    if (x == 2) x = 4;

    return x;
  }

  public void attack(Pokemon enemy, Move move){
    double mod = modifier(move, enemy);

    double dmg = ((42 * move.getPower()) *
           (attack / enemy.getDefense()+2) // Formula found online - it's the actual formula used to calculate damage )
           / 50 * mod);

    System.out.println("Attack was " + mod + "x effective. " + enemy.getName() + " took " + dmg + " damage!");

    enemy.setHP(enemy.getHP() - dmg);
  }
}
