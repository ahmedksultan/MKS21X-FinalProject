import java.util.*; //scanner, ArrayList, Map, Random
import java.io.*; //file, filenotfoundexception

public class Battle{
  public static void main(String[] args) {

    ArrayList<Pokemon> team = new ArrayList<Pokemon>(3);
    ArrayList<Pokemon> team1 = new ArrayList<Pokemon>(1);

    Pokemon Bulb = new Pokemon("Bulbasaur");
    Pokemon Chari = new Pokemon("Charizard");
    Pokemon Mew2 = new Pokemon("Mewtwo");
    Pokemon Mew = new Pokemon("Mew");
    Pokemon Venusaur = new Pokemon("Venusaur");
    Pokemon Rattata = new Pokemon("Rattata");
    Pokemon Tentacruel = new Pokemon("Tentacruel");
    Pokemon Weezing = new Pokemon("Weezing");
    Pokemon Starmie = new Pokemon("Starmie");
    Pokemon Electabuzz = new Pokemon("Electabuzz");
    Pokemon Lapras = new Pokemon("Lapras");

    team.add(Bulb);
    team.add(Chari);
    team.add(Mew2);
    team.add(Venusaur);
    team.add(Rattata);
    team.add(Tentacruel);

    team1.add(Weezing);
    team1.add(Starmie);
    team1.add(Electabuzz);
    team1.add(Lapras);

    Player one = new Trainer("Al", team);
    Player enemy = new Trainer("Jo", team1);

    Battle battle = new Battle(one, enemy);


    Scanner user_input = new Scanner( System.in );
    String firstname;

    System.out.println("Your enemy is " + enemy.getName() + "! Their first pokemon is " + battle.getActive2());
    System.out.println(battle.getOne().getParty());
    System.out.println(battle.getTwo().getParty());

    while (!battle.isOver()){

      System.out.println(battle.getActive1().toString() + " and " + battle.getActive2() + " are battling!");
      System.out.println("Choose your move");
      firstname = user_input.next();
      battle.move(firstname, "absorb");
      System.out.println("You used " + firstname + "! Your opponent used absorb");
      battle.forceSwitch();
    }
    System.out.println("The Battle is over! " + battle.getWinner()  + " has won!");
  }

  private Player one, two;
  private Pokemon active1, active2;
  private boolean over;
  private String winner;

  public Battle(Player one1, Player two2){
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
    active1 = one.getMon(index);
  }

  public void autoSwitch(){
    for (int x = 0; x < two.getParty().size(); x++){
      if (!two.getParty().get(x).isDead()){
        active2 = two.getParty().get(x);
      }
    }
    System.out.println(two.getName() + " has switched to " + active2);
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

  // move is the method that makes players choose their next move, and checks if
  // the game is over.

  public void move(String a, String b){
    if (!active1.isDead() && !active2.isDead()){
      System.out.println(active1.getName() + " " + active1.getSpeed());
      System.out.println(active2.getName() + " " + active2.getSpeed());
      if (active1.getSpeed() > active2.getSpeed()){
        active1.attack(active2, a);
        if (!active2.isDead()){
          active2.attack(active1, b);
        }
        else if (active2.isDead()){
          autoSwitch();
        }
      }
      else{
        active2.attack(active1, b);
        if (!active1.isDead()){
          active1.attack(active2, a);
        }
        else if (active2.isDead()) {
          Scanner user_input = new Scanner( System.in );
          String firstname;
          chooseSwitch(user_input.nextInt());
        }
      }
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
