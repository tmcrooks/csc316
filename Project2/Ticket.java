/**
 * This is the ticket object class
 * @author Abby Richman <alrichma>
 * @author Thomas Petrovich <tppetrov>
 * @author Timothy Crooks <tmcrooks>
 *
 */
public class Ticket {
	/** The priority of the ticket */
	private int priority;
	/** The id of the ticket */
	private int id;
	
	/**
	 * Creates a ticket object
	 * @param priority The priority of the ticket
	 * @param id The id of the ticket
	 */
	public Ticket(int priority, int id){
		this.priority = priority;
		this.id = id;
	}
	
	/**
	 * Gets the id of the ticket
	 * @return The id of the ticket
	 */
	public int getID(){
		return this.id;
	}
	
	/**
	 * Gets the priority of the ticket
	 * @return the priority of the ticket
	 */
	public int getPriority(){
		return this.priority;
	}
}
