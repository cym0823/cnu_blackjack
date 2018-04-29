package com.cnu.blackjack;

import java.util.List;
import java.util.Map;

public class Evaluator {

    private Map<String, Player> playerMap;
    private Dealer dealer;

    public Evaluator(Map<String, Player> playerMap) {
        this.playerMap = playerMap;
        dealer = new Dealer();
        dealCardToPlayers();
    }

    public void start() {
        int deal_score = 0;

        for(int i= 0; i < playerMap.size(); i++) {
            Player p = playerMap.get("p"+i);
            Hand pHand = p.getHand();
            List<Card> pList = pHand.getCardList();

            int total  =0;
            for (int j = 0; j < pList.size(); j++) {
                total += pList.get(j).getRank();
            }//내이

            if(total <=16){
                p.hitCard();
                total = 0;
                for (int j = 0; j < pList.size(); j++) {
                    total += pList.get(j).getRank();
                }
            }

            deal_score = dealer.getDealerScore();
            if(total == 21){
                System.out.println("player "+i+"가 이겼습니다 !");
                p.setBalance(p.getBalance()+2*p.getCurrentBet());
            }
            else if(total > 21) System.out.println("player "+i+"가 졌습니다 !");
            else {
                if (deal_score < total){
                    System.out.println("player "+i+"가 이겼습니다 !");
                    p.setBalance(p.getBalance()+2*p.getCurrentBet());
                }
                else if (total == deal_score) System.out.println("player "+i+"가 졌습니다 !");
                else System.out.println("player "+i+"가 졌습니다 !");
            }
        }
        //  플레이어가 16이하 일때 한장더
        //
        //  승패 판정
    }

    private void dealCardToPlayers() {
        playerMap.forEach((name, player) -> {
            player.hitCard();
            player.hitCard();
        });
    }
}
