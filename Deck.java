import greenfoot.*;
import java.util.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Deck represents card deck. It maintain 52 cards (without Jokers).
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class Deck extends Actor
{
    // Instance variables.
    private final String[] CARD_NAME = {"2", "3", "4", "5", "6", "7", 
        "8", "9", "10", "jack", "queen", "king", "ace"};
    private final String[] CARD_TYPE = {"clubs", "diamonds", "spades", "hearts"};
    private ArrayList<Card> deck;
    
    /**
     * Constructors for objects of class Deck.
     * 
     */
    public Deck()
    {
        this.deck = new ArrayList<Card>();
        shuffle();
    }
    
    /**
     * Method to shuffle all 52 cards in the deck.
     */
    public void shuffle()
    {
        int typeCount = 0;
        int numberCount = 0;
        this.deck = new ArrayList<Card>();
        // Add 52 cards to the deck (13 cards from each type)
        for (int i = 0; i < this.CARD_TYPE.length; i++)
        {
            for (int j = 0; j < this.CARD_NAME.length; j++)
            {
                Card card = new Card(this.CARD_TYPE[i], this.CARD_NAME[j]);
                this.deck.add(card);
            }
        }
        // Shuffle the deck by iterating through the deck from end to start.
        // For each iteration, swap it with a random card before it.
        for(int i = this.deck.size() - 1; i >= 2; i--)
        {
            Card card = this.deck.get(i);
            int randomIndex = Greenfoot.getRandomNumber(i - 1);
            Card randomCard = this.deck.get(randomIndex);
            this.deck.set(i, randomCard);
            this.deck.set(randomIndex, card);
        }
        Card card = this.deck.get(0);
        this.deck.set(0, deck.get(1));
        this.deck.set(1, card);
    }
    
    // Randomly get a card from the deck.
    public Card getCard()
    {
        int index = Greenfoot.getRandomNumber( this.deck.size() ); 
        Card card = this.deck.get(index); 
        this.deck.remove(index);   
        return card;
    }
}
     
    

