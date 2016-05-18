/**
 * 
 */
package eu.sffi.webandpaper.shared.ruleset.dsa5;

import java.util.Map;
import java.util.TreeMap;

import eu.sffi.webandpaper.shared.ruleset.CharacterCreationException;


/**
 * An class for skills
 * @author Andi Popp
 *
 */
public class Skill extends AbstractSkill{

	/**
	 * The constant for the "Körpertalente" skill group
	 */
	public static final byte SKILL_GROUP_KOERPER = 0;
	
	/**
	 * The constant for the "Gesellschaftstalente" skill group
	 */
	public static final byte SKILL_GROUP_GESELLSCHAFT = 1;
	
	/**
	 * The constant for the "Naturtalente" skill group
	 */
	public static final byte SKILL_GROUP_NATUR = 2;
	
	/**
	 * The constant for the "Wissenstalente" skill group
	 */
	public static final byte SKILL_GROUP_WISSEN = 3;
	
	/**
	 * The constant for the "Handwerkstalente" skill group
	 */
	public static final byte SKILL_GROUP_HANDWERK = 4;

	/**
	 * The skill group this skill belongs to
	 */
	public final byte skillGroup;

	/**
	 * Full parameter constructor
	 * @param name
	 * @param attributes
	 * @param increaseCostFactor
	 * @param skillGroup
	 * @throws CharacterCreationException if the values of the attribute fields do not conform to attribute byte identifiers
	 */
	public Skill(String name, byte[] attributes, byte increaseCostFactor,
			byte skillGroup) throws IllegalArgumentException, CharacterCreationException {
		super(name, attributes, increaseCostFactor);
		
		//check skill Group
		switch (skillGroup) {
			case SKILL_GROUP_KOERPER: break;
			case SKILL_GROUP_HANDWERK: break;
			case SKILL_GROUP_GESELLSCHAFT: break;
			case SKILL_GROUP_NATUR: break;
			case SKILL_GROUP_WISSEN: break;
			default: throw new CharacterCreationException("Illegal increase cost factor byte: "+skillGroup);
		}
		this.skillGroup = skillGroup;
	}
	
	
	
	/**
	 * Creates a complete list of all skills
	 * @return a complete list of all skills, indexed with skill name
	 */
	public static Map<String, Skill> getSkillMap(){
		//create Map object
		Map<String, Skill> speciesMap = new TreeMap<String, Skill>();
		
		//add skills
		//Körpertalente
		speciesMap.put("Fliegen", new Skill("Fliegen", getAttributes("MU/IN/GE"), IncreaseCostTable.B, SKILL_GROUP_KOERPER));
		speciesMap.put("Gaukeleien", new Skill("Gaukeleien", getAttributes("MU/CH/FF"), IncreaseCostTable.A, SKILL_GROUP_KOERPER));
		speciesMap.put("Klettern", new Skill("Klettern", getAttributes("MU/GE/KK"), IncreaseCostTable.B, SKILL_GROUP_KOERPER));
		//Gesellschaftstalente
		speciesMap.put("Bekehren & Überzeugen", new Skill("Bekehren & Überzeugen", getAttributes("MU/KL/CH"), IncreaseCostTable.B, SKILL_GROUP_GESELLSCHAFT));
		speciesMap.put("Betören", new Skill("Betören", getAttributes("MU/CH/CH"), IncreaseCostTable.B, SKILL_GROUP_GESELLSCHAFT));
		//TODO complete skill set
		
		//return map
		return speciesMap;
	}
	

}
