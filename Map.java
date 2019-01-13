public class Map{
  //routing: pkmnlab > hometown > route1a-1e > city > gym
  private static String[][] test;
  private static String[][] menu;
  private static String[][] hometown;
  private static String[][] pkmnlab;
  private static String[][] route1a;

  public static void main(String[] args) {
    initTest();
  }

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

  private static void initTest() {
    test = new String[20][20];
    makeBorders(test);
    System.out.println(toString(test));
  }

  private static void initHome(){
    // Initialize homeTown
  }

  public static void getHome(){
    // print out String[][] hometown;
  }

  private static void initPkmnLab(){
    // Initialize pkmnlab;
  }

  public static void getPkmnLab() {

  }

  private static void initRoute1a(){
    // Initialize route1a
  }

  public static String toString(String[][] x) {
    String result = "";

    for (int i = 0; i < x.length; i++) {
      for (int j = 0; j < x[i].length; j++) {
        result = result + " " + x[i][j];
      }
      result = result + "\n";
    }

    return result;
  }

}
