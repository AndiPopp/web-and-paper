package eu.sffi.webandpaper.shared.ruleset.dsa5;

import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import eu.sffi.webandpaper.shared.ruleset.AbstractCharacter;
import eu.sffi.webandpaper.shared.ruleset.AbstractRuleset;
import eu.sffi.webandpaper.shared.ruleset.CharacterCreationException;

/**
 * This class represents a "raw" starting character which has been created in accordance to the rules.
 * @author Andi Popp
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Character extends AbstractCharacter {
	
	/**
	 * The starting experience level
	 */
	@Persistent
	private byte startingExperienceLevel;
	
	/**
	 * The character's species as String
	 */
	@Persistent 
	private String species;
	
	/**
	 * The characters' culture
	 */
	@Persistent
	private String culture;
	
	/**
	 * The character's attributes.
	 */
	@Persistent
	public byte[] attributes = new byte[8];
	
	/**
	 * The character's skill values
	 */
	@Persistent(mappedBy = "owner", defaultFetchGroup = "true")
	private List<SkillValue> skillValues;
	
	//Constructors
	
	/**
	 * empty constructor
	 */
	public Character(){};
	
	/**
	 * Creates a new character with a given starting experience level, species and culture
	 * @param startingExperienceLevel
	 * @param species
	 * @param culture
	 */
	public Character(byte startingExperienceLevel, String species,
			String culture) {
		super();
		this.startingExperienceLevel = startingExperienceLevel;
		this.species = species;
		this.culture = culture;
		this.attributes = new byte[8];
	}	
	
	//Setters, Getters and similar methods
	
	/**
	 * Returns the character's starting experience level as {@link CharacterExperienceLevel} object
	 * @return the character's starting experience level as {@link CharacterExperienceLevel} object
	 */
	public CharacterExperienceLevel getStartingExperienceLevel(){
		return CharacterExperienceLevel.getLevel(startingExperienceLevel);
	}
	
	/**
	 * Returns the character's species as {@link CharacterSpecies} object
	 * @return the character's species as {@link CharacterSpecies} object
	 */
	public CharacterSpecies getSpecies(){
		return Ruleset.ruleset.species.get(this.species);
	}
	
	/**
	 * Returns the character's culture as {@link CharacterCulture} object
	 * @return the character's culture as {@link CharacterCulture} object
	 */
	public CharacterCulture getCulture(){
		return Ruleset.ruleset.cultures.get(this.culture);
	}

	/**
	 * Takes the values of an attribute array and copies them to the character's attribut array
	 * @param attributes the new values for the attribute array
	 * @throws ArrayIndexOutOfBoundsException if the size of the attribute array does not match the size of the character's attribute array
	 */
	public void setAttributes(byte[] attributes) throws CharacterCreationException{
		if (this.attributes.length != attributes.length) throw new CharacterCreationException("Attributes array to be assigned does not have the same length as the characters attribute array.");
		for (int i = 0; i < attributes.length; i++){
			this.attributes[i] = attributes[i];
		}
	}

	public List<SkillValue> getSkillValues() {
		return skillValues;
	}

	public void setSkillValues(List<SkillValue> skillValues) {
		this.skillValues = skillValues;
		for (SkillValue skillValue : this.skillValues){
			skillValue.setOwner(this);
		}
	}
	
	//other methods
	
	/**
	 * Calculates this character's left AP.
	 * @return this character's left AP
	 */
	public int getLeftAP() throws CharacterCreationException {
		//Start by initialising with starting experience level
		CharacterExperienceLevel expLevel = this.getStartingExperienceLevel();
		if (expLevel == null) throw new CharacterCreationException("Unzulässiger Byte-Wert für Erfahrungsgrad: " + this.startingExperienceLevel);
		int ap = expLevel.exp;
		
		//subtract species cost
		CharacterSpecies species = this.getSpecies();
		if (species == null) throw new CharacterCreationException("Unzulässige Spezies: " + this.species);
		ap -= species.apCost;
		
		//subtract the attribute cost
		ap -= getAttributeCosts();
		
		//return calculated ap
		return ap;
	}
	
	/**
	 * Returns the sum of the attribute values
	 * @return the sum of the attribute values
	 */
	public short getAttributeSum(){
		short attrSum = 0;
		for (byte attr : attributes){
			attrSum += attr;
		}
		return attrSum;
	}
	
	/**
	 * Checks if the attribute sum does not exceed the maximum of the experience level
	 * @return true if the character's attribute sum is within the limits of the experience level, false otherwise
	 */
	public boolean checkAttributeSum(){
		if (getAttributeSum() > this.getStartingExperienceLevel().maxAttributeSum) return false;
		else return true;
	}
	
	/**
	 * Checks if the values of the character's attributes are within the legal range
	 * @return true if all of the values of the character's attributes are within the legal range
	 * @throws CharacterCreationException when at least one value is not within the legal range
	 */
	public boolean checkAttributeRange() throws CharacterCreationException{
		String result = "Attribute range issues: ";
		for (byte i = 0; i < this.attributes.length; i++){
			byte maxVal = this.getStartingExperienceLevel().maxAttribute; //TODO add species modifications
			if (this.attributes[i] < 8)  result += CharacterAttributes.attributeName(i)+" "+this.attributes[i]+" < 8; ";
			else if (this.attributes[i] > maxVal) 
				result += CharacterAttributes.attributeName(i)+" "+this.attributes[i]+" > "+maxVal+"; ";
		}
		//TODO add species modification
		if (result.equals("Attribute range issues: ")) return true;
		else throw new CharacterCreationException(result);
	}
	
	/**
	 * Returns the cost to increase the character's attributes to their current value
	 * @return the cost to increase the character's attributes to their current value
	 */
	public int getAttributeCosts(){
		int costs = 0;
		for (byte attr : attributes){
			costs += IncreaseCostTable.getCost(8, attr, IncreaseCostTable.E);
		}
		return costs;
	}

	@Override
	public boolean verify() throws CharacterCreationException{
		//Check experience level
		CharacterExperienceLevel startExpLevel = this.getStartingExperienceLevel();
		if (startExpLevel == null) throw new CharacterCreationException("The byte value "+this.startingExperienceLevel+" is not a valid character experience level identifier.");
		//Check species
		CharacterSpecies characterSpecies = this.getSpecies();
		if (characterSpecies == null) throw new CharacterCreationException("Unknown species: "+this.species);
		//Check culture
		CharacterCulture characterCulture = this.getCulture();
		if (characterCulture == null) throw new CharacterCreationException("Unknown culture: "+this.culture);
		//Check attribute value ranges
		checkAttributeRange();
		//Check attribute sum
		if (!checkAttributeSum()) throw new CharacterCreationException("The attribute sum "+this.getAttributeSum()+" exceeds the limit of "+this.getStartingExperienceLevel().maxAttributeSum);
		
		//default name if necessary
		if (this.getName() == null) this.setName("Der Namenlose "+(""+System.currentTimeMillis()).substring(9));
		//TODO uncomment in productive environment
		//check name length
		if (this.getName().length() < 4) throw new CharacterCreationException("Character name must be at least 4 symbols.");
			
		//everything went smooth
		return true;
	}

	@Override
	public String rulesetName() {
		return getRuleset().toString();
	}
	
	@Override
	public AbstractRuleset getRuleset(){
		return Ruleset.ruleset;
	}
	

}