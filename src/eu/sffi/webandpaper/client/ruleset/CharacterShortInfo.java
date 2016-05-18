/**
 * 
 */
package eu.sffi.webandpaper.client.ruleset;

import java.io.Serializable;

import eu.sffi.webandpaper.shared.ruleset.AbstractCharacter;

/**
 * A class containing only basic character information; mainly for listing purposes.
 * @author Andi Popp
 *
 */
public class CharacterShortInfo implements Serializable{
	
	/**
	 * The characters id
	 */
	private Long id;
	
	/**
	 * The characters name
	 */
	private String name;
	
	/**
	 * The characters ruleset as String
	 */
	private String rulesetName;
	
	/**
	 * default (empty) constructor
	 */
	public CharacterShortInfo(){}
	
	/**
	 * Full parameter constructor
	 * @param id
	 * @param name
	 * @param rulesetName
	 */
	public CharacterShortInfo(Long id, String name, String rulesetName) {
		this();
		setId(id);
		setName(name);
		setRulesetName(rulesetName);
	}
	
	/**
	 * Constructs a character short info and fille it with infos extracted from an {@link AbstractCharacter} object
	 * @param character the characters this short info is based on
	 */
	public CharacterShortInfo(AbstractCharacter character){
		this(character.getId(), character.getName(),character.rulesetName());
	}

	//Getters and setters

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRulesetName() {
		return rulesetName;
	}

	public void setRulesetName(String rulesetName) {
		this.rulesetName = rulesetName;
	}
	
	
	
}
