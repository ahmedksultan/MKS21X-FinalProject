import java.util.*; //scanner, ArrayList, Map

public class Trainer extends Player{
  private ArrayList<Pokemon> party;
  private boolean catchable;
  private int len;
  private String name;

  public Trainer(String name1, ArrayList<Pokemon> party1){

    /*
    if (party1.size() > 6 || party1.size() < 1){
      System.out.println("LENGTH ERROR CHECK TRAINER CONSTRUCTOR");
      throw new Error();
    } */

    party = new ArrayList<Pokemon>(party1.size());

    party = party1;
    name = name1;
    len = party.size();
    catchable = false;
  }

  // Accessor methods

  public String getName(){
    return name;
  }

  public ArrayList<Pokemon> getParty(){
    return party;
  }

  public Pokemon getMon(int index){
    return party.get(index);
  }

  /////////////////////////

  // Cannot catch a trainer, so it returns false.
  public boolean canCatch(){
    return catchable;
  }

  // allDead and pokemonLeft go hand in hand - one returns a boolean, but the
  // other just returns an integer value.
  public boolean allDead(){
    int count = 0;
    for (int i = 0; i < party.size(); i++){
      if (party.get(i).isDead()){
        count++;
      }
    }
    return count == len;
  }

  public int pokemonLeft(){
    int x = 0;
    for (int i = 0; x < party.size(); i++){
      if (!(party.get(i).isDead())){
        x++;
      }
    }
    return x;
  }
}
