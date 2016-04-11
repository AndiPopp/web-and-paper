/**
 * 
 */
package eu.sffi.webandpaper.shared.ruleset.dsa5;

import eu.sffi.webandpaper.shared.ruleset.CharacterCreationException;


/**
 * An abstract super class for skills
 * @author Andi Popp
 *
 */
public abstract class Skill {

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
	 * The skill group this skill belongs to
	 */
	public final SkillGroup skillGroup;

	/**
	 * Full parameter constructor
	 * @param name
	 * @param attributes
	 * @param increaseCostFactor
	 * @param skillGroup
	 * @throws CharacterCreationException if the values of the attribute fields do not conform to attribute byte identifiers
	 */
	public Skill(String name, byte[] attributes, byte increaseCostFactor,
			SkillGroup skillGroup) throws IllegalArgumentException, CharacterCreationException {
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
		this.skillGroup = skillGroup;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}
