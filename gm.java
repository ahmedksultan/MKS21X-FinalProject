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
    boolean isroute1a = false;
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
        if (key.getCharacter() == 's' && choosepkmn == true) {
          Pokemon Squirtle = new Pokemon("Squirtle");
          pparty.add(Squirtle);
          choosepkmn = false;
          screen.clear();
          istown1 = true;
        }
        if (key.getCharacter() == 'b' && choosepkmn == true) {
          Pokemon Bulbasaur = new Pokemon("Bulbasaur");
          pparty.add(Bulbasaur);
          choosepkmn = false;
          screen.clear();
          istown1 = true;
        }
        if (key.getCharacter() == 'c' && choosepkmn == true) {
          Pokemon Charmander = new Pokemon("Charmander");
          pparty.add(Charmander);
          choosepkmn = false;
          screen.clear();
          istown1 = true;
        }
      }

      //playerinfo displayers information about the player at all times
      //NOTE: only exception is when there is a battle going on!
      playerinfo(screen, pparty);

      //choosepkmn appears when the code starts
      //disappears when a starter pokemon is chosen
      if (choosepkmn = true) {
        choosepkmn(screen);
      }

      if (istown1 == true) {
        town1(screen, town, pparty);
        screen.refresh();
      }

      if (isroute1a == true) {
        route1a(screen, route1a, pparty);
        screen.refresh();
      }



      //screen switchers - this creates the "scrolling effect"
      if (y == 24 && istown1 == true) {
        istown1 = false;
        isroute1a = true;
        y = 3;
      }

      if (y == 2 && isroute1a == true) {
        isroute1a = false;
        istown1 = true;
        y = 24;
      }



      //player icon 
      screen.putString(oldx, oldy, " ", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
      screen.putString(x, y, "@", Terminal.Color.WHITE, Terminal.Color.BLUE);

      screen.refresh();
    }
    //end of gameloop ^^^


  }
  //end of main ^^^

  public static void playerinfo(Screen x, ArrayList<Pokemon> p) {
    x.putString(44,2, "Welcome to Javamon!", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(44,4, "Created by Ahmed Sultan & Ali Taoube.", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(44,6, "PLAYER INFORMATION", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(44,8, "Party:" + p, Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
  }

  public static void choosepkmn(Screen x) {
    x.putString(2,2, "Choose your starter Pokemon!", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(2,4, "[S] for Squirtle.", Terminal.Color.BLUE, Terminal.Color.DEFAULT);
    x.putString(2,5, "[B] for Bulbasaur.", Terminal.Color.GREEN, Terminal.Color.DEFAULT);
    x.putString(2,6, "[C] for Charmander.", Terminal.Color.RED, Terminal.Color.DEFAULT);
  }

  public static void town1(Screen x, String[][] town, ArrayList<Pokemon> p) {
    for (int b = 0; b < town.length; b++) {
      for (int a = 0; a < town[b].length; a++) {

        //r = roof -- RED
        //b = building -- WHITE
        //d = door -- BLACK
        //g = grass -- GREEN
        //| = border -- BLACK
        //p = path -- YELLOW

        //Tejas helped me with this switch case!
        switch(town[a][b]) {
          case "r": x.putString(b,a, " ", Terminal.Color.DEFAULT, Terminal.Color.RED);
          break;
          case "b": x.putString(b,a, " ", Terminal.Color.DEFAULT, Terminal.Color.WHITE);
          break;
          case "d": x.putString(b,a, " ", Terminal.Color.DEFAULT, Terminal.Color.BLACK);
          break;
          case "g": x.putString(b,a, "^", Terminal.Color.DEFAULT, Terminal.Color.GREEN);
          break;
          case "p": x.putString(b,a, " ", Terminal.Color.DEFAULT, Terminal.Color.YELLOW);
          break;
          default: x.putString(b,a, " ", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
        }

        playerinfo(x, p);
        /*
        if (town[a][b] == "r") {
          x.putString(b,a, " ", Terminal.Color.DEFAULT, Terminal.Color.RED);
        }

        if (town[a][b] == "b") {
          x.putString(b,a, " ", Terminal.Color.DEFAULT, Terminal.Color.WHITE);
        }

        if (town[a][b] == "d") {
          x.putString(b,a, " ", Terminal.Color.DEFAULT, Terminal.Color.BLACK);
        }

        if (town[a][b] == "g") {
          x.putString(b,a, " ", Terminal.Color.DEFAULT, Terminal.Color.GREEN);
        }

        if (town[a][b] == "p") {
          x.putString(b,a, " ", Terminal.Color.DEFAULT, Terminal.Color.YELLOW);
        }

        else {
          x.putString(b,a, " ", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
        }
        */
      }
    }
  }

  public static void route1a(Screen x, String[][] map, ArrayList<Pokemon> p) {
    for (int b = 0; b < map.length; b++) {
      for (int a = 0; a < map[b].length; a++) {

        //r = roof -- RED
        //b = building -- WHITE
        //d = door -- BLACK
        //g = grass -- GREEN
        //| = border -- BLACK
        //p = path -- YELLOW

        switch(map[a][b]) {
          case "r": x.putString(b,a, " ", Terminal.Color.DEFAULT, Terminal.Color.RED);
          break;
          case "b": x.putString(b,a, " ", Terminal.Color.DEFAULT, Terminal.Color.WHITE);
          break;
          case "d": x.putString(b,a, " ", Terminal.Color.DEFAULT, Terminal.Color.BLACK);
          break;
          case "g": x.putString(b,a, "^", Terminal.Color.DEFAULT, Terminal.Color.GREEN);
          break;
          case "p": x.putString(b,a, " ", Terminal.Color.DEFAULT, Terminal.Color.YELLOW);
          break;
          case "!t": x.putString(b,a, "!", Terminal.Color.YELLOW, Terminal.Color.MAGENTA);
          break;
          default: x.putString(b,a, " ", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
        }

        playerinfo(x, p);
      }
    }
  }

}
