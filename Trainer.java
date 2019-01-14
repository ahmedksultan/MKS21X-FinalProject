import java.util.*; //scanner, ArrayList, Map

public class Trainer extends Player{
  private ArrayList<Pokemon> party;
  private boolean catchable;
  private int len;
  private String name;

  public Trainer(String name1, ArrayList<Pokemon> party1){

    if (party1.size() > 6 || party1.size() < 1){
      System.out.println("LENGTH ERROR CHECK TRAINER CONSTRUCTOR");
      throw new Error();
    }

    party = new ArrayList<Pokemon>(party1.size());

    party = party1;
    name = name1;
    len = party.size();
    catchable = false;
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
    for (int i = 0; i < party.size(); i++){
      if (!party.get(i).isDead()){
        return false;
      }
    }
    return false;
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

  public Pokemon getMon(int index){
    return party.get(index);
  }

  public boolean outofMons(){
    return pokemonLeft() > 0;
  }
}
