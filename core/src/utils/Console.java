package utils;

import java.util.ArrayList;

public class Console {
	private ArrayList<String> log = new ArrayList<String>();

	/**
	 * Issues a command to the console.
	 * @param input The command.
	 */
	public void in(String input) {

	}

	/**
	 * @return The last command issued;
	 */
	public String out() {
		if (log.size() > 0) {
			return log.get(log.size() - 1);
		} 
		return "";
	}
}
