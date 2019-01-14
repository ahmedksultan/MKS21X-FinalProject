//API : http://mabe02.github.io/lanterna/apidocs/2.1/
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
import java.util.Arrays;


public class tl {

	public static void putString(int r, int c, Terminal t, String s) {
		t.moveCursor(r,c);
		for (int i = 0; i < s.length(); i++) {
			t.putCharacter(s.charAt(i));
		}
	}

	public static void main (String[] args) {

		int x = 50;
		int y = 20;

		Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();
		TerminalSize size = terminal.getTerminalSize();
    System.out.println(size);
		terminal.setCursorVisible(false);

		boolean running = true;

    String[][] test;
    Map.initTest();
    test = Map.getTest();
    //System.out.println(Map.toString(test));

		while (running) {

      //player
			terminal.moveCursor(x,y);
			terminal.applyBackgroundColor(Terminal.Color.BLACK);
			terminal.applyForegroundColor(Terminal.Color.WHITE);
			//applySGR(a,b) for multiple modifiers (bold,blink) etc.
			terminal.applySGR(Terminal.SGR.ENTER_UNDERLINE);
      terminal.applySGR(Terminal.SGR.ENTER_BLINK);
      terminal.applySGR(Terminal.SGR.ENTER_BOLD);
			terminal.putCharacter('@');
			terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
			terminal.applyForegroundColor(Terminal.Color.DEFAULT);
			terminal.applySGR(Terminal.SGR.RESET_ALL);

      for (int b = 0; b < test.length; b++) {
        for (int a = 0; a < test[b].length; a++) {
          terminal.moveCursor(b,a);
          if (test[a][b] == "r") {
            terminal.applyBackgroundColor(Terminal.Color.RED);
            terminal.putCharacter(' ');
          }
          if (test[a][b] == "b") {
            terminal.applyBackgroundColor(Terminal.Color.YELLOW);
            terminal.putCharacter(' ');
          }
          if (test[a][b] == "d") {
            terminal.applyBackgroundColor(Terminal.Color.BLACK);
            terminal.putCharacter(' ');
          }
          else {
            terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
            terminal.putCharacter(' ');
          }
        }
      }

      /*
			terminal.moveCursor(size.getColumns()-5,1);
			terminal.applyBackgroundColor(Terminal.Color.BLUE);
			terminal.applyForegroundColor(Terminal.Color.WHITE);
			terminal.applySGR(Terminal.SGR.ENTER_BOLD);
			terminal.putCharacter(' ');
			terminal.putCharacter(' ');
			terminal.putCharacter('\u200d');
			terminal.putCharacter(' ');
			terminal.moveCursor(size.getColumns()-5,2);
			terminal.putCharacter(' ');
			terminal.putCharacter(' ');
			terminal.putCharacter(' ');
			terminal.putCharacter(' ');
			terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
			terminal.applyForegroundColor(Terminal.Color.DEFAULT);
      */

			Key key = terminal.readInput();

			if (key != null)
			{

				if (key.getKind() == Key.Kind.Escape) {

					terminal.exitPrivateMode();
					running = false;
				}

				if (key.getKind() == Key.Kind.ArrowLeft) {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					x--;
				}

				if (key.getKind() == Key.Kind.ArrowRight) {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					x++;
				}

				if (key.getKind() == Key.Kind.ArrowUp) {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					y--;
				}

				if (key.getKind() == Key.Kind.ArrowDown) {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					y++;
				}
				//space moves it diagonally
				if (key.getCharacter() == ' ') {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					y++;
					x++;
				}
			}


		}
	}
}
