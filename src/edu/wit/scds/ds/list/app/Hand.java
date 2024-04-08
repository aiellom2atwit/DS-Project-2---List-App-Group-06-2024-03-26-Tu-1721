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


package edu.wit.scds.ds.list.app ;

import java.util.ArrayList;

/**
 * Representation of a hand of cards
 *
 * @author Jordan Garcia
 *
 * @version 1.0.0 2024-03-26 Initial implementation
 */
public class Hand extends Pile
    {
    //ArrayList to store cards in hand
	ArrayList< Card > cards ;
	public int handLength = 0 ;

	public Hand() {
		this.cards = new ArrayList< Card >() ;
	}
	
	public boolean addCard( Card card ) {
		//Add card to arraylist
		this.handLength ++ ;
		return cards.add( card ) ;
	}
	
	public Card[] toArray() {
		Card[] cardArray = new Card[ cards.size() ] ;
		for ( int i = 0 ; i < cards.size() ; i++ ) {
			cardArray[ i ] = cards.get( i ) ;
		}
		return cardArray ;
	}
	
	//Attempts to remove
	public boolean removeCard( Card card ) {
		this.handLength -- ;
		return cards.remove( card ) ;
	} // end removeCard
	
	public void printHand( String playerIdentifier ) {
		String output = "" ;
		output += playerIdentifier + "'s hand" ;
		int lineNumber = 0 ;
		
		for ( int i = 0 ; i < this.cards.size() ; i++ )
			{
			output += "\n" + lineNumber + ": " + cards.get( i ).toString() ;
			lineNumber++ ;
			}
		
		System.out.print( output ) ;
	} // end printHand
	
	public boolean isEmpty() {
		return ( this.handLength <= 0 ) ;
	} // end isEmpty
	
	public int cardsLeft() {
		return this.handLength ;
	} // end cardsLeft
	
    /**
     * (optional) test driver
     *
     * @param args
     *     -unused-
     */
    public static void main( final String[] args )
        {
        // OPTIONAL for testing and debugging

        }	// end main()

    }	// end class Hand