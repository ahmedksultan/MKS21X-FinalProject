import java.util.*; //scanner, ArrayList, Map
import java.io.*; //file, filenotfoundexception

public class Enemy extends Player{
  private ArrayList<Pokemon> party;
  private Pokemon poke;
  private boolean catchable;
  private String name;

  public Enemy(String name1, ArrayList<Pokemon> party1){

    if (party1.size() != 1){
      System.out.println("LENGTH ERROR CHECK ENEMY CONSTRUCTOR");
      throw new Error();
    }

    party = new ArrayList<Pokemon>(party1.size());
    party = party1;

    poke = party.get(0);
    name = name1;
  }

  // Accessor methods

  public String getName(){
    return name;
  }

  public ArrayList<Pokemon> getParty(){
    return party;
  }

  // Only one Pokemon, so you don't have to go through the list.
  public Pokemon getMon(int index){
    if (party.size() > 0){
      return party.get(0);
    }
    else{
      System.out.println("Error with enemy class");
      throw new IndexOutOfBoundsException();
    }
  }

////////////////////////////////

  public boolean canCatch(){
    return catchable;
  }

  public boolean allDead(){
    return party.get(0).isDead();
  }

  public int pokemonLeft(){
    if (poke.isDead()){
      return 0;
    }
    return 1;
  }

  // Can just return allDead since there is only one Pokemon
  public boolean outofMons(){
    return allDead();
  }
}
