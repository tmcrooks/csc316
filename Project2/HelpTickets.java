import java.util.Scanner;

/**
 * This is the main method that will read in the input from standard input
 * @author Abby Richman <alrichma>
 * @author Thomas Petrovich <tppetrov>
 * @author Timothy Crooks <tmcrooks>
 */
public class HelpTickets {

	/**
	 * The main method of program
	 * @param args
	 */
	public static void main(String[] args) {
		Wrapper wrapper = new Wrapper();
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			String line = scanner.nextLine();
			input(line, wrapper);
		}
		scanner.close();
	}

	/**
	 * Will choose what to do with the arguments based on the command
	 * @param line The line of input being checked
	 * @param wrapper The helper class that will handle the methods
	 */
	private static void input(String line, Wrapper wrapper){
		System.out.println(line);
		try{
			if(line.substring(0, 1).equals("+")){
				if(checkRest(line)){
					String rest = line.substring(2, line.length()).replaceAll(" ", "");
					boolean hasNoneNumber = hasNoneNumber(rest.toCharArray());
					if(hasNoneNumber){
						throw new Warning("priority " + rest + " is not an integer");
					}
					int priority = Integer.parseInt(rest);			
					int id = wrapper.add(priority);
					if(id == 0){
						throw new Warning("a ticket with priority " + priority + " is already in the queue");
					}
					else{
						System.out.println("id = " + id);	
					}
				}
			}
			else if(line.substring(0,1).equals("-")){
				if(wrapper.tree.isEmpty()){
					throw new Warning("removal attempted when queue is empty");
				}
				if(checkRest(line)){
					String rest = line.substring(2, line.length()).replaceAll(" ", "");;
					boolean hasNoneNumber = hasNoneNumber(rest.toCharArray());
					if(hasNoneNumber){
						throw new Warning("id " + rest + " is not an integer");
					}
					int id = Integer.parseInt(rest);
					Integer priority = wrapper.map.get(id);
					if(priority == null){
						throw new Warning("there is no ticket with id = " + id + " in the queue");
					}
					int position = wrapper.remove(id);
					System.out.println(priority + ", pos = " + position);
				}
			}
			else if(line.substring(0,1).equals("*")){
				if(wrapper.tree.isEmpty()){
					throw new Warning("removal attempted when queue is empty");
				}
				Ticket ticket = wrapper.removeHighestPriority();
				int id = ticket.getID();
				int priority = ticket.getPriority();
				System.out.println("id = " + id + ", " + priority);
			}
			else if(line.substring(0,1).equals("?")){
				if(checkRest(line)){
					String rest = line.substring(2, line.length()).replaceAll(" ", "");;
					boolean hasNoneNumber = hasNoneNumber(rest.toCharArray());
					if(hasNoneNumber){
						throw new Warning("id " + rest + " is not an integer");
					}
					int id = Integer.parseInt(rest);
					Integer priority = wrapper.map.get(id);
					if(priority == null){
						throw new Warning("there is no ticket with id = " + id + " in the queue");
					}
					int position = wrapper.currentPosition(id);
					System.out.println("pos = " + position);
				}
			}
			else{
				throw new Warning("invalid command " + line.substring(0, 1));
			}
		}
		catch(Warning w){
			System.out.println(w);
		}

	}
	
	/**
	 * Checks if the rest of the line falls under certain formating requirements
	 * including make sure it's not just the command and that there is a space after the command
	 * @param line the string that is being checked
	 * @return true if there were no warnings
	 */
	private static boolean checkRest(String line) {
		if(line.length() <= 2){
			throw new Warning("invalid command " + line);
		}
		else if(!line.substring(1,2).equals(" ")){
			throw new Warning("invalid command " + line);
		}
		return true;
	}

	/**
	 * Checks a list of chars if any of the characters are not digits
	 * @param rest The list of chars that are being checked
	 * @return true if there is a none number in the array, false if the array is all numbers
	 */
	private static boolean hasNoneNumber(char[] rest){
		for(int i = 0; i < rest.length; i++){
			if(!Character.isDigit(rest[i])){
				return true;
			}
		}
		return false;
	}

}
