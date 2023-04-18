import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlackjackGameTest {

    @Test
    public void testGoodCase() {
        Blackjack blackjack = new Blackjack();
        blackjack.addCard(new Card(Card.Rank.ACE, Card.Suit.SPADES));
        blackjack.addCard(new Card(Card.Rank.TEN, Card.Suit.HEARTS));

        assertTrue(blackjack.isBlackjack());
        assertTrue(blackjack.isWinner());
    }

    @Test
    public void testBadCase() {
        Blackjack blackjack = new Blackjack();
        blackjack.addCard(new Card(Card.Rank.KING, Card.Suit.CLUBS));
        blackjack.addCard(new Card(Card.Rank.QUEEN, Card.Suit.SPADES));
        blackjack.addCard(new Card(Card.Rank.JACK, Card.Suit.HEARTS));

        assertTrue(blackjack.isBust());
        assertTrue(blackjack.isLoser());
    }

    @Test
    public void testBoundaryCase() {
        Blackjack blackjack = new Blackjack();
        blackjack.addCard(new Card(Card.Rank.ACE, Card.Suit.SPADES));
        blackjack.addCard(new Card(Card.Rank.THREE, Card.Suit.CLUBS));
        blackjack.addCard(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));

        assertEquals(21, blackjack.getHandValue());
    }

    @Test
    public void testHandValueCalculation() {
        Blackjack blackjack = new Blackjack();
        blackjack.addCard(new Card(Card.Rank.TWO, Card.Suit.SPADES));
        blackjack.addCard(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));

        assertEquals(7, blackjack.getHandValue());

        blackjack.addCard(new Card(Card.Rank.QUEEN, Card.Suit.CLUBS));

        assertEquals(17, blackjack.getHandValue());

        blackjack.addCard(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));

        assertEquals(18, blackjack.getHandValue());

        blackjack.addCard(new Card(Card.Rank.NINE, Card.Suit.SPADES));

        assertEquals(17, blackjack.getHandValue());

        blackjack.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));

        assertEquals(18, blackjack.getHandValue());
    }

    @Test
    public void testBlackjackDetection() {
        Blackjack blackjack = new Blackjack();
        blackjack.addCard(new Card(Card.Rank.ACE, Card.Suit.SPADES));
        blackjack.addCard(new Card(Card.Rank.TEN, Card.Suit.HEARTS));

        assertTrue(blackjack.isBlackjack());

        blackjack.addCard(new Card(Card.Rank.FIVE, Card.Suit.CLUBS));

        assertFalse(blackjack.isBlackjack());
    }

    @Test
    public void testBustDetection() {
        Blackjack blackjack = new Blackjack();
        blackjack.addCard(new Card(Card.Rank.KING, Card.Suit.HEARTS));
        blackjack.addCard(new Card(Card.Rank.QUEEN, Card.Suit.SPADES));
        blackjack.addCard(new Card(Card.Rank.JACK, Card.Suit.CLUBS));

        assertTrue(blackjack.isBust());

        blackjack.addCard(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));

        assertFalse(blackjack.isBust());
    }

    @Test
    public void testWinDetection() {
        Blackjack playerHand = new Blackjack();
        playerHand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));
        playerHand.addCard(new Card(Card.Rank.NINE, Card.Suit.CLUBS));

        Blackjack dealerHand = new Blackjack();
        dealerHand.addCard(new Card(Card.Rank.SIX, Card.Suit.SPADES));
        dealerHand.addCard(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS));

        assertTrue(playerHand.isWinner(dealerHand));
    }

    @Test
    public void testLossDetection() {
        Blackjack playerHand = new Blackjack();
        playerHand.addCard(new Card(Card.Rank.FIVE, Card.Suit.CLUBS));
        playerHand.addCard(new Card(Card.Rank.JACK, Card.Suit.HEARTS));

        Blackjack dealerHand = new Blackjack();
        dealerHand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS));
        dealerHand.addCard(new Card(Card.Rank.QUEEN, Card.Suit.SPADES));

        assertTrue(playerHand.isLoser(dealerHand));
    }

    @Test
    public void testPushDetection() {
        Blackjack playerHand = new Blackjack();
        playerHand.addCard(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        playerHand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.SPADES));

        Blackjack dealerHand = new Blackjack();
        dealerHand.addCard(new Card(Card.Rank.TEN, Card.Suit.CLUBS));
        dealerHand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS));

        assertTrue(playerHand.isPush(dealerHand));
    }
}
