import java.util.TreeMap;

/**
 * The wrapper class handles the specifics of the methods for the tree and map
 * @author Abby Richman <alrichma>
 * @author Thomas Petrovich <tppetrov>
 * @author Timothy Crooks <tmcrooks>
 */
public class Wrapper {
	/** The tree that contains all of the tickets */
	BinaryTicketTree tree;
	/** The map that contains all of the tickets */
	TreeMap<Integer,Integer> map;
	/** The number of the next id being added */
	int id;
	/**
	 * Creates a wrapper object that will set the new tree and map and id to 0
	 */
	public Wrapper(){
		tree = new BinaryTicketTree();
		map = new TreeMap<Integer, Integer>();
		id = 0;
	}

	/**
	 * Adds the priority to a new ticket in the tree and map
	 * @param priority The priority of the ticket
	 * @return The id of the ticket, 0 if the ticket already exists in the list
	 */
	public int add(int priority){
		if(!map.containsValue(priority)){
			id++;
			tree.add(new Ticket(priority, id));
			map.put(id, priority);
			return id;	
		}
		return 0;
		
	}

	/**
	 * Removes the ticket with the specific id given
	 * @param id the id of the ticket that is being removed
	 * @return The position of the ticket removed
	 */
	public int remove(int id){
		if(tree.isEmpty()){
			throw new Warning("removal attempted when queue is empty");
		}
		Integer priority = map.get(id);
		int position = 0;
		if(priority != null){
			position = tree.remove(priority);
			map.remove(id);
		}
		return position;
	}	
	/**
	 * Removes the ticket with the highest Priority
	 * @return The ticket that is being removed with the Highest Priority
	 */
	public Ticket removeHighestPriority(){
		Ticket removed = tree.removeHighestPriority();
		map.remove(removed.getID());
		return removed;
	}

	/**
	 * Finds the current position with the id of the ticket
	 * @param id The id of the ticket
	 * @return The position of the ticket
	 */
	public int currentPosition(int id){
		return tree.currentPosition(map.get(id));
	}
}
