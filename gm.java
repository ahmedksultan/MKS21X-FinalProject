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

  public static void main (String[] args) {

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

    int potions = 10;

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
        //Locked until further use is necessary //DEMO VERSION
        //Need to figure out: How to print Pokemon list on more than one line?
        if (key.getCharacter() == 'd' && choosepkmn == true && maxstarter < 1) {
          maxstarter++;
          Pokemon Bulbasaur = new Pokemon("Bulbasaur");
          Pokemon Charmander = new Pokemon("Charmander");
          Pokemon Squirtle = new Pokemon("Squirtle");
          Pokemon Mewtwo = new Pokemon("Mewtwo");
          Pokemon Pikachu = new Pokemon("Pikachu");
          Pokemon Ditto = new Pokemon("Ditto");
          choosepkmn = false;
          pparty.add(Bulbasaur);
          pparty.add(Charmander);
          pparty.add(Squirtle);
          pparty.add(Mewtwo);
          pparty.add(Pikachu);
          pparty.add(Ditto);
          screen.clear();
          istown1 = true;
        }

        //just messing around! Mewtwo mode (coolest pokemon)
        if (key.getCharacter() == 'm' && maxstarter < 1) {
          maxstarter++;
          Pokemon Mewtwo = new Pokemon("Mewtwo");
          pparty.add(Mewtwo);
          choosepkmn = false;
          screen.clear();
          istown1 = true;
        }
      }

      //playerinfo displayers information about the player at all times
      //NOTE: only exception is when there is a battle going on!
      playerinfo(screen, pparty, potions);

      //choosepkmn appears when the code starts
      //disappears when a starter pokemon is chosen
      if (choosepkmn = true) {
        choosepkmn(screen);
      }

      if (istown1 == true) {
        town1(screen, town, pparty, potions);
        screen.refresh();
      }

      if (ishouse1 == true) {
        house1(screen, house1, pparty, potions);
        screen.refresh();
      }

      if (isroute1a == true) {
        route1a(screen, route1a, pparty, potions);
        screen.refresh();

        //FIRST TRAINER BATTLE GOES HERE...
        if (y == 8 && tbattles == 0) {
          //creating new trainer battle, "John"
          //first trainer battle (tbattles), hence tbattles = 0
          ArrayList<Pokemon> johnparty = new ArrayList<Pokemon>();

          //Pokemon Mewtwo = new Pokemon("Mewtwo");
          Pokemon eeveejohn = new Pokemon("Eevee");
          Pokemon oddishjohn = new Pokemon("Oddish");

          //johnparty.add(Mewtwo);
          johnparty.add(eeveejohn);
          johnparty.add(oddishjohn);

          Player John = new Trainer("JOHN", johnparty);
          Battle johnbattle = new Battle(player, John);

          Scanner user_input = new Scanner(System.in);
          String yourattack;
          String enemyattack;

          terminal.exitPrivateMode();

          System.out.println("\n---A BATTLE HAS BEGUN!---");
          System.out.println("\n[MSG] JOHN: I'M SURE TO WIN!\n");

          System.out.println("Your enemy is " + John.getName() + "! Their first pokemon is " + johnbattle.getActive2().getName().toUpperCase() + ".");
          System.out.println("Your opponent's team is " + johnbattle.getTwo().getParty().toString() + "\n");

          System.out.println("Your team is " + johnbattle.getOne().getParty().toString());

          ArrayList<String> moves1 = johnbattle.getOne().getMon(0).getAttacks();

          // System.out.println("Your Pokemon's moves are here: " + johnbattle.getActive1().getAttacks());
          // System.out.println("Your pokemon's moves are here: " + battle.getOne().getMon(4).attackstoString(1));

          while (!johnbattle.isOver()) {

            System.out.println(Sprites.toString(Sprites.getArray(johnbattle.getActive1().getName())));

            System.out.println(johnbattle.getActive1().getName().toUpperCase() + " and " + johnbattle.getActive2().getName().toUpperCase() + " are battling!");
            System.out.println("\n" + johnbattle.getActive1() + "'s HP: " + johnbattle.getActive1().getHP() + "/" + johnbattle.getActive1().getTotalHP());
            System.out.println(johnbattle.getActive2() + "'s HP: " + johnbattle.getActive2().getHP() + "/" + johnbattle.getActive2().getTotalHP());
            System.out.println("Choose your move!\n");
            for (int i = 0; i < johnbattle.getActive1().getAttacks().size(); i++) {
              System.out.println("[" + (i + 1) + "] for " + johnbattle.getActive1().getAttacks().get(i).toUpperCase());
            }
            System.out.println("[H] to use a Potion.");
            System.out.println("");

            String userinput = user_input.next();

            if (userinput.equals("h") && potions >= 0) {
              johnbattle.getActive2().attack(johnbattle.getActive1());
              potions--;
              if (johnbattle.getActive1().getHP() + 15 > johnbattle.getActive1().getTotalHP()) {
                johnbattle.getActive1().setHP(johnbattle.getActive1().getTotalHP());
              }
              else {
                johnbattle.getActive1().setHP(johnbattle.getActive1().getHP() + 15);
              }
              enemyattack = johnbattle.getActive2().getEnemyAttack();
              System.out.println("\nYou used a POTION! Your opponent used " + enemyattack.toUpperCase() + "." );

            }
            else {
              yourattack = johnbattle.getActive1().getAttacks().get(Integer.parseInt(userinput) - 1);
              johnbattle.getActive1().attack(johnbattle.getActive2(), yourattack);
              if (johnbattle.getActive2().getHP() <= 0) {
                System.out.println("\nYou used " + yourattack.toUpperCase() + "!");
              }
              else {
                johnbattle.getActive2().attack(johnbattle.getActive1());
                enemyattack = johnbattle.getActive2().getEnemyAttack();
                System.out.println("\nYou used " + yourattack.toUpperCase() + "! Your opponent used " + enemyattack.toUpperCase() + "." );
              }
            }

            /*
            for (int i = 0; i < moves1.size(); i++) {
              if (moves1.get(i).equals(user_input.next())) {
                yourattack = moves1.get(i);
              }
              else {
                yourattack = "IMPOSSIBLE MOVE";
              }
            }
            */

            johnbattle.forceSwitch();
          }

          System.out.println("\n[MSG] The battle is over! " + johnbattle.getWinner().toUpperCase()  + " has won! Returning to the game...");
          tbattles = 1;

          //https://stackoverflow.com/questions/26388527/how-do-i-make-my-system-wait-5-seconds-before-continuing
          //Thread.sleep() to keep the game waiting for three seconds so the player can process the battle's result
          try {
            Thread.sleep(3000); //1000 milliseconds is one second. (3000ms is three seconds)
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
          }

          terminal.enterPrivateMode();
          //using completeRefresh() instead of refresh() as nothing new is actually getting placed - thus, force repaint of the screen is necessary
          screen.completeRefresh();
        }
      }

      if (isroute1b == true) {
        route1b(screen, route1b, pparty, potions);
        screen.refresh();

        if (x == 9 && tbattles == 1) {
          //creating new trainer battle, "Nina"
          ArrayList<Pokemon> ninaparty = new ArrayList<Pokemon>();

          Pokemon metapodnina = new Pokemon("Metapod");
          Pokemon staryunina = new Pokemon("Staryu");

          //ninaparty.add(Mewtwo);
          ninaparty.add(metapodnina);
          ninaparty.add(staryunina);

          Player Nina = new Trainer("NINA", ninaparty);
          Battle ninabattle = new Battle(player, Nina);

          Scanner user_input = new Scanner(System.in);
          String yourattack;
          String enemyattack;

          terminal.exitPrivateMode();

          System.out.println("\n---A BATTLE HAS BEGUN!---");
          System.out.println("\n[MSG] NINA: YOU'RE GONNA HAVE A BAD TIME!\n");

          System.out.println("Your enemy is " + Nina.getName() + "! Their first pokemon is " + ninabattle.getActive2().getName().toUpperCase() + ".");
          System.out.println("Your opponent's team is " + ninabattle.getTwo().getParty().toString() + "\n");

          System.out.println("Your team is " + ninabattle.getOne().getParty().toString());

          ArrayList<String> moves1 = ninabattle.getOne().getMon(0).getAttacks();

          System.out.println("Your Pokemon's moves are here: " + ninabattle.getActive1().getAttacks());
          // System.out.println("Your pokemon's moves are here: " + battle.getOne().getMon(4).attackstoString(1));

          while (!ninabattle.isOver()){

            System.out.println(ninabattle.getActive1().getName().toUpperCase() + " and " + ninabattle.getActive2().getName().toUpperCase() + " are battling!");
            System.out.println("\n" + ninabattle.getActive1() + "'s HP: " + ninabattle.getActive1().getHP() + "/" + ninabattle.getActive1().getTotalHP());
            System.out.println(ninabattle.getActive2() + "'s HP: " + ninabattle.getActive2().getHP() + "/" + ninabattle.getActive2().getTotalHP());
            System.out.println("Choose your move!\n");
            for (int i = 0; i < ninabattle.getActive1().getAttacks().size(); i++) {
              System.out.println("[" + (i + 1) + "] for " + ninabattle.getActive1().getAttacks().get(i).toUpperCase());
            }
            System.out.println("[H] to use a Potion.");
            System.out.println("");

            String userinput = user_input.next();

            if (userinput.equals("h") && potions >= 0) {
              ninabattle.getActive2().attack(ninabattle.getActive1());
              potions--;
              if (ninabattle.getActive1().getHP() + 15 > ninabattle.getActive1().getTotalHP()) {
                ninabattle.getActive1().setHP(ninabattle.getActive1().getTotalHP());
              }
              else {
                ninabattle.getActive1().setHP(ninabattle.getActive1().getHP() + 15);
              }
              enemyattack = ninabattle.getActive2().getEnemyAttack();
              System.out.println("\nYou used a POTION! Your opponent used " + enemyattack.toUpperCase() + "." );

            }
            else {
              //if Pokemon gets KO'd,
              yourattack = ninabattle.getActive1().getAttacks().get(Integer.parseInt(userinput) - 1);
              ninabattle.getActive1().attack(ninabattle.getActive2(), yourattack);
              if (ninabattle.getActive2().getHP() <= 0) {
                System.out.println("\nYou used " + yourattack.toUpperCase() + "!");
              }
              else {
                ninabattle.getActive2().attack(ninabattle.getActive1());
                enemyattack = ninabattle.getActive2().getEnemyAttack();
                System.out.println("\nYou used " + yourattack.toUpperCase() + "! Your opponent used " + enemyattack.toUpperCase() + "." );
              }
            }
            /*
            for (int i = 0; i < moves1.size(); i++) {
              if (moves1.get(i).equals(user_input.next())) {
                yourattack = moves1.get(i);
              }
              else {
                yourattack = "IMPOSSIBLE MOVE";
              }
            }
            */

            ninabattle.forceSwitch();
          }

          System.out.println("\n[MSG] The battle is over! " + ninabattle.getWinner().toUpperCase()  + " has won! Returning to the game...");
          tbattles++;

          //https://stackoverflow.com/questions/26388527/how-do-i-make-my-system-wait-5-seconds-before-continuing
          //Thread.sleep() to keep the game waiting for three seconds so the player can process the battle's result
          try {
            Thread.sleep(3000); //1000 milliseconds is one second. (3000ms is three seconds)
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
          }

          terminal.enterPrivateMode();
          //using completeRefresh() instead of refresh() as nothing new is actually getting placed - thus, force repaint of the screen is necessary
          screen.completeRefresh();
        }

        /* commenting out NPC functionality
        int npc1ticker = 0;
        if (x >= 30 && x <= 32 && y <= 9 && y >= 7 && npc1ticker == 0) {
          try {
            Thread.sleep(1000); //1000 milliseconds is one second.
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
          }
          Key npc1 = screen.readInput();
          if (npc1 != null) {
            if (npc1.getCharacter() == 'i') {
                screen.putString(43, 23, "Hey! Want a POTION? (Y/N)", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
                if (npc1.getCharacter() == 'y') {
                  potions++;
                  npc1ticker++;
                  screen.clear();
                  screen.completeRefresh();
                }
                else {
                  npc1ticker++;
                  screen.clear();
                  screen.completeRefresh();
                }
            }
          }
        }
        */

      }

      if (isroute1c == true) {
        route1c(screen, route1c, pparty, potions);
        screen.refresh();
      }

      //screen switchers - this creates the "scrolling effect"
      if (y == 23 && istown1 == true) {
        istown1 = false;
        isroute1a = true;
        y = 3;
      }

      if ((x == 5 || x == 6 ) && (y == 12) && istown1 == true ) {
        istown1 = false;
        choosepkmn = false;
        ishouse1 = true;
        y = 2;
        x = 2;
      }

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

  public static void playerinfo(Screen x, ArrayList<Pokemon> p, int q) {
    x.putString(43,1, "Welcome to Javamon!", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(43,2, "Created by Ahmed Sultan & Ali Taoube.", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(43,4, "[[[INSTRUCTIONS]]]", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(43,6, "Use directional keys to move.", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(43,7, "Use [I] to interact.", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);

    x.putString(43, 9, "--------------", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);

    x.putString(43,11, "PLAYER INFORMATION", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(43,13, "Party: " + p, Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);

    x.putString(43, 17, "Potions: " + q + " ", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);

    x.putString(43, 19, "--------------", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);

    x.putString(43,21, "STATUS", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
  }

  public static void choosepkmn(Screen x) {
    x.putString(2,1, "Choose your starter Pokemon!", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
    x.putString(2,3, "[S] for Squirtle.", Terminal.Color.BLUE, Terminal.Color.DEFAULT);
    x.putString(2,4, "[B] for Bulbasaur.", Terminal.Color.GREEN, Terminal.Color.DEFAULT);
    x.putString(2,5, "[C] for Charmander.", Terminal.Color.RED, Terminal.Color.DEFAULT);
  }

  public static void town1(Screen x, String[][] town, ArrayList<Pokemon> p, int q) {
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

        playerinfo(x, p, q);
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

  public static void route1a(Screen x, String[][] map, ArrayList<Pokemon> p, int q) {
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
    playerinfo(x, p, q);
  }

  public static void route1b(Screen x, String[][] map, ArrayList<Pokemon> p, int q) {
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
          case "?": x.putString(b,a, "?", Terminal.Color.YELLOW, Terminal.Color.RED);
          break;
          default: x.putString(b,a, " ", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT);
        }
      }
    }
    playerinfo(x, p, q);
  }

    public static void route1c(Screen x, String[][] map, ArrayList<Pokemon> p, int q) {
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

          playerinfo(x, p, q);
        }
      }
  }

  public static void house1(Screen x, String[][] map, ArrayList<Pokemon> p, int q) {
    for (int b = 0; b < map.length; b++) {
      for (int a = 0; a < map[b].length; a++) {
        switch(map[b][a]) {
          case "-": x.putString(b + 1, a + 1, " ", Terminal.Color.DEFAULT, Terminal.Color.BLACK);
          break;
          case "|": x.putString(b + 1, a + 1, " ", Terminal.Color.DEFAULT, Terminal.Color.BLACK);
          break;
          default: x.putString(b + 1, a + 1, " ", Terminal.Color.DEFAULT, Terminal.Color.YELLOW);
        }
      }
    }
    playerinfo(x, p, q);
  }

  public static void house2(Screen x, String[][] map, ArrayList<Pokemon> p, int q) {
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
    playerinfo(x, p, q);
  }


}
