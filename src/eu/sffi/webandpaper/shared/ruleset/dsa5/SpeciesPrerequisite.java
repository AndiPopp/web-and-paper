/**
 * 
 */
package eu.sffi.webandpaper.shared.ruleset.dsa5;

/**
 * A prerequisite to check if a characters species matches one of 
 * a set of given species
 * @author Andi Popp
 *
 */
public class SpeciesPrerequisite implements Prerequisite {

	/**
	 * The set of species to be checked
	 */
	private final CharacterSpecies[] requiredSpecies;
	
	/**
	 * Full parameter constructor
	 * @param species
	 */
	public SpeciesPrerequisite(CharacterSpecies... species) {
		super();
		this.requiredSpecies = species;
	}

	/* (non-Javadoc)
	 * @see eu.sffi.webandpaper.shared.ruleset.dsa5.Prerequisite#check(eu.sffi.webandpaper.shared.ruleset.dsa5.Character)
	 */
	@Override
	public boolean check(Character character) {
		for (CharacterSpecies charSpecies : requiredSpecies){
			if (charSpecies.equals(character.getSpecies())) return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see eu.sffi.webandpaper.shared.ruleset.dsa5.Prerequisite#explain(eu.sffi.webandpaper.shared.ruleset.dsa5.Character)
	 */
	@Override
	public String explain(Character character) {
		if (check(character)) return character.getName()+" ist "+character.getSpecies().toString();
		else {
			String explenation = character.getName()+" ist nicht";
			char seperator = ' ';
			for (CharacterSpecies charSpecies : requiredSpecies){
				explenation += seperator + charSpecies.toString();
				seperator = '/';
			}
			return explenation;
		}
	}

}
