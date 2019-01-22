import java.util.*; //scanner, ArrayList, Map, Random
import java.io.*; //file, filenotfoundexception

public class Battle{
  private Player one, two;
  private Pokemon active1, active2;
  private boolean over;
  private String winner;

  //PLAYER COMES FIRST, ENEMY COMES SECOND

  public Battle(Trainer one1, Player two2){
    one = one1;
    two = two2;

    active1 = one.getMon(0);
    active2 = two.getMon(0);
    over = false;
  }

//Accessor methods

  public Pokemon getActive1(){
    return active1;
  }

  public Pokemon getActive2(){
    return active2;
  }

  public Player getOne(){
    return one;
  }

  public Player getTwo(){
    return two;
  }

  public String getWinner(){
    return winner.toString();
  }

//////////////////////////////////////////


  // chooseSwitch allows the player to switch to another pokemon, autoSwitch
  // makes the enemy switch out when their pokemon has fainted. forceSwitch
  // encompasses these, as it forces an autoSwitch if needed, or make the player
  // switch if necessary.

  public void chooseSwitch(int index){
    try{
      if (one.getMon(index).isDead()){
      throw new Error();
      }
    }
    catch(Error e){
      System.out.println("That Pokemon has fainted! Choose another!");
      forceSwitch();
    }
    active1 = one.getMon(index);
  }

  public void autoSwitch(){
    if (two.allDead()){
      over = true;
      winner = one.getName();
      System.out.println(two.getName() + " has switched to " + active2);
    }
    else{
      for (int x = 0; x < two.getParty().size(); x++){
        if (!two.getParty().get(x).isDead()){
          active2 = two.getParty().get(x);
        }
      }
    }
  }

  public void forceSwitch(){
    Scanner user_input = new Scanner( System.in );
    String firstname;

    if (one.allDead()){
      System.out.println("Your pokemon, " + active1 + " has fainted!");
      over = true;
      winner = two.getName();
    }
    else if(two.allDead()){
      System.out.println("Enemy pokemon, " + active2 + " has fainted!");
      over = true;
      winner = one.getName();
    }

    if (active1.isDead() && over != true){
      System.out.println("Your pokemon," + active1 + " has fainted! Choose your next pokemon!");
      chooseSwitch(user_input.nextInt());
    }
    if (active2.isDead() && over != true){
      System.out.println("Enemy pokemon, " + active2 + " has fainted!");
      autoSwitch();
    }
  }


  public void move(){
    if (two.canCatch()){
      try{
        if (active1.getSpeed() > active2.getSpeed()){
          if (!canCatch()){
            active2.attack(active1);
          }
        }
        else{
          active2.attack(active1);
          canCatch();
        }
      }
      catch(Error e){
        autoSwitch();
      }
      catch(NumberFormatException e){
        forceSwitch();
      }
    }
  }

  // move is the method that makes players choose their next move, and checks if
  // the game is over. Also automatically chooses the move for the NPCs.

  public void move(String a){
    try{
      if (active1.getSpeed() > active2.getSpeed()){
        active1.attack(active2, a);
        active2.attack(active1);
      }
      else{
        active2.attack(active1);
        active1.attack(active2, a);
      }
    }
    catch(Error e){
      autoSwitch();
    }
    catch(NumberFormatException e){
      forceSwitch();
    }
  }

  public boolean isOver(){
    return over;
  }

  // This method allows the player to attempt to catch an enemy pokemon through
  // a percent chance that can be easily adjusted.

  public boolean canCatch(){
    Random rand = new Random();
    if (two.canCatch()){
      if (rand.nextInt(100) >= 70){
        if (one.getParty().size() < 6){
          System.out.println("Congratulations! You have caught " + active2.getName() + "!");
          one.getParty().add(active2);
          over = true;
          return true;
        }
      }
      else{
        System.out.println("Aw! You have not caught it! Try again!");
        return false;
      }
    }
    return false;
  }

}
