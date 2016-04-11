package eu.sffi.webandpaper.shared.ruleset;

import java.io.Serializable;

/**
 * This exception is thrown when something went wrong during character creation.
 * @author Andi Popp
 *
 */
public class CharacterCreationException extends RuntimeException implements Serializable {

	public CharacterCreationException() {
		super();
	}

	public CharacterCreationException(String message) {
		super(message);
	}
	
}
