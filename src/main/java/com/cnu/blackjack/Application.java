package com.cnu.blackjack;

import java.util.Scanner;

public class Application {
    public static  void  main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("덱의 갯수를 입력하세요\n");

        Game game = new Game(new Deck(sc.nextInt()));
            game.start();





    }
}
