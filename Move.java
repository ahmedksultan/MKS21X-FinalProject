import java.util.Scanner; //scanner for .csv files
import java.io.*; //file, filenotfoundexception

public class Move{
  private String name, type;
  private int power, typeID;
  //Have to convert this way because CSV file gives IDs for types, not names, so
  //we can match them up.
  private String[] types =
  {"Normal", "Fighting", "Flying", "Poison", "Ground",
   "Rock", "Bug", "Ghost", "Steel", "Fire", "Water",
   "Grass", "Electric", "Psychic", "Ice", "Dragon", "Dark", "Fairy"};

  public Move(String moveName){
    try{
      File f = new File("moves.csv");
      Scanner in = new Scanner(f);

      while (in.hasNext()){
        String line = in.nextLine();
        String[] stats = line.split(",");

        if (stats[1].toLowerCase().equals(moveName.toLowerCase()  )){
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

  public Move(int index){
    try{
      File f = new File("moves.csv");
      Scanner in = new Scanner(f);

      while (in.hasNext()){
        String line = in.nextLine();
        String[] stats = line.split(",");

        if (stats[0].equals(index)){
          name = stats[1];
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

  //Accessor Methods//
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

  public String getName(){
    return name;
  }
  //////////////////////////
}
