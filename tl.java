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

import java.util.*;

public class tl {

  //credit to Mr. K, from TerminalDemo.java
	public static void putString(int r, int c, Terminal t, String s) {
		t.moveCursor(r,c);
		for (int i = 0; i < s.length(); i++) {
			t.putCharacter(s.charAt(i));
		}
	}

  /* dont work - will figure out some other time

  //modified version of Mr. K's putString method, from TerminalDemo
  public static void putStringBold(int r, int c, Terminal t, String s) {
		t.moveCursor(r,c);
		for (int i = 0; i < s.length(); i++) {
      terminal.applySGR(Terminal.SGR.Enter_BOLD);
			t.putCharacter(s.charAt(i));
		}
	}

  //modified version of Mr. K's putString
  public static void putStringUL(int r, int c, Terminal t, String s) {
		t.moveCursor(r,c);
		for (int i = 0; i < s.length(); i++) {
      terminal.applySGR(Terminal.SGR.Enter_UNDERLINE);
			t.putCharacter(s.charAt(i));
		}
	}
  */

	public static void main (String[] args) {

		int x = 2;
		int y = 15;

		Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();
		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);

		boolean running = true;

    String[][] test;
    Map.initTown1();
    test = Map.getTown1();
    //System.out.println(Map.toString(test));

		while (running) {

      //player
			terminal.moveCursor(x,y);
			terminal.applyBackgroundColor(Terminal.Color.BLUE);
			terminal.applyForegroundColor(Terminal.Color.WHITE);
			terminal.applySGR(Terminal.SGR.ENTER_UNDERLINE);
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
            terminal.putCharacter('.');
          }
          if (test[a][b] == "b") {
            terminal.applyBackgroundColor(Terminal.Color.YELLOW);
            terminal.putCharacter(' ');
          }
          if (test[a][b] == "d") {
            terminal.applyBackgroundColor(Terminal.Color.BLACK);
            terminal.putCharacter(' ');
          }
          if (test[a][b] == "g") {
            terminal.applyBackgroundColor(Terminal.Color.GREEN);
            terminal.putCharacter('^');
          }
          if (test[a][b] == "|" || test[a][b] == "_") {
            terminal.applyBackgroundColor(Terminal.Color.BLACK);
            terminal.putCharacter(' ');
          }
          else {
            terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
            terminal.putCharacter(' ');
          }
        }
      }

      putString(44,2,terminal,"Welcome to Javamon!");
      putString(44,4,terminal, "Created by Ahmed Sultan and Ali Taoube");
      putString(44,6,terminal,"PLAYER INFORMATION");

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
			}
    try {
      Thread.sleep(20);
    } catch (InterruptedException e) {
      System.exit(1);
    }
		}
	}
}
