//importing lanterna stuff
import java.util.Arrays;
import com.googlecode.lanterna.terminal.Terminal.SGR;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;

public class tl {
  //this is just a file for testing lanterna stuff

  public static void putString(int r, int c,Terminal t, String s){
    t.moveCursor(r,c);
    for(int i = 0; i < s.length();i++){
      t.putCharacter(s.charAt(i));
    }
  }

  public static void main (String[] args) {
    Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();

		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);

    boolean running = true;

    String[][] tmapa;
    Map.initTest();
    tmapa = Map.getTest();
    System.out.println(Map.toString(tmapa));

    while (running) {

      //System.out.println();

      terminal.moveCursor(size.getColumns()9,5);
      terminal.applyBackgroundColor(Terminal.Color.BLUE);
      terminal.applyForegroundColor(Terminal.Color.WHITE);
      terminal.applySGR(Terminal.SGR.ENTER_BOLD);
      terminal.putCharacter('\u200d');
      terminal.putCharacter(' ');
      terminal.moveCursor(size.getColumns()-5,6);
      terminal.putCharacter(' ');
      terminal.putCharacter(' ');
      terminal.putCharacter(' ');
      terminal.putCharacter(' ');
      terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
      terminal.applyForegroundColor(Terminal.Color.DEFAULT);

      Key key = terminal.readInput();

      if (key != null) {
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          running = false;
        }
      }

    }

  }

}
