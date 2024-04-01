/* @formatter:off
 *
 * Dave Rosenberg
 * Comp 2000 - Data Structures
 * Lab: List application - card game
 * Spring, 2024
 * 
 * Usage restrictions:
 * 
 * You may use this code for exploration, experimentation, and furthering your
 * learning for this course. You may not use this code for any other
 * assignments, in my course or elsewhere, without explicit permission, in
 * advance, from myself (and the instructor of any other course).
 * 
 * Further, you may not post (including in a public repository such as on github)
 * nor otherwise share this code with anyone other than current students in my 
 * sections of this course. Violation of these usage restrictions will be considered 
 * a violation of the Wentworth Institute of Technology Academic Honesty Policy.
 *
 * Do not remove this notice.
 *
 * @formatter:on
 */


package edu.wit.scds.ds.list.app;

import java.util.Collections;
import java.util.Stack;
import java.util.random.*;

/**
 * Representation of a deck of cards
 *
 * @author Maxwell Aiello
 *
 * @version 1.0.0 2024-03-26 Initial implementation
 */
public class Deck extends Pile
	{
	/**
	 * State variables
	 */
	Stack<Card> cards = new Stack<Card>();
	private int cardTotal = 0;


	
	/**
	 * Methods
	 */
	// Constructor
	public Deck()
		{
		initializeDeck();
		
		} // end no param constructor
	
	
	// Initialize deck
	public void initializeDeck()
		{
		this.cardTotal = 0;
		//Loop over every possible card Suit
		for ( int i = 0 ; i < Suit.values().length - 1; i++)
			{
			//Loop over every possible card Rank
			for ( int j = 0 ; j < Rank.values().length - 1; j++)
				{
				//Generate new card with current Suit + Rank
				Card currentCard = new Card(Suit.values()[i], Rank.values()[j], true);
				//Add card to array
				cards.add(currentCard);
				//Increment cardTotal
				cardTotal++;
				}
			}
		//Cards are in sequential order, shuffle them
		this.cards = shuffleCards(this.cards);
		} // end initializeDeck()
	
	
	public Stack<Card> shuffleCards(Stack<Card> cardStack)
		{
		//Shuffle cards in stack using Collections.shuffle
		Collections.shuffle(cardStack);
		//Return shuffled array
		return cardStack;
		
		} // end shuffleCards()
	
	
	// Take top card from deck
	public Card deal()
		{
		checkIntegrity();
		
		//Return top of stack
		return this.cards.pop();
		} // end deal()
	
	
	// Check there is a card to draw
	public boolean checkIntegrity()
		{
		// If no cards -> bad integrity
		if ( this.cardTotal > 0 )
			{
			return false;
			}
		
		return true;
		} // end checkIntegrity()
	
	
	// Resets the deck to null
	public boolean clear()
		{
		cards = null;
		return true;
		} // end clear()
	
	
	// Clear existing deck and generate new deck
	public boolean resetDeck()
		{
		clear();
		initializeDeck();
		return checkIntegrity();
		} // end resetDeck()
	
	
	//Determine if the card stack is empty
	public boolean isEmpty() {
		return (this.cardTotal <= 0);
	} // end isEmpty()
	
	
	/**
     * (optional) test driver
     * 
     * @param args
     *     -unused-
     */
	public static void main( String[] args )
		{
        // OPTIONAL for testing and debugging

		}	// end main()

	}	// end class Deck