import java.util.*; //scanner, ArrayList, Map
import java.io.*; //file, filenotfoundexception

public class Sprites{
  public static void main(String[] args) {
    getArray(150);
  }

  private static ArrayList<Character> data;


  public static ArrayList<Character> getArray(int x){
    try{
      File f = new File("Sprites.txt");
      Scanner in = new Scanner(f);

      while (in.hasNext()){
        String line = in.nextLine();
        while (!line.equals("break;")){
          line = in.nextLine();
          if (line.equals(x+":")){
            for (int i = 0; x < line.length(); i++){
              data.add(line.charAt(i));
            }
          }
        }
      }
    }
    catch(FileNotFoundException e){
      System.out.println("Error at getArray");
      throw new Error();
    }
    return data;
  }
}
