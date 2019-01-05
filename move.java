import java.util.Scanner;
import java.io.*; //file, filenotfoundexception

public class move{

  public static void main(String[] args) {
    move test = new move("pound");
    System.out.println(test);
  }

  private String name, type;
  private int power, typeID;

  public move(String moveName){
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

  // public String getPower
}
