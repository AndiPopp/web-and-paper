package eu.sffi.webandpaper.client;

import java.io.Serializable;

public class CharacterServiceException extends Exception implements
		Serializable {

	public CharacterServiceException() {
	}

	public CharacterServiceException(String message) {
		super(message);
	}

	public CharacterServiceException(Throwable cause) {
		super(cause);
	}

}
