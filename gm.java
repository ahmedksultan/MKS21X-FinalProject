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
import com.googlecode.lanterna.screen.Screen;

import java.util.*;
import java.io.*; //file, filenotfoundexception
import java.util.concurrent.TimeUnit; //time

public class gm {

  public static void main(String[] args) {

    //starting position
    int x = 2;
    int y = 15;

    boolean choosepkmn = true;
    boolean istown1 = false;
    boolean isroute1 = false;
    boolean iscity = false;

    int tbattles = 0;

    //creates a new, private terminal, puts screen overlay over it
		Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();
		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);
		Screen screen = new Screen(terminal);

		boolean running = true;

    //IMPORTING MAP INFORMATION - takes values from map class

    String[][] town;
    Map.initTown1();
    town = Map.getTown1();

    String[][] route1a;
    Map.initRoute1a();
    route1a = Map.getRoute1a();

    //STORES INFORMATION ABOUT THE PLAYER:

    ArrayList<Pokemon> pparty = new ArrayList<Pokemon>();
    Trainer player = new Trainer("Player", pparty);

    screen.startScreen();

    //game loop starts here
    while (running) {
      screen.refresh();
      int oldx = x;
      int oldy = y;

      //reading key inputs happens HERE!
      Key key = screen.readInput();
			if (key != null) {
				if (key.getKind() == Key.Kind.Escape) {
          screen.stopScreen();
          terminal.clearScreen();
					terminal.exitPrivateMode();
					running = false;
				}

				if (key.getKind() == Key.Kind.ArrowLeft) {
          //terminal.clearScreen();
					x--;
				}

				if (key.getKind() == Key.Kind.ArrowRight) {
          //terminal.clearScreen();
					x++;
				}

				if (key.getKind() == Key.Kind.ArrowUp) {
          //terminal.clearScreen();
					y--;
				}

				if (key.getKind() == Key.Kind.ArrowDown) {
          //terminal.clearScreen();
					y++;
				}
      }
      screen.putString(oldx, oldy, " ", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
      screen.putString(x, y, "@", Terminal.Color.WHITE, Terminal.Color.BLUE);

      //playerinfo(screen, pparty);
      screen.putString(44,2, "Welcome to Javamon!", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
      screen.putString(44,4, "Created by Ahmed Sultan & Ali Taoube.", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
      screen.putString(44,6, "PLAYER INFORMATION", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
      screen.putString(44,8, "Party:" + pparty, Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);

      if (choosepkmn = true) {
        screen.putString(2,2, "Choose your starter Pokemon!", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
        screen.putString(2,4, "[S] for Squirtle.", Terminal.Color.BLUE, Terminal.Color.DEFAULT);
        screen.putString(2,5, "[B] for Bulbasaur.", Terminal.Color.GREEN, Terminal.Color.DEFAULT);
        screen.putString(2,6, "[C] for Charmander.", Terminal.Color.RED, Terminal.Color.DEFAULT);
      }

      screen.refresh();
    }
    //end of gameloop ^^^


  }
  //end of main ^^^

  //playerinfo displayers information about the player at all times
  //NOTE: only exception is when there is a battle going on!
  /*
  public static void playerinfo(Screen x, ArrayList<Pokemon> p) {
    x.putString(2,2, "Welcome to Javamon!", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(2,4, "Created by Ahmed Sultan & Ali Taoube.", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(2,6, "PLAYER INFORMATION", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(2,8, "Party:" + p, Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
  }
  */

}
