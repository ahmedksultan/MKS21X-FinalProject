import java.util.*; //scanner, ArrayList, Map

public class Trainer extends Player{
  private ArrayList<Pokemon> party;
  private boolean catchable;

  public Trainer(String name, ArrayList<Pokemon> party1){
    super(name, party1);
    catchable = false;
  }

  public boolean canCatch(){
    return catchable;
  }
}
