/**
 * 
 */
package eu.sffi.webandpaper.client;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

import eu.sffi.webandpaper.shared.ruleset.AbstractCharacter;

/**
 * An abstract class for the panels that display the characters of the different rulesets to be extended from
 * @author Andi Popp
 *
 */
public abstract class AbstractDisplayCharacterPanel extends VerticalPanel {

	/**
	 * The character associated with this panel
	 */
	protected final AbstractCharacter character;

	/**
	 * Full parameter constructor
	 * @param character
	 */
	public AbstractDisplayCharacterPanel(AbstractCharacter character) {
		super();
		this.character = character;
	}
	
	/**
	 * Getter for character
	 */
	public AbstractCharacter getCharacter(){
		return this.character;
	}
	
	
}
