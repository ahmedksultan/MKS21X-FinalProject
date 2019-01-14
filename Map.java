import java.util.*; //array lists

public class Map{
  //routing: pkmnlab > hometown > route1a-1e > city > gym
  private static String[][] test;
  private static String[][] menu;
  private static String[][] town1;
  private static String[][] pkmnlab;
  private static String[][] route1a;

  public static void main(String[] args) {
    initTest();
    //initTown1();
    getTest();
    //getTown1();
  }

  private static void makeBorders(String[][] x) {
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

  public static void initTest() {
    //(((note: coords are in y-x format)))
    test = new String[40][40];
    makeBorders(test);

    //taking structure from town1 map to test in Lanterna
    for (int y = 2; y < 8; y++) {
      for (int x = 2; x < 10; x++) {
        test[y][x] = "r";
      }
    }

    for (int x = 2; x < 10; x++) {
      test[8][x] = "b";
    }

    for (int y = 8; y < 12; y++) {
      //creating rest of house
      test[y][2] = "b";
      test[y][5] = "b";

      //creating door
      test[y][3] = "d";
      test[y][4] = "d";
    }

    System.out.println(toString(test));

  }

  public static String[][] getTest() {
    return test;
  }

  public static void initTown1(){
    //note: coords are in y-x format

    //initializing town1 map
    town1 = new String[20][40];
    makeBorders(town1);

    //placing stuff
    for (int y = 2; y < 5; y++) {
      for (int x = 2; x < 6; x++) {
        town1[y][x] = "r";
      }
    }

    for (int x = 2; x < 6; x++) {
      town1[5][x] = "b";
    }

    for (int y = 6; y < 8; y++) {
      //creating rest of house
      town1[y][2] = "b";
      town1[y][5] = "b";

      //creating door
      town1[y][3] = "d";
      town1[y][4] = "d";
    }

    System.out.println(toString(town1));

  }

  public static String[][] getTown1(){
    System.out.println(town1);
    return town1;
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
    String result = "\n";

    for (int i = 0; i < x.length; i++) {
      for (int j = 0; j < x[i].length; j++) {
        result = result + " " + x[i][j];
      }
      result = result + "\n";
    }

    return result;
  }

}
