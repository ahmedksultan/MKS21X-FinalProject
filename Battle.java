public class Battle{
  private int start, turnCount;
  private boolean turn; // True for player 1's turn, false for player two's turn
  private Player one;
  private Player two;

  public Battle(Player one1, Player two2){
    one = one1;
    two = two2;
  }

  public void updateTurn(){
    turn = !turn; // Simplest way to flip the value of a boolean
    turnCount++;
  }

  public boolean getTurn(){
    return turn;
  }

  public boolean whosFirst(int x, int y){ // x and y represent pokemon in Player 1 and Player 2's parties, respecitvely.
    turn = one.getMon(x).getSpeed() > two.getMon(x).getSpeed();
  }

}
