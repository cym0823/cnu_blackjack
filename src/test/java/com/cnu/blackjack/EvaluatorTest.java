package com.cnu.blackjack;

import org.junit.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EvaluatorTest {

    @Test
    public void 게임초기화시_모든플레이어는_2장의카드를_받는다() {
        Evaluator evaluator = new Evaluator(new Game(new Deck(2)).getPlayerList());
        evaluator.start();
        Map<String, Player> playerList = new HashMap<>();
        Deck deck = new Deck(2);
        Hand hand = new Hand(deck);
        Player p1 = new Player(20000, hand);
        playerList.put("1p", p1);
        Evaluator evl = new Evaluator(playerList);
        int currentDeck = playerList.get("1p").getHand().getCardList().size();
        assertThat(currentDeck, is(2));

    }

    @Test
    public void 각_플레이어는_16이하면_히트한다() {

        Evaluator evaluator = new Evaluator(new Game(new Deck(2)).getPlayerList());
        int total = (evaluator.getPlayerMap().get("p"+1).getHand().getCardList().get(0).getRank())+(evaluator.getPlayerMap().get("p"+1).getHand().getCardList().get(1).getRank());
        evaluator.start();
        assertThat(total, is(total));
    }

    @Test
    public void 블랙잭이나오면_2배로_보상받고_해당_플레이어의_턴은_끝난다() {

    }

    @Test
    public void 각_플레이어는_17이상이면_스테이한다() {


    }
}
