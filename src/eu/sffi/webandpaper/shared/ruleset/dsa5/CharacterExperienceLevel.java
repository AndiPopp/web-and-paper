package eu.sffi.webandpaper.shared.ruleset.dsa5;


/**
 * A class representing an experience level (cf. DSA5 rule book p. 39)
 * @author Andi Popp
 *
 */
public class CharacterExperienceLevel {
	
	/**
	 * byte representing the skill level "Unerfahren"
	 */
	public static final byte UNERFAHREN = 0;
	
	/**
	 * byte representing the skill level "Durchschnittlich"
	 */
	public static final byte DURCHSCHNITTLICH = 1;
	
	/**
	 * byte representing the skill level "Erfahren"
	 */
	public static final byte ERFAHREN = 2;
	
	/**
	 * byte representing the skill level "Kompetent"
	 */
	public static final byte KOMPETENT = 3;
	
	/**
	 * byte representing the skill level "Meisterlich"
	 */
	public static final byte MEISTERLICH = 4;
	
	/**
	 * byte representing the skill level "Brilliant"
	 */
	public static final byte BRILLIANT = 5;
	
	/**
	 * byte representing the skill level "Legendär"
	 */
	public static final byte LEGENDAER = 6;
	
	public static final String[] levelNames = {
			"Unerfahren", "Durchschnittlich", "Erfahren", 
			"Kompetent", "Meisterlich", "Brilliant", "Legendär"};
	
	/**
	 * The name of the experience level according to the constants
	 */
	public final byte level;
	
	/**
	 * The exp count associated with the experience level
	 */
	public final int exp;
	
	/**
	 * maximum value for each attribute in this experience level
	 */
	public final byte maxAttribute;
	
	/**
	 * maximum sum of attributes in this experience level
	 */
	public final byte maxAttributeSum;
	
	/**
	 * maximum skill value at this experience level
	 */
	public final byte maxSkill;
	
	/**
	 * maximum combate skill value at this experience level
	 */
	public final byte maxCombatSkill;
	
	/**
	 * maximum spell count at this experience level 
	 */
	public final byte maxSpellCount; 
	
	/**
	 * maximum number of foreign spell at this experience level
	 */
	public final byte maxForeignSpellCount;

	//CONSTRUCTORS
	
	/**
	 * Full parameter constructor
	 * @param level
	 * @param exp
	 * @param maxAttribute
	 * @param maxAttributeSum
	 * @param maxSkill
	 * @param maxCombatSkill
	 * @param maxSpellCount
	 * @param maxForeignSpellCount
	 */
	public CharacterExperienceLevel(byte level, int exp, byte maxAttribute,
			byte maxAttributeSum, byte maxSkill, byte maxCombatSkill,
			byte maxSpellCount, byte maxForeignSpellCount) {
		super();
		this.level = level;
		this.exp = exp;
		this.maxAttribute = maxAttribute;
		this.maxAttributeSum = maxAttributeSum;
		this.maxSkill = maxSkill;
		this.maxCombatSkill = maxCombatSkill;
		this.maxSpellCount = maxSpellCount;
		this.maxForeignSpellCount = maxForeignSpellCount;
	}
	
	//GETTERS AND SETTERS
	
	
	
	//OTHER METHODS
	
	/**
	 * Translates the byte name of the experience level into a string
	 * @param level the byte name of the experience level
	 * @return the name of the experience level as string; empty string if byte name does not match any level.
	 */
	public static String nameAsString(byte level){
		if ((level < 0) || (level >= levelNames.length)) return "";
		else return levelNames[level];
	}

	public static byte stringAsLevel(String levelName) throws IllegalArgumentException{
		for(int i = 0; i < levelNames.length; i++){
			if (levelName.equals(levelNames[i])) return (byte) i;
		}
		throw new IllegalArgumentException("Der String " + levelName + " ist kein gültiger Erfahrungsgrad");
	}
	
	/**
	 * Creates the 7 experience levels in the rule book 
	 * @param level the byte name of the experience level
	 * @return the experience level object; null if the byte name is not valid
	 */
	public static CharacterExperienceLevel getLevel(byte level){
		if (level == UNERFAHREN) return new CharacterExperienceLevel(UNERFAHREN, 900, (byte) 12, (byte) 95, (byte) 10, (byte) 8, (byte) 8, (byte) 0);
		else if (level == DURCHSCHNITTLICH) return new CharacterExperienceLevel(DURCHSCHNITTLICH, 1000, (byte) 13, (byte) 98, (byte) 10, (byte) 10, (byte) 10, (byte) 1);
		else if (level == ERFAHREN) return new CharacterExperienceLevel(ERFAHREN, 1100, (byte) 14, (byte) 100, (byte) 10, (byte) 12, (byte) 12, (byte) 2);
		else if (level == KOMPETENT) return new CharacterExperienceLevel(KOMPETENT, 1200, (byte) 15, (byte) 102, (byte) 13, (byte) 14, (byte) 14, (byte) 3);
		else if (level == MEISTERLICH) return new CharacterExperienceLevel(MEISTERLICH, 1400, (byte) 16, (byte) 105, (byte) 16, (byte) 16, (byte) 16, (byte) 4);
		else if (level == BRILLIANT) return new CharacterExperienceLevel(BRILLIANT, 1700, (byte) 17, (byte) 109, (byte) 19, (byte) 18, (byte) 18, (byte) 5);
		else if (level == LEGENDAER) return new CharacterExperienceLevel(LEGENDAER, 2100, (byte) 18, (byte) 114, (byte) 20, (byte) 20, (byte) 20, (byte) 6);
		else return null;
	}
	
	/**
	 * Creates the experience level associated with a particular exp (experience points) level
	 * @param exp the number of experience points
	 * @return the character experience level associated with this number of exp; null if exp is less then 900
	 */
	public static CharacterExperienceLevel getLevelByExp(int exp){
		if (exp < 900) return null;
		else if (exp < 1000) return getLevel(UNERFAHREN);
		else if (exp < 1100) return getLevel(DURCHSCHNITTLICH);
		else if (exp < 1200) return getLevel(ERFAHREN);
		else if (exp < 1400) return getLevel(KOMPETENT);
		else if (exp < 1700) return getLevel(MEISTERLICH);
		else if (exp < 2100) return getLevel(BRILLIANT);
		else return getLevel(LEGENDAER);
	}
	
	
}
