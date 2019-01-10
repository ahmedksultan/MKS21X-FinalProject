import java.util.*; //scanner, ArrayList, Map
<<<<<<< HEAD

=======
import java.io.*; //file, filenotfoundexception
>>>>>>> Trainer

public class Trainer extends Player{
  private ArrayList<Pokemon> party;
  private boolean catchable;
<<<<<<< HEAD

  public Trainer(String name, ArrayList<Pokemon> party1){
    super(name, party1);
=======
  private String name;

  public Trainer(String name1, ArrayList<Pokemon> party1){
    super(name1, party1);
>>>>>>> Trainer
    catchable = false;
  }

}
