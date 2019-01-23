import java.util.*; //array lists

public class Map {
  //routing: hometown > route1a-1e > city > gym
  private static String[][] test; //functions as town1
  private static String[][] house1; //left house in town1
  private static String[][] house2; //right house in town1
  private static String[][] route1a;
  private static String[][] route1b;
  private static String[][] route1c; //branches off to the left of route 1b!
  private static String[][] route1d;

  public static void main(String[] args) {
    initTown1();
    System.out.println(toString(getTown1()));
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

  //town1 has two houses, house1 & house2
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

    //creating a pathway
    for (int y = 14; y < 19; y++) {
      for (int x = 2; x < 18; x++ ) {
        test[y][x] = "p";
      }
    }

    for (int y = 14; y < 25; y++) {
      for (int x = 18; x < 30; x++) {
        test[y][x] = "p";
      }
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

  public static void initHouse1() {
    house1 = new String[10][20];
    makeBorders(house1);
  }

  public static String[][] getHouse1() {
    return house1;
  }

  public static void initHouse2() {
    house2 = new String[20][10];
    makeBorders(house2);
  }

  public static String[][] getHouse2() {
    return house2;
  }

  public static void initRoute1a(){
    route1a = new String[40][40];
    makeBorders(route1a);

    String[][] test = route1a;

    for (int y = 2; y < 25; y++) {
      for (int x = 18; x < 30; x++) {
        test[y][x] = "p";
      }
    }

    //creating grass
    for (int y = 10; y < 25; y++) {
      for (int x = 2; x < 16; x++) {
        test[y][x] = "g";
      }
    }

    //placing trainer
    test[8][31] = "!t";

    route1a = test;

  }

  public static String[][] getRoute1a() {
    return route1a;
  }

  public static void initRoute1b() {
    route1b = new String[40][40];
    makeBorders(route1b);

    String[][] test = route1b;

    //creating main path, going toward route 1d > city > pkmngym
    for (int y = 2; y < 25; y++) {
      for (int x = 18; x < 30; x++) {
        test[y][x] = "p";
      }
    }

    //creating splitting path going into route1c
    for (int y = 5; y < 10; y++) {
      for (int x = 2; x < 25; x++) {
        test[y][x] = "p";
      }
    }

    //creating grass
    for (int y = 11; y < 25; y++) {
      for (int x = 2; x < 16; x++) {
        test[y][x] = "g";
      }
    }

    route1b = test;
  }

  public static String[][] getRoute1b() {
    return route1b;
  }

  public static void initRoute1c() {
    route1c = new String[40][40];
    makeBorders(route1c);

    String[][] test = route1c;

    //creating pathway
    for (int y = 5; y < 10; y++) {
      for (int x = 30; x > 2; x--) {
        test[y][x] = "p";
      }
    }

    route1c = test;
  }

  public static String[][] getRoute1c() {
    return route1c;
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
