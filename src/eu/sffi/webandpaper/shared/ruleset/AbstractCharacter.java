/**
 * 
 */
package eu.sffi.webandpaper.shared.ruleset;

import java.io.Serializable;

import javax.jdo.annotations.*;

import com.google.appengine.api.users.User;

/**
 * An abstract class to represent characters in different rule sets.
 * @author Andi Popp
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
@Inheritance(strategy=InheritanceStrategy.SUBCLASS_TABLE)
public abstract class AbstractCharacter implements Serializable {

	/**
	 * The Character's id
	 */
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY, defaultFetchGroup="true")
	private Long id;
	
	/**
	 * The Characters name
	 */
	@Persistent
	private String name;
	
	/**
	 * The owner's google user id
	 */
	@Persistent
	private String ownerId;
	
	//getters and setters
	
	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwner(String ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public String toString(){
		return this.name;
	}
	
	/**
	 * Checks if the character is indeed a valid DSA5 Character
	 * @return true if the character is a valid DSA5 Character
	 * @throws CharacterCreationException when an exception was encountered while creating the character
	 */
	public abstract boolean verify() throws CharacterCreationException;
	
	/**
	 * Returns a string identifying the ruleset
	 * @return a string identifying the ruleset
	 */
	public abstract String rulesetName();

	/**
	 * Returns the rule set object associated with this character
	 * @return the rule set object associated with this character
	 */
	public abstract AbstractRuleset getRuleset();
}