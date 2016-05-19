/**
 * 
 */
package eu.sffi.webandpaper.client.ruleset.dsa5;

import com.google.gwt.user.client.ui.ListBox;

import eu.sffi.webandpaper.client.ui.ConvenientListBox;
import eu.sffi.webandpaper.shared.ruleset.dsa5.CharacterExperienceLevel;

/**
 * The list box to choose the experience level in the character creation panel.
 * @author Andi Popp
 *
 */
public class CharacterCreationExpLevelListBox extends ConvenientListBox {

	/**
	 * Creates a {@link ListBox} and fills it with the experience levels described in {@link CharacterExperienceLevel}
	 */
	public CharacterCreationExpLevelListBox() {
		super();
		this.addItems(CharacterExperienceLevel.levelNames, false);
		this.setMultipleSelect(false);
		this.setSelectedIndex(CharacterExperienceLevel.ERFAHREN);
	}
	
	/**
	 * Returns a {@link CharacterExperienceLevel} object according to the current selection.
	 * @return a {@link CharacterExperienceLevel} object according to the current selection.
	 */
    public CharacterExperienceLevel getExperienceLevel(){
    	return CharacterExperienceLevel.getLevel((byte)this.getSelectedIndex());
    }
	
}
