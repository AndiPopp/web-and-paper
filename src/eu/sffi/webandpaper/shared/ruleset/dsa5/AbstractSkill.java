/**
 * 
 */
package eu.sffi.webandpaper.shared.ruleset.dsa5;

/**
 * An abstract skill class
 * @author Andi Popp
 *
 */
public class AbstractSkill {

	/**
	 * The skills name
	 */
	public final String name;
	
	/**
	 * The attributes this skill is tested on
	 */
	public final byte[] attributes;
	
	/**
	 * The increase factor column of this skill
	 */
	public final byte increaseCostFactor;

	/**
	 * Full parameter constructor
	 * @param name
	 * @param attributes
	 * @param increaseCostFactor
	 */
	public AbstractSkill(String name, byte[] attributes, byte increaseCostFactor) {
		super();
		this.name = name;
		//check attributes
		for (byte attribute : attributes){
			CharacterAttributes.attributeName(attribute);
		}
		this.attributes = attributes;
		//check increase cost factor
		if (IncreaseCostTable.costFactorString(increaseCostFactor) == ' ') throw new IllegalArgumentException("Illegal increase cost facotor: "+increaseCostFactor);
		this.increaseCostFactor = increaseCostFactor;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
	/**
	 * Converts a standard XX/XX/XX string into an attribute set
	 * @param attributeSetString
	 * @return an array of byte representing the set of skills in the string
	 * @throws IllegalArgumentException if the string does not have the correct format or a skill could not be identified
	 */
	public static byte[] getAttributes(String attributeSetString) throws IllegalArgumentException{
		String[] attributeStrings = attributeSetString.split("/");
		byte[] attributes = new byte[attributeStrings.length];
		for (byte i = 0; i < attributes.length; i++){
			//map the string to the byte, throws the IllegalArgumentException if not valid
			attributes[i] = CharacterAttributes.attributeValue(attributeStrings[i]);
		}
		return attributes;
	}
}
