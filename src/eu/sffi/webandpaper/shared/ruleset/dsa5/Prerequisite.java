/**
 * 
 */
package eu.sffi.webandpaper.shared.ruleset.dsa5;

/**
 * This is the interface that needs to be implemented by any classes representing
 * prerequisites for Advantages, Disadvantages or special skills
 * @author Andi Popp
 *
 */
public interface Prerequisite {

	/**
	 * This method checks if a character fulfills the prerequisite
	 * @param character the character to be checked
	 * @return true if the character fulfills the prerequisite, false otherwise
	 */
	public boolean check(Character character);
	
	/**
	 * This methods checks if a character fulfills the prerequisite, but instead of return a machine readable
	 * boolean value, it returns a human readable explanation for passing or failing.
	 * @param character the character to be checked
	 * @return a human readable explanation for passing or failing
	 */
	public String explain(Character character);
	
}
