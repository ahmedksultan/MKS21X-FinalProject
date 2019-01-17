import java.util.*; //scanner, ArrayList, Map
import java.io.*; //file, filenotfoundexception

public class Sprites{
  public static void main(String[] args) {
    ArrayList<Character> test = getArray(1);
    String output = "";
    for (int x = 0; x < test.size(); x++){
      output += test.get(x);
    }
    System.out.println(output);
  }

  private static ArrayList<Character> data;


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
          // System.out.println(line);
            System.out.println("IN");
            if (line.contains("break;")){
              break;
            }
            for (int i = 0; i < line.length(); i++){
              // System.out.println("doubleIN");
              data.add(line.charAt(i));
              // System.out.println(line.charAt(i));
            }
          }
        }
      }
    }
    catch(FileNotFoundException e){
      System.out.println("Error at getArray");
      throw new Error();
    }
    // System.out.println(data);
    return data;
  }
}
