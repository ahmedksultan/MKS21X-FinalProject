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
    int x = 3;
    int y = 16;

    int maxstarter = 0;

    boolean choosepkmn = true;
    boolean istown1 = false;
    boolean ishouse1 = false;
    boolean ishouse2 = false;
    boolean isroute1a = false;
    boolean isroute1b = false;
    boolean isroute1c = false;
    boolean iscity = false;

    //tbattles track how many trainer battles have occurred
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

    String[][] house1;
    Map.initHouse1();
    house1 = Map.getHouse1();

    String[][] route1a;
    Map.initRoute1a();
    route1a = Map.getRoute1a();

    String[][] route1b;
    Map.initRoute1b();
    route1b = Map.getRoute1b();

    String[][] route1c;
    Map.initRoute1c();
    route1c = Map.getRoute1c();

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

        //choose pokemon stuff goes here
        if (key.getCharacter() == 's' && choosepkmn == true && maxstarter < 1) {
          Pokemon Squirtle = new Pokemon("Squirtle");
          pparty.add(Squirtle);
          choosepkmn = false;
          maxstarter++;
          screen.clear();
          istown1 = true;
        }
        if (key.getCharacter() == 'b' && choosepkmn == true && maxstarter < 1) {
          Pokemon Bulbasaur = new Pokemon("Bulbasaur");
          pparty.add(Bulbasaur);
          choosepkmn = false;
          maxstarter++;
          screen.clear();
          istown1 = true;
        }
        if (key.getCharacter() == 'c' && choosepkmn == true && maxstarter < 1) {
          maxstarter++; //^^doesn't matter where it goes in the code, as its just an int value
          Pokemon Charmander = new Pokemon("Charmander");
          pparty.add(Charmander);
          choosepkmn = false;
          screen.clear();
          istown1 = true;
        }
        /* Locked until further use is necessary //DEMO VERSION
        if (key.getCharacter() == 'd' && choosepkmn == true && maxstarter < 1) {
          maxstarter++;
          Pokemon Bulbasaur = new Pokemon("Bulbasaur");
          Pokemon Charmander = new Pokemon("Charmander");
          Pokemon Squirtle = new Pokemon("Squirtle");
          Pokemon Mewtwo = new Pokemon("Mewtwo");
          Pokemon Pikachu = new Pokemon("Pikachu");
          Pokemon Ditto = new Pokemon("Ditto");
          pparty.add(Bulbasaur);
          pparty.add(Charmander);
          pparty.add(Squirtle);
          pparty.add(Mewtwo);
          pparty.add(Pikachu);
          pparty.add(Ditto);
          screen.clear();
          istown1 = true;
        } */
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

      if (ishouse1 == true) {
        house1(screen, house1, pparty);
        screen.refresh();
      }

      if (isroute1a == true) {
        route1a(screen, route1a, pparty);
        screen.refresh();

        //FIRST TRAINER BATTLE GOES HERE...
        if (y == 8 && tbattles == 0) {
          //creating new trainer battle, "John"
          //first tbattle, hence tbattles = 0
          ArrayList<Pokemon> johnparty = new ArrayList<Pokemon>();

          Pokemon Mewtwo = new Pokemon("Mewtwo");
          //Pokemon Eevee = new Pokemon("Eevee");

          johnparty.add(Mewtwo);
          //johnparty.add(Eevee);

          Player John = new Trainer("John", johnparty);
          Battle johnbattle = new Battle(player, John);

          Scanner user_input = new Scanner(System.in);
          String yourattack;
          String enemyattack;

          terminal.exitPrivateMode();

          System.out.println("\n---A BATTLE HAS BEGUN!---");
          System.out.println("Your enemy is " + John.getName() + "! Their first pokemon is " + johnbattle.getActive2() + ".");
          System.out.println("Your team is " + johnbattle.getOne().getParty().toString());

          ArrayList<String> moves1 = johnbattle.getOne().getMon(0).getAttacks();

          System.out.println("Your pokemon's moves are here: " + johnbattle.getActive1().getAttacks());
          // System.out.println("Your pokemon's moves are here: " + battle.getOne().getMon(4).attackstoString(1));

          System.out.println("Your opponent's team is " + johnbattle.getTwo().getParty().toString());

          while (!johnbattle.isOver()){

            System.out.println(johnbattle.getActive1().toString() + " and " + johnbattle.getActive2() + " are battling!");
            System.out.println("Choose your move!");
            yourattack = user_input.next();
            enemyattack = johnbattle.getActive2().getEnemyAttack();

            for (int i = 0; i < moves1.size(); i++) {
              if (moves1.get(i).equals(user_input.next())) {
                yourattack = moves1.get(i);
              }
              else {
                yourattack = "IMPOSSIBLE MOVE";
              }
            }

            johnbattle.move(yourattack);
            System.out.println("You used " + yourattack + "! Your opponent used" + enemyattack + "." );
            johnbattle.forceSwitch();
          }

          System.out.println("The battle is over! " + johnbattle.getWinner()  + " has won!");
          tbattles = 1;
        }


      }

      if (isroute1b == true) {
        route1b(screen, route1b, pparty);
        screen.refresh();
      }

      if (isroute1c == true) {
        route1c(screen, route1c, pparty);
        screen.refresh();
      }



      //screen switchers - this creates the "scrolling effect"
      if (y == 23 && istown1 == true) {
        istown1 = false;
        isroute1a = true;
        y = 3;
      }

      //house stuff - buggy for now, better to not waste time
      /*
      if ((x == 5 || x == 6 ) && (y == 12) && istown1 == true ) {
        istown1 = false;
        ishouse1 = true;
        y = 2;
        x = 2;
      }
      */

      if (y == 2 && isroute1a == true) {
        isroute1a = false;
        istown1 = true;
        y = 22;
      }

      if (y == 23 && isroute1a == true) {
        isroute1a = false;
        isroute1b = true;
        y = 3;
      }

      if (x == 2 && isroute1b == true) {
        isroute1b = false;
        isroute1c = true;
        x = 28;
      }

      if (y == 2 && isroute1b == true) {
        isroute1b = false;
        isroute1a = true;
        y = 22;
      }

      if (x == 29 && isroute1c == true) {
        isroute1c = false;
        isroute1b = true;
        x = 3;
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
    x.putString(43,2, "Welcome to Javamon!", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(43,4, "Created by Ahmed Sultan & Ali Taoube.", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(43,6, "PLAYER INFORMATION", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(43,8, "Party: " + p, Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);

    x.putString(43, 10, "--------------", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);

    x.putString(43, 12, "[[[INSTRUCTIONS]]]", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(43, 14, "Use directional keys to move.", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(43, 15, "Use [I] to interact.", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
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
      }
    }
    playerinfo(x, p);
  }

  public static void route1b(Screen x, String[][] map, ArrayList<Pokemon> p) {
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
      }
    }
    playerinfo(x, p);
  }

    public static void route1c(Screen x, String[][] map, ArrayList<Pokemon> p) {
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

  public static void house1(Screen x, String[][] map, ArrayList<Pokemon> p) {
    for (int b = 0; b < map.length; b++) {
      for (int a = 0; a < map[b].length; a++) {
        switch(map[b][a]) {
          case "-": x.putString(b, a, " ", Terminal.Color.DEFAULT, Terminal.Color.BLACK);
          break;
          case "|": x.putString(b, a, " ", Terminal.Color.DEFAULT, Terminal.Color.BLACK);
          break;
        }
      }
    }
    playerinfo(x, p);
  }

  public static void house2(Screen x, String[][] map, ArrayList<Pokemon> p) {
    for (int b = 0; b < map.length; b++) {
      for (int a = 0; a < map[b].length; a++) {
        switch(map[b][a]) {
          case "-": x.putString(b, a, " ", Terminal.Color.DEFAULT, Terminal.Color.BLACK);
          break;
          case "|": x.putString(b, a, " ", Terminal.Color.DEFAULT, Terminal.Color.BLACK);
          break;
        }
      }
    }
    playerinfo(x, p);
  }


}
