package com.cnu.blackjack;

import java.util.Scanner;

public class Application {
    public static  void  main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("< BlackJack Game >");
        System.out.println("------- Start -------");
        System.out.print(">> 덱의 갯수를 입력하세요.: ");

        Game game = new Game(new Deck(sc.nextInt()));
        game.start();
    }
}
