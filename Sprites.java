import java.util.*; //scanner, ArrayList, Map
import java.io.*; //file, filenotfoundexception

public class Sprites{

  public String[][] read(int x){
    try{
      File f = new File("Pokemon.csv");
      Scanner in = new Scanner(f);
      while (in.hasNext()){
        String line = in.nextLine();
        int y = 0;
        if (line.equals(x+":")){
          while (!line.equals("break;")){
            y++;
          }
        }
      }
    }
    catch(FileNotFoundException e){

    }
  }

}
