/**
 * 
 */
package eu.sffi.webandpaper.shared.ruleset.dsa5;


/**
 * This class represents a character attribute modification as it is provided by e.g. species
 * @author Andi Popp
 *
 */
public class CharacterAttributesModification {
	
	/**
	 * The value by which the attribute is modified
	 */
	public final byte value;
	
	/**
	 * An array to save all the attributes that are possible alternatives for this modification
	 */
	public final byte[] attributes;
	
	/**
	 * Creates a new attribute modification
	 * @param value the value of this modification
	 * @param attributes the attributes that are possible alternatives for this modification (cf. {@link CharacterAttributes}
	 */
	public CharacterAttributesModification(byte value, byte... attributes){
		this.value = value;
		this.attributes = attributes;
	}

}
