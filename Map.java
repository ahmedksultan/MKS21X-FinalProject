public class Map{
  //routing: pkmnlab > hometown > route1a-1e > city > gym
  String[][] test;
  String[][] menu;
  String[][] hometown;
  String[][] pkmnlab;
  String[][] route1a;

  private static void makeBorders(String[][] x) {
    //uses techniques created in testmapbbb
    for (int i = 0; i < x.length; i++) {
      for (int j = 0; j < x[i].length; j++) {
        if (i == 0 || i == x.length - 1) {
          x[i][j] = "-";
        }
        else if (j == 0 || j == x[i].length - 1) {
          x[i][j] = "|";
        }
        else {
          x[i][j] = ".";
        }
      }
    }
  }

  private void initTest() {

  }

  private void initHome(){
    // Initialize homeTown
  }

  public void getHome(){
    // print out String[][] hometown;
  }

  private void initPkmnLab(){
    // Initialize pkmnlab;
  }

  public void getPkmnLab() {

  }

  private void initRoute1a(){
    // Initialize route1a
  }

}
