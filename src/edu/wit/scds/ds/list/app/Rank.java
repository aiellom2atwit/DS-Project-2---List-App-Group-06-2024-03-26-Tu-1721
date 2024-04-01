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

/**
 * An enumeration of card ranks.
 *
 * @author David M Rosenberg
 *
 * @version 1.0.0 2016-03-16 initial version
 * @version 1.1.0 2022-11-06 add switches for standard vs alternate points and order
 *
 * @author Your Name    // TODO
 *
 * @version 1.2.0 2024-03-26 Modifications for use for our game
 */
public enum Rank
    {

// @formatter:off
//  Element     Display Name    Graphic     Points      Alt Points  Order      Alt Order
    /** Ace */
    ACE     (   "Ace",          "A",        1,          11,         1,          14 )
    , /** Two */
    TWO     (   "Duece",        "2",        2,          2,          2,          2 )
    , /** Three */
    THREE   (   "Three",        "3",        3,          3,          3,          3 )
    , /** Four */
    FOUR    (   "Four",         "4",        4,          4,          4,          4 )
    , /** Five */
    FIVE    (   "Five",         "5",        5,          5,          5,          5 )
    , /** Six */
    SIX     (   "Six",          "6",        6,          6,          6,          6 )
    , /** Seven */
    SEVEN   (   "Seven",        "7",        7,          7,          7,          7 )
    , /** Eight */
    EIGHT   (   "Eight",        "8",        8,          8,          8,          8 )
    , /** Nine */
    NINE    (   "Nine",         "9",        9,          9,          9,          9 )
    , /** Ten */
    TEN     (   "Ten",          "10",       10,         10,         10,         10 )
    , /** Jack */
    JACK    (   "Jack",         "J",        10,         10,         11,         10 )
    , /** Queen */
    QUEEN   (   "Queen",        "Q",        10,         10,         12,         10 )
    , /** King */
    KING    (   "King",         "K",        10,         10,         13,         10 )
    , /** Joker */
    JOKER   (   "Joker",        "R",        0,          0,          99,         99 )
    ;
// @formatter:on


    // static fields
    /** when true, evaluations will use {@code altPoints} instead of {@code points} */
    private static boolean useAltPoints = false ;
    /** when true, evaluations will use {@code altOrder} instead of {@code order} */
    private static boolean useAltOrder = false ;

    // data fields
    /** 'pretty' name for the rank */
    private final String displayName ;
    /** graphic representation of the rank */
    private final String graphic ;
    /** points for a card of this rank */
    private final int points ;
    /** alternate points for a card of this rank */
    private final int altPoints ;
    /** sort order */
    private final int order ;
    /** alternate sort order */
    private final int altOrder ;


    /*
     * constructor
     */


    /**
     * Sets all fields representing a card rank
     * 
     * @param rankDisplayName
     *     more esthetically pleasing for display
     * @param rankGraphic
     *     the 'standard' icon
     * @param rankPoints
     *     numeric value for the card
     * @param rankAltPoints
     *     alternate value for the card (e.g., Ace can be worth 1 or 11 points)
     * @param rankOrder
     *     numeric order for the card
     * @param rankAltOrder
     *     alternate order for the card (e.g., Ace can have the lowest or highest order)
     */
    private Rank( final String rankDisplayName,
                  final String rankGraphic,
                  final int rankPoints,
                  final int rankAltPoints,
                  final int rankOrder,
                  final int rankAltOrder )
        {
        this.displayName = rankDisplayName ;
        this.graphic = rankGraphic ;
        this.points = rankPoints ;
        this.altPoints = rankAltPoints ;
        this.order = rankOrder ;
        this.altOrder = rankAltOrder ;

        } // end constructor


    /*
     * getters
     */


    /**
     * Retrieves the alternate order flag
     * 
     * @return the alternate order
     */
    public int getAltOrder()
        {
        return this.altOrder ;

        } // end getAltOrder()


    /**
     * Retrieves the alternate point value
     * 
     * @return the alternate point value
     */
    public int getAltPoints()
        {
        return this.altPoints ;

        } // end getAltPoints()


    /**
     * Retrieves the display name
     * 
     * @return the display name
     */
    public String getDisplayName()
        {
        return this.displayName ;

        } // end getDisplayName()


    /**
     * Retrieves the graphic/icon
     * 
     * @return the graphic/icon
     */
    public String getGraphic()
        {
        return this.graphic ;

        } // end getGraphic()


    /**
     * Retrieves the order flag (regular or alternate)
     * 
     * @return the order
     */
    public int getOrder()
        {
        return Rank.useAltOrder
                    ? this.altOrder
                    : this.order ;

        } // end getOrder()


    /**
     * Retrieves the point value
     * 
     * @return the point value
     */
    public int getPoints()
        {
        return Rank.useAltPoints
                    ? this.altPoints
                    : this.points ;

        } // end getPoints()


    /*
     * miscellaneous utilities
     */


    /**
     * Retrieves the current setting of {@code useAltPoints}
     *
     * @return the current setting of {@code useAltPoints}
     */
    public static boolean getUseAltPoints()
        {
        return Rank.useAltPoints ;

        }   // getUseAltPoints()


    /**
     * Sets {@code useAltPoints}
     *
     * @param newUseAltPoints
     *     the new setting
     *
     * @return the previous setting of {@code useAltPoints}
     */
    public static boolean setUseAltPoints( final boolean newUseAltPoints )
        {
        final boolean wasUseAltPoints = Rank.useAltPoints ;

        Rank.useAltPoints = newUseAltPoints ;

        return wasUseAltPoints ;

        }   // setUseAltPoints()


    /**
     * Retrieves the  current setting of {@code useAltOrder}
     *
     * @return the current setting of {@code useAltOrder}
     */
    public static boolean getUseAltOrder()
        {
        return Rank.useAltOrder ;

        }   // getUseAltOrder()


    /**
     * Sets {@code useAltOrder}
     *
     * @param newUseAltOrder
     *     the new setting
     *
     * @return the previous setting of {@code useAltOrder}
     */
    public static boolean setUseAltOrder( final boolean newUseAltOrder )
        {
        final boolean wasUseAltOrder = Rank.useAltOrder ;

        Rank.useAltOrder = newUseAltOrder ;

        return wasUseAltOrder ;

        }   // setUseAltOrder()


    /*
     * utility methods
     */


    /**
     * For display, use the graphic/icon
     */
    @Override
    public String toString()
        {
        return this.graphic ;

        }	// end toString()


    /**
     * Test driver
     *
     * @param args
     *     -unused-
     */
    public static void main( final String[] args )
        {
        // display column headers
        System.out.printf( "%-5s %-8s %-8s %-15s %-15s %-6s   %-10s   %-10s %-15s%n",
                           "#",
                           "Rank",
                           "Graphic",
                           "Name",
                           "Display Name",
                           "Points",
                           "Alt Points",
                           "Order",
                           "Alt Order" ) ;

        // display each element of the enumeration
        for ( final Rank aRank : Rank.values() )
            {
            System.out.printf( "%-5d %-8s %-8s %-15s %-15s %-6d   %-10d   %-10d %-15d%n",
                               aRank.ordinal(),
                               aRank,
                               aRank.graphic,
                               aRank.name(),
                               aRank.displayName,
                               aRank.points,
                               aRank.altPoints,
                               aRank.order,
                               aRank.altOrder ) ;
            }	// end for

        }	// end main()

    } // end enum Rank