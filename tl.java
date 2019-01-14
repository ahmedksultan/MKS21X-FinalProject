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
import java.io.*; //file, filenotfoundexception

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

    boolean choosepkmn = true;
    boolean istown1 = false;
    boolean isroute1 = false;
    boolean iscity = false;

    int tbattles = 0;

		Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();
		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);

		boolean running = true;

    String[][] test;
    Map.initTown1();
    test = Map.getTown1();

    String[][] route1;
    Map.initRoute1();
    route1 = Map.getRoute1();

    ArrayList<Pokemon> pparty = new ArrayList<Pokemon>();
    Trainer player = new Trainer("Player", pparty);

		while (running) {

      terminal.applyForegroundColor(Terminal.Color.BLACK);
      putString(44,2,terminal,"Welcome to Javamon!");
      putString(44,4,terminal, "Created by Ahmed Sultan and Ali Taoube.");
      putString(44,6,terminal,"PLAYER INFORMATION");
      putString(44,8,terminal,"Party:" + pparty);

      if (choosepkmn == true) {
        putString(2, 2, terminal, "Choose your starter Pokemon!");
        putString(2, 4, terminal, "[S] for Squirtle.");
        putString(2, 5, terminal, "[B] for Bulbasaur.");
        putString(2, 6, terminal, "[C] for Charmander.");
      }

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


      if (istown1 == true ) {
        //town1 stuff...
        for (int b = 0; b < test.length; b++) {
          for (int a = 0; a < test[b].length; a++) {
            terminal.moveCursor(b,a);
            //r = roof
            //b = building
            //d = door
            //g = grass
            //| = border
            //p = path
            if (test[a][b] == "r") {
              terminal.applyBackgroundColor(Terminal.Color.RED);
              terminal.putCharacter('.');
            }
            if (test[a][b] == "b") {
              terminal.applyBackgroundColor(Terminal.Color.WHITE);
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
            if (test[a][b] == "|") {
              terminal.applyBackgroundColor(Terminal.Color.BLACK);
              terminal.putCharacter(' ');
            }
            if (test[a][b] == "p") {
              terminal.applyBackgroundColor(Terminal.Color.YELLOW);
              terminal.putCharacter(' ');
            }
            else {
              terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
              terminal.putCharacter(' ');
            }
          }
        }
      }

      if (y == 22 && istown1 == true) {
        istown1 = false;
        isroute1 = true;
        y = 3;
      }

      if (y == 2 && isroute1 == true) {
        isroute1 = false;
        istown1 = true;
        y = 21;
      }

      if (isroute1 == true) {
        //route1 stuff...
        for (int b = 0; b < route1.length; b++) {
          for (int a = 0; a < route1[b].length; a++) {
            terminal.moveCursor(b,a);
            //r = roof
            //b = building
            //d = door
            //g = grass
            //| = border
            //p = path
            if (route1[a][b] == "r") {
              terminal.applyBackgroundColor(Terminal.Color.RED);
              terminal.putCharacter('.');
            }
            if (route1[a][b] == "b") {
              terminal.applyBackgroundColor(Terminal.Color.WHITE);
              terminal.putCharacter(' ');
            }
            if (route1[a][b] == "d") {
              terminal.applyBackgroundColor(Terminal.Color.BLACK);
              terminal.putCharacter(' ');
            }
            if (route1[a][b] == "g") {
              terminal.applyBackgroundColor(Terminal.Color.GREEN);
              terminal.putCharacter('^');
            }
            if (route1[a][b] == "|") {
              terminal.applyBackgroundColor(Terminal.Color.BLACK);
              terminal.putCharacter(' ');
            }
            if (route1[a][b] == "p") {
              terminal.applyBackgroundColor(Terminal.Color.YELLOW);
              terminal.putCharacter(' ');
            }
            if (route1[a][b] == "!t") {
              terminal.applyBackgroundColor(Terminal.Color.MAGENTA);
              terminal.applyForegroundColor(Terminal.Color.YELLOW);
              terminal.putCharacter('!');
            }
            else {
              terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
              terminal.putCharacter(' ');
            }
          }
        }
        if (y == 8 && tbattles == 0) {
          ArrayList<Pokemon> johnparty = new ArrayList<Pokemon>();
          Pokemon blastoise = new Pokemon("Blastoise");
          Pokemon eevee = new Pokemon("Eevee");
          johnparty.add(blastoise);
          johnparty.add(eevee);
          Player John = new Trainer("John", johnparty);
          Battle johnbattle = new Battle(player, John);

          Scanner user_input = new Scanner( System.in );
          String firstname;

          System.out.println("Your enemy is " + John.getName() + "! Their first pokemon is " + johnbattle.getActive2());
          System.out.println("Your team is " + johnbattle.getOne().getParty().toString());
          System.out.println("Your opponent's team is " + johnbattle.getTwo().getParty().toString());

          while (!johnbattle.isOver()){

            System.out.println(johnbattle.getActive1().toString() + " and " + johnbattle.getActive2() + " are battling!");
            System.out.println("Choose your move");
            firstname = user_input.next();
            johnbattle.move(firstname, "absorb");
            System.out.println("You used " + firstname + "! Your opponent used absorb");
            johnbattle.forceSwitch();
          }
          System.out.println("The Battle is over! " + johnbattle.getWinner()  + " has won!");
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
        if (key.getCharacter() == 's' && choosepkmn == true) {
          Pokemon Squirtle = new Pokemon("Squirtle");
          pparty.add(Squirtle);
          choosepkmn = false;
          istown1 = true;
        }
        if (key.getCharacter() == 'b' && choosepkmn == true) {
          Pokemon Bulbasaur = new Pokemon("Bulbasaur");
          pparty.add(Bulbasaur);
          choosepkmn = false;
          istown1 = true;
        }
        if (key.getCharacter() == 'c' && choosepkmn == true) {
          Pokemon Charmander = new Pokemon("Charmander");
          pparty.add(Charmander);
          choosepkmn = false;
          istown1 = true;
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
