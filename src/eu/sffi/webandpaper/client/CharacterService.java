package eu.sffi.webandpaper.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import eu.sffi.webandpaper.client.ruleset.CharacterShortInfo;
import eu.sffi.webandpaper.shared.ruleset.AbstractCharacter;
import eu.sffi.webandpaper.shared.ruleset.CharacterCreationException;


/**
 * The service to save and load characters
 * @author Andi Popp
 *
 */
@RemoteServiceRelativePath("character")
public interface CharacterService extends RemoteService {

	/**
	 * Saves a character to the character database
	 * @param character the character to be saved
	 * @return the database id of the character
	 * @throws NotLoggedInException if the user is not logged in
	 * @throws CharacterServiceException if the user has exceeded their character limit 
	 */
	public CharacterServiceResult saveCharacter(AbstractCharacter character) throws NotLoggedInException, CharacterServiceException, CharacterCreationException;
	
	/**
	 * Gets a specific character by id, if the user requesting the character is the owner
	 * @param charId the character id
	 * @return the character with the given id 
	 * @throws NotLoggedInException if the user is not logged in
	 * @throws CharacterServiceException if the character could not be delivered, i.e. the character does not exist or the user does not have the right to see the character
	 */
	public AbstractCharacter getCharacter(Long charId, String rulesetName) throws NotLoggedInException, CharacterServiceException;
	
	/**
	 * Composes a short list of all the character's a user owns
	 * @return a short list of all the character's a user owns
	 * @throws NotLoggedInException if the user is not logged in
	 */
	public CharacterShortInfo[] listCharacters() throws NotLoggedInException;
	
	/**
	 * Deletes a character from the database
	 * @param charId the characters id
	 * @return a confirmation or nonconfirmation message
	 * @throws NotLoggedInException if the user is not logged in
	 * @throws CharacterServiceException if the user is not the chars owner or the char is in a group
	 */
	public CharacterServiceResult deleteCharacter(Long charId, String rulesetName) throws NotLoggedInException, CharacterServiceException;
}
