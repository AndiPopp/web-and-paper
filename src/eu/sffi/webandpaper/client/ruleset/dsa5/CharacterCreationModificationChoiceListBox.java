/**
 * 
 */
package eu.sffi.webandpaper.client.ruleset.dsa5;

import eu.sffi.webandpaper.client.Debug;
import eu.sffi.webandpaper.client.ui.ConvenientListBox;
import eu.sffi.webandpaper.shared.ruleset.CharacterCreationException;
import eu.sffi.webandpaper.shared.ruleset.dsa5.CharacterAttributes;
import eu.sffi.webandpaper.shared.ruleset.dsa5.CharacterAttributesModification;

/**
 * A list box associated with a character modification
 * @author Andi Popp
 *
 */
public class CharacterCreationModificationChoiceListBox extends
		ConvenientListBox {
	
	/**
	 * The associated attribute modification
	 */
	public final CharacterAttributesModification attributesModification;
	
	/**
	 * Full parameter constructor
	 * @param attributesModification
	 * @throws CharacterCreationException if the attributes in the attribute modifications are not valid attribute identifiers
	 */
	public CharacterCreationModificationChoiceListBox(
			CharacterAttributesModification attributesModification) throws CharacterCreationException {
		super();
		this.attributesModification = attributesModification;
		for (byte attribute : attributesModification.attributes){
			this.addItem(CharacterAttributes.attributeName(attribute));
		}
	}

	
}
