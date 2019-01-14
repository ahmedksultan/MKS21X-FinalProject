import java.util.*; //array lists

public class Map{
  //routing: pkmnlab > hometown > route1a-1e > city > gym
  private static String[][] test;
  private static String[][] menu;
  private static String[][] town1;
  private static String[][] pkmnlab;
  private static String[][] route1a;

  public static void main(String[] args) {
    initTown1();
    getTown1();
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

  public static void initTown1() {
    //(((note: coords are in y-x format)))
    //renamed test to town1
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
      test[9][x] = "b";
    }

    for (int y = 10; y < 13; y++) {
      //creating rest of house
      test[y][2] = "b";
      test[y][3] = "b";
      test[y][4] = "b";
      test[y][7] = "b";
      test[y][8] = "b";
      test[y][9] = "b";

      //creating door
      test[y][5] = "d";
      test[y][6] = "d";
    }

    for (int y = 2; y < 8; y++) {
      for (int x = 12; x < 20; x++) {
        test[y][x] = "r";
      }
    }

    for (int x = 12; x < 20; x++) {
      test[8][x] = "b";
      test[9][x] = "b";
    }

    for (int y = 10; y < 13; y++) {
      //creating rest of house
      test[y][12] = "b";
      test[y][13] = "b";
      test[y][14] = "b";
      test[y][17] = "b";
      test[y][18] = "b";
      test[y][19] = "b";

      //creating door
      test[y][15] = "d";
      test[y][16] = "d";
    }

    //creating grass
    for (int y = 20; y < 25; y++) {
      for (int x = 2; x < 15; x++) {
        test[y][x] = "g";
      }
    }

  }

  public static String[][] getTown1() {
    return test;
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
