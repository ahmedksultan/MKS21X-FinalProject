import java.util.*; //scanner, ArrayList, Map, Random
import java.io.*; //file, filenotfoundexception

public class Battle{
  public static void main(String[] args) {

    ArrayList<Pokemon> team = new ArrayList<Pokemon>();

    Pokemon bulb = new Pokemon("Bulbasaur");
    Pokemon chari = new Pokemon("Charizard");
    Pokemon mew2 = new Pokemon("Mewtwo");
    Pokemon mew = new Pokemon("Mew");

    team.add(bulb);
    team.add(chari);
    team.add(mew2);

    ArrayList<Pokemon> team1 = new ArrayList<Pokemon>();
    team1.add(mew);

    Player one = new Trainer("Al", team);
    Player enemy = new Enemy("Jo", team1);
    Battle battle = new Battle(one, enemy);

    while (!battle.isOver()){
      Console console = System.console();
      String input = console.readLine("Choose your next move!");

      battle.move(input, "fire-punch");

    }
  }

  private Player one, two, winner;
  private Pokemon active1, active2;
  private boolean over;

  public Battle(Player one1, Player two2){
    one = one1;
    two = two2;
    active1 = one.getMon(0);
    active2 = two.getMon(0);
    over = false;
  }

  // public boolean run(){
  //   while (!run){
  //
  //   }
  // }

  public boolean escape(){
    if (one.allDead()){
      over = true;
      winner = two;
    }
    if (two.allDead()){
      over = true;
      winner = one;
    }
  }

  public void chooseSwitch(int x, int index){
    if (x == 1){
      active1 = one.getMon(index);
    }
    else{
      active2 = two.getMon(index);
    }
  }

  public void ifDead(){
    Console console = System.console();

    if (active1.getHP() <= 0){
      String input = console.readLine("Your pokemon has fainted! Choose a number from 1-6 corresponding to the next Pokemon you wish to use");
      active1 = one.getMon(Integer.parseInt(input));
    }

    if (active2.getHP() <= 0){
      int len = two.getParty().size();
      for (int x = 0; x < len; x++){     // COULD EDIT THIS SO THAT IT'S RANDOM
        if (!(two.getMon(x).isDead())){
          active2 = two.getMon(x);
        }
      }
    }
  }

  public void move(String a, String b){
    if (!active1.isDead() && !active2.isDead()){
      if (active1.getSpeed() > active2.getSpeed()){
        active1.attack(active2, a);
      }
      else{
        active2.attack(active1, b);
      }
    }
  }

  public boolean isOver(){
    return over;
  }

  // public void

  public boolean canCatch(){
    Random rand = new Random();
    if (two.canCatch()){
      if (rand.nextInt(100) >= 70){
        if (one.getParty().size() < 6){
          System.out.println("Congratulations! You have caught " + active2.getName() + "!");
          one.getParty().add(active2);
          over = true;
        }
      }
      else{
        System.out.println("Aw! You have not caught it! Try again!");
      }
    }
  }

}
