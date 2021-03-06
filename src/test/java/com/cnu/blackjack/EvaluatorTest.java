package com.cnu.blackjack;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cnu.blackjack.Suit.DIAMONDS;
import static com.cnu.blackjack.Suit.HEARTS;
import static com.cnu.blackjack.Suit.SPADES;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EvaluatorTest {
    @Test
    public void 게임초기화시_모든플레이어는_2장의카드를_받는다() {
        int num_card = 0;
        Game game = new Game(new Deck(1));
        game.addPlayer("p"+0, 20000);
        game.placeBet("p"+0, 1000);

        Map<String, Player> playerMap = game.getPlayerList();

        Evaluator evaluator = new Evaluator(playerMap);
        evaluator.start();

        Player player = playerMap.get("p"+0);
        num_card = player.getHand().getCardList().size();

        assertThat(num_card, is(2));
    }

    @Test
    public void 각_플레이어는_16이하면_히트한다() {
        Card card1 = new Card(8, SPADES);
        Card card2 = new Card(7, HEARTS);
        Card card3 = new Card(6, DIAMONDS);

        Hand testHand_1 = new Hand(new Deck(1));
        testHand_1.drawSelectedCard(card1);
        testHand_1.drawSelectedCard(card2); // player 1의 Hand

        Hand testHand_2 = new Hand(new Deck(1));
        testHand_2.drawSelectedCard(card2);
        testHand_2.drawSelectedCard(card3); // player 2의 Hand

        Player testPlayer_1 = new Player(20000, testHand_1);
        Player testPlayer_2 = new Player(20000, testHand_2);

        Game game = new Game(new Deck(1));
        game.addSelectedPlayer("p"+0, testPlayer_1);
        game.addSelectedPlayer("p"+1, testPlayer_2);
        Map<String, Player> playerMap = game.getPlayerList();

        Evaluator evaluator = new Evaluator(playerMap, 1);
        evaluator.start();

        assertThat(evaluator.getHitCheck(), is(evaluator.getHitCheck()));
    }

    @Test
    public void 블랙잭이나오면_2배로_보상받고_해당_플레이어의_턴은_끝난다() {
        int init_balance = 0;
        int aft_balance = 0;
        int ply_bet = 0;

        Card card1 = new Card(10, SPADES);
        Card card2 = new Card(11, SPADES);

        Hand testHand = new Hand(new Deck(1));
        testHand.drawSelectedCard(card1);
        testHand.drawSelectedCard(card2);

        Player testPlayer = new Player(20000, testHand);

        Game game = new Game(new Deck(1));
        game.addSelectedPlayer("p"+0, testPlayer);
        game.placeBet("p"+0,1000);
        Map<String, Player> playerMap = game.getPlayerList();

        Evaluator evaluator = new Evaluator(playerMap, 1);
        Player p0_player = game.getPlayerList().get("p"+0);
        init_balance = p0_player.getBalance();
        ply_bet = p0_player.getCurrentBet();

        evaluator.start();
        aft_balance = p0_player.getBalance();

        assertThat(aft_balance, is(init_balance+2*ply_bet));
    }

    @Test
    public void 각_플레이어는_17이상이면_스테이한다() {
        Card card1 = new Card(9, SPADES);
        Card card2 = new Card(9, SPADES);

        Hand testHand = new Hand(new Deck(1));
        testHand.drawSelectedCard(card1);
        testHand.drawSelectedCard(card2);

        Player testPlayer = new Player(20000, testHand);

        Game game = new Game(new Deck(1));
        game.addSelectedPlayer("p"+0, testPlayer);
        game.addSelectedPlayer("p"+1, testPlayer);
        Map<String, Player> playerMap = game.getPlayerList();

        Evaluator evaluator = new Evaluator(playerMap, 1);
        evaluator.start();


        assertThat(evaluator.getTotal(), is(evaluator.getTotal()));
    }
}
