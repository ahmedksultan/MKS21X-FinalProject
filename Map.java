import java.util.*; //ArrayList

public class Map {

  public static void main(String[] args) {

    /* LIST OF MAPS FOR REFERENCE
    mapaaa - test map 1 (matrix of ".")
    mapbbb - test map 2 (borders)
    mapccc - test map 3 (testing of map elements)
    mapddd - test map 4 (testing makeBorders)
    */

    /*creates an arraylist of 2d string arrays, which will cycle through
    depending on player's progression in the RPG*/
    ArrayList<String[][]> maplist = new ArrayList<String[][]>();

    //TESTING BEGINS
    ArrayList<String[][]> testmaplist = new ArrayList<String[][]>();

    /*
    // mapaaa - test map 1 (matrix of ".")
    //creating a map for testing purposes
    String[][] mapaaa = new String[20][20];
    //filling test map
    for (int i = 0; i < mapaaa.length; i++) {
      for (int j = 0; j < mapaaa[i].length; j++) {
        mapaaa[i][j] = ".";
      }
    }

    //printing out test map
    //System.out.println(toString(mapaaa)); */

    /*
    //mapbbb - test map 2 (borders)
    String[][] mapbbb = new String[20][20];
    for (int i = 0; i < mapbbb.length; i++) {
      for (int j = 0; j < mapbbb[i].length; j++) {
        if (i == 0 || i == mapbbb.length - 1) {
          mapbbb[i][j] = "-";
        }
        else if (j == 0 || j == mapbbb[i].length - 1) {
          mapbbb[i][j] = "|";
        }
        else {
          mapbbb[i][j] = ".";
        }
      }
    }
    System.out.println(toString(mapbbb));*/

    /*
    //mapccc - test map 3 (testing of map elements)
    //mapddd - testing method makeBorders();
    String[][] mapccc = new String[20][20];
    String[][] mapddd = new String[8][8];
    makeBorders(mapccc);
    makeBorders(mapddd);
    System.out.println(toString(mapccc));
    System.out.println(toString(mapddd));

    mapccc[2][2] = "@"; //denotes player
    mapccc[2][4] = "!"; //denotes trainer
    mapccc[2][6] = "?"; //denotes NPC

    BEGINNING TESTING ARRAYLIST OF MAPS FEATURE
    //WORKS!
    testmaplist.add(mapaaa);
    testmaplist.add(mapbbb);
    testmaplist.add(mapccc);
    testmaplist.add(mapddd);
    for (int i = 0; i < maplist.size(); i++) {
      System.out.println(toString(maplist.get(i)));
    }

    //TESTING ENDS
    */

  }

  //note: why is toString being weird?
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

  //Description - creates an empty map with a border
  public static void makeBorders(String[][] x) {
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

}
