import java.util.Scanner;
import java.io.*; //file, filenotfoundexception

public class Move{

  public static void main(String[] args) {
    Move test = new Move("pound");
  }

  private String name, type;
  private int power, typeID;
  private String[] types =
  {"Normal", "Fighting", "Flying", "Poison", "Ground",
   "Rock", "Bug", "Ghost", "Steel", "Fire", "Water",    //Have to convert this way
   "Grass", "Electric", "Psychic", "Ice", "Dragon", "Dark", "Fairy"};     //Because CSV file gives IDs for types, not names

  public Move(String moveName){
    try{
      File f = new File("moves.csv");
      Scanner in = new Scanner(f);

      while (in.hasNext()){
        String line = in.nextLine();
        String[] stats = line.split(",");

        if (stats[1].equals(moveName)){
          name = moveName;
          typeID = Integer.parseInt(stats[3]);
          power = Integer.parseInt(stats[4]);
          break;
        }
      }
    }
    catch(FileNotFoundException e){
      System.out.println("Error");
      System.exit(1);
    }
  }

  // Accessor Methods////////////////////
  public int getPower(){
    return power;
  }

  public String getType(){
    if (typeID < 0 || typeID > 15) return "Special";
    else {
      return types[typeID];
    }
  }

  public int getTypeID(){
    return typeID;
  }
}
