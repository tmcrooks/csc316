/**
 * This is the tree object that holds a binary tree of tickets 
 * @author Abby Richman <alrichma>
 * @author Thomas Petrovich <tppetrov>
 * @author Timothy Crooks <tmcrooks>
 *
 */
public class BinaryTicketTree {
	
	/** The top of the tree */
	public Node root;
	
	/** This will create a new BinaryTicketTree */
	public BinaryTicketTree(){
		root = null;
	}
	/**
	 * This will add the ticket to the tree
	 * @param ticket The ticket being added
	 */
	public void add(Ticket ticket){
		if(ticket != null){
			if(isEmpty()){
				root = new Node(ticket);
			}
			else{
				addTraverse(root, ticket);
			}
		}
	}

	/**
	 * This will remove the ticket with the specific priority
	 * @param priority The priority of the ticket being removed
	 * @return The position of the ticket being removed, 0 if the list is now empty
	 */
	public int remove(int priority){
		int position = currentPosition(priority);
		root = removeTraverse(root,priority);
		if(root == null){
			return 0;
		}
		return position;
	}

	/**
	 * This will tell if the tree is empty or not
	 * @return True if the tree is empty, false if not
	 */
	public boolean isEmpty(){
		return root == null;
	}

	/**
	 * Finds the current position of a ticket of a certain priority
	 * @param priority The priority of the ticket
	 * @return The current position of the ticket
	 */
	public int currentPosition(int priority){
		return higherPriority(root,priority);
	}

	/**
	 * This will remove the ticket with the highest priority
	 * @return The ticket that with the highest priority that is being removed
	 */
	public Ticket removeHighestPriority(){
		Node highestPriority = highestPriority(root);
		remove(highestPriority.ticket.getPriority());
		return highestPriority.ticket;
	}
	
	/**
	 * Finds the number of tickets that have a higher priority
	 * @param current The current node that is being looked at
	 * @param priority The priority that is being tested
	 * @return The number of tickets that have a higher priority then the given priority
	 */
	private int higherPriority(Node current, int priority){
		if(current.ticket.getPriority() == priority){
			if(current.right != null){
				return current.right.descendants + 1;
			}
			return 1;
		}
		if(current.ticket.getPriority() > priority){
			if(current.right != null){
				return higherPriority(current.left,priority) + current.right.descendants + 1;
			}
			return higherPriority(current.left,priority) + 1;
		}
		else{
			return higherPriority(current.right,priority);
		}
	}

	/**
	 * This will traverse through the tree to find the node to be removed and change the tree based on the nodes being removed
	 * @param current The current node being looked at
	 * @param priority The priority number of the ticket being removed
	 * @return The new tree with the node removed
	 */
	private Node removeTraverse(Node root, int priority) {
		if(root != null){
			if(root.ticket.getPriority() > priority) {
				root.descendants--; // may be in wrong spot
				root.left  = removeTraverse(root.left,  priority);
			}
			else if (root.ticket.getPriority() < priority) {
				root.descendants--;
				root.right = removeTraverse(root.right, priority);
			}
			else { 
				//case 1: leaf node
				if(root.right == null & root.left == null){
					root = null;
				}
				//case 2: if there is only a left node
				else if (root.right == null) {
					root = root.left;
				}
				//case 3: if there is only a right node
				else if (root.left  == null) {
					root = root.right;
				}
				//case 4: there are both left and right nodes
				else{
					if(this.root == root){
						
					}
					Node current  = root;
					root = current.left;
					addToNewRoot(root, current.right);
				}
			} 
		}
		return root;
	}

	/**
	 * Adds the node to the most right of the current node
	 * @param current The node that is being added to
	 * @param add The node that is being added
	 */
	private void addToNewRoot(Node current, Node add) {
		current.descendants += add.descendants;
		if(current.right != null){
			addToNewRoot(current.right,add);
		}
		else{
			current.right = add;
		}
	}

	/**
	 * Looks for the ticket with the highest priority in the tree
	 * @param current The current node being looked at
	 * @return The node with the highest priority
	 */
	private Node highestPriority(Node current){
		if(current.right != null){
			return highestPriority(current.right);
		}
		else{
			return current;
		}
	}

	/** 
	 * Will traverse through the tree looking for the spot to add
	 * the new node
	 * @param root The current node being looked at
	 * @param ticket The ticket being added
	 * @return The node that is being added
	 */
	private Node addTraverse(Node root, Ticket ticket){
		root.descendants++;
		if(root.ticket.getPriority() > ticket.getPriority()){
			if(root.left == null){
				root.left = new Node(ticket,null,null);
			}
			else{
				return addTraverse(root.left, ticket);
			}
		}
		else{
			if(root.right == null){
				root.right = new Node(ticket,null,null);
			}
			else{
				return addTraverse(root.right, ticket);
			}
		}
		return root;
	}


	/**
	 * The node class that will keep track of the specific ticket at the location
	 * and it's children on the left and right side
	 * @author Abby Richman <alrichma>
	 * @author Thomas Petrovich <tppetrov>
	 * @author Timothy Crooks <tmcrooks>
	 *
	 */
	private class Node{
		/** The ticket at this node location */
		private Ticket ticket;
		/** The node on the left side */
		private Node left;
		/** The node on the right side */
		private Node right;
		/** The number of descendants including itself */
		private int descendants;

		/**
		 * This will create a new node with no children
		 * @param ticket The ticket being added to the node
		 */
		private Node(Ticket ticket){
			this(ticket,null,null);
		}

		/**
		 * This will create a node with the two children specified
		 * @param ticket The ticked being added to the node
		 * @param left The node on the left
		 * @param right The node on the right
		 */
		private Node(Ticket ticket, Node left, Node right){
			this.ticket = ticket;
			this.left = left;
			this.right = right;
			descendants = 1;
		}
	}
}
