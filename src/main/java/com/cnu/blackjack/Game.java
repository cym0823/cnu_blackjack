package com.cnu.blackjack;

import com.cnu.blackjack.exceptions.DuplicatePlayerException;
import com.cnu.blackjack.exceptions.NotEveyonePlacedBetException;
import com.cnu.blackjack.exceptions.PlayerDoesNotExistException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Data
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

    public Map<String, Player> getPlayerList() {
        return playerList;
    }

    public void start() {
        //1.플레이어 추가 맴엡 플레이어리스트에
        Scanner sc = new Scanner(System.in);
        System.out.println("몇명에서 플레이:");
        int playerNumber = sc.nextInt() ;

        for (int i = 0; i < playerNumber; i++){
            addPlayer("p"+i, 20000 );
        }

        //2.각 플레이어 베팅
        for (int i = 0; i < playerNumber; i++){
            System.out.print("배팅할 금액을 입력하시오");
            placeBet("p"+i, sc.nextInt());
        }

        //3. 배팅안하면에러

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
