/**
 * 
 */
package eu.sffi.webandpaper.client.ruleset.dsa5;

import com.google.gwt.user.client.ui.Panel;

import eu.sffi.webandpaper.client.AbstractDisplayCharacterPanel;
import eu.sffi.webandpaper.shared.ruleset.dsa5.Character;

/**
 * A panel to display a raw DSA 5 character
 * @author Andi Popp
 *
 */
public class DisplayCharacterPanel extends AbstractDisplayCharacterPanel {

	private final Panel sidenav;
	
	public DisplayCharacterPanel(Character character, Panel sidenav) {
		super(character);
		this.sidenav = sidenav;
	}

	
	
	
}
