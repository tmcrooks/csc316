import java.util.InputMismatchException;

/**
 * Warning class for Help Tickets
 * @author Abby Richman <alrichma>
 * @author Thomas Petrovich <tppetrov>
 * @author Timothy Crooks <tmcrooks>
 *
 */
public class Warning extends InputMismatchException{
	
	/**
	 * A general warning class that will refer back to InputMismatchException if called without specifications
	 */
	public Warning(){
		super();
	}
	
	/**
	 * A warning with a specific message
	 * @param message The warning message
	 */
	public Warning(String message){
		super(message);
	}

}
