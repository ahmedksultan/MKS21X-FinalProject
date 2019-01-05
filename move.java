import java.util.Scanner;
import java.io.*; //file, filenotfoundexception

public class Move{

  public static void main(String[] args) {
    Move test = new Move("pound");
    System.out.println(test);
  }

  private String name, type;
  private int power, typeID;
  private String[] types =
  {"normal", "fighting", "flying", "poison", "ground",
   "rock", "bug", "ghost", "steel", "fire", "water",    //Have to convert this way
   "grass", "electric", "psychic", "ice"};     //Because CSV file gives IDs for types, not names

  public Move(String moveName){
    try{
      File f = new File("moves.csv.txt");
      Scanner in = new Scanner(f);

      while (in.hasNext()){
        String line = in.nextLine();
        String[] stats = line.split(",");
        if (stats[1] == moveName){
          name = moveName;
          typeID = Integer.parseInt(stats[3]);
          power = Integer.parseInt(stats[4]);
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
      return types[typeID - 1];
    }
  }
}
