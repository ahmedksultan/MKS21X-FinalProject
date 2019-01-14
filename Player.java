import java.util.*; //scanner, ArrayList, Map


public abstract class Player{

  public abstract ArrayList<Pokemon> getParty();

  public abstract String getName();

  public abstract int pokemonLeft();

  public abstract Pokemon getMon(int index);

  public abstract boolean canCatch();

  public abstract boolean allDead();
}
