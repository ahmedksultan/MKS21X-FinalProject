import java.util.*; //scanner, ArrayList, Map
import java.io.*; //file, filenotfoundexception

public class Pokemon{

  public static void main(String[] args) {
    Pokemon test = new Pokemon("Charmander");
    System.out.println(test.getDefense());
    Pokemon test1 = new Pokemon("Bulbasaur");
    Move water = new Move("water-gun");
    test1.dealDamage(water, test);
    System.out.println(test.getHP());
  }

  private String[][] data;
  private String name, type1, type2;
  private int attack, speed, defense;
  private double hp;
  private ArrayList<Move> attacks;
  private ArrayList<String> typeWeakness, typeResistance;

  public Pokemon(String name1){
    name = name1;

    for (int x = 0; data[x][2] != name; x++){
      if (data[x][2] == name){
        type1 = data[x][2];
        type2 = data[x][3];
        hp = Integer.parseInt(data[x][5]);
        attack = Integer.parseInt(data[x][6]);
        defense = Integer.parseInt(data[x][7]);
        speed = Integer.parseInt(data[x][11]);
      }
    }
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
  /////////////////////////////////

  private void setHP(double num){
    hp -= num;
  }

  private int modifier(Move move, Pokemon enemy){
    int x = 0;
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
    return x;
  }

  public void dealDamage(Move move, Pokemon enemy){
    double mod = modifier(move, enemy);
    if (mod == -2) mod = .25;
    if (mod == -1) mod = .5;
    if (mod == 0) mod = 1;
    if (mod == 1) mod = 2;
    if (mod == 2) mod = 4;

    enemy.setHP(enemy.getHP() - ((42 * move.getPower()) *
           (attack / enemy.getDefense()+2) // Formula found online -
           / 50 * mod)); // it's the actual formula used to calculate damage )
  }
}
