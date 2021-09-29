package com.javatextrpg.main;

public class Story {

    public static void printIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("STORY");
        GameLogic.printSeparator(30);
        System.out.println("You are the last man standing after your village got raided by the henchmen of the evil emperor");
        System.out.println("Every single on of your friends, family, and neighbors got murdered. You are standing in the burning ruins of this once great village");
        System.out.println("All you want now is revenge, so you begin planning your journey to defeat the evil emperor and free the lands!");
        GameLogic.anyKeyToContinue();
    }

    public static void printFirstActIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT I - INTRO");
        GameLogic.printSeparator(30);
        System.out.println("As you begin your journey you start traveling through the nearby woods to reach the everlasting mountains");
        System.out.println("\nAfter a long day of walking through the woods, you finally reach the everlasting mountains.");
        System.out.println("You don't care about the dangers ahead, your only concern is defeating the evil emperor.");
        GameLogic.anyKeyToContinue();
    }

    public static void printFirstActOutro() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT I - OUTRO");
        GameLogic.printSeparator(30);
        System.out.println("You did it! You crossed the everlasting mountains and you're still alive!");
        System.out.println("As you climb down the last hill, you're more than happy to feel hard ground underneath your feet again.");
        System.out.println("\nYou feel empowered and the experience you gained allows you to learn another trait!");
        GameLogic.anyKeyToContinue();
    }

    public static void printSecondActIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT II - INTRO");
        GameLogic.printSeparator(30);
        System.out.println("You begin traveling across the landlines of this once well populated countryside");
        System.out.println("You collected some gold from the monsters you killed along the way.");
        System.out.println("Luckily you know that every once in a while a traveling trader comes across these landlines");
        System.out.println("You know exactly where the castle of the evil emperor is, but you have to cross these haunted landlines first...");
        GameLogic.anyKeyToContinue();
    }

    public static void printSecondActOutro() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT II - OUTRO");
        GameLogic.printSeparator(30);
        System.out.println("You managed to cross these haunted landlines and you're still alice!");
        System.out.println("You are just a few hours away from your final destination; the caste of the evil emperor!");
        System.out.println("You know that you probably can't rest there, so you make a last break to restore some health");
        System.out.println("\nYou feel empowered and the experience you gained allows you to learn another trait!");
        GameLogic.anyKeyToContinue();
    }

    public static void printThirdActIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT III - INTRO");
        GameLogic.printSeparator(30);
        System.out.println("You see the huge black castle in front of you.");
        System.out.println("As you stand in front of the gates, you know there's no going back");
        System.out.println("You're disguised as a mercenary and enter the castle. You don't know how much time you have left before someone discovers you.");
        GameLogic.anyKeyToContinue();
    }

    public static void printThirdActOutro() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT III - OUTRO");
        GameLogic.printSeparator(30);
        System.out.println("You came so far. You defeated all of the evil emperor's minions.");
        System.out.println("As you stand in front of the door to the throne room, you know there's no going back");
        System.out.println("You recall your lost power and restore your health");
        System.out.println("You get a chance to learn a last trait before entering the throne room.");
        GameLogic.anyKeyToContinue();
    }

    public static void printFourthActIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("ACT IV - INTRO");
        GameLogic.printSeparator(30);
        System.out.println("You enter the throne room of the evil emperor.");
        System.out.println("He stares you dead in the eyes. You feel the darkness around you.");
        System.out.println("He takes out the unholy sword of darkness, the mightiest weapon known to man.");
        System.out.println("All you can do is fight for your life and pray to come out as the winner...");
        GameLogic.anyKeyToContinue();
    }

    public static void printEnd(Player player) {
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("Congratulations, " + player.name + "! You defeated the evil emperor and saved the world!");
        GameLogic.printSeparator(30);
        System.out.println("This game was developed by Mad Signtist. Designed by codestudent.net");
        System.out.println("Hope you enjoyed it!");
    }
}
