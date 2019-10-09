import java.util.*;

public class CommandParser {
	
	private Command command; 
	private Scanner scanner; 
	
	public CommandParser () {
		this.command = new Command();
		this.scanner = new Scanner(System.in);
	}
	
	public Command getCommand () {
		String input; 
		String [] words;
		input = this.scanner.nextLine().toUpperCase();
		if (input != null && input.contains(" ")) {
			words = input.split(" ");
			if (words.length == 3) {
				this.command.addCommand(words[0], words[1], words[2]);	
			} else if (words.length == 2) {
				this.command.addCommand(words[0], words[1]);
			}
		}
		return this.command;
	}
	
}
