import java.util.*; //scanner, ArrayList, Map
import java.io.*; //file, filenotfoundexception

public class Pokemon{
  private String[][] data;
  private String name, type1, type2;
  private int attack, speed, defense, hp;
  private ArrayList<String> attacks, typeWeakness, typeResistance;

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
  //Accessor Methods
  public String getType1(){
    return type1;
  }

  public String getType2(){
    return type2;
  }

  public int getHP(){
    return hp;
  }

  public int getAttack(){
    return attack;
  }

  public int getDefense(){
    return defense;
  }

  private int modifier(String move){
    int x = 0;
    for (int i = 0; i < typeWeakness; i++){
      if (typeWeakness.get(i) == move.getType()){
        x++;
      }
    }
    for (int i = 0; i < typeResistance; i++){
      if (typeResistance.get(i) == move.getType()){
        x--;
      }
    }
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

  public int dealDamage(String attackname, String move, String enemy){
    double mod = modifier(move);
    if (mod == -2) mod = .25;
    if (mod == -1) mod = .5;
    if (mod == 0) mod = 1;
    if (mod == 1) mod = 2;
    if (mod == 2) mod = 4;

    return (((42 * data.getPower(move) *
           (attack / enemy.getDefense())+2)
           / 50 * mod);
  }
}
