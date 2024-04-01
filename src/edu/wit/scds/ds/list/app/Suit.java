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
 * An enumeration of card suits. (Listing C-2 of Appendix C.)
 * <p>
 * You may want/need to adjust the priorities for your game. As provided, suits are ordered by
 * priority:<br>
 *
 * <pre>
 * Spades (highest) -> Diamonds -> Hearts -> Clubs -> none (lowest)
 * </pre>
 *
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 *
 * @version 4.0
 *
 * @author David M Rosenberg
 *
 * @version 4.1.0 2016-03-16
 *     <ul>
 *     <li>enhanced definition: added display name and graphic
 *     <li>added test driver main()
 *     </ul>
 * @version 4.1.1 2021-11-19
 *     <ul>
 *     <li>fill in Javadoc comments
 *     <li>add Comparator/compare()
 *     </ul>
 * @version 4.2.0 2022-11-06 add switch for standard vs alternate priority
 *
 * @author Your Name    // TODO
 *
 * @version 4.3.0 2024-03-26 Modifications for use for our game
 */
public enum Suit
    {

// @formatter:off
//  Element         Display Name    Graphic     Color      Priority   Alt Priority
    /** Spades suit */
    SPADES      (   "Spades",       "♠",        "black",    4,        1 )
    , /** Diamonds suit */
    DIAMONDS    (   "Diamonds",     "♦",        "red",      3,        2 )
    , /** Hearts suit */
    HEARTS      (   "Hearts",       "♥",        "red",      2,        3 )
    , /** Clubs suit */
    CLUBS       (   "Clubs",        "♣",        "black",    1,        4 )
    , /** no suit - for cards such as Joker */
    NONE        (   "",             "",         "",         5,        5 )
    ;
// @formatter:on


    // static fields
    /** when true, evaluations will use {@code altPriority} instead of {@code priority} */
    private static boolean useAltPriority = false ;

    // data fields
    /** 'pretty' name for the suit */
    private final String displayName ;
    /** graphic representation of the suit */
    private final String graphic ;
    /** black or red for a standard, 52-card deck */
    private final String color ;
    /** relative strength of each suite - may be more natural for comparisons */
    private final int priority ;
    /** alternative priority ordering - may be more natural for display */
    private final int altPriority ;


    /**
     * Sets all fields representing a card suit
     * 
     * @param suitDisplayName
     *     more esthetically pleasing for display
     * @param suitGraphic
     *     the 'standard' icon for the suit
     * @param suitColor
     *     the 'standard' color for the suit
     * @param suitPriority
     *     relative value/priority of the suit - used by compare()
     * @param altSuitPriority
     *     alternate relative value/priority of the suit - used by compare()
     */
    private Suit( final String suitDisplayName,
                  final String suitGraphic,
                  final String suitColor,
                  final int suitPriority,
                  final int altSuitPriority )
        {

        this.displayName = suitDisplayName ;
        this.graphic = suitGraphic ;
        this.color = suitColor ;
        this.priority = suitPriority ;
        this.altPriority = altSuitPriority ;

        }   // end constructor


    /**
     * Retrieves the display name
     * 
     * @return the display name
     */
    public String getDisplayName()
        {

        return this.displayName ;

        }   // end getDisplayName()


    /**
     * Retrieves the graphic/icon
     * 
     * @return the graphic/icon
     */
    public String getGraphic()
        {

        return this.graphic ;

        }   // end getGraphic()


    /**
     * Retrieves the color
     * 
     * @return the color
     */
    public String getColor()
        {

        return this.color ;

        }   // end getColor()


    /**
     * Retrieves the priority
     * 
     * @return the priority or alternate priority depending upon the setting of
     *     {@code useAltPriority}
     */
    public int getPriority()
        {

        return Suit.useAltPriority
            ? this.altPriority
            : this.priority ;

        }   // end getPriority()


    /**
     * Retrieves the alternate priority
     * 
     * @return the alternate priority
     */
    public int getAltPriority()
        {

        return this.altPriority ;

        }   // end getAltPriority()


    /*
     * miscellaneous utilities
     */


    /**
     * Retrieves the current setting of {@code useAltPriority}
     *
     * @return the current setting of {@code useAltPriority}
     */
    public static boolean getUseAltPriority()
        {

        return Suit.useAltPriority ;

        }   // getUseAltPriority()


    /**
     * Set {@code useAltPriority}
     *
     * @param newUseAltPriority
     *     the new setting
     *
     * @return the previous setting of {@code useAltPriority}
     */
    public static boolean setUseAltPriority( final boolean newUseAltPriority )
        {

        final boolean wasUseAltPriority = Suit.useAltPriority ;

        Suit.useAltPriority = newUseAltPriority ;

        return wasUseAltPriority ;

        }   // setUseAltPriority()


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

        }   // end toString()


    /**
     * Test driver
     *
     * @param args
     *     -unused-
     */
    public static void main( final String[] args )
        {

        // display column headers
        System.out.printf( "%-5s %-8s %-8s %-15s %-15s %-10s %-10s %-10s%n",
                           "#",
                           "Suit",
                           "Graphic",
                           "Name",
                           "Display Name",
                           "Color",
                           "Priority",
                           "Alt Priority" ) ;

        // display each element of the enumeration
        for ( final Suit aSuit : Suit.values() )
            {
            System.out.printf( "%-5d %-8s %-8s %-15s %-15s %-10s %-10d %-10d%n",
                               aSuit.ordinal(),
                               aSuit,
                               aSuit.graphic,
                               aSuit.name(),
                               aSuit.displayName,
                               aSuit.color,
                               aSuit.getPriority(),
                               aSuit.getAltPriority() ) ;
            }	// end for

        }	// end main()

    }   // end enum Suit