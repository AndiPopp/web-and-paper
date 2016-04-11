package eu.sffi.webandpaper.shared.ruleset;

/**
 * An abstract class representing a ruleset and its static rule elements
 * @author Andi Popp
 *
 */
public class AbstractRuleset {
	
	/**
	 * This ruleset's name
	 */
	String name;

	/**
	 * Construct a new ruleset with a given name
	 * @param name
	 */
	public AbstractRuleset(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Returns an array containing all supported rule sets
	 * @return an array containing all supported rule sets
	 */
	public static AbstractRuleset[] getRulesets(){
		AbstractRuleset[] rulesets = new AbstractRuleset[1];
		rulesets[0] = eu.sffi.webandpaper.shared.ruleset.dsa5.Ruleset.ruleset;
		return rulesets;
	}
	
	@Override
	public String toString(){
		return getName();
	}

	/**
	 * Getter for {@link AbstractRuleset#name}
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	
}