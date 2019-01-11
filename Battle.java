import java.util.*; //scanner, ArrayList, Map
import java.io.*; //file, filenotfoundexception

public class Battle{
  private Player one;
  private Player two;
  private Pokemon active1, active2;

  public Battle(Player one1, Player two2){
    one = one1;
    two = two2;
    active1 = one.getMon(0);
    active2 = two.getMon(0);

  }

  public void chooseSwitch(int x, int index){
    if (x == 1){
      active1 = one.getMon(index);
    }
    else{
      active2 = two.getMon(index);
    }
  }

  public ifDead(){
    Console console = System.console();
    String input = console.readLine("Enter input:");
  }

  public void move(String a, String b){
      if (active1.getSpeed() > active2.getSpeed()){
        active1.attack(active2, a);
      }
      else{
        active2.attack(active1, b);
      }

  }

}
