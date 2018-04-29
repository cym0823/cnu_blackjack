package com.cnu.blackjack;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Evaluator {

    private Map<String, Player> playerMap;
    private Dealer dealer;
    private int total = 0;
    private int hitCheck = 0;

    public Evaluator(Map<String, Player> playerMap) {
        this.playerMap = playerMap;
        dealer = new Dealer();
        dealCardToPlayers();
    }

    public void start() {
        int deal_score = 0;

        System.out.println("\n\n------- Result -------");
        for(int i= 0; i < playerMap.size(); i++) {
            Player p = playerMap.get("p" + i);
            Hand pHand = p.getHand();
            List<Card> pList = pHand.getCardList();

            total = 0;
            for (int j = 0; j < pList.size(); j++) {
                total += pList.get(j).getRank();
            }

            if (total <= 16) {
                p.hitCard();
                hitCheck = 1;
                for (int j = 0; j < pList.size(); j++) {
                    total += pList.get(j).getRank();
                }
            }

            deal_score = dealer.getDealerScore();

            if (total == 21) {
                System.out.println(">> player " + i + "가 이겼습니다 !");
                p.setBalance(p.getBalance() + 2 * p.getCurrentBet());
            } else if (total > 21) System.out.println(">> player " + i + "가 졌습니다 !");
            else {
                if (deal_score < total) {
                    System.out.println(">> player " + i + "가 이겼습니다 !");
                    p.setBalance(p.getBalance() + 2 * p.getCurrentBet());
                } else if (total == deal_score) System.out.println(">> player " + i + "가 졌습니다 !");
                else System.out.println(">> player " + i + "가 졌습니다 !");
            }
        }
    }

    private void dealCardToPlayers() {
        playerMap.forEach((name, player) -> {
            player.hitCard();
            player.hitCard();
        });
    }
}
