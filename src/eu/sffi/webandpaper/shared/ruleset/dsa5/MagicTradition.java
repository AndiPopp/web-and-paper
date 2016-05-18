/**
 * 
 */
package eu.sffi.webandpaper.shared.ruleset.dsa5;

/**
 * A class representing a magic tradition
 * @author Andi Popp
 *
 */
public class MagicTradition extends SpecialSkill {

	/**
	 * The byte value of the main attribute according to {@link CharacterAttributes}
	 */
	public final byte mainAttribute;

	/**
	 * Full parameter constructor
	 * @param name
	 * @param apCost
	 * @param prerequisites
	 * @param mainAttribute
	 */
	public MagicTradition(String name, int apCost,
			Prerequisite[] prerequisites, byte mainAttribute) throws IllegalArgumentException{
		
		//super constructor
		super(name, apCost, prerequisites);
		
		//check's if the mainAttribute is valid and throws an IllegalArgumentException if not
		CharacterAttributes.attributeName(mainAttribute);
		
		this.mainAttribute = mainAttribute;
	}
	
	
	

}
