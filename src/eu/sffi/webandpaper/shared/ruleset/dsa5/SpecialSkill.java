/**
 * 
 */
package eu.sffi.webandpaper.shared.ruleset.dsa5;

/**
 * A class representing a special skill (de: "Sonderfertigkeit")
 * @author Andi Popp
 *
 */
public class SpecialSkill {

	/**
	 * The tradition's name
	 */
	public final String name;
	
	/**
	 * The AP cost of this magic tradition
	 */
	public final int apCost;
	
	/**
	 * Prerequisites of this special skill
	 */
	public final Prerequisite[] prerequisites;
	
	/**
	 * Full parameter constructor
	 * @param name
	 * @param apCost
	 * @param prerequisites
	 */
	public SpecialSkill(String name, int apCost, Prerequisite[] prerequisites) {
		super();
		this.name = name;
		this.apCost = apCost;
		this.prerequisites = prerequisites;
	}



	@Override
	public String toString(){
		return this.name;
	}

}
