package com.javatextrpg.main;

public class Enemy extends Character {

    //variable to store players current xp
    int playerXP;

    //enemy specific constructor
    public Enemy(String name, int playerXP) {
        super(name, (int) (Math.random()*playerXP + playerXP / 3 + 5), (int) (Math.random()*(playerXP / 4 + 2) + 1));

        //assign this variable
        this.playerXP = playerXP;
    }

    //enemy specific attack and defense calculations
    @Override
    public int attack() {
        return (int) (Math.random()*(playerXP/4 + 2) + xp/4 + 3);
    }

    @Override
    public int defend() {
        return (int) (Math.random()*(playerXP/4 + 2) + xp/4 + 3);
    }
}
