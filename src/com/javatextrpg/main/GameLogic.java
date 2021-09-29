package com.javatextrpg.main;

import java.util.Scanner;

public class GameLogic {
    static Scanner scanner = new Scanner(System.in);
    static Player player;

    public static boolean isRunning;

    //random encounters
    public static String[] encounters = {"Battle", "Battle", "Battle", "Rest", "Rest"};

    //enemy names
    public static String[] enemies = {"Ogre", "Ogre", "Goblin", "Goblin", "Skeleton Knight"};

    //Story elements
    public static int place = 0, act = 1;
    public static String[] places = {"Everylasting Mountains", "Haunted Landlines", "Castle of Evil Emperor", "Throne Room"};

    //method to get user input from console
    public static int readInt(String prompt, int userChoices) {
        int input;

        do {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                input = -1;
                System.out.println("Please enter an integer!");
            }
        } while (input < 1 || input > userChoices);

        return input;
    }

    //method to simulate clearing out the console
    public static void clearConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    //method to print a separator
    public static void printSeparator(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    //method to print a heading
    public static void printHeading(String title) {
        printSeparator(30);
        System.out.println(title);
        printSeparator(30);
    }

    //method to stop the game until user enters something
    public static void anyKeyToContinue() {
        System.out.println("\nEnter any key to continue...");
        scanner.nextLine();
    }

    //method to start the game
    public static void startGame() {
        boolean nameSet = false;
        String name;

        //print title screen
        clearConsole();
        printSeparator(40);
        printSeparator(30);
        System.out.println("AGE OF THE EVIL EMPEROR");
        System.out.println("TEXT RPG BY MAD SIGNTIST");
        printSeparator(30);
        printSeparator(40);
        anyKeyToContinue();

        //getting the player name
        do {
            clearConsole();
            printHeading("What's your name?");
            name = scanner.next();

            //asking player if name is correct
            clearConsole();
            printHeading("Your name is " + name + ".\nIs that correct?");
            System.out.println("(1) Yes!");
            System.out.println("(2) No, I want to change my name.");
            int input = readInt("->", 2);
            if (input == 1) {
                nameSet = true;
            }
        } while (!nameSet);

        //print story intro
        Story.printIntro();

        //create new player object with name
        player = new Player(name);

        //print ACT I - INTRO
        Story.printFirstActIntro();

        //setting isRunning to true, so the game loop can continue
        isRunning = true;

        //start main game loop
        gameLoop();
    }

    //method that changes the game's ACT based on player XP
    public static void checkAct() {
        //change act based on xp
        if (player.xp >= 10 && act == 1) {
            //increment act and place
            act = 2;
            place = 1;

            //story
            Story.printFirstActOutro();

            //let player "level up"
            player.chooseTrait();

            //story
            Story.printSecondActIntro();

            //assign new values to enemies
            enemies[0] = "Evil Mercenary";
            enemies[1] = "Goblin";
            enemies[2] = "Wolf Pack";
            enemies[3] = "Henchman";
            enemies[4] = "Wolf Pack";

            //assign new values to encounters
            encounters[0] = "Battle";
            encounters[1] = "Battle";
            encounters[2] = "Battle";
            encounters[3] = "Rest";
            encounters[4] = "Shop";

        } else if (player.xp >= 50 && act == 2) {
            //increment act and place
            act = 3;
            place = 2;

            //story
            Story.printSecondActOutro();

            //let player "level up"
            player.chooseTrait();

            //story
            Story.printThirdActIntro();

            //assign new values to enemies
            enemies[0] = "Evil Mercenary";
            enemies[1] = "Evil Mercenary";
            enemies[2] = "Zombie";
            enemies[3] = "Henchman";
            enemies[4] = "Zombie";

            //assign new values to encounters
            encounters[0] = "Battle";
            encounters[1] = "Battle";
            encounters[2] = "Battle";
            encounters[3] = "Battle";
            encounters[4] = "Shop";

            //fully heal player
//            player.hp = player.maxHP;

        } else if (player.xp >= 100 && act == 3) {
            //increment act and place
            act = 4;
            place = 3;

            //story
            Story.printThirdActOutro();

            //let player "level up"
            player.chooseTrait();

            //story
            Story.printFourthActIntro();

            //fully heal player
//            player.hp = player.maxHP;

            //calling the final battle
            finalBattle();
        }
    }

    //method to calculate a random encounter
    public static void randomEncounter() {
        //random number between 0 and length of the encounters
        int encounter = (int) (Math.random()* encounters.length);

        //calling the respective methods
        if (encounters[encounter].equals("Battle")) {
            randomBattle();
        } else if (encounters[encounter].equals("Rest")) {
            takeRest();
        } else {
            shop();
        }
    }

    //method to continue the journey
    public static void continueJourney() {
        //check if ACT must be increased
        checkAct();

        //check if game is in ACT IV
        if (act != 4) {
            randomEncounter();
        }
    }

    //printing out Player Info
    public static void characterInfo() {
        clearConsole();
        printHeading("CHARACTER INFO");
        System.out.println(player.name + "\tHP:" + player.hp + "/" + player.maxHP);
        printSeparator(20);
        //player xp and gold
        System.out.println("XP: " + player.xp + "\tGold: " + player.gold);
        printSeparator(20);
        //# of pots
        System.out.println("Potions: " + player.pots);
        printSeparator(20);

        //printing the chosen traits
        if (player.numAtkUpgrades > 0) {
            System.out.println("Offensive trait: " + player.atkUpgrades[player.numAtkUpgrades - 1]);
            printSeparator(20);
        }
        if (player.numDefUpgrades > 0) {
            System.out.println("Defensive trait: " + player.defUpgrades[player.numDefUpgrades - 1]);
        }
        anyKeyToContinue();
    }

    //shopping from traveler
    public static void shop() {
        clearConsole();
        printHeading("You meet a mysterious stranger. \nHe offers you something:");
        int price = (int) (Math.random()* (10 + player.pots*3) + 10 + player.pots);
        System.out.println("- Magic Potion: " + price + " gold.");
        printSeparator(20);

        //ask if play wants to buy one
        System.out.println("Do you want to buy one?\n(1) Yes\n(2) No thanks.");
        int input = readInt("->", 2);
        //check answer
        if (input == 1) {
            clearConsole();
            //check gold amount
            if (player.gold >= price) {
                printHeading("You bought a magical potion for " + price + " gold.");
                player.pots++;
                player.gold -= price;
                anyKeyToContinue();
            } else {
                printHeading("You don't have enough gold to buy this...");
                anyKeyToContinue();
            }
        }
    }

    //taking a rest
    public static void takeRest() {
        clearConsole();
        if (player.restsLeft >= 1) {
            printHeading("Do you want to take a rest? (" + player.restsLeft + " rest(s) left).");
            //ask if play wants to rest
            System.out.println("Do you want to rest now?\n(1) Yes\n(2) No, not now.");
            int input = readInt("->", 2);
            //check answer
            if (input == 1) {
                clearConsole();
                if (player.hp < player.maxHP) {
                    int hpRestored = (int) (Math.random() * (player.xp / 4 + 1) + 10);
                    player.hp += hpRestored;
                    if (player.hp > player.maxHP) {
                        player.hp = player.maxHP;
                    }
                    System.out.println("You took a rest and restored up to " + hpRestored + " health.");
                    System.out.println("You are now at " + player.hp + "/" + player.maxHP);
                    player.restsLeft--;
                    anyKeyToContinue();
                } else {
                    System.out.println("You are at full health. You don't need to rest right now!");
                    anyKeyToContinue();
                }
            } else {
                anyKeyToContinue();
            }
        }
    }

    public static void randomBattle() {
        clearConsole();
        printHeading("You've encountered an enemy! You'll have to fight it!");
        anyKeyToContinue();

        //create enemy with random name
        battle(new Enemy(enemies[(int) (Math.random()* enemies.length)], player.xp));
    }

    //main BATTLE method
    public static void battle(Enemy enemy) {
        //main battle loop
        while (true) {
            clearConsole();
            printHeading(enemy.name + "\nHP: " + enemy.hp + "/" + enemy.maxHP);
            printHeading(player.name + "\nHP: " + player.hp + "/" + player.maxHP);
            System.out.println("Choose an action:");
            printSeparator(20);
            System.out.println("(1) Fight\n(2) Use Potion\n(3) Run Away");
            int input = readInt("->", 3);

            //react to player choice
            if (input == 1) {
                //FIGHT
                //calculate dmg and dmgTook (dmg enemy deals to player)
                int dmg = player.attack() - enemy.defend();
                int dmgTook = enemy.attack() - player.defend();
                if (dmgTook < 0) {
                    dmg -= dmgTook/2;
                    dmgTook = 0;
                }
                if (dmg < 0) {
                    dmg = 0;
                }

                //deal damage to both parties
                player.hp -= dmgTook;
                enemy.hp -= dmg;

                //print info of battle round
                clearConsole();
                printHeading("BATTLE");
                System.out.println("You dealt " + dmg + " damage to the " + enemy.name + ".");
                printSeparator(15);
                System.out.println("The " + enemy.name + " dealt " + dmgTook + " damage to you.");
                anyKeyToContinue();

                //chick if player died
                if (player.hp <= 0) {
                    playerDied(); //method to end the game
                    break;
                } else if (enemy.hp <= 0) {
                    //tell player they won
                    clearConsole();
                    printHeading("You defeated the " + enemy.name + "!");

                    //increase xp
                    player.xp += enemy.xp;
                    System.out.println("You earned " + enemy.xp + " XP!");

                    //random drops
                    boolean addRest = (Math.random()*5 + 1 <= 2.25);
                    int goldEarned = (int) (Math.random()*enemy.xp);
                    if (addRest) {
                        player.restsLeft++;
                        System.out.println("You earned the chance to get an additional rest!");
                    }
                    if (goldEarned > 0) {
                        player.gold += goldEarned;
                        System.out.println("You collected " + goldEarned + " gold from the " + enemy.name + "'s corpse!");
                    }
                    anyKeyToContinue();
                    break;
                }

            } else if (input == 2) {
                //USE POTION
                clearConsole();
                if (player.pots > 0 && player.hp < player.maxHP) {
                    //player takes potion
                    System.out.println("Do you want to drink a potion? (" + player.pots + " left).");
                    System.out.println("(1) Yes\n(2) No, maybe later");
                    input = readInt("->", 2);
                    if (input == 1) {
                        //player took potion
                        player.hp = player.maxHP;
                        clearConsole();
                        printHeading("You drank a magic potion. It restored your health back to " + player.maxHP);
                        player.pots--;
                        anyKeyToContinue();
                    }
                } else {
                    //player cannot take potion
                    printHeading("You don't have any potions or you're at full health.");
                    anyKeyToContinue();
                }

            } else {
                //RUN AWAY
                clearConsole();

                //check player is at final boss
                if (act != 4) {
                    //chance of 35% to escape
                    if (Math.random()*10 + 1 <= 3.5) {
                        printHeading("You ran away from the " + enemy.name + "!");
                        anyKeyToContinue();
                        break;
                    } else {
                        printHeading("You didn't manage to escape.");
                        int dmgTook = enemy.attack();
                        System.out.println("In your hurry you took " + dmgTook + " damage!");
                        anyKeyToContinue();

                        //check if player died
                        if (player.hp <= 0) {
                            playerDied(); //method to end the game
                            break;
                        }
                    }
                } else {
                    printHeading("YOU CANNOT ESCAPE THE EVIL EMPEROR!!!");
                    anyKeyToContinue();
                }

            }
        }
    }

    //print main menu
    public static void printMenu() {
        clearConsole();
        printHeading(places[place]);
        System.out.println("Choose an action:");
        printSeparator(20);
        System.out.println("(1) Continue on your journey");
        System.out.println("(2) Character Info");
        System.out.println("(3) Exit Game");
    }

    //the final battle of the game
    public static void finalBattle() {
        battle(new Enemy("THE EVIL EMPEROR", 150));

        //print ending
        Story.printEnd(player);
        isRunning = false;
    }

    //method that gets called when player is dead
    public static void playerDied() {
        clearConsole();
        printHeading("You died...");
        printHeading("You earned " + player.xp + " XP on your journey. Try to earn more next time!");
        System.out.println("Thank you for playing my game. I hope you enjoyed it");
        isRunning = false;
        System.exit(0);
    }

    //main game loop
    public static void gameLoop() {
        while (isRunning) {
            printMenu();
            int input = readInt("->", 3);
            if (input == 1) {
                continueJourney();
            } else if (input == 2) {
                characterInfo();
            } else {
                System.out.println("\nGoodbye!");
                isRunning = false;
            }
        }
    }

}
