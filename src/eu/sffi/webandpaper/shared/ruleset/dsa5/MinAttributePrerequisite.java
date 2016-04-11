/**
 * 
 */
package eu.sffi.webandpaper.shared.ruleset.dsa5;

import eu.sffi.webandpaper.shared.ruleset.CharacterCreationException;

/**
 * Checks if a character has a minimal value in a specific attribute
 * @author Andi Popp
 *
 */
public class MinAttributePrerequisite implements Prerequisite {

	/**
	 * The attribute to be checked
	 */
	private final byte attribute;
	
	/**
	 * The minmal value to be checked
	 */
	private final byte minValue;
	
	
	/**
	 * 
	 * @param attribute
	 * @param minValue
	 * @throws CharacterCreationException if the value attribute is not a valid {@link CharacterAttributes} identifier
	 */
	public MinAttributePrerequisite(byte attribute, byte minValue) throws CharacterCreationException {
		super();
		CharacterAttributes.attributeName(attribute);
		this.attribute = attribute;
		this.minValue = minValue;
	}

	/* (non-Javadoc)
	 * @see eu.sffi.webandpaper.shared.ruleset.dsa5.Prerequisite#check(eu.sffi.webandpaper.shared.ruleset.dsa5.Character)
	 */
	@Override
	public boolean check(Character character) {
		if (character.attributes[attribute] < this.minValue) return false;
		else return true;
	}

	@Override
	public String explain(Character character) {
		String explanation = CharacterAttributes.attributeName(attribute)+" "+character.attributes[attribute]+"/"+minValue+" ";
		if (check(character)) explanation += "\u2713"; //add check mark
		else explanation += "\u2717"; //add cross
		return explanation;
	}
	
	

}
