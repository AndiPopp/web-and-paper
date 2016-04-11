package eu.sffi.webandpaper.shared.ruleset.dsa5;

import java.util.Map;

import eu.sffi.webandpaper.shared.ruleset.AbstractRuleset;

/**
 * The DSA5 rule set
 * @author Andi Popp
 *
 */
public class Ruleset extends AbstractRuleset {
	
	/**
	 * A static rule set object
	 */
	public static final Ruleset ruleset = new Ruleset();
	
	/**
	 * The list of species
	 */
	public Map<String, CharacterSpecies> species;
	
	/**
	 * The list of cultures
	 */
	public CharacterCultureList cultures;
	
	/**
	 * Constructs a DSA5 rule set
	 */
	private Ruleset() {
		super("DSA 5");
		this.species = CharacterSpecies.getSpeciesMap();
		this.cultures = CharacterCulture.getCultures(this);
	}
	
}
