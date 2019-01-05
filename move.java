import java.util.Scanner;
import java.io.*; //file, filenotfoundexception

public class move{
  private String name, type;
  private int power;

  public move(String moveName){
    try{
      File f = new File("moves.csv.txt");
      Scanner in = new Scanner(f);

      while (in.hasNext()){
        String line = in.nextLine();
      }
    }
    catch(FileNotFoundException e){
      System.out.println("Error");
      System.exit(1);
    }
  }
}
