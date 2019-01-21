import java.util.*; //scanner, ArrayList, Map
import java.io.*; //file, filenotfoundexception

public class Sprites{
  private static ArrayList<Character> data;
  
  public static ArrayList<Character> getArray(String name){
    // Can just convert from the name to ID and use the next function.
    int ID = Integer.parseInt(Pokemon.nameToID(name));
    return getArray(ID);
  }

  public static ArrayList<Character> getArray(int x){
    data = new ArrayList<Character>();
    try{
      File f = new File("Sprites.txt");
      Scanner in = new Scanner(f);

      while (in.hasNext()){
        String line = in.nextLine();
        if (line.equals(x+":")){
          line = in.nextLine();
        while (!line.contains("break;")){
          line = in.nextLine();
          // Once it reaches break, that signals that we've reached the end of the character sprite,
          // afterwards, it reaches the next Pokemon sprite.
            if (line.contains("break;")){
              break;
            }
            for (int i = 0; i < line.length() - 1; i++){
              data.add(line.charAt(i));
            }
            // Need to add a new line to properly format it.
            data.add('\n');
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

  public static String toString(ArrayList<Character> input){
    String output = "";
    // Have to cut it off at approximately 1200 characters because otherwise the Sprites
    // are way too large
    for (int x = 0; x < input.size() && x < 1200; x++){
      output += input.get(x);
    }
    return output;
  }
}
