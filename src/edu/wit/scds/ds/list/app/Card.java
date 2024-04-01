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

import java.util.ArrayList ;
import java.util.Collections ;
import java.util.List ;
import java.util.Objects ;

/**
 * Representation of a playing card with a suit and rank.
 * <p>
 * The suit and rank are immutable.
 *
 * @author Dave Rosenberg
 *
 * @version 1.0.0 2020-11-19 initial version<br>
 * @version 2.0.0 2021-12-08
 *     <ul>
 *     <li>add support for face up/down
 *     <li>add {@code matches()}
 *     </ul><br>
 * @version 2.1.0 2022-11-06 support dynamic switching to compare cards based on suit and rank or
 *     rank alone<br>
 * @version 2.2.0 2024-03-26
 *     <ul>
 *     <li>minor cosmetic changes
 *     <li>revise {@code toString()} for greater formatting flexibility and control
 *     </ul><br>
 *
 * @author Your Name    // TODO
 *
 * @version 2.3.0 2024-03-26 Modifications for use for our game
 */
public class Card implements Comparable<Card>
    {
    // utility constants
    /** indicate card is face up */
    public final static boolean FACE_UP = true ;
    /** indicate card is face down */
    public final static boolean FACE_DOWN = false ;

    // static data
    /** default state of a card when instantiated - face up or down */
    private static boolean defaultFaceUp = FACE_DOWN ;
    /** controls whether {@code equals()} and {@code compareTo()} consider suit in calculations */
    private static boolean compareSuit = true ;
    /** the text to display when the card is face down - if not specified, use current default */
    private static String faceDownText = "--" ;
    
    public static final String RED_COLOR = "\u001B[31m";
    public static final String RESET_COLOR = "\u001B[0m";

    // data fields
    /** The card's suit */
    public final Suit suit ;
    /** The card's rank within its suit */
    public final Rank rank ;
    /** controls display - face up/down */
    private boolean faceUp ;


    /*
     * constructors
     */


    /**
     * Initializes a card with no suit (e.g., a joker)
     *
     * @param theRank
     *     this card's rank
     */
    public Card( final Rank theRank )
        {
        this( Suit.NONE, theRank ) ;

        }   // end 1-arg constructor


    /**
     * Initializes a card with a specified suit and rank and the default face up setting
     * 
     * @param theSuit
     *     this card's suit
     * @param theRank
     *     this card's rank
     */
    public Card( final Suit theSuit, final Rank theRank )
        {
        this( theSuit, theRank, Card.defaultFaceUp ) ;

        }   // end 2-arg constructor


    /**
     * Initializes a card with a specified suit rank, and face up setting
     * 
     * @param theSuit
     *     this card's suit
     * @param theRank
     *     this card's rank
     * @param initiallyFaceUp
     *     if {@code true}, the card is face up; if {@code false}, the card is face down
     */
    public Card( final Suit theSuit, final Rank theRank, final boolean initiallyFaceUp )
        {
        this.suit = theSuit ;
        this.rank = theRank ;
        this.faceUp = initiallyFaceUp ;

        }   // end full/3-arg constructor


    /*
     * methods to affect face up/down state and display of an instance
     */


    /**
     * Flips a card over
     *
     * @return the previous state (face up/down)
     */
    public boolean flip()
        {
        final boolean wasFaceUp = this.faceUp ;

        this.faceUp = !this.faceUp ;

        return wasFaceUp ;

        }  // end flip()


    /**
     * Turns a card face down
     *
     * @return the previous state (face up/down)
     */
    public boolean hide()
        {
        final boolean wasFaceUp = this.faceUp ;

        this.faceUp = FACE_DOWN ;

        return wasFaceUp ;

        }  // end hide()


    /**
     * Turns a card face up
     *
     * @return the previous state (face up/down)
     */
    public boolean reveal()
        {
        final boolean wasFaceUp = this.faceUp ;

        this.faceUp = FACE_UP ;

        return wasFaceUp ;

        }  // end reveal()


    /**
     * Retrieves the current behavior of {@code equals()} and {@code compareTo()} wrt
     * {@code this.suit}
     *
     * @return {@code true} if {@code equals()} and {@code compareTo()} include {@code this.suit};
     *     {@code false} otherwise
     */
    public static boolean getCompareSuit()
        {
        return Card.compareSuit ;

        }  // end getCompareSuit()


    /**
     * Sets the behavior of {@code equals()} and {@code compareTo()} wrt {@code this.suit}
     *
     * @param newCompareRankSuit
     *     the new evaluation behavior wrt {@code this.suit} where {@code true} causes
     *     {@code equals()} and {@code compareTo()} to include {@code this.suit} in their
     *     evaluations; {@code false} won't consider it
     *
     * @return the previous state (consider/ignore)
     */
    public static boolean setCompareSuit( final boolean newCompareRankSuit )
        {
        final boolean wasCompareSuit = Card.compareSuit ;

        Card.compareSuit = newCompareRankSuit ;

        return wasCompareSuit ;

        }  // end setCompareSuit()


    /**
     * Retrieves whether a card is face up or down
     *
     * @return the card's state (face up/down)
     */
    public boolean getFaceUp()
        {
        return this.faceUp ;

        }  // end getFaceUp()


    /**
     * Sets a card to be face up/down
     *
     * @param newFaceUp
     *     the new face up/down state, where {@code true} indicates face up, {@code false} is face
     *     down
     *
     * @return the previous state (face up/down)
     */
    public boolean setFaceUp( final boolean newFaceUp )
        {
        final boolean wasFaceUp = this.faceUp ;

        this.faceUp = newFaceUp ;

        return wasFaceUp ;

        }  // end setFaceUp()


    /**
     * Retrieves the text to display when the card is face down
     *
     * @return the text to display when the card is face down - {@code null} indicates to use the
     *     default
     */
    public static String getFaceDownText()
        {
        return Card.faceDownText ;

        }  // end getFaceDownText()


    /**
     * Sets the text to display when the card is face down
     *
     * @param newFaceDownText
     *     the new text to display when the card is face down
     *
     * @return the previous text to display when the card is face down
     */
    public static String setFaceDownText( final String newFaceDownText )
        {
        final String savedFaceDownText = Card.faceDownText ;

        Card.faceDownText = newFaceDownText ;

        return savedFaceDownText ;

        }  // end setFaceDownText()


    /*
     * methods related to the defaults
     */


    /**
     * Retrieves the default for new cards to be face up/down
     *
     * @return the current default state (face up/down)
     */
    public static boolean getDefaultFaceUp()
        {
        return Card.defaultFaceUp ;

        }  // end getDefaultFaceUp()


    /**
     * Sets the default for new cards to be face up/down
     * <p>
     * Note that this will only be effective for cards instantiated
     *
     * @param newFaceUp
     *     the new face up/down state, where {@code true} indicates face up, {@code false} is face
     *     down
     *
     * @return the previous state (face up/down)
     */
    public static boolean setDefaultFaceUp( final boolean newFaceUp )
        {
        final boolean wasFaceUp = Card.defaultFaceUp ;

        Card.defaultFaceUp = newFaceUp ;

        return wasFaceUp ;

        }  // end setDefaultFaceUp()


    /*
     * utility methods
     */


    /**
     * Compares two cards to see if they match, which may be different from them being
     * {@code equal()}
     * <p>
     * The game or {@code Pile} class may require this method to behave differently from
     * {@code equals()} or {@code compreTo()}.
     *
     * @param otherCard
     *     the card to compare against this card
     *
     * @return {@code true} if the cards match, {@code false} otherwise
     */
    public boolean matches( final Card otherCard )
        {
        // for now, delegate to equals() - may change based on game's requirements
        return equals( otherCard ) ;

        }  // end matches()


    /*
     * general methods
     */

    @Override
    public int compareTo( final Card otherCard )
        {

        // check suit first, if necessary
        if ( Card.compareSuit )
            {
            final int suitComparison = this.suit.getPriority() - otherCard.suit.getPriority() ;

            if ( suitComparison != 0 )
                {
                // suits are different - done
                return suitComparison ;
                }

            }

        // either didn't need to compare suits or both cards are of the same suit
        // check rank

        return this.rank.getOrder() - otherCard.rank.getOrder() ;

        }	// end compareTo()

    @Override
    public boolean equals( final Object otherObject )
        {

        // same object?
        if ( this == otherObject )
            {
            return true ;
            }

        // another Card? false if otherObject is null
        if ( otherObject instanceof final Card otherCard )
            {
            return ( Card.compareSuit
                        ? this.suit.equals( otherCard.suit )
                        : true ) &&
                     ( this.rank.getOrder() == otherCard.rank.getOrder() ) ;
            }

        // no match
        return false ;

        }	// end equals()

    @Override
    public int hashCode()
        {
        return Objects.hash( Card.compareSuit
                                ? this.suit
                                : 0,    // 2xCk does this work properly?
                             this.rank ) ;

        }   // end hashCode()

    @Override
    public String toString()
        {
    	String outputValue = "";
    	if (this.suit == Suit.DIAMONDS || this.suit == Suit.HEARTS) {
    		outputValue += RED_COLOR;
    	}
        outputValue = this.faceUp
                    ? String.format( "%s%s", this.rank, this.suit )
                    : Card.faceDownText ;
        outputValue += RESET_COLOR;
        
        return outputValue;
        }	// end toString()

    /**
     * Sample demo program
     *
     * @param args
     *     -unused-
     */
    public static void main( final String[] args )
        {
        final Suit[] suits = Suit.values() ;
        final Rank[] ranks = Rank.values() ;

        final List<Card> cards = new ArrayList<>( suits.length * ranks.length ) ;

        // generate a deck of cards
        System.out.printf( "New cards:%n" ) ;

        for ( final Suit suit : suits )
            {

            // skip placeholder suit
            if ( Suit.NONE.equals( suit ) )
                {
                continue ;
                }

            for ( final Rank rank : ranks )
                {

                // skip non-playing card(s) - Joker
                if ( Rank.JOKER.equals( rank ) )
                    {
                    continue ;
                    }

                // build a card
                final Card newCard = new Card( suit, rank ) ;
                System.out.printf( " %s", newCard ) ;

                // keep track of it
                cards.add( newCard ) ;
                }

            }

        // turn top card over
        cards.get( cards.size() - 1 ).flip() ;

        // display all the cards
        System.out.printf( "%n%nAll cards:%n%s%n%n", cards.toString() ) ;

        // turn all cards face up
        for ( final Card aCard : cards )
            {
            aCard.reveal() ;
            }

        // display all the cards
        System.out.printf( "%n%nAll cards:%n%s%n%n", cards.toString() ) ;

        // shuffled
        Collections.shuffle( cards ) ;
        System.out.printf( "%nShuffled:%n%s%n%n", cards.toString() ) ;

        // sorted
        Collections.sort( cards ) ;
        System.out.printf( "%nSorted (rank and suit):%n%s%n%n", cards.toString() ) ;

        // sort only on rank
        setCompareSuit( false ) ;

        // shuffled
        Collections.shuffle( cards ) ;
        System.out.printf( "%nShuffled:%n%s%n%n", cards.toString() ) ;

        // sorted
        Collections.sort( cards ) ;
        System.out.printf( "%nSorted (rank only):%n%s%n%n", cards.toString() ) ;

        // sorted
        Collections.sort( cards ) ;
        System.out.printf( "%nSorted (rank only):%n%s%n%n", cards.toString() ) ;

        // sort on rank and suit
        setCompareSuit( true ) ;

        // sorted
        Collections.sort( cards ) ;
        System.out.printf( "%nSorted (rank and suit):%n%s%n%n", cards.toString() ) ;


        // compare some cards against each other
        Card card1 = cards.get( 15 ) ;
        Card card2 = cards.get( 43 ) ;
        System.out.printf( "%s.compareTo(%s) = %,d (rank and suit)%n", card1, card2, card1.compareTo( card2 ) ) ;

        card2 = cards.get( 4 ) ;
        System.out.printf( "%s.compareTo(%s) = %,d (rank and suit)%n", card1, card2, card1.compareTo( card2 ) ) ;

        card2 = cards.get( 20 ) ;
        System.out.printf( "%s.compareTo(%s) = %,d (rank and suit)%n", card1, card2, card1.compareTo( card2 ) ) ;


        System.out.printf( "%n" ) ;
        System.out.printf( "%s.equals(%s) = %b (rank and suit)%n", card1, card1, card1.equals( card1 ) ) ;
        System.out.printf( "%s.equals(%s) = %b (rank and suit)%n", card1, card2, card1.equals( card2 ) ) ;

        Card.setDefaultFaceUp( true ) ;
        card1 = new Card( Suit.DIAMONDS, Rank.FOUR ) ;
        card2 = new Card( Suit.HEARTS, Rank.FOUR ) ;
        System.out.printf( "%s.equals(%s) = %b (rank and suit)%n", card1, card2, card1.equals( card2 ) ) ;


        // repeat comparisons without considering suit
        setCompareSuit( false ) ;

        System.out.printf( "%n" ) ;

        // compare some cards against each other
        card1 = cards.get( 15 ) ;
        card2 = cards.get( 43 ) ;
        System.out.printf( "%s.compareTo(%s) = %,d (rank only)%n", card1, card2, card1.compareTo( card2 ) ) ;

        card2 = cards.get( 4 ) ;
        System.out.printf( "%s.compareTo(%s) = %,d (rank only)%n", card1, card2, card1.compareTo( card2 ) ) ;

        card2 = cards.get( 20 ) ;
        System.out.printf( "%s.compareTo(%s) = %,d (rank only)%n", card1, card2, card1.compareTo( card2 ) ) ;


        System.out.printf( "%n" ) ;
        System.out.printf( "%s.equals(%s) = %b (rank only)%n", card1, card1, card1.equals( card1 ) ) ;
        System.out.printf( "%s.equals(%s) = %b (rank only)%n", card1, card2, card1.equals( card2 ) ) ;

        Card.setDefaultFaceUp( true ) ;
        card1 = new Card( Suit.DIAMONDS, Rank.FOUR ) ;
        card2 = new Card( Suit.HEARTS, Rank.FOUR ) ;
        System.out.printf( "%s.equals(%s) = %b (rank only)%n", card1, card2, card1.equals( card2 ) ) ;

        }	// end main()

    }	// end class Card