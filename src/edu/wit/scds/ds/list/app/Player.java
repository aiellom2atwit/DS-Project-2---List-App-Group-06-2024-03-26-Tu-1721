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
 * Representation of a player
 *
 * @author Maxwell Aiello
 *
 * @version 1.0.0 2024-03-26 Initial implementation
 */
public class Player
    {
    /**
     * State variables
     */
	int playerIndex;
	static int totalPlayers = 0;
	
	boolean isPlayerTurn = false;
	
	Hand hand;
	
	// if player is bot, bot will calculate best move, otherwise player decides move
	boolean botPlayer = false;
	
	/**
	 * Methods
	 */
	public Player(boolean isBot) {
		//Determine whether player is bot
		this.botPlayer = isBot;
		//Set player index to currentPlayer amount
		this.playerIndex = totalPlayers;
		//increment totalPlayers
		totalPlayers++;
		
		boolean isPlayerTurn = false;
		
		//Generate blank hand for player
		hand = new Hand();
	}
	
	public boolean addCard(Card currentCard) {
		return hand.addCard(currentCard);
	}
	
	public String getPlayerName() {
		return "Player #" + (playerIndex + 1);
	}
	
	public void printHand()
		{
		this.hand.printHand(getPlayerName());
		}
	
	public Hand getHand() {
		return this.hand;
	}
	


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

    }	// end class Player