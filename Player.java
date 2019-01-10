import java.util.*; //scanner, ArrayList, Map


public abstract class Player{
  private ArrayList<Pokemon> party;
  private String name;

  public Player(String name, ArrayList<Pokemon> party1){
    if (party1.size() > 6 || party1.size() < 1){
      throw new Error();
    }
    party = new ArrayList<Pokemon>(6);
    for (int x = 0; x < party.size(); x++){
      party.set(x, party1.get(x));
    }

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

  public boolean outofMons(){
    return pokemonLeft() > 0;
  }

  public Pokemon getMon(int index){
    return party.get(index);
  }
}
