import java.util.*; //scanner, ArrayList, Map
import java.io.*; //file, filenotfoundexception

public class Sprites{
  private ArrayList<Character> data;


  public ArrayList<Character> getArray(int x){
    try{
      File f = new File("Sprites.txt");
      Scanner in = new Scanner(f);

      while (in.hasNext()){
        String line = in.nextLine();

        if (line.equals(x+":")){
          while (!line.equals("break;")){
            for (int i = 0; x < line.length(); i++){
              data.add(line.charAt(i));
            }
          }
        }
    }
  }
    catch(FileNotFoundException e){
      System.out.println("Error at getArray");
      throw new FileNotFoundException();
    }
    return data;
  }

  public int getNumArrays(int x){
    try{
      File f = new File("Sprites.txt");
      Scanner in = new Scanner(f);
      int y = 0;
      while (in.hasNext()){
        String line = in.nextLine();

        if (line.equals(x+":")){
          while (!line.equals("break;")){
            y++;
          }
        }
      }
      return y;
    }
    catch(FileNotFoundException e){
      System.out.println("In getNumArrays");
      throw new Error();
    }
    throw new Error();
  }

  public void getLenArrays(int x){

    try{
      File f = new File("Sprites.txt");
      Scanner in = new Scanner(f);

      int y = 0;
      while (in.hasNext()){
        String line = in.nextLine();

        if (line.equals(x+":")){
          while (!line.equals("break;")){
                    }
        }

      }
    }
    catch(FileNotFoundException e){
      throw new FileNotFoundException();
    }
  }


}
