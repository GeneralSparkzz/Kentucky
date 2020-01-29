/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Coby
 */
public class CardTest {

    Card aCard = new Card();
    int[] suitCounts = {0, 0, 0, 0};
    int[] cardCounts = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    static JFrameCardGame theFrame;
    static DefaultListModel cards;
    static JLabel lblWin;
    static JButton btnNewDeal;
    static JButton btnHigh;
    static JButton btnLow;
    static Card lastCard, newCard;

    @BeforeClass
    public static void setupClass() {
        theFrame = new JFrameCardGame();
        btnNewDeal = theFrame.getBtnDeal();
        btnHigh = theFrame.getBtnHigh();
        lblWin = theFrame.getLblWin();
        lastCard = theFrame.getLastCard();
        newCard = theFrame.getNewCard();
    }

    @Test
    public void testHigh() {
        btnHigh.doClick();
        lastCard = theFrame.getLastCard();
        newCard = theFrame.getNewCard();
        String expected;
        if (lastCard.getValue() < newCard.getValue()) {
            expected = "Good";
        } else {
            expected = "Bad";
        }
        lblWin = theFrame.getLblWin();
        assertEquals("btnHigh test has failed.", expected, lblWin.getText().toString());
    }

    public CardTest() {
    }

    /**
     * Test of pickCard method, of class Card.
     */
    @Test
    public void testPickCard() {
        int[] correctCardCount = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        for (int x = 0; x < 52; x++) {
            aCard.pickCard();
            cardCounts[aCard.getValue() - 1]++;
        }
        assertArrayEquals("Card count is incorrect.", correctCardCount, cardCounts);
    }

    @Test
    public void testPickCardSuits() {
        int[] correctSuitCount = {13, 13, 13, 13};
        for (int x = 0; x < 52; x++) {
            aCard.pickCard();
            switch (aCard.getSuit()) {
                case "Hearts":
                    suitCounts[0]++;
                    break;
                case "Spades":
                    suitCounts[1]++;
                    break;
                case "Diamonds":
                    suitCounts[2]++;
                    break;
                case "Clubs":
                    suitCounts[3]++;
                    break;
            }
            assertArrayEquals("Testing suit count for pick card.", correctSuitCount, suitCounts);
        }
    }

    /**
     * Test of showCard method, of class Card.
     */
    @Test
    public void testShowCard() {
    }

    /**
     * Test of getSuit method, of class Card.
     */
    @Test
    public void testGetSuit() {
    }

    /**
     * Test of setSuit method, of class Card.
     */
    @Test
    public void testSetSuit() {
    }

    /**
     * Test of getValue method, of class Card.
     */
    @Test
    public void testGetValue_0args() {
    }

    /**
     * Test of getValue method, of class Card.
     */
    @Test
    public void testGetValue_String() {
    }

    /**
     * Test of setValue method, of class Card.
     */
    @Test
    public void testSetValue() {
    }

    /**
     * Test of toString method, of class Card.
     */
    @Test
    public void testToString() {
    }

    /**
     * Test of hashCode method, of class Card.
     */
    @Test
    public void testHashCode() {
    }

    /**
     * Test of equals method, of class Card.
     */
    @Test
    public void testEquals() {
        Card c1 = new Card();
        Card c2 = new Card();
        c1.setSuit("Spades");
        c2.setSuit("Spades");
        for (int x = 1; x < 14; x++) {
            c1.setValue(x);
            c2.setValue(x);
            assertTrue("Cards are the same.", c1.equals(c2));
        }
    }

}
