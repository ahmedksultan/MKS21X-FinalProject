import java.util.*; //ArrayList

public class Map {

  /* LIST OF MAPS FOR REFERENCE
  mapaaa - test map 1 (matrix of ".")
  mapbbb - test map 2 (borders)
  */

  /*creates an arraylist of 2d string arrays, which will cycle through
  depending on player's progression in the RPG*/
  ArrayList<String[][]> maplist = new ArrayList<String[][]>();

  public static void main(String[] args) {

    //creating a map for testing purposes
    String[][] mapaaa = new String[20][20];

    //filling test map with periods
    for (int i = 0; i < mapaaa.length; i++) {
      for (int j = 0; j < mapaaa[i].length; j++) {
        mapaaa[i][j] = ".";
      }
    }

    //printing out test map
    System.out.println(toString(mapaaa));

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
    System.out.println(toString(mapbbb));

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
