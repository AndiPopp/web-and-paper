package eu.sffi.webandpaper.shared.ruleset.dsa5;

import java.util.Map;
import java.util.TreeMap;

/**
 * Class representing a characters culture
 * @author Andi Popp
 *
 */
public class CharacterCulture {

	/**
	 * This culutures name
	 */
	public final String name;
	
	/**
	 * The species associated with this culture
	 */
	public final CharacterSpecies[] species;

	/**
	 * Full parameter constructor
	 * @param name
	 * @param species
	 */
	private CharacterCulture(String name, CharacterSpecies... species) {
		super();
		this.name = name;
		this.species = species;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
	/**
	 * Creates a complete list of cultures using the species list of the given ruleset
	 * @param ruleset a DSA5 ruleset
	 * @return a complete list of cultures using the species list of the given ruleset
	 */
	public static CharacterCultureList getCultures(Ruleset ruleset){
		//Create map
		CharacterCultureList cultures = new CharacterCultureList();
		//Create short link to species map
		Map<String, CharacterSpecies> species = ruleset.species;
		//add cultures
			//Human cultures
			cultures.add(new CharacterCulture("Andergaster", species.get("Mensch"), species.get("Halbelf")));
			cultures.add(new CharacterCulture("Aranier", species.get("Mensch")));
			cultures.add(new CharacterCulture("Bornländer", species.get("Mensch")));
			//Elven cultures
			cultures.add(new CharacterCulture("Auelfen", species.get("Elf"), species.get("Halbelf")));
			cultures.add(new CharacterCulture("Firnelfen", species.get("Elf"), species.get("Halbelf")));
			cultures.add(new CharacterCulture("Waldelfen", species.get("Elf"), species.get("Halbelf")));
			//Dwarven cultures
			cultures.add(new CharacterCulture("Ambosszwerg", species.get("Zwerg")));
			cultures.add(new CharacterCulture("Brillantzwerge", species.get("Zwerg")));
			cultures.add(new CharacterCulture("Erzzwerge", species.get("Zwerg")));
			cultures.add(new CharacterCulture("Hügelzwerge", species.get("Zwerg")));
		//return map
		return cultures;
	}
	
}
