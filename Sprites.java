import java.util.*; //scanner, ArrayList, Map
import java.io.*; //file, filenotfoundexception

public class Sprites{
  public static void main(String[] args) {
    ArrayList<Character> test = getArray(1);
    String output = "";
    String output1 = "";
    for (int x = 0; x < test.size(); x++){
      output += test.get(x);
    }
    System.out.println(output);

    ArrayList<Character> test1 = getArray("Charizard");

    for (int x = 0; x < test1.size(); x++){
      output1 += test1.get(x);
    }

    System.out.println();

    System.out.println(output1);

  }

  private static ArrayList<Character> data;


  public static ArrayList<Character> getArray(String name){
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
          // System.out.println(line);
            if (line.contains("break;")){
              break;
            }
            for (int i = 0; i < line.length() - 1; i++){
              data.add(line.charAt(i));
            }
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
}
