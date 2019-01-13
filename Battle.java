import java.util.*; //scanner, ArrayList, Map, Random
import java.io.*; //file, filenotfoundexception

public class Battle{
  public static void main(String[] args) {

    ArrayList<Pokemon> team = new ArrayList<Pokemon>(3);
    // System.out.println("here1");


    Pokemon bulb = new Pokemon("Bulbasaur");
    Pokemon chari = new Pokemon("Charizard");
    Pokemon mew2 = new Pokemon("Mewtwo");
    Pokemon mew = new Pokemon("Mew");
    // System.out.println("here2");

    team.add(bulb);
    team.add(chari);
    team.add(mew2);
    // System.out.println("here3");

    ArrayList<Pokemon> team1 = new ArrayList<Pokemon>(1);
    // System.out.println("here4");
    team1.add(mew);


// System.out.println("here5");
    Player one = new Trainer("Al", team);
    // System.out.println("here6");
    Player enemy = new Enemy("Jo", team1);
    // System.out.println("here7");
    Battle battle = new Battle(one, enemy);
    // System.out.println("here8");


    Scanner user_input = new Scanner( System.in );
    String firstname;
    // System.out.println("Here");
    System.out.println("Your enemy is " + enemy.getName() + "! Their first pokemon is " + battle.getActive1());
    System.out.println(battle.getOne().getParty());
    System.out.println(battle.getTwo().getParty());

    while (!battle.isOver()){
      System.out.println(battle.getActive1() + " and " + battle.getActive2() + " are battling!");
      System.out.println("Choose next move");
      firstname = user_input.next();
      System.out.println(firstname);
      battle.move(firstname, "absorb");
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

  // public boolean run(){
  //
  //   }
  // }
  //   while (!run){

  public String getActive1(){
    return active1.toString();
  }

  public String getActive2(){
    return active2.toString();
  }

  public void escape(){
    if (one.allDead()){
      System.out.println("hereee");
      over = true;
      winner = two.getName();
    }
    if (two.allDead()){
      System.out.println("here");
      over = true;
      winner = one.getName();
    }
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

  public void chooseSwitch(int index){
    active1 = one.getMon(index);
  }

  public void autoSwitch(){
    for (int x = 0; x < two.getParty().size(); x++){
      if (!two.getParty().get(x).isDead()){
        chooseSwitch(2, x);
      }
    }
  }

  public void forceSwitch(){
    Scanner user_input = new Scanner( System.in );
    String firstname;

    if (one.allDead()){
      over = true;
      winner = two.getName();
    }
    else if(two.allDead()){
      over = true;
      winner = one.getName();
    }

    if (active1.isDead() && over != true){
      System.out.println("Your pokemon has fainted! Choose your next pokemon!");
      chooseSwitch(1, user_input.nextInt());
    }
    if (active2.isDead() && over != true){
      autoSwitch();
    }
  }

  // public void ifDead(){
  //   Console console = System.console();
  //
  //   if (active1.getHP() <= 0){
  //     String input = console.readLine("Your pokemon has fainted! Choose a number from 1-6 corresponding to the next Pokemon you wish to use");
  //     active1 = one.getMon(Integer.parseInt(input));
  //   }
  //
  //   if (active2.getHP() <= 0){
  //     int len = two.getParty().size();
  //     for (int x = 0; x < len; x++){     // COULD EDIT THIS SO THAT IT'S RANDOM
  //       if (!(two.getMon(x).isDead())){
  //         active2 = two.getMon(x);
  //       }
  //     }
  //   }
  // }

  public void move(String a, String b){
    if (!active1.isDead() && !active2.isDead()){
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
        if (!active2.isDead()){
          active1.attack(active2, b);
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

  // public void

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
