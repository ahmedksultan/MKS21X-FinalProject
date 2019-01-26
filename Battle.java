import java.util.*; //scanner, ArrayList, Map, Random
import java.io.*; //file, filenotfoundexception

public class Battle{
  public static void main(String[] args) {
    ArrayList<Pokemon> johnparty = new ArrayList<Pokemon>();
    ArrayList<Pokemon> pparty = new ArrayList<Pokemon>();

    //Pokemon Mewtwo = new Pokemon("Mewtwo");
    Pokemon Bulbasaur = new Pokemon("Bulbasaur");
    Pokemon Charmander = new Pokemon("Charmander");
    Pokemon Squirtle = new Pokemon("Squirtle");
    Pokemon Mewtwo = new Pokemon("Mewtwo");
    Pokemon Pikachu = new Pokemon("Pikachu");
    Pokemon Ditto = new Pokemon("Ditto");
    pparty.add(Bulbasaur);
    pparty.add(Charmander);
    pparty.add(Squirtle);
    pparty.add(Mewtwo);
    pparty.add(Pikachu);
    pparty.add(Ditto);

    Trainer player = new Trainer("Player", pparty);

    Pokemon eeveejohn = new Pokemon("Eevee");
    Pokemon oddishjohn = new Pokemon("Oddish");

    String yourattack;
    String enemyattack;
    Scanner user_input = new Scanner(System.in);


    //johnparty.add(Mewtwo);
    johnparty.add(eeveejohn);
    johnparty.add(oddishjohn);

    Player John = new Trainer("JOHN", johnparty);
    Battle johnbattle = new Battle(player, John);

    while (!johnbattle.isOver()) {

      System.out.println(Sprites.toString(Sprites.getArray(johnbattle.getActive1().getName())));

      System.out.println(johnbattle.getActive1().getName().toUpperCase() + " and " + johnbattle.getActive2().getName().toUpperCase() + " are battling!");
      System.out.println("\n" + johnbattle.getActive1() + "'s HP: " + johnbattle.getActive1().getHP() + "/" + johnbattle.getActive1().getTotalHP());
      System.out.println(johnbattle.getActive2() + "'s HP: " + johnbattle.getActive2().getHP() + "/" + johnbattle.getActive2().getTotalHP());
      System.out.println("Choose your move!\n");
      for (int i = 0; i < johnbattle.getActive1().getAttacks().size(); i++) {
        System.out.println("[" + (i + 1) + "] for " + johnbattle.getActive1().getAttacks().get(i).toUpperCase());
      }
      System.out.println("[H] to use a Potion.");
      System.out.println("");

      String userinput = user_input.next();

        yourattack = johnbattle.getActive1().getAttacks().get(Integer.parseInt(userinput) - 1);
        johnbattle.getActive1().attack(johnbattle.getActive2(), yourattack);
        if (johnbattle.getActive2().getHP() <= 0) {
          System.out.println("\nYou used " + yourattack.toUpperCase() + "!");
        }
        else {
          johnbattle.getActive2().attack(johnbattle.getActive1());
          enemyattack = johnbattle.getActive2().getEnemyAttack();
          System.out.println("\nYou used " + yourattack.toUpperCase() + "! Your opponent used " + enemyattack.toUpperCase() + "." );
        }

      /*
      for (int i = 0; i < moves1.size(); i++) {
        if (moves1.get(i).equals(user_input.next())) {
          yourattack = moves1.get(i);
        }
        else {
          yourattack = "IMPOSSIBLE MOVE";
        }
      }
      */

      johnbattle.forceSwitch();
    }
  }


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

    try{
      if (one.allDead()){
        System.out.println("Your Pokemon, " + active1.getName().toUpperCase() + " has fainted!");
        over = true;
        winner = two.getName();
      }
      else if(two.allDead()){
        System.out.println("Enemy Pokemon, " + active2.getName().toUpperCase() + " has fainted!");
        over = true;
        winner = one.getName();
      }
      if (active1.isDead() && over != true){
        System.out.println("Your Pokemon, " + active1.getName().toUpperCase() + " has fainted! Choose your next pokemon!");
        chooseSwitch(user_input.nextInt());
      }
      if (active2.isDead() && over != true){
        System.out.println("Enemy Pokemon, " + active2.getName().toUpperCase() + " has fainted!");
        autoSwitch();
      }
    }
    catch(IndexOutOfBoundsException e){
      String output = "";
      for (int x = 0; x < one.getParty().size(); x++){
        output += one.getParty().get(x);
        if (x+1 != one.getParty().size()){
          output += ", ";
        }
      }
      System.out.println("That Pokemon index doesn't exist");
      System.out.println("Your party is: " + output);
      forceSwitch();
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
