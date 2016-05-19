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
			default: throw new CharacterCreationException("Illegal skill group byte: "+skillGroup);
		}
		this.skillGroup = skillGroup;
	}
	
	/**
	 * Calculates to total costs to raise this skill to a certain level
	 * @param level the level to which the skill is raised
	 * @return the total leveling costs
	 */
	public int getTotalLevelingCosts(byte level){
		return IncreaseCostTable.getCost(0, level, this.increaseCostFactor);
	}
	
	/**
	 * Creates a complete list of all skills
	 * @return a complete list of all skills, indexed with skill name
	 */
	public static Map<String, Skill> getSkillMap(){
		//create Map object
		Map<String, Skill> skillMap = new TreeMap<String, Skill>();
		
		//add skills
		//Körpertalente
		skillMap.put("Fliegen", new Skill("Fliegen", getAttributes("MU/IN/GE"), IncreaseCostTable.B, SKILL_GROUP_KOERPER));
		skillMap.put("Gaukeleien", new Skill("Gaukeleien", getAttributes("MU/CH/FF"), IncreaseCostTable.A, SKILL_GROUP_KOERPER));
		skillMap.put("Klettern", new Skill("Klettern", getAttributes("MU/GE/KK"), IncreaseCostTable.B, SKILL_GROUP_KOERPER));
		skillMap.put("Körperbeherrschung", new Skill("Körperbeherrschung", getAttributes("GE/GE/KO"), IncreaseCostTable.D, SKILL_GROUP_KOERPER));
		//TODO complete skill set
		
		//Gesellschaftstalente
		skillMap.put("Bekehren & Überzeugen", new Skill("Bekehren & Überzeugen", getAttributes("MU/KL/CH"), IncreaseCostTable.B, SKILL_GROUP_GESELLSCHAFT));
		skillMap.put("Betören", new Skill("Betören", getAttributes("MU/CH/CH"), IncreaseCostTable.B, SKILL_GROUP_GESELLSCHAFT));
		skillMap.put("Einschüchtern", new Skill("Einschüchtern", getAttributes("MU/IN/CH"), IncreaseCostTable.B, SKILL_GROUP_GESELLSCHAFT));
		skillMap.put("Etikette", new Skill("Etikette", getAttributes("KL/IN/CH"), IncreaseCostTable.B, SKILL_GROUP_GESELLSCHAFT));
		//TODO complete skill set
		
		//Naturtalente
		skillMap.put("Fährtensuchen", new Skill("Fährtensuchen", getAttributes("MU/IN/GE"), IncreaseCostTable.C, SKILL_GROUP_NATUR));
		skillMap.put("Fesseln", new Skill("Fesseln", getAttributes("KL/FF/KK"), IncreaseCostTable.A, SKILL_GROUP_NATUR));
		skillMap.put("Fischen & Angeln", new Skill("Fischen & Angeln", getAttributes("FF/GE/KO"), IncreaseCostTable.A, SKILL_GROUP_NATUR));
		skillMap.put("Orientierung", new Skill("Orientierung", getAttributes("KL/IN/IN"), IncreaseCostTable.B, SKILL_GROUP_NATUR));
		//TODO complete skill set
		
		//Wissenstalente
		skillMap.put("Brett- & Glücksspiel", new Skill("Brett- & Glücksspiel", getAttributes("KL/KL/IN"), IncreaseCostTable.A, SKILL_GROUP_WISSEN));
		skillMap.put("Geographie", new Skill("Geographie", getAttributes("KL/KL/IN"), IncreaseCostTable.B, SKILL_GROUP_WISSEN));
		skillMap.put("Geschichtswissen", new Skill("Geschichtswissen", getAttributes("KL/KL/IN"), IncreaseCostTable.B, SKILL_GROUP_WISSEN));
		skillMap.put("Götter & Kulter", new Skill("Götter & Kulter", getAttributes("KL/KL/IN"), IncreaseCostTable.B, SKILL_GROUP_WISSEN));
		//TODO complete skill set
		
		//Handwerkstalenten
		skillMap.put("Alchemie", new Skill("Alchemie", getAttributes("MU/KL/FF"), IncreaseCostTable.C, SKILL_GROUP_HANDWERK));
		skillMap.put("Boote & Schiffe", new Skill("Boote & Schiffe", getAttributes("FF/GE/KK"), IncreaseCostTable.B, SKILL_GROUP_HANDWERK));
		skillMap.put("Fahrzeuge", new Skill("Fahrzeuge", getAttributes("CH/FF/KO"), IncreaseCostTable.A, SKILL_GROUP_HANDWERK));
		skillMap.put("Handel", new Skill("Handel", getAttributes("KL/IN/CH"), IncreaseCostTable.B, SKILL_GROUP_HANDWERK));
		//TODO complete skill set
		
		//return map
		return skillMap;
	}
	

}
