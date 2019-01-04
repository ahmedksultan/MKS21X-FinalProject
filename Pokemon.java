import java.util.*; //scanner
import java.io.*; //file, filenotfoundexception

public class Pokemon{
  public static void main(String[] args) {
    organizeData();
  }

  private String[][] data;

  public static void organizeData(){
    String filename = "Pokemon.csv";
    try{
      File f = new File(filename);//can combine
      Scanner in = new Scanner(f);//into one line
    }
    catch{

    }
  }
}
