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

  public ArrayList<String> getAttacks(){
    return attacks;
  }

  public ArrayList<String> getTypeWeakness(){
    return typeWeakness;
  }

  public ArrayList<String> getTypeResistance(){
    return typeResistance;
  }
}
