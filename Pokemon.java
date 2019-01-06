import java.util.*; //scanner, ArrayList, Map
import java.io.*; //file, filenotfoundexception

public class Pokemon{

  public static void main(String[] args) {
    Pokemon bulb = new Pokemon("Bulbasaur");
    Pokemon ivy = new Pokemon("Ivysaur");


    System.out.println("Testing Bulbasaur properties");
    System.out.println(bulb.getHP());
    System.out.println(bulb.getAttack());
    System.out.println(bulb.getDefense());
    System.out.println(bulb.getSpeed());

    System.out.println();
    System.out.println(ivy.getHP());
    bulb.attack(ivy, "razor-leaf");
    System.out.println(ivy.getHP());


  }

  private String name, type1, type2;
  private int attack, speed, defense;
  private double hp;
  private ArrayList<Move> attacks;
  private ArrayList<String> typeWeakness, typeResistance;

  public Pokemon(String name1){
    name = name1;

    setWeaknesses();
    setResistances();
    String[] data = organizeData(name1);

    type1 = data[2];
    type2 = data[3];
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

  private void setWeaknesses(){
    typeWeakness = new ArrayList<String>(10);

    // if (type1.equals("fire") || type2.equals("fire")){    // DUMMY VALUES - REPLACE WITH ACTUAL VALUES
      typeWeakness.add("water");
    // }
  }

  private void setResistances(){
    typeResistance = new ArrayList<String>(10);
    // if (type1.equals("fire") || type2.equals("fire")){  // DUMMY VALUES - REPLACE WITH ACTUAL VALUES
      typeResistance.add("grass");
    // }
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

  public void attack(Pokemon enemy, String move1){
    Move move = new Move(move1);
    double mod = modifier(move, enemy);

    double dmg = ((42 * move.getPower()) *
           (attack / enemy.getDefense()+2) // Formula found online - it's the actual formula used to calculate damage )
           / 50 * mod);

    System.out.println("Attack was " + mod + "x effective. " + enemy + "took " + dmg + "damage!");

    enemy.setHP(enemy.getHP() - dmg);
  }
}
