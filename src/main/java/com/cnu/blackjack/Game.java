package com.cnu.blackjack;

import com.cnu.blackjack.exceptions.DuplicatePlayerException;
import com.cnu.blackjack.exceptions.NotEveyonePlacedBetException;
import com.cnu.blackjack.exceptions.PlayerDoesNotExistException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {

    private Map<String, Player> playerList = new HashMap<>();
    private Deck deck;


    public Game(Deck deck) {
        this.deck = deck;
    }

    public void addPlayer(String playerName, int seedMoney) {
        Player player = new Player(seedMoney, new Hand(deck));
        if (playerList.get(playerName) != null) {
            throw new DuplicatePlayerException();
        }
        playerList.put(playerName, player);
    }

    public void addSelectedPlayer(String playerName, Player player) {
        if (playerList.get(playerName) != null) {
            throw new DuplicatePlayerException();
        }
        playerList.put(playerName, player);
    }

    public Map<String, Player> getPlayerList() {
        return playerList;
    }

    public void start() {

        Scanner sc = new Scanner(System.in);
        System.out.print(">> 몇 명에서 플레이합니까?: ");
        int playerNumber = sc.nextInt() ;

        for (int i = 0; i < playerNumber; i++){
            addPlayer("p"+i, 20000 );
        }

        for (int i = 0; i < playerNumber; i++){
            System.out.print(">> 배팅할 금액을 입력하시오.: ");
            placeBet("p"+i, sc.nextInt());
        }

        playerList.forEach((name, player) -> {
            if (player.getCurrentBet() == 0) {
                throw new NotEveyonePlacedBetException();
            }


        });

        Evaluator evaluator = new Evaluator(playerList);
        evaluator.start();
    }

    public void placeBet(String name, int bet) {
        Player player = playerList.get(name);
        if (player == null) {
            throw new PlayerDoesNotExistException();
        }
        player.placeBet(bet);
    }
}
