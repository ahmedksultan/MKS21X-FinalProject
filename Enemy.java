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
    // System.out.println("1here");
    party = party1;
    // System.out.println("2here");

    // // System.out.println(party1.size() + "really?");
    // for (int i = 0; i < party1.size(); i++){
    //   // System.out.println("1here1");
    //   party.add(party1.get(i));
    // }
    // System.out.println("3here");
    poke = party.get(0);
    // System.out.println("4here");
    name = name1;
  }

  public String getName(){
    return name;
  }

  public ArrayList<Pokemon> getParty(){
    return party;
  }

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

  public Pokemon getMon(int index){
    if (party.size() > 0){
      return party.get(0);
    }
    else{
      System.out.println("HEEERE");
      throw new IndexOutOfBoundsException();
    }
  }

  public boolean outofMons(){
    return allDead();
  }

}
