import java.util.*; //scanner, ArrayList, Map
import java.io.*; //file, filenotfoundexception

public class Enemy extends Player{
  private ArrayList<Pokemon> party;
  private boolean catchable;
  private String name;

  public Enemy(String name1, ArrayList<Pokemon> party1){
    super(name1, party1);
    if (party1.size() > 1){
      throw new Error();
    }
    catchable = true;
  }

}
